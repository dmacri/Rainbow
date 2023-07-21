package it.icarcnr.rainbow.client.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.icarcnr.rainbow.client.charts.ChartsContainer;
import it.icarcnr.rainbow.client.main.RainbowMain;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.HeaderBuilder;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker.ClickHandler;
import com.google.maps.gwt.client.MarkerImage;
import com.google.maps.gwt.client.MarkerOptions;
import com.google.maps.gwt.client.MouseEvent;
import com.google.maps.gwt.client.Point;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.ConnectionConfig;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.TabPanel;
import com.lowagie.text.pdf.ArabicLigaturizer;

public class MapsServiceNew extends BasePanel {

	final SimplePanel widg = new SimplePanel();
	private static final int TIMEOUT = 30000 * 6;/* three minutes */
	private GoogleMap map;
	private static final LatLng COSENZA = LatLng.create(39.297775, 16.254867);
	ArrayList<MapsBean> datiSensori = null;
	private Store store;
	private HttpProxy httpProxy;
	private HashMap<String, List<MapsBean>> mappaNodoSensori = new HashMap<String, List<MapsBean>>();
	private TabPanel appTabPanel;
	private LatLng sensorePos = null;
	private RainbowMain mainPanel;
	private HashMap<String, List<MapsBean>> sensoriTemperatura = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriRumore = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriUmidita = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriLuminosita = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriBatteria = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriPressioneAtmosferica = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriConcentrazioneCO = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriConcentrazioneCoDue = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriConcentrazioneNoDue = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriConcentrazioneOTre = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriIndiceSSI = new HashMap<String, List<MapsBean>>();
	private HashMap<String, List<MapsBean>> sensoriConcentrazioneHumidex = new HashMap<String, List<MapsBean>>();


	@Override
	public void init() {
		store = getStore();
		createEmptyMaps(null);
		drawMaps();
		

	}
	
	public void createEmptyMaps(LatLng location){
		MapOptions mapOptions = MapOptions.create();
		mapOptions.setZoom(15.0);
		mapOptions.setCenter(location!=null?location:COSENZA);
		mapOptions.setMapTypeId(MapTypeId.ROADMAP);
		widg.setSize("100%", "100%");
		map = GoogleMap.create(widg.getElement(), mapOptions);
	
		add(widg);	
		
	}

	public MapsServiceNew(TabPanel appTabPanel, RainbowMain mainPanel) {
		this.appTabPanel = appTabPanel;
		this.mainPanel = mainPanel;
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
							if (!mappaNodoSensori.containsKey(record.getAsString("idNodeSensore"))) {
								mappaNodoSensori.put(record.getAsString("idNodeSensore"),new ArrayList<MapsBean>());
							}
							MapsBean mapsBean = new MapsBean();
							mapsBean.setDateSampling(record
									.getAsString("dataTime"));
							mapsBean.setIdSensore(record
									.getAsString("idSensore"));
							mapsBean.setIdNodeSensore(record.getAsString("idNodeSensore"));
							mapsBean.setDescrizioneNode(record.getAsString("descrizioneNode"));
							mapsBean.setValue(record.getAsString("value"));
							mapsBean.setNameDescription(record.getAsString("value"));
							mapsBean.setNameDescription(record.getAsString("nameSensor"));
							mapsBean.setLivello(record.getAsString("livello"));
							mapsBean.setLat(record.getAsString("lat"));
							mapsBean.setLng(record.getAsString("lng"));
							mapsBean.setColorLevel(record
									.getAsString("colorLevel"));
                         
							mappaNodoSensori.get(record.getAsString("idNodeSensore")).add(mapsBean);
						}
				

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					MessageBox.alert("Info", "Nessun Sensore da graficare");
				}
				choiseTypeListSensor(mappaNodoSensori);
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

	private CellTable<MapsBean> createPanel(List<MapsBean> listData) {
		final ListDataProvider<MapsBean> model = new ListDataProvider<MapsBean>(
				listData);
		
		Column<MapsBean, String> nameSensor = new Column<MapsBean, String>(
				new TextCell()) {
			@Override
			public String getValue(MapsBean name) {
				return name.getNameDescription();
			}
		};
		Column<MapsBean, String> valueColumn = new Column<MapsBean, String>(
				new TextCell()) {
			@Override
			public String getValue(MapsBean value) {
				return value.getValue();
			}
		};
		Column<MapsBean, String> valueLatLng = new Column<MapsBean, String>(
				new TextCell()) {
			@Override
			public String getValue(MapsBean latlng) {
				return latlng.getLat() + "," + latlng.getLng();
			}
		};
		Column<MapsBean, String> timestamp = new Column<MapsBean, String>(
				new TextCell()) {
			@Override
			public String getValue(MapsBean timestamp) {
				return timestamp.getDateSampling();
			}
		};
		CellTable<MapsBean> table = new CellTable<MapsBean>();

		table.addColumn(nameSensor, "Sensore");
		table.addColumn(valueColumn, "Valore");
		table.addColumn(valueLatLng, "Lat Lng");
		table.addColumn(timestamp, "Timestamp");
		Column<MapsBean, String> chartBtn = new Column<MapsBean, String>(
				new ButtonCell()) {
			@Override
			public String getValue(MapsBean c) {
				return "Open Chart";
			}
		};
		table.addColumn(chartBtn, "Chart");
		chartBtn.setFieldUpdater(new FieldUpdater<MapsBean, String>() {
			@Override
			public void update(int index, MapsBean object, String value) {
				showScreen(new ChartsContainer(object), "Chart",
						"line-charts-icon", "");
			}
		});
		model.addDataDisplay(table);
		return table;
	}
	public void showScreen(ChartsContainer panel, String title, String iconCls,	String screenName) {
		String panelID = panel.getId();
		if (!appTabPanel.getActiveTab().getId().equals(panelID)) {
			mainPanel.setRefreshOnTabChange(false);
		}
		panel.setIconCls(iconCls);
		if (!panel.isRendered()) {
			panel.setTitle(title);
			if (iconCls == null) {
				iconCls = "plugins-nav-icon";
			}
			panel.setClosable(true);
			panel.init();
		} else {
			panel.loadData();
		}
		appTabPanel.add(panel);
		appTabPanel.activate(panel.getId());

	}
	private void choiseTypeListSensor(HashMap<String, List<MapsBean>> mappaNodoSensori) {
		for (Map.Entry<String, List<MapsBean>> entry : mappaNodoSensori
				.entrySet()) {
			List<MapsBean> listSen = entry.getValue();
			for (MapsBean mapsBean : listSen) {
				if (mapsBean.getNameDescription().contains("Temperatura")) {
					  sensoriTemperatura.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
				       sensoriTemperatura.get(mapsBean.getIdNodeSensore()).add(mapsBean);	
					   setSensoriTemperatura(sensoriTemperatura);
								
				}else if(mapsBean.getNameDescription().contains("Rumore")){
				  	 sensoriRumore.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					 sensoriRumore.get(mapsBean.getIdNodeSensore()).add(mapsBean);	
					 setSensoriRumore(sensoriRumore);
					
				}else if (mapsBean.getNameDescription().contains("Luminosità")) {
					sensoriLuminosita.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriLuminosita.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriLuminosita(sensoriLuminosita);
					
				} else if (mapsBean.getNameDescription().contains("Batteria")) {
					sensoriBatteria.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriBatteria.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriBatteria(sensoriBatteria);
				} else if (mapsBean.getNameDescription().contains("Umidità")) {
					sensoriUmidita.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriUmidita.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriUmidita(sensoriUmidita);
					}
				else if (mapsBean.getNameDescription().contains("Pressione")) {
					sensoriPressioneAtmosferica.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriPressioneAtmosferica.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriPressioneAtmosferica(sensoriPressioneAtmosferica);
					}
				else if (mapsBean.getNameDescription().contains("MONO OSSIDO")) {
					sensoriConcentrazioneCO.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriConcentrazioneCO.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriConcentrazioneCo(sensoriConcentrazioneCO);
					}
				else if (mapsBean.getNameDescription().contains("CO2")) {
					sensoriConcentrazioneCoDue.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriConcentrazioneCoDue.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriConcentrazioneCoDue(sensoriConcentrazioneCoDue);
					}
				else if (mapsBean.getNameDescription().contains("NO2")) {
					sensoriConcentrazioneNoDue.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriConcentrazioneNoDue.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriConcentrazioneNoDue(sensoriConcentrazioneNoDue);
					}
				else if (mapsBean.getNameDescription().contains("O3")) {
					sensoriConcentrazioneOTre.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriConcentrazioneOTre.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriConcentrazioneOTre(sensoriConcentrazioneOTre);
					}
				else if (mapsBean.getNameDescription().contains("SSI")) {
					sensoriIndiceSSI.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriIndiceSSI.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriIndiceSSi(sensoriIndiceSSI);
					}
				else if (mapsBean.getNameDescription().contains("Humidex")) {
					sensoriConcentrazioneHumidex.put(mapsBean.getIdNodeSensore(),new ArrayList<MapsBean>());
					sensoriConcentrazioneHumidex.get(mapsBean.getIdNodeSensore()).add(mapsBean);
					setSensoriConcentrazioneHumidex(sensoriConcentrazioneHumidex);
					}
				
	
			}

		}
	
	}
	private RecordDef getRecorDef() {
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("idSensore"),
				new StringFieldDef("idNodeSensore"),
				new StringFieldDef("descrizioneNode"),
				new StringFieldDef("dataTime"), new StringFieldDef("value"),
				new StringFieldDef("nameSensor"),
				new StringFieldDef("livello"), new StringFieldDef("lat"),
				new StringFieldDef("lng"), new StringFieldDef("colorLevel"), });
		return recordDef;
	}

	public HashMap<String, List<MapsBean>> getMappaNodoSensori() {
		return mappaNodoSensori;
	}

	public void setMappaNodoSensori(
			HashMap<String, List<MapsBean>> mappaNodoSensori) {
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
	
	

	public HashMap<String, List<MapsBean>> getSensoriTemperatura() {
		return sensoriTemperatura;
	}

	public void setSensoriTemperatura(
			HashMap<String, List<MapsBean>> sensoriTemperatura) {
		this.sensoriTemperatura = sensoriTemperatura;
	}

	public HashMap<String, List<MapsBean>> getSensoriRumore() {
		return sensoriRumore;
	}

	public void setSensoriRumore(HashMap<String, List<MapsBean>> sensoriRumore) {
		this.sensoriRumore = sensoriRumore;
	}

	public HashMap<String, List<MapsBean>> getSensoriUmidita() {
		return sensoriUmidita;
	}

	public void setSensoriUmidita(HashMap<String, List<MapsBean>> sensoriUmidita) {
		this.sensoriUmidita = sensoriUmidita;
	}

	public HashMap<String, List<MapsBean>> getSensoriLuminosita() {
		return sensoriLuminosita;
	}

	public void setSensoriLuminosita(
			HashMap<String, List<MapsBean>> sensoriLuminosita) {
		this.sensoriLuminosita = sensoriLuminosita;
	}

	public HashMap<String, List<MapsBean>> getSensoriBatteria() {
		return sensoriBatteria;
	}

	public void setSensoriBatteria(HashMap<String, List<MapsBean>> sensoriBatteria) {
		this.sensoriBatteria = sensoriBatteria;
	}
	
	

	public HashMap<String, List<MapsBean>> getSensoriPressioneAtmosferica() {
		return sensoriPressioneAtmosferica;
	}

	public void setSensoriPressioneAtmosferica(
			HashMap<String, List<MapsBean>> sensoriPressioneAtmosferica) {
		this.sensoriPressioneAtmosferica = sensoriPressioneAtmosferica;
	}

	public HashMap<String, List<MapsBean>> getSensoriConcentrazioneCo() {
		return sensoriConcentrazioneCO;
	}

	public void setSensoriConcentrazioneCo(
			HashMap<String, List<MapsBean>> sensoriConcentrazioneCo) {
		this.sensoriConcentrazioneCO = sensoriConcentrazioneCo;
	}

	public HashMap<String, List<MapsBean>> getSensoriConcentrazioneCoDue() {
		return sensoriConcentrazioneCoDue;
	}

	public void setSensoriConcentrazioneCoDue(
			HashMap<String, List<MapsBean>> sensoriConcentrazioneCoDue) {
		this.sensoriConcentrazioneCoDue = sensoriConcentrazioneCoDue;
	}

	public HashMap<String, List<MapsBean>> getSensoriConcentrazioneNoDue() {
		return sensoriConcentrazioneNoDue;
	}

	public void setSensoriConcentrazioneNoDue(
			HashMap<String, List<MapsBean>> sensoriConcentrazioneNoDue) {
		this.sensoriConcentrazioneNoDue = sensoriConcentrazioneNoDue;
	}

	public HashMap<String, List<MapsBean>> getSensoriConcentrazioneOTre() {
		return sensoriConcentrazioneOTre;
	}

	public void setSensoriConcentrazioneOTre(
			HashMap<String, List<MapsBean>> sensoriConcentrazioneOTre) {
		this.sensoriConcentrazioneOTre = sensoriConcentrazioneOTre;
	}

	public HashMap<String, List<MapsBean>> getSensoriIndiceSSi() {
		return sensoriIndiceSSI;
	}

	public void setSensoriIndiceSSi(HashMap<String, List<MapsBean>> sensoriIndiceSSi) {
		this.sensoriIndiceSSI = sensoriIndiceSSi;
	}

	public HashMap<String, List<MapsBean>> getSensoriConcentrazioneHumidex() {
		return sensoriConcentrazioneHumidex;
	}

	public void setSensoriConcentrazioneHumidex(
			HashMap<String, List<MapsBean>> sensoriConcentrazioneHumidex) {
		this.sensoriConcentrazioneHumidex = sensoriConcentrazioneHumidex;
	}

	public void drawMaps() {
		UrlParam[] params = getUrlParams();
		store.load(params);

	}
	
	public void choiseSensorListFromMenu(HashMap<String, List<MapsBean>> mappaNodoSensori){
		for (Map.Entry<String, List<MapsBean>> entry : mappaNodoSensori.entrySet()) {
			 List<MapsBean> listSen = entry.getValue();
			for (final MapsBean mapsBean2 : listSen) {
				final LatLng sensorePos = LatLng.create(Double.parseDouble(mapsBean2.getLat()),	Double.parseDouble(mapsBean2.getLng()));
				MarkerOptions markerOptions = MarkerOptions.create();markerOptions.setClickable(true);
				markerOptions.setMap(map);
			
				markerOptions.setIcon(mapsBean2.getColorLevel());
				markerOptions.setPosition(sensorePos);
				final Marker marker = Marker.create(markerOptions);
				marker.addClickListener(new ClickHandler() {
					@Override
					public void handle(MouseEvent event) {
					NXInfoWindow coordInfoWindow = NXInfoWindow.create(1l);
					List<MapsBean> mb=new ArrayList<MapsBean> ();
				            	mb.add(mapsBean2);
					    		coordInfoWindow.setContent(createPanel(mb));
								coordInfoWindow.setPosition(sensorePos);
								coordInfoWindow.open(map);
						
					}
				});
			}
		}
	}
	public void selectedSensorFromChart(final MapsBean mapsBean2){
			final LatLng sensorePos = LatLng.create(Double.parseDouble(mapsBean2.getLat()),	Double.parseDouble(mapsBean2.getLng()));
				MarkerOptions markerOptions = MarkerOptions.create();markerOptions.setClickable(true);
				markerOptions.setMap(map);
				markerOptions.setIcon("images/custom/icon-blu.png");
				markerOptions.setPosition(sensorePos);
				final Marker marker = Marker.create(markerOptions);
				marker.addClickListener(new ClickHandler() {
					@Override
					public void handle(MouseEvent event) {
					NXInfoWindow coordInfoWindow = NXInfoWindow.create(1l);
					List<MapsBean> mb=new ArrayList<MapsBean> ();
				            	mb.add(mapsBean2);
					    		coordInfoWindow.setPosition(sensorePos);
								coordInfoWindow.open(map);
						
					}
				});
	    	}
	
	public void resetMaps(){
		MapsContainer panelParent = (MapsContainer)getPanelParent();
		panelParent.reset();
	}
	
	
	@Override
	public void refreshData() {
		store.load(getUrlParams());
	}
	 
	public class ImageCustom{
		
		Image m=new Image();
		public ImageCustom(){
			
			
		}
		
	}
	
}
