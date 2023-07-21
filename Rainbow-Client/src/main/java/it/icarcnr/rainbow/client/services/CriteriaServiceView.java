package it.icarcnr.rainbow.client.services;


import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.ICriteriaConstants;
import it.icarcnr.rainbow.client.util.constants.INetworkConstants;
import it.icarcnr.rainbow.client.util.constants.IUtilityConstants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.ConnectionConfig;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Hidden;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

public class CriteriaServiceView extends BasePanel {

	
	static IServices iServices = (IServices)GWT.create(IServices.class);
	static Ii18nGlobalConstants iConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);
	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);

	private int requestId;
	private int networkId;
	private String requestName;
	private String nodeName;
	private boolean request403ForbiddenRegister;

	
	private Panel criteriaInfoPanel;
//	private FormPanel buttonPanel;
	private Button button;
	private Store storeSearchCLI;

	@Override
	public void init() {

		requestName = getParameters().get("requestName").isString().stringValue();
		nodeName =getParameters().get("nodeName").isString().stringValue();
		requestId = Double.valueOf(getParameters().get("requestId").isNumber().doubleValue()).intValue();
		networkId = Double.valueOf(getParameters().get("networkId").isNumber().doubleValue()).intValue();
		request403ForbiddenRegister =getParameters().get("request403ForbiddenRegister").isBoolean().booleanValue();
		
		storeSearchCLI = getSearchCLIStore();


		setPaddings(10,3,3,3);
		setAutoScroll(false);
		this.setLayoutData(new FitLayout());

		
		
		criteriaInfoPanel = new Panel();
		criteriaInfoPanel.setAutoScroll(true);
		criteriaInfoPanel.setBorder(false);

		add(criteriaInfoPanel, new RowLayoutData("80%"));

		button=new Button(iUtility.Search_CLI());

		if(request403ForbiddenRegister && networkId==INetworkConstants.PK3){
			
			FormPanel buttonPanel = new FormPanel();
			buttonPanel.setBorder(false);
			buttonPanel.setLayout(new FormLayout());
			buttonPanel.setFrame(true);

			Hidden requestNameField = new Hidden("requestName",requestName);
			Hidden nodeNameField = new Hidden("nodeName", nodeName);

			buttonPanel.add(requestNameField);
			buttonPanel.add(nodeNameField);

			buttonPanel.addButton(button);
			button.addListener(new ButtonListenerAdapter(){
				@Override
				public void onClick(Button button, EventObject e) {
					getEl().mask(iUtility.Loading(), "x-mask-loading");
					storeSearchCLI.load(getParams());
				}
			});
			add(buttonPanel, new RowLayoutData());

		}
		
		doLayout(true);
		
		final Store store = getGridValueStore();

		store.setBaseParams(getUrlParams());
		
		store.load();

	}


	private Store getGridValueStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CRITERIA_SERVICE_ACTION_PATH);       
		RecordDef dataRecordDef = geGridValuetRecorDefGridValue();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		final Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length>0){
					showServiceInfo(records); 
				}
			}

			/**
			 * @param records
			 */
			private void showServiceInfo(Record[] records) {
				Record record = records[0];

				Map<String, String> thresholdStringValues = getStringThresholdValues(record);

				Boolean monday = record.getAsBoolean("monday"); //$NON-NLS-1$
				Boolean tuesday = record.getAsBoolean("tuesday"); //$NON-NLS-1$
				Boolean wednesday = record.getAsBoolean("wednesday"); //$NON-NLS-1$
				Boolean thursday = record.getAsBoolean("thursday"); //$NON-NLS-1$
				Boolean friday = record.getAsBoolean("friday"); //$NON-NLS-1$
				Boolean saturday = record.getAsBoolean("saturday"); //$NON-NLS-1$
				Boolean sunday = record.getAsBoolean("sunday"); //$NON-NLS-1$
				StringBuilder days = new StringBuilder();

				if(monday&&tuesday&&wednesday&&tuesday&&friday&&saturday&&sunday){
					days.append(iServices.CriteriaServiceView_all_days()); //$NON-NLS-1$
				}else if(monday&&tuesday&&wednesday&&tuesday&&friday){
					days.append(iServices.CriteriaServiceView_from_Monday_to_Friday()); //$NON-NLS-1$
				}
				else if(saturday&&sunday){
					days.append(iServices.CriteriaServiceView_Saturday_and_Sunday()); //$NON-NLS-1$
				}else{

					if(monday){
						days.append(iServices.CriteriaServiceView_Monday()); //$NON-NLS-1$
					}
					if(tuesday){
						days.append(iServices.CriteriaServiceView_Tuesday()); //$NON-NLS-1$
					}
					if(wednesday){
						days.append(iServices.CriteriaServiceView_Wednesday()); //$NON-NLS-1$
					}
					if(thursday){
						days.append(iServices.CriteriaServiceView_Thursday()); //$NON-NLS-1$
					}
					if(friday){
						days.append(iServices.CriteriaServiceView_Friday()); //$NON-NLS-1$
					}
					if(saturday){
						days.append(iServices.CriteriaServiceView_Saturday()); //$NON-NLS-1$
					}
					if(sunday){
						days.append(iServices.CriteriaServiceView_Sunday()); //$NON-NLS-1$
					}

				}

				String valueToBeDefined = iServices.CriteriaServiceView_To_Be_Defined();
				String typeCheckString = null;
				String typeCheck = record.getAsString("typeCheck"); //$NON-NLS-1$
				if(typeCheck.equals("below")){
					typeCheckString=iServices.CriteriaServiceView_Below();
				}else if(typeCheck.equals("above")){
					typeCheckString=iServices.CriteriaServiceView_Above();

				}else if(typeCheck.equals("range")){
					typeCheckString=iServices.CriteriaServiceView_Range();
				}else{
					typeCheckString = valueToBeDefined;
				}


				String thresholdTypeString = null;
				String thresholdType = record.getAsString("thresholdType"); //$NON-NLS-1$
				if(thresholdType.equals(ICriteriaConstants.HOLTWINTERS_THRESHOLD_TYPE)){
					thresholdTypeString=iServices.CriteriaServiceView_Dynamic_Threshold();
				}else if(thresholdType.equals(ICriteriaConstants.STATIC_THRESHOLD_TYPE)){
					thresholdTypeString=iServices.CriteriaServiceView_Static_Threshold();
				}else{
					thresholdTypeString = valueToBeDefined;
				}

				StringBuilder statusCriteriaString= new StringBuilder();
				String statusCriteria = record.getAsString("status");

				if(statusCriteria.equals("suspended")){
					statusCriteriaString.append(iConstants.Service_Status_Suspended());

					statusCriteriaString.append(" ( ");

					Boolean suspended = record.getAsBoolean("suspended");
					if(suspended){
						statusCriteriaString.append(iServices.CriteriaServiceView_Suspended_By_Operator());
					}else{
						statusCriteriaString.append(iServices.CriteriaServiceView_Suspended_By_System());
					}

					statusCriteriaString.append(" ) ");

				}else if(statusCriteria.equals("normal")){
					statusCriteriaString.append(iConstants.Service_Status_Normal());
				}else if(statusCriteria.equals("critical")){
					statusCriteriaString.append(iConstants.Service_Status_Critical());
				}else if(statusCriteria.equals("major")){
					statusCriteriaString.append(iConstants.Service_Status_Major());
				}else{
					statusCriteriaString.append(valueToBeDefined);
				}
				String from =  DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).format((record.getAsDate("from")));
				String to = DateTimeFormat.getFormat(IDateFormatUtil.i18nTimeDateFormat).format((record.getAsDate("to")));
				Integer samplingPeriod = record.getAsInteger("samplingPeriod"); //$NON-NLS-1$
				String units = null;
				if(samplingPeriod>=360 && samplingPeriod<1440){
					samplingPeriod/=60;
					units = "h"; //$NON-NLS-1$
				}else if(samplingPeriod>=1440){
					samplingPeriod/=1440;
					units = "gg";  //$NON-NLS-1$
				}else{
					units = "min"; //$NON-NLS-1$
				}
				criteriaInfoPanel.setHtml(

						iServices.CriteriaServiceView_Threshold_Type() + thresholdTypeString+"<br><br>"+
						iServices.CriteriaServiceView_Type_Check() + typeCheckString+"<br><br>"+ 
						iServices.CriteriaServiceView_Major_Value() + thresholdStringValues.get("majorValueString") +"</p><br>"+
						iServices.CriteriaServiceView_Critical_Value() + thresholdStringValues.get("criticalValueString")+"</p><br>"+
						"<b>"+iServices.CriteriaServiceView_Status()+"</b>&nbsp;" + statusCriteriaString+"<br><br>"+
						//						"<b>"+iServices.CriteriaServiceView_Type_Suspended()+"</b>&nbsp;" + thresholdTypeSuspended+"<br><br>"+
						iServices.CriteriaServiceView_Sampling_Period() + samplingPeriod +" &nbsp;"+  units +"</p><br>"+
						iServices.CriteriaServiceView_Time_Interval() + days+ "&nbsp;(" +  from  + " &#247 " + to + ")</p><br>"
				);
			}
		});

	


		return st;
	}



	private Map<String, String> getStringThresholdValues(Record record) {

		boolean majorValueHasDefined = !record.isEmpty("majorValue"); //$NON-NLS-1$
		boolean criticalValueHasDefined = !record.isEmpty("criticalValue"); //$NON-NLS-1$
		boolean secondaryMajorValueHasDefined = !record.isEmpty("secondaryMajorValue"); //$NON-NLS-1$
		boolean secondaryCriticalValueHasDefined = !record.isEmpty("secondaryCriticalValue"); //$NON-NLS-1$

		String typeCheck = record.getAsString("typeCheck"); //$NON-NLS-1$

		StringBuilder majorValueString = new StringBuilder();
		StringBuilder criticalValueString = new StringBuilder();

		String valueToBeDefined = iServices.CriteriaServiceView_To_Be_Defined();

		if(majorValueHasDefined){
			if(record.getAsDouble("majorValue") == record.getAsInteger("majorValue") ){
				majorValueString.append(String.valueOf(record.getAsInteger("majorValue"))); //$NON-NLS-1$
			}else{
				majorValueString.append(String.valueOf(record.getAsDouble("majorValue"))); //$NON-NLS-1$
			}
		}else{
			majorValueString.append(valueToBeDefined);
		}
		if(criticalValueHasDefined){
			if(record.getAsDouble("criticalValue") == record.getAsInteger("criticalValue") ){
				criticalValueString.append(String.valueOf(record.getAsInteger("criticalValue"))); //$NON-NLS-1$
			}else{
				criticalValueString.append(String.valueOf(record.getAsDouble("criticalValue"))); //$NON-NLS-1$
			}
		}else{
			criticalValueString.append(valueToBeDefined);
		}

		if(typeCheck.equals("range")){
			majorValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryMajorValueHasDefined){
				if(record.getAsDouble("secondaryMajorValue") == record.getAsInteger("secondaryMajorValue") ){
					majorValueString.append(record.getAsInteger("secondaryMajorValue")); //$NON-NLS-1$
				}else{
					majorValueString.append(record.getAsDouble("secondaryMajorValue")); //$NON-NLS-1$
				}
			}else{
				majorValueString.append(valueToBeDefined);
			}
			majorValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");

			criticalValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryCriticalValueHasDefined){
				if(record.getAsDouble("secondaryCriticalValue") == record.getAsInteger("secondaryCriticalValue") ){
					criticalValueString.append(String.valueOf(record.getAsInteger("secondaryCriticalValue"))); //$NON-NLS-1$
				}else{
					criticalValueString.append(String.valueOf(record.getAsDouble("secondaryCriticalValue"))); //$NON-NLS-1$
				}
			}else{
				criticalValueString.append(valueToBeDefined);
			}
			criticalValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");
		}

		Map<String, String> thresholdStringValues = new HashMap<String, String>(2);
		thresholdStringValues.put("majorValueString", majorValueString.toString());
		thresholdStringValues.put("criticalValueString", criticalValueString.toString());
		return thresholdStringValues;
	}


	private RecordDef geGridValuetRecorDefGridValue(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new FloatFieldDef("majorValue"), //$NON-NLS-1$
						new FloatFieldDef("criticalValue"), //$NON-NLS-1$
						new FloatFieldDef("secondaryMajorValue"), //$NON-NLS-1$
						new FloatFieldDef("secondaryCriticalValue"), //$NON-NLS-1$
						new IntegerFieldDef("samplingPeriod"), //$NON-NLS-1$
						new StringFieldDef("typeCheck"), //$NON-NLS-1$
						new StringFieldDef("thresholdType"), //$NON-NLS-1$
						new DateFieldDef("from"), //$NON-NLS-1$
						new DateFieldDef("to"), //$NON-NLS-1$
						new BooleanFieldDef("monday"), //$NON-NLS-1$
						new BooleanFieldDef("tuesday"), //$NON-NLS-1$
						new BooleanFieldDef("wednesday"), //$NON-NLS-1$
						new BooleanFieldDef("thursday"), //$NON-NLS-1$
						new BooleanFieldDef("friday"), //$NON-NLS-1$
						new BooleanFieldDef("thursday"), //$NON-NLS-1$
						new BooleanFieldDef("saturday"), //$NON-NLS-1$
						new BooleanFieldDef("sunday"), //$NON-NLS-1$
						new BooleanFieldDef("suspended"),
						new StringFieldDef("status")
				}  
		); 
		return recordDef;
	}

	private Store getSearchCLIStore() {
		ConnectionConfig connectionConfig = new ConnectionConfig();
		connectionConfig.setTimeout(IUtilityConstants.TIME_WAIT_60_Sec);
		connectionConfig.setUrl(IActionPathConstants.SEARCH_CLI_403FORB_REG_ACTION_PATH);
		HttpProxy httpProxy = new HttpProxy(new Connection(connectionConfig));
		//		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.SEARCH_CLI_403FORB_REG_ACTION_PATH);       
		RecordDef dataRecordDef = getSearchCLIRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store st = new Store(httpProxy,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					 String nodeNameField = record.getAsString("nodeNameField");
					 String requestNameField = record.getAsString("requestNameField");
					String nameJob = record.getAsString("nameJob");
					Integer processes = record.getAsInteger("processes");
					String file = record.getAsString("file");
					Boolean reachedMaxNumberOfProcess = record.getAsBoolean("reachedMaxNumberOfProcess");
					final String waitExecution = record.getAsString("waitExecution");
					if (reachedMaxNumberOfProcess) {
						MessageBox.alert("Warning",iUtility.Maximum_number_of_processes_reached()+": "+processes+". "+iUtility.Please_try_again_later()+".");
					}
					else if(waitExecution == null || waitExecution.equals("")){
//						getPanelParent().refreshData();
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else{
//						getPanelParent().refreshData();
						if(waitExecution.contains(("No Results!"))){
							MessageBox.alert("Warning",iUtility.CLI_not_Found());
						}else{

							final Window window = new Window();  
							window.setClosable(true);  
							window.setLayout(new FitLayout());
							window.setWidth(500);  
							window.setHeight(300);
							window.setPlain(true);  
							window.setMaximizable(true);  
							window.setResizable(true);  
							window.setIconCls("automatism-icon"); //(\public\images.css)

							window.setModal(false);  
							window.setLayout(new FitLayout());
							window.setCloseAction(Window.CLOSE);
							window.setTitle(iUtility.Execution_Job()+" "+nodeNameField+" "+requestNameField);

							Panel rowpanel = new Panel();
							rowpanel.setLayout(new RowLayout());
							Panel panel1 = new Panel();
							panel1.setLayout(new FitLayout());
							panel1.setHeight(15);
							panel1.setHtml("<b>"+iUtility.Output_execution()+":</b>");
							rowpanel.add(panel1);


							Panel panel2=new Panel();
							panel2.setFrame(true);
							panel2.setLayout(new FitLayout());
							panel2.setAutoScroll(true);
							panel2.setHtml(waitExecution);
							rowpanel.add(panel2);

							window.add(rowpanel);
							window.show();

						}
					}

				}

			}

		});

		return st;
	}
	private RecordDef getSearchCLIRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new IntegerFieldDef("processes"),
						new StringFieldDef("file"),
						new StringFieldDef("waitExecution"),
						new StringFieldDef("nameJob"),
						new StringFieldDef("nodeNameField"),
						new StringFieldDef("requestNameField"),
						new BooleanFieldDef("reachedMaxNumberOfProcess"),
						new BooleanFieldDef("numberNoCheck")
				}  
		); 
		return recordDef;
	}
	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[2];
		params [0] = new UrlParam("requestName", requestName);
		params [1] = new UrlParam("nodeName",nodeName);

		return params;
	}

}
