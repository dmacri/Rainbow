package it.icarcnr.rainbow.client.charts;



import it.icarcnr.rainbow.client.combobox.SensorComboBox;
import it.icarcnr.rainbow.client.combobox.NodeSensorComboBox;
import it.icarcnr.rainbow.client.maps.MapsBean;
import it.icarcnr.rainbow.client.maps.MapsServiceNew;
import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.JsonConverter;
import it.icarcnr.rainbow.client.util.UrlParamsUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.basecomponents.FakeIFrame;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IParameterHttpServletRequestContants;
import it.icarcnr.rainbow.client.util.i18n.charts.IChart;

import com.google.gwt.user.datepicker.client.CalendarUtil;




import java.util.Date;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.maps.gwt.client.LatLng;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.TimeField;
import com.gwtext.client.widgets.form.ValidationException;
import com.gwtext.client.widgets.form.Validator;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.form.event.FormPanelListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.form.Field;


public class ChartParameter extends BasePanel {

	private FormPanel formPanel;
	private FormPanel mapPanel;
	
	private NodeSensorComboBox nodeSensorCB;
	private SensorComboBox sensorCB;
	private Button drawButton;
	private Button resetButton;
	private Button exportButton;
	private Item pdfMenuItem;
	private Item csvMenuItem;
	private Item onlyChartMenuItem;
	private DateField startDate;
	private DateField endDate;
	private TimeField startTimeField;
	private TimeField endTimeField;
	private Integer nodeSensorId;
	private String nodeSensorDescripton;
	private Integer sensorId;
    private String sensorDescription;
	private Store checkPermissionStore;
	boolean drawChartDisabled;
	boolean pdfReportDisabled;
	boolean pdfOnlyChartReportDisabled;
	boolean csvReportDisabled;
	private JSONArray serviceIdList;
	private Date dateFomSensor=null;
	private boolean chartFromMaps=false;
	
	static IChart iChart = (IChart)GWT.create(IChart.class);
	
	private MapsBean valueFromMaps;

	public ChartParameter(MapsBean valueFromMaps){
		this.valueFromMaps=valueFromMaps;
	}

	@Override
	public void init() {
		setWidth(300);
		setFrame(true);
		setTitle(iChart.ChartParameter_Criteria());
		setCollapsible(true);
		setLayout(new FitLayout());
//		checkPermissionStore = getCheckPermissionStore();
		formPanel = makeFomPanel(getValueFromMaps());
		formPanel.setAutoScroll(true);
		formPanel.addListener(new FormPanelListenerAdapter(){
			@Override
			public boolean doBeforeShow(Component component) {
			
				formPanel.doLayout(true);
				return super.doBeforeShow(component);
			}

		});
		add(formPanel);
		doLayout(true);

	}

  

	public void drawChart() {
		JSONObject parameters = getParamtersToPlotGraph();
		ChartsContainer panelParent = (ChartsContainer)getPanelParent();
		panelParent.drawChart(parameters);
	}

	private JSONObject getParamtersToPlotGraph() {
	
		JSONObject parameters = makeParameters(getValueFromMaps().getIdSensore()!=null?Integer.parseInt(getValueFromMaps().getIdSensore()):sensorId);
              if(getValueFromMaps().getIdSensore()!=null){
           	   dateFomSensor=DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S").parse(valueFromMaps.getDateSampling());
            		   
            	  int  anno=dateFomSensor.getYear()+1900;
            	  int  mese=dateFomSensor.getMonth()+1;
            	  int giorno=dateFomSensor.getDate();
            	  String date=giorno+"/"+mese+"/"+anno;
            	  dateFomSensor=DateTimeFormat.getFormat("dd/MM/yyyy").parse(date);
            	  parameters.put("serviceId",new JSONString("[0]"));
            	  parameters.put("dateEnd",new JSONString(chartFromMaps==true?DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(endDate.getValue()):date));
            	  CalendarUtil.addDaysToDate(dateFomSensor,-7);
           	      anno=dateFomSensor.getYear()+1900;
           	      mese=dateFomSensor.getMonth()+1;
        	      giorno=dateFomSensor.getDate();
        	      date=giorno+"/"+mese+"/"+anno;
        	      dateFomSensor=DateTimeFormat.getFormat("dd/MM/yyyy").parse(date);
            	  parameters.put("dateStart",new JSONString(chartFromMaps==true?DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(startDate.getValue()):date));
            	  parameters.put("timeStart",new JSONString("00:00"));
            	  parameters.put("timeEnd",new JSONString("00:00"));
            	  parameters.put("nodeSensorkId",new JSONString(chartFromMaps!=true?getValueFromMaps().getIdNodeSensore():(nodeSensorId==null?getValueFromMaps().getIdNodeSensore():nodeSensorId.toString())));
            	  parameters.put("networkDescription",new JSONString(chartFromMaps!=true?getValueFromMaps().getDescrizioneNode():(nodeSensorDescripton==null?getValueFromMaps().getDescrizioneNode():nodeSensorDescripton)));
            	  parameters.put("sensorId",new JSONString(chartFromMaps!=true?getValueFromMaps().getIdSensore():(sensorId==null?getValueFromMaps().getIdSensore():sensorId.toString())));
            	  parameters.put("sensorDescription",new JSONString(chartFromMaps!=true?getValueFromMaps().getNameDescription():(sensorDescription==null?getValueFromMaps().getNameDescription():sensorDescription)));
              	  panelParent.setParameters(parameters);
                    	 
                   }
		if(panelParent.getParameters()!=null){
			Set<String> keySet = getParameters().keySet();
			for (String key : keySet) {
				parameters.put(key, getParameters().get(key));
			}
		}
		return parameters;
	}
	private boolean validateFields() {
		return nodeSensorCB.isValid() && sensorCB.isValid() && startDate.isValid() && startTimeField.isValid() && endDate.isValid() && endTimeField.isValid();

	
	}

	protected JSONObject makeParameters(Integer i) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		jsonArray.set(i-1, new JSONNumber(i-1));
		jsonObject.put("serviceId", jsonArray); //$NON-NLS-1$
		jsonObject.put("dateStart",new JSONString(DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(startDate.getValue())));
		jsonObject.put("timeStart",new JSONString(startTimeField.getValue())); //$NON-NLS-1$
		jsonObject.put("dateEnd", new JSONString(DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(endDate.getValue())));
		jsonObject.put("timeEnd",new JSONString( endTimeField.getValue())); //$NON-NLS-1$


		return jsonObject;
	}

	private FormPanel makeFomPanel(MapsBean valueFromMaps) {
		formPanel = new FormPanel();
		formPanel.setLabelWidth(75);
		MapsServiceNew mapsServiceNew=new MapsServiceNew(null,null);
		mapsServiceNew.createEmptyMaps(null);
		if(valueFromMaps.getDateSampling()!=null){
		dateFomSensor=DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S").parse(valueFromMaps.getDateSampling());
		}
		nodeSensorCB = makeNodeSensorComboBox(valueFromMaps.getDescrizioneNode()==null?"":valueFromMaps.getDescrizioneNode(),valueFromMaps.getIdNodeSensore());
		sensorCB = makeSensorComboBox(valueFromMaps.getNameDescription()==null?"":valueFromMaps.getNameDescription());
		resetButton = makeResetButton();
		Panel startDatePanel = makeStardDatePanel(dateFomSensor);	
		Panel endDatePanel = makeEndDatePanel(dateFomSensor);
		setValidators();
		formPanel.add(nodeSensorCB, new AnchorLayoutData("94%")); 
		formPanel.add(sensorCB, new AnchorLayoutData("94%")); 		
		formPanel.add(startDatePanel, new AnchorLayoutData("100%"));
		formPanel.add(endDatePanel, new AnchorLayoutData("100%"));
		
		mapsServiceNew.setId(getId()+"mappaGDS");
     	mapsServiceNew.setMargins(30, 5, 5, 35);
		formPanel.add(mapsServiceNew, new AnchorLayoutData("90%"));
		drawButton = makeDrawButton();
		formPanel.addButton(drawButton);
		exportButton = makeExportButtonMenu(pdfReportDisabled,pdfOnlyChartReportDisabled, csvReportDisabled);
		formPanel.addButton(exportButton);
		formPanel.addButton(resetButton);
		disableReportButtons();
		if(valueFromMaps.getDateSampling()!=null){
			enableButtonsD();
		}
		return formPanel;
	}


	private void disableReportButtons() {
		drawChartDisabled = true;
		pdfReportDisabled = true;
		pdfOnlyChartReportDisabled = true;
		csvReportDisabled = true;
		enableButtons();
	}

	private Button makeDrawButton() {
		drawButton = new Button(iChart.ChartParameter_Draw_chart()); //$NON-NLS-1$
		drawButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {

				long endDateMilliseconds = endDate.getValue().getTime();
				//				Date endTime = DateUtil.parseDate(endTimeField.getValue(), IDateFormatUtil.timeDateFormat);
				Date endTime = DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).parse(endTimeField.getValue());

				long endTimeMillisecond =endTime.getTime();
				endDateMilliseconds+=endTimeMillisecond;

				long startDateMilliseconds = startDate.getValue().getTime();
				//				Date startTime = DateUtil.parseDate(startTimeField.getValue(), IDateFormatUtil.timeDateFormat);
				Date startTime = DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).parse(startTimeField.getValue());

				long startTimeMillisecond = startTime.getTime();
				startDateMilliseconds+=startTimeMillisecond;

				long month =2678400000L;
				if((endDateMilliseconds-startDateMilliseconds)>month){

					MessageBox.alert(iChart.ChartParameter_Warning(), iChart.ChartParameter_Range_of_more_than_31_days());
					//					endTimeField.setInvalidText("Range di data troppo ampio");

				}
//				else if(validateFields()){
//
//					collapse(true);
//					drawChart();
//				}
				else {
					if(getValueFromMaps().getIdSensore()!=null){
						chartFromMaps=true;
				}
					if(validateFields()){
					collapse(true);
					drawChart();
				}
				}
			}
		});
		drawButton.setIconCls("line-charts-icon"); //$NON-NLS-1$
		return drawButton;
	}

	private Button makeResetButton() {
		resetButton = new Button(iChart.ChartParameter_Clear()); //$NON-NLS-1$
		resetButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				resetChart();
			}
		});
		resetButton.setIconCls("reset-form-icon"); //$NON-NLS-1$
		return resetButton;
	}

	private void makeExportReportCsvButton(){
		if(validateFields()){
			JSONObject parameters = getParamtersToPlotGraph();
			UrlParam[] params = UrlParamsUtil.getUrlParams(parameters);
			FakeIFrame downloadFrame = new FakeIFrame(IActionPathConstants.CSV_REPORT_ACTION, params);	
		}
	}


	private void makeExportReportPdfButton(){
//		if(validateFields()){
//			JSONObject parameters = getParamtersToPlotGraph();
//			UrlParam[] params = UrlParamsUtil.getUrlParams(parameters);
//			FakeIFrame downloadFrame = new FakeIFrame(IActionPathConstants.CHART_REPORT_ACTION, params);	
//		}
	}

	private void makeExportOnlyChartReportButton(){
//		if(validateFields()){
//			JSONObject parameters = getParamtersToPlotGraph();
//			UrlParam[] params = UrlParamsUtil.getUrlParams(parameters);
//			FakeIFrame downloadFrame = new FakeIFrame(IActionPathConstants.ONLY_CHART_REPORT_ACTION, params);	
//		}
	}

	private Button makeExportButtonMenu(boolean pdfReportEnabled, boolean pdfOnlyChartReportEnabled, boolean csvReportEnabled){
		exportButton = new Button(iChart.ChartParameter_Export()); //$NON-NLS-1$
		Menu menu = new Menu();   
		menu.setShadow(true);   
		menu.setMinWidth(10);   
    	pdfMenuItem = new Item(); 
		pdfMenuItem.setText(iChart.ChartParameter_Complete_Pdf()); //$NON-NLS-1$
		pdfMenuItem.setIconCls("pdf-chart-icon"); //$NON-NLS-1$
		pdfMenuItem.addListener(new BaseItemListenerAdapter() {
			public void onClick(BaseItem item, EventObject e) {
				makeExportReportPdfButton();
			}
		});
		csvMenuItem = new Item();
		csvMenuItem.setText(iChart.ChartParameter_Data_in_Csv()); //$NON-NLS-1$
		csvMenuItem.setIconCls("csv-chart-icon"); //$NON-NLS-1$
		csvMenuItem.addListener(new BaseItemListenerAdapter() {
			public void onClick(BaseItem item, EventObject e){
				makeExportReportCsvButton();
			}
		});
		onlyChartMenuItem = new Item();
		onlyChartMenuItem.setText(iChart.ChartParameter_Only_Chart_Pdf()); //$NON-NLS-1$
		onlyChartMenuItem.setIconCls("pdf-chart-icon"); //$NON-NLS-1$
		onlyChartMenuItem.addListener(new BaseItemListenerAdapter() {
			public void onClick(BaseItem item, EventObject e){
				makeExportOnlyChartReportButton();
			}
		});

		menu.addItem(onlyChartMenuItem);
		menu.addItem(pdfMenuItem);
		menu.addItem(csvMenuItem);
		menu.addSeparator();
		exportButton.setMenu(menu);
		exportButton.setIconCls("export-report-icon"); //$NON-NLS-1$
		exportButton.setPressed(false);
		return exportButton;

	}

	private void setValidators() {

		startDate.setValidator(new Validator(){
			public boolean validate(String value) throws ValidationException {				
				if (endDate.getValue() == null){
					return true;
				}
				if (startDate.getValue().after(endDate.getValue())){
					startDate.setInvalidText(iChart.ChartParameter_Start_date_must_come_before_end_date()); //$NON-NLS-1$
					return false;
				}
				startTimeField.validate();
				return true;
			}

		});

		endDate.setValidator(new Validator(){
			public boolean validate(String value) throws ValidationException {
				startDate.validate();
				return true;
			}
		});

		startTimeField.setValidator(new Validator(){
			public boolean validate(String value) throws ValidationException {    
				if (endTimeField.getValue() == null){
					return true;
				}
				if (startDate.getValue()!=null && endDate.getValue()!=null && startDate.getValue().equals(endDate.getValue()) ){
					Date endTime = DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).parse(endTimeField.getValue());
					Date startTime = DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).parse(value);

					if (startTime.equals(endTime) || startTime.after(endTime)){
						startTimeField.setInvalidText(iChart.ChartParameter_Start_date_i_equal_to_end_date_so_start_time_must_come_before_end_time()); //$NON-NLS-1$
						return false;
					}
				}
				return true;
			}
		});

		endTimeField.setValidator(new Validator(){
			public boolean validate(String value) throws ValidationException {
				startTimeField.validate();
				return true;
			}
		});
	}



	private MultiFieldPanel makeStardDatePanel(Date dateFromSensor) {
		MultiFieldPanel dateTimePanel = new MultiFieldPanel();  
		startDate = makeStartDateField(dateFromSensor);
		dateTimePanel.addToRow(startDate, 200);  
		startTimeField = makeStartTimeField();
		dateTimePanel.setPaddings(10, 0, 0, 0);
		if(dateFromSensor!=null)
		startTimeField.setValue("00:00");
		dateTimePanel.addToRow(startTimeField, new ColumnLayoutData(1));  
	
		return dateTimePanel;
	}


	private MultiFieldPanel makeEndDatePanel(Date dateFromSensor) {
		MultiFieldPanel dateTimePanel = new MultiFieldPanel();  
		endDate = makeEndDateField(dateFromSensor);
		dateTimePanel.addToRow(endDate, 200);  
		endTimeField = makeEndTimeField();
		//		dateTimePanel.setPaddings(10, 0, 0, 0);
		if(dateFromSensor!=null)
		endTimeField.setValue("00:00");
		
		dateTimePanel.addToRow(endTimeField, new ColumnLayoutData(1));  
		
		return dateTimePanel;
	}

	private DateField makeStartDateField(Date dateFromSensor) {
		DateField startDate = new DateField(iChart.ChartParameter_Start_Date(), IDateFormatUtil.dateFormat); //$NON-NLS-1$
		startDate.setName("startDate"); 
		startDate.setAllowBlank(false);
		startDate.setWidth(100);
		if(dateFromSensor!=null){
	    CalendarUtil.addDaysToDate(dateFromSensor, -7);
		startDate.setValue(dateFromSensor);
		}
		return startDate;
	}

	private DateField makeEndDateField(Date dateFromSensor) {
		DateField endDate = new DateField(iChart.ChartParameter_End_Date(), IDateFormatUtil.dateFormat); //$NON-NLS-1$
		endDate.setName("endDate"); //$NON-NLS-1$
		endDate.setAllowBlank(false);
		endDate.setWidth(100);
		if(dateFromSensor!=null){
		    CalendarUtil.addDaysToDate(dateFromSensor,7);
		    endDate.setValue(dateFromSensor);
		}
		return endDate;
	}

	private TimeField makeStartTimeField() {
		TimeField startTimeField = new TimeField(iChart.ChartParameter_Start_Time(), "timeStart");   //$NON-NLS-1$ //$NON-NLS-2$
		startTimeField.setHideLabel(true);
		startTimeField.setAllowBlank(false);
		startTimeField.setWidth(65);

		startTimeField.setFormat("H:i"); 
		startTimeField.setIncrement(60);

		return startTimeField;
	}
	

	private TimeField makeEndTimeField() {
		TimeField endTimeField = new TimeField(iChart.ChartParameter_End_Time(),"timeEnd"); //$NON-NLS-1$ //$NON-NLS-2$
		endTimeField.setHideLabel(true);
		endTimeField.setAllowBlank(false);
		endTimeField.setWidth(65);
		endTimeField.setFormat("H:i"); 
		endTimeField.setIncrement(60);
		enableButtonsD();
		return endTimeField;
	}




	private NodeSensorComboBox makeNodeSensorComboBox(String valueFromMapsDescription,String valuefromMapsId) {
		NodeSensorComboBox nodeSensorCB = new NodeSensorComboBox(IActionPathConstants.NETWORK_COMBO_BOX_ACTION_PATH, getUrlParams(),NodeSensorComboBox.LoadOption.SINGLE_SELECT, Boolean.TRUE,valueFromMapsDescription);
		nodeSensorCB.setAllowBlank(false);
		nodeSensorCB.setWidth(-1);
		nodeSensorCB.setDisabled(Boolean.FALSE);
		if(valueFromMaps.getIdNodeSensore()!=null){
			nodeSensorId = Integer.parseInt(valuefromMapsId);
			nodeSensorDescripton =valueFromMapsDescription;
			addParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID, new JSONNumber(nodeSensorId));
			addParameter("networkDescription", new JSONString(valueFromMapsDescription));
		}
		nodeSensorCB.addListener(new ComboBoxListenerAdapter(){
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {
				onSelectNodeSensor(record);
			}
		});
		return nodeSensorCB;
	}


	private SensorComboBox makeSensorComboBox(String valueFromMaps) {
		SensorComboBox sensorCB = new SensorComboBox(IActionPathConstants.SENSOR_COMBO_BOX_ACTION_PATH, getUrlParams(),SensorComboBox.LoadOption.SINGLE_SELECT, Boolean.TRUE,valueFromMaps);
		sensorCB.setAllowBlank(false);
		sensorCB.setWidth(-1);
		sensorCB.setDisabled(Boolean.TRUE);
		if(!valueFromMaps.equals("")){
			sensorCB.setDisabled(Boolean.FALSE);
			sensorCB.refreshData(getUrlParams());
		}
		sensorCB.addListener(new ComboBoxListenerAdapter(){
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {
				onSelectSensor(record);
			}
		});
		return sensorCB;
	}

	public MapsServiceNew generateMapsFromChart( final MapsBean mapsBean){
		if(mapsBean.getLat()!=null&&Double.parseDouble(mapsBean.getLat())!= Double.NaN){
   		MapsServiceNew m=(MapsServiceNew)formPanel.findByID(getId()+"mappaGDS");
		m.createEmptyMaps(LatLng.create(Double.parseDouble(mapsBean.getLat()),Double.parseDouble(mapsBean.getLng())));
   		m.selectedSensorFromChart(mapsBean);
	    return m;
		}else 
			return null;
	}
	

	

	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[7];
		params [0] = new UrlParam("serviceIdList", serviceIdList.toString()); //$NON-NLS-1$
		params [1] = new UrlParam("dateStart",DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(startDate.getValue()));
		params [2] = new UrlParam("timeStart",startTimeField.getValue()); //$NON-NLS-1$
		params [3] = new UrlParam("dateEnd", DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(endDate.getValue()));
		params [4] = new UrlParam("timeEnd", endTimeField.getValue()); //$NON-NLS-1$

		return params;
	}



	private void refreshSensorCB() {
		sensorCB.refreshData(getUrlParams());
	}


	protected void resetNodeSensorCB() {
		nodeSensorId = null;
		addParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID, null);
		nodeSensorCB.reset();

	}

	protected void resetSensorCB() {
		sensorId = null;
		addParameter(IParameterHttpServletRequestContants.SENSOR_ID, null);
		sensorCB.reset();

	}


	public void reset(){
		resetNodeSensorCB();
		resetSensorCB();
		startDate.reset();
		startTimeField.reset();
		endDate.reset();
		endTimeField.reset();
		nodeSensorCB.enable();
		sensorCB.disable();
		disableReportButtons();

	}

	public void resetChart(){
		ChartsContainer panelParent = (ChartsContainer)getPanelParent();
		panelParent.reset();
	}

	/**
	 * @param record
	 */
	private void onSelectNodeSensor(Record record) {
		nodeSensorId = record.getAsInteger("id");
		nodeSensorDescripton = record.getAsString("description");
		addParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID, new JSONNumber(nodeSensorId));
		addParameter("networkDescription", new JSONString(nodeSensorDescripton));
		resetSensorCB();
		sensorCB.enable();
		disableReportButtons();
		refreshSensorCB();
	}

	/**
	 * @param record
	 */
	private void onSelectSensor(Record record) {
		sensorId = record.getAsInteger("id"); //$NON-NLS-1$
		sensorDescription = record.getAsString("description");
		addParameter(IParameterHttpServletRequestContants.SENSOR_ID, new JSONNumber(sensorId));
		addParameter("sensorDescription", new JSONString(sensorDescription));
//		disableReportButtons();
//		checkPermissionStore.load(getUrlParams());
	

	}
	


	private void enableButtons() {
		drawButton.setDisabled(drawChartDisabled);
		exportButton.setDisabled(pdfReportDisabled && pdfOnlyChartReportDisabled && csvReportDisabled);
		pdfMenuItem.setDisabled(pdfReportDisabled);
		onlyChartMenuItem.setDisabled(pdfOnlyChartReportDisabled);
		csvMenuItem.setDisabled(csvReportDisabled);
	}
	private void enableButtonsD() {
		if(validateFields()){
		drawButton.setDisabled(false);
		exportButton.setDisabled(false);
		pdfMenuItem.setDisabled(false);
		onlyChartMenuItem.setDisabled(false);
		csvMenuItem.setDisabled(false);
		}
	}

	private Store getCheckPermissionStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECK_CHART_PERMISSION_ACTION);       
		RecordDef dataRecordDef = getCheckPermissionRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record = records[0];
					drawChartDisabled = record.getAsBoolean("drawChartDisabled"); //$NON-NLS-1$
					pdfReportDisabled = record.getAsBoolean("pdfReportDisabled"); //$NON-NLS-1$
					pdfOnlyChartReportDisabled = record.getAsBoolean("pdfOnlyChartReportDisabled"); //$NON-NLS-1$
					csvReportDisabled = record.getAsBoolean("csvReportDisabled"); //$NON-NLS-1$
					enableButtons();
				}
			}
		});
		return st;
	}

	private RecordDef getCheckPermissionRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("drawChartDisabled"), //$NON-NLS-1$
						new BooleanFieldDef("pdfReportDisabled"), //$NON-NLS-1$
						new BooleanFieldDef("pdfOnlyChartReportDisabled"), //$NON-NLS-1$
						new BooleanFieldDef("csvReportDisabled") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}



	public MapsBean getValueFromMaps() {
		return valueFromMaps;
	}



	public void setValueFromMaps(MapsBean valueFromMaps) {
		this.valueFromMaps = valueFromMaps;
	}
	
	

}