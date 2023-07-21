package it.icarcnr.rainbow.client.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.InfoWindow;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker.ClickHandler;
import com.google.maps.gwt.client.Marker.ClickableChangedHandler;
import com.google.maps.gwt.client.MarkerOptions;
import com.google.maps.gwt.client.MouseEvent;
import com.google.maps.gwt.client.Point;
import com.google.maps.gwt.client.GoogleMap.ZoomChangedHandler;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.ConnectionConfig;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.MessageBox;

public class MapsService extends BasePanel {

	final SimplePanel widg = new SimplePanel();

	private static final int TIMEOUT = 30000 * 6;/* three minutes */

	private GoogleMap map;
	private static final int TILE_SIZE = 256;
	private static final LatLng COSENZA = LatLng.create(39.297775, 16.254867);
	ArrayList<MapsBean> datiSensori = null;
	private Store store;
	private HttpProxy httpProxy;
	HashMap<String, List<MapsBean>> mappaNodoSensori = new HashMap<String, List<MapsBean>>();


	private LatLng sensorePos=null ;


	@Override
	public void init() {
		store = getStore();
		drawMaps();
		MapOptions mapOptions = MapOptions.create();
		mapOptions.setZoom(15.0);
		mapOptions.setCenter(COSENZA);
		mapOptions.setMapTypeId(MapTypeId.ROADMAP);
		widg.setSize("100%", "100%");
		map = GoogleMap.create(widg.getElement(), mapOptions);
		add(widg);

	}

	private Store getStore() {
		ConnectionConfig connectionConfig = new ConnectionConfig();
		connectionConfig.setTimeout(TIMEOUT);
		connectionConfig.setUrl(IActionPathConstants.SERVICE_MAPS_ACTION_PATH);
		httpProxy = new HttpProxy(new Connection(connectionConfig));
		RecordDef dataRecordDef = getRecorDef();
		JsonReader readerLoad = new JsonReader(dataRecordDef);
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		final Store st = new Store(httpProxy, readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
			}

			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				// getEl().mask(iChart.ServiceChart_Drawing_chart(),
				// "x-mask-loading");
			
		
				if (records != null && records.length > 0) {
					try {
						datiSensori = new ArrayList<MapsBean>();
						for (Record record : records) {
							if (!mappaNodoSensori.containsKey(record
									.getAsString("idNodeSensore"))) {
								mappaNodoSensori.put(
										record.getAsString("idNodeSensore"),
										new ArrayList<MapsBean>());
							}

							MapsBean mapsBean = new MapsBean();
							mapsBean.setDateSampling(record
									.getAsString("dataTime"));
							mapsBean.setIdNodeSensore(record
									.getAsString("idNodeSensore"));
							mapsBean.setIdSensore(record
									.getAsString("idSensore"));
							mapsBean.setValue(record.getAsString("value"));
							mapsBean.setNameDescription(record
									.getAsString("value"));
							mapsBean.setNameDescription(record
									.getAsString("nameSensor"));
							mapsBean.setLivello(record.getAsString("livello"));
							mapsBean.setLat(record.getAsString("lat"));
							mapsBean.setLng(record.getAsString("lng"));
							mapsBean.setColorLevel(record.getAsString("colorLevel"));
										
							mappaNodoSensori.get(record.getAsString("idNodeSensore")).add(mapsBean);
						}
						for (Map.Entry<String, List<MapsBean>> entry : mappaNodoSensori.entrySet()) {
							List<MapsBean> listSen = entry.getValue();
							for (final MapsBean mapsBean2 : listSen) {
								final LatLng sensorePos = LatLng.create(
										Double.parseDouble(mapsBean2.getLat()),
										Double.parseDouble(mapsBean2.getLng()));
								MarkerOptions markerOptions = MarkerOptions.create();
								markerOptions.setClickable(true);
								markerOptions.setMap(map);
								markerOptions.setPosition(sensorePos);
								final Marker marker = Marker.create(markerOptions);
								marker.addClickListener(new ClickHandler() {
									@Override
									public void handle(MouseEvent event) {
										LatLng sensorePosM=null;
											StringBuilder strInfo=new StringBuilder();
											strInfo.append("<table style=\"font-size:10px\"> ");
												for (Map.Entry<String, List<MapsBean>> entry : mappaNodoSensori.entrySet()) {
													sensorePosM=	marker.getPosition();
													LatLng sensorePosMV=LatLng.create(Double.parseDouble(entry.getValue().get(0).getLat()),	Double.parseDouble(entry.getValue().get(0).getLng()));
													if(sensorePosM.equals(sensorePosMV)){
													InfoWindow coordInfoWindow = InfoWindow.create();
														for (final MapsBean mapsBean2 : entry.getValue()) {
																					
														if(mapsBean2.getNameDescription().equals("Rumore")){
															strInfo.append("<tr><td>Tipo di Sensore</td><td>");
															strInfo.append(mapsBean2.getNameDescription()+"</td></tr>");
															strInfo.append("<tr><td>Lat Lng"+"</td><td>");
															strInfo.append(mapsBean2.getLat()+","+mapsBean2.getLng()+"</td></tr>");
															strInfo.append("<tr><td bgcolor=\""+mapsBean2.getColorLevel()+"\">Valore Sensore"+"</td>");
															strInfo.append("<td bgcolor=\" "+mapsBean2.getColorLevel()+"   \">"+mapsBean2.getValue()+"</td></tr>");
															
														  }else if(mapsBean2.getNameDescription().equals("Temperatura")){
																strInfo.append("<tr><td>Tipo di Sensore</td><td>");
																strInfo.append(mapsBean2.getNameDescription()+"</td></tr><tr><td>");
																strInfo.append("Lat Lng"+"</td><td>");
																strInfo.append(mapsBean2.getLat()+","+mapsBean2.getLng()+"</td></tr>");
														
																strInfo.append("<tr><td bgcolor=\""+mapsBean2.getColorLevel()+"\">Valore Sensore"+"</td>");
																strInfo.append("<td bgcolor=\" "+mapsBean2.getColorLevel()+"   \">"+mapsBean2.getValue()+"</td></tr>");
																
																strInfo.append("<tr><td>Data Campione"+"</td><td>"+mapsBean2.getDateSampling()+"</td></tr>");
														  }else if(mapsBean2.getNameDescription().equals("Umidità relativa")){
																strInfo.append("<tr><td>Tipo di Sensore</td><td>");
																strInfo.append(mapsBean2.getNameDescription()+"</td></tr><tr><td>");
																strInfo.append("Lat Lng"+"</td><td>");
																strInfo.append(mapsBean2.getLat()+","+mapsBean2.getLng()+"</td></tr>");
																strInfo.append("<tr><td bgcolor=\""+mapsBean2.getColorLevel()+"\">Valore Sensore"+"</td>");
																strInfo.append("<td bgcolor=\" "+mapsBean2.getColorLevel()+"   \">"+mapsBean2.getValue()+"</td></tr>");
															  
														  }else if(mapsBean2.getNameDescription().equals("Luminosità")){
																strInfo.append("<tr><td>Tipo di Sensore</td><td>");
																strInfo.append(mapsBean2.getNameDescription()+"</td></tr>");
																strInfo.append("<tr><td>Lat Lng"+"</td><td>");
																strInfo.append(mapsBean2.getLat()+","+mapsBean2.getLng()+"</td></tr>");
																strInfo.append("<tr><td bgcolor=\""+mapsBean2.getColorLevel()+"\">Valore Sensore"+"</td>");
																strInfo.append("<td bgcolor=\" "+mapsBean2.getColorLevel()+"   \">"+mapsBean2.getValue()+"</td></tr>");
															  
														  }else if(mapsBean2.getNameDescription().equals("Batteria")){
																strInfo.append("<tr><td>Tipo di Sensore</td><td>");
																strInfo.append(mapsBean2.getNameDescription()+"</td></tr>");
																strInfo.append("<tr><td>Lat Lng"+"</td><td>");
																strInfo.append(mapsBean2.getLat()+","+mapsBean2.getLng()+"</td></tr>");
																strInfo.append("<tr><td bgcolor=\""+mapsBean2.getColorLevel()+"\">Valore Sensore"+"</td>");
																strInfo.append("<td bgcolor=\" "+mapsBean2.getColorLevel()+"   \">"+mapsBean2.getValue()+"</td></tr>");
															  
														  }
													        
																	  
														}
														strInfo.append("</table>");
											        	setSensorePos(sensorePosM);
														coordInfoWindow.setContent(createInfoWindowContent(strInfo,sensorePosM));
														coordInfoWindow.setPosition(sensorePosM);
														coordInfoWindow.open(map);
														
													}
									
										
												
										        }		
										
									}
								});
							
									

							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					MessageBox.alert("Info", "Nessun Sensore da graficare");

				}

				ScheduledCommand command = new ScheduledCommand() {
					@Override
					public void execute() {

					}
				};
				Scheduler.get().scheduleDeferred(command);

			}

		});

		return st;
	}

	

	private RecordDef getRecorDef() {
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("idSensore"),
				new StringFieldDef("idNodeSensore"),
				new StringFieldDef("descrizioneNode"),
				new StringFieldDef("dataTime"), new StringFieldDef("value"),
				new StringFieldDef("nameSensor"),
				new StringFieldDef("livello"), new StringFieldDef("lat"),
				new StringFieldDef("lng"),
				new StringFieldDef("colorLevel"),});
		return recordDef;
	}

	


	
	public static class MercatorProjection {
		private static final Point PIXEL_ORIGIN = Point.create(TILE_SIZE / 2,
				TILE_SIZE / 2);
		private static final double PIXELS_PER_LON_DEGREE = MapsService.TILE_SIZE / 360;
		private static final double PIXELS_PER_LON_RADIAN = MapsService.TILE_SIZE/ (2 * Math.PI);

		public MercatorProjection() {
		}

		public static final double degreesToRadians(double deg) {
			return deg * (Math.PI / 180);
		}

		public static final double radiansToDegrees(double rad) {
			return rad / (Math.PI / 180);
		}

		public static final double bound(double value, double min, double max) {
			value = Math.max(value, min);
			value = Math.min(value, max);
			return value;
		}

		public Point fromLatLngToPoint(LatLng loc) {
			Point point = Point.create(0, 0);
			Point origin = PIXEL_ORIGIN;

			point.setX(origin.getX() + loc.lng() * PIXELS_PER_LON_DEGREE);

			double siny = bound(Math.sin(degreesToRadians(loc.lat())), -0.9999,
					0.9999);
			point.setY(origin.getY() + 0.5
					* (Math.log((1 + siny) / (1 - siny)))
					- PIXELS_PER_LON_RADIAN);
			return point;
		}

		public LatLng fromPointToLatLng(Point point) {
			double lng = (point.getX() - PIXEL_ORIGIN.getX())
					/ PIXELS_PER_LON_DEGREE;
			double latRadians = (point.getY() - PIXEL_ORIGIN.getY())
					/ PIXELS_PER_LON_RADIAN;
			double lat = radiansToDegrees(2 + Math.atan(Math.exp(latRadians))
					- Math.PI / 2);
			return LatLng.create(lat, lng);
		}
	}
	public HashMap<String, List<MapsBean>> getMappaNodoSensori() {
		return mappaNodoSensori;
	}
	public void setMappaNodoSensori(HashMap<String, List<MapsBean>> mappaNodoSensori) {
		this.mappaNodoSensori = mappaNodoSensori;
	}
	public GoogleMap getMap() {
		return map;
	}
	public void setMappa(GoogleMap map) {
		this.map = map;
	}
	public LatLng getSensorePos() {
		return sensorePos;
	}
	public void setSensorePos(LatLng sensorePos) {
		this.sensorePos = sensorePos;
	}
	
	private String createInfoWindowContent(StringBuilder table,LatLng sensorePos) {
		
		int numTiles = 1 << (int) map.getZoom();
//		MercatorProjection projection = new MercatorProjection();
//		Point worldCoordinate = projection.fromLatLngToPoint(sensorePos);
//		Point pixelCoordinate = Point.create(worldCoordinate.getX() * numTiles,
//				worldCoordinate.getY() * numTiles);
//		Point tileCoordinate = Point.create(Math.floor(pixelCoordinate.getX() / TILE_SIZE),Math.floor(pixelCoordinate.getY() / TILE_SIZE));
		SafeHtmlBuilder returnString = new SafeHtmlBuilder();
			returnString.appendHtmlConstant(table.toString());
	   		return returnString.toSafeHtml().asString();

	} 

	public void drawMaps() {
		UrlParam[] params = getUrlParams();
		store.load(params);

	}
	


}
