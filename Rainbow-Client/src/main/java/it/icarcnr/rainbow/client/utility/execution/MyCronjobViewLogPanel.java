package it.icarcnr.rainbow.client.utility.execution;

import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

public class MyCronjobViewLogPanel extends BasePanel {

	static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	private Record record=null;
	private Store st=getStore();
	private UtilityOutputExecutionPanel utilityOutputExecutionPanel;
	public ToolbarButton timerOn;
	public ToolbarButton timerOff;
	private Integer index=0;

	@Override
	public void init() {

		setLayout(new RowLayout());
		if (record==null) {
			setHtml("<center>"+iUtility.MyCronjobViewLogPanel_Load_Window_Fail()+"</center>"); //$NON-NLS-1$
		}
		else {
			boolean withTimer = false;
			Panel headPanel = new Panel();

			headPanel.setBorder(false);
			headPanel.setStyle("border-bottom: 1px solid #666666"); //$NON-NLS-1$
			headPanel.setLayout(new FitLayout());
			RowLayoutData rowLayoutDataHeadPanel;
			RowLayoutData rowLayoutDataUtilityExecutionOutputPanel;
			if (record.getAsString("status").equals("running")) { //$NON-NLS-1$ //$NON-NLS-2$
				rowLayoutDataHeadPanel = new RowLayoutData("15%"); //$NON-NLS-1$
				rowLayoutDataUtilityExecutionOutputPanel = new RowLayoutData("85%"); //$NON-NLS-1$
				withTimer=true;
				Toolbar toolbar = new Toolbar();
				ToolbarTextItem text = new ToolbarTextItem(iUtility.MyCronjobViewLogPanel_Automatic_Refresh()+": "); //$NON-NLS-1$
				toolbar.addFill();
				toolbar.addItem(text);
				timerOn = new ToolbarButton();
				timerOn.setText(iUtility.MyCronjobViewLogPanel_On()); //$NON-NLS-1$
				timerOn.setToggleGroup("timerbuttons");   //$NON-NLS-1$
				timerOn.setTooltip(iUtility.MyCronjobViewLogPanel_Refresh_ON()); //$NON-NLS-1$
				timerOn.toggle(true);
				timerOn.addListener(new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						if (button.isPressed()) {
							scheduleRefreshData(7000);
						}
						else {
							timerOn.toggle(true);
						}
					}
				});
				toolbar.addButton(timerOn);
				timerOff = new ToolbarButton();
				timerOff.setText(iUtility.MyCronjobViewLogPanel_Off()); //$NON-NLS-1$
				timerOff.setToggleGroup("timerbuttons");   //$NON-NLS-1$
				timerOff.setTooltip(iUtility.MyCronjobViewLogPanel_Refresh_OFF());   //$NON-NLS-1$
				timerOff.addListener(new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						if (button.isPressed()) {
							unscheduleRefreshData();
						}
						else {
							timerOff.toggle(true);
						}
					}
				});
				toolbar.addButton(timerOff);
				headPanel.setTopToolbar(toolbar);
				scheduleRefreshData(7000);
			}
			else {
				rowLayoutDataHeadPanel = new RowLayoutData("15%"); //$NON-NLS-1$
				rowLayoutDataUtilityExecutionOutputPanel = new RowLayoutData("85%"); //$NON-NLS-1$
			}


			StringBuilder headHTML = new StringBuilder();
			headHTML.append("<b>");
			headHTML.append(iUtility.MyCronjobViewLogPanel_Scheduled());
			headHTML.append(": </b>");
			headHTML.append(DateTimeFormat.getFormat(IDateFormatUtil.i18nDateTimeFormat).format(record.getAsDate("startExecution")));
			if (record.getAsDate("endExecution")!=null) {
				headHTML.append("<br>");
				headHTML.append("<b>");
				headHTML.append(iUtility.MyCronjobViewLogPanel_End_Execution());
				headHTML.append(": </b>");
				headHTML.append(DateTimeFormat.getFormat(IDateFormatUtil.i18nDateTimeFormat).format(record.getAsDate("endExecution")));
			}

			headHTML.append("<br>");
			headHTML.append("<b>");
			headHTML.append(iUtility.MyCronjobViewLogPanel_Utility());
			headHTML.append(": </b>");
			headHTML.append(record.getAsString("utilityDescription")); 

			headPanel.setHtml(headHTML.toString());
			add(headPanel, rowLayoutDataHeadPanel);

			utilityOutputExecutionPanel=new UtilityOutputExecutionPanel();
			utilityOutputExecutionPanel.init();

			if (!withTimer) {
				utilityOutputExecutionPanel.setOutput(record);
			}else {
				utilityOutputExecutionPanel.setHtml(iUtility.MyCronjobViewLogPanel_Please_wait_Ensure_that_Button_On_are_pressed());
			}
			add(utilityOutputExecutionPanel, rowLayoutDataUtilityExecutionOutputPanel);
		}	
	}

	public void setRecord(Record rec) {
		record=rec;
	}

	public void refreshData() {
		if (record != null) {
			st.load(getParams());
		}
	}

	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.MYCRONJOB_VIEWLOG_TIMER_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoad(Store store, Record[] records) {
				utilityOutputExecutionPanel.setHtml(records[0].getAsString("outputrefresh"));
				index=records[0].getAsInteger("index");
			}
		});
		return st;
	}


	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{
						new StringFieldDef("outputrefresh"),
						new IntegerFieldDef("index")
				}  
		); 
		return recordDef;
	}

	private UrlParam[] getParams() {
		UrlParam[] urlp = new UrlParam[2];
		urlp[0] = new UrlParam("output",record.getAsString("output"));
		urlp[1] = new UrlParam("index",index);
		return urlp;
	}



}
