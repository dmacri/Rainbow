package it.icarcnr.rainbow.client.charts;

import java.util.Date;

import it.icarcnr.rainbow.client.maps.MapsBean;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.charts.IChart;

import com.github.highcharts4gwt.client.view.widget.HighchartsLayoutPanel;
import com.github.highcharts4gwt.model.array.api.Array;
import com.github.highcharts4gwt.model.factory.api.HighchartsOptionFactory;
import com.github.highcharts4gwt.model.factory.jso.JsoHighchartsOptionFactory;
import com.github.highcharts4gwt.model.highcharts.option.api.ChartOptions;
import com.github.highcharts4gwt.model.highcharts.option.api.Series;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
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
import com.gwtext.client.widgets.layout.FitLayout;
import com.google.gwt.user.client.ui.SimplePanel;




public class ServiceChart extends BasePanel {

	private static final int MILLISECOND_OF_SECOND = 1000;

	private static final int SECOND_OF_MINUTE = 60;

	private static final int Y_VALUE_TOLERANCE = 10;

	private static final int NUMBER_OF_Y_LABELS = 5;

	private static final int NUMBER_OF_X_LABELS = 15;
	private MapsBean mp;
	private Double lat;
	private Double lng;
	private static final int TIMEOUT = 30000 * 6;/* three minutes */
	static IChart iChart = (IChart) GWT.create(IChart.class);
	private Store store;
	private HttpProxy httpProxy;
	private boolean hasToDrawChart;
	final HighchartsLayoutPanel highchartsLayoutPanel = new HighchartsLayoutPanel();
	HighchartsOptionFactory highchartsFactory = new JsoHighchartsOptionFactory();
	final ChartOptions options = highchartsFactory.createChartOptions();
	private MapsBean valueFromMaps;
	@Override
	public void init() {
		setPaddings(15);
		setLayout(new FitLayout());
		hasToDrawChart = false;
		store = getStore();
		createChartEmpty();
	}
	
	public ServiceChart(MapsBean valueFromMaps){
		this.valueFromMaps=valueFromMaps;
	}

	private Store getStore() {
		ConnectionConfig connectionConfig = new ConnectionConfig();
		connectionConfig.setTimeout(TIMEOUT);
		connectionConfig.setUrl(IActionPathConstants.SERVICE_CHART_ACTION_PATH);
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
				error.getCause();
				MessageBox.alert(iChart.ServiceChart_Error(),
						iChart.ServiceChart_System_busy_Please_try_later()); //$NON-NLS-1$ //$NON-NLS-2$
			}

			final ChartOptions optionsStore = highchartsFactory
					.createChartOptions();

			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				getEl().mask(iChart.ServiceChart_Drawing_chart(),
						"x-mask-loading"); 

				if (records != null && records.length > 0) {
					try {
						optionsStore.title().text("Grafico sensori");
					
						optionsStore.chart().backgroundColor("#ffffff"); //$NON-NLS-1$
						setYAxis(records);
						String format = "{   \"millisecond\":\"%H:%M:%S.%L\","
								+ "    \"second\":\"%e/%b/%Y %H:%M:%S\","
								+ "    \"minute\":\"%e/%b/%Y %H:%M\","
								+ "    \"hour\":\"%e/%b/%Y %H:%M\","
								+ "    \"day\":\"%e. %b\","
								+ "    \"week\":\"%e. %b\","
								+ "    \"month\":\"%b %y\","
								+ "    \"year\":\"%Y\"} ";
						optionsStore.xAxis().dateTimeLabelFormats(format);
						optionsStore.xAxis().type("datetime");
						optionsStore.xAxis().labels().rotation(-45);

						Series serie = highchartsFactory.createSeries();
						String name = "";
						StringBuilder stringBuilder = new StringBuilder("[");
						for (Record record : records) {
							name = record.getAsString("name");
							stringBuilder.append("[");
							String xValuesS = record.getAsString("xValue");
							long xValues = Long.parseLong(xValuesS);
							System.out.println(xValuesS + "valore long="
									+ xValues);
							stringBuilder.append(xValues).append(",");
							Double yValue = record.getAsDouble("yValue"); //$NON-NLS-1$
							stringBuilder.append(yValue).append("],");
							lat = record.getAsDouble("lat");
							lng = record.getAsDouble("lng");
						}
						MapsBean g = new MapsBean();
						g.setLng(lng.toString());
						g.setLat(lat.toString());
						setMp(g);
						if (records.length > 0)
							stringBuilder.deleteCharAt(stringBuilder.length() - 1);

						stringBuilder.append("]");

						System.out.println(stringBuilder.toString());
						optionsStore.title().text(name);
						Array<Series> series = optionsStore.series();
						if (series.length() == 0) {
							serie.setFieldAsJsonObject("data",
									stringBuilder.toString());
							series.addToEnd(serie);
						} else {
							series.getItem(0).setFieldAsJsonObject("data",
									stringBuilder.toString());
						}

						highchartsLayoutPanel.renderChart(optionsStore);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					MessageBox.alert(
							"Info", iChart.ServiceChart_No_data_to_draw()); 
					reset();
				}

				ScheduledCommand command = new ScheduledCommand() {
					@Override
					public void execute() {
						highchartsLayoutPanel.isVisible();
						highchartsLayoutPanel.renderChart(optionsStore);
						if (hasToDrawChart) {
							drawMapsLitle();
						}
						getEl().unmask();
					}
				};
				Scheduler.get().scheduleDeferred(command);

			}

		});

		return st;
	}

	private RecordDef getRecorDef() {
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("xValue"),
				new FloatFieldDef("yValue"),
				new FloatFieldDef("startDate"),
				new FloatFieldDef("endDate"),
				new StringFieldDef("name"), 
				new FloatFieldDef("lat"),
				new FloatFieldDef("lng") 
				});
		return recordDef;
	}

	private void setYAxis(Record[] records) {

		Double max = null;
		Double min = null;

		for (Record record : records) {
			Double maxTemp = record.getAsDouble("maxYValue"); //$NON-NLS-1$
			Double minTemp = record.getAsDouble("minYValue"); //$NON-NLS-1$

			if (max == null || max < maxTemp) {
				max = maxTemp;
			}
			if (min == null || min > minTemp) {
				min = minTemp;
			}

		}
		if (max != null && min != null) {
			if (min.intValue() == min.doubleValue()
					&& max.intValue() == max.doubleValue()) {
				int maxYValue = max.intValue();
				int minYValue = min.intValue();

				int delta = (maxYValue - minYValue) / Y_VALUE_TOLERANCE;
				if (delta == 0) {
					delta = Y_VALUE_TOLERANCE;
				}
				int minrange = minYValue - delta;
				int maxrange = maxYValue + delta;

				int step = (maxrange - minrange) / NUMBER_OF_Y_LABELS;
				// ya.setRange(minrange, maxrange, step);
				options.yAxis().max(maxrange);
				options.yAxis().min(minrange);
				options.yAxis().tickInterval(step);
			} else {
				double minrange = min - ((max - min) / Y_VALUE_TOLERANCE);
				double maxrange = max + ((max - min) / Y_VALUE_TOLERANCE);

				double step = (maxrange - minrange) / NUMBER_OF_Y_LABELS;
				options.yAxis().max(maxrange);
				options.yAxis().min(minrange);
				options.yAxis().tickInterval(step);
			}

		}

	}

	public void drawChart() {
		reset();
		UrlParam[] params = getUrlParams();
		if(getValueFromMaps().getIdSensore()==null)
		getEl().mask(iChart.ServiceChart_Loading_data(), "x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
		hasToDrawChart = true;
		store.load(params);

	}

	public void reset() {
		hasToDrawChart = false;
		if(getValueFromMaps().getIdSensore()==null)
        	getEl().unmask();
		
		httpProxy.getConnection().abort();
		store.removeAll();
		createChartEmpty();

	}

	private void createChartEmpty() {
		options.chart().type("line");
		options.chart().margin().push(75);
		options.xAxis().max(50);
		options.xAxis().min(0);
		options.yAxis().max(50);
		options.yAxis().min(0);
		options.noData();
		options.title().text("Choise chart");
		highchartsLayoutPanel.renderChart(options);
		add((SimplePanel) highchartsLayoutPanel);

		highchartsFactory.createGlobalOptions().global().timezoneOffset(90);
//		highchartsFactory.createGlobalOptions().global().useUTC(true);

	}
	public MapsBean getValueFromMaps() {
		return valueFromMaps;
	}

	public void drawMapsLitle() {
		ChartsContainer panelParent = (ChartsContainer) getPanelParent();
		panelParent.activeLitleMaps(getMp());

	}

	public MapsBean getMp() {
		return mp;
	}

	public void setMp(MapsBean mp) {
		this.mp = mp;
	}

}
