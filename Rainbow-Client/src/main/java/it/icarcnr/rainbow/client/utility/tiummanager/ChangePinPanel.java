package it.icarcnr.rainbow.client.utility.tiummanager;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IUtilityConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class ChangePinPanel extends BasePanel {
	
	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	TextField pinValue = new TextField(iUtility.Pin(), "", 80);
	private String telNumber;
	private String nameJob;
	final FormPanel formPanel = new FormPanel(); 
	Panel panel1 = new Panel();
	Panel panel2 = new Panel();

	
	@Override
	public void init() {
	 
		telNumber=getParameters().get("telNumber").isString().stringValue();
		nameJob=getParameters().get("nameJob").isString().stringValue();
        formPanel.setFrame(true);  
        formPanel.setTitle(iUtility.Insert_New_Pin());  
   
        formPanel.setLabelWidth(100);    
       
        pinValue.setAllowBlank(false);
        pinValue.setMaxLength(4);      // 4 digit
        pinValue.setRegex("[0-9]{4}"); // only numberS
        pinValue.setPassword(true);
        pinValue.setInvalidText(iUtility.TiUMManager_Insert_valid_number());
        formPanel.add(pinValue,new AnchorLayoutData("100%"));  
  
        Button confirmButton = new Button(iUtility.Confirm());  
        formPanel.addButton(confirmButton);
        confirmButton.addListener(new ButtonListenerAdapter() {
			@Override
			public void onClick(Button button, EventObject e) {
				if (validateFields()) {
					MessageBox.confirm(iUtility.Confirm_Change_Pin(), iUtility.Are_You_Sure_Change_Pin(), 
							new MessageBox.ConfirmCallback() {
						@Override
						public void execute(String btnID) {
							if (btnID.equals(IUtilityConstants.YES)){
								ChangePinPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
								Store stChangePin = getStoreChangePin();
								stChangePin.load(getParams());
							}
						}
					});
				}
			}
        });
        this.setLayout(new FitLayout());
		this.add(formPanel);

	}
	
	
	private boolean validateFields() {
		boolean isValid =  pinValue.isValid();
		if(!isValid){
			MessageBox.alert(iUtility.TiUMManager_Warning(),iUtility.TiUMManager_Insert_valid_number()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return isValid;
	}

	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[3];
		params [0] = new UrlParam("telNumber", telNumber);
		params [1] = new UrlParam("pinValue", pinValue.getValueAsString());
		params [2] = new UrlParam("nameJob", nameJob);
		return params;
	}
	
	private Store getStoreChangePin() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.TIUMCHANGEPIN_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDefChangePin();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store stDefaultPin = new Store(dataProxyLoad,readerLoad);
		stDefaultPin.setRemoteSort(true);
		stDefaultPin.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				ChangePinPanel.this.getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}
			
			public void onLoad(Store store, Record[] records) {
		        if (records!=null && records.length > 0 ) {
		        	Record record = records[0];
		        	String waitExecution = record.getAsString("waitExecution");
		        	if(waitExecution == null || waitExecution.equals("")){
		        		getPanelParent().refreshData();
		        		MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
		        	}
		        	else
		        	{  PageBus.publish(ISubjectToPublishTheMessageTo.UTILITY_AREA, waitExecution);
		        	   getWindowParent().close();	
		        	}
		        }
			}
		});
	 return stDefaultPin;	
	}		
	
	private RecordDef getRecorDefChangePin() {
		RecordDef recorDefChangePin = new RecordDef(
				new FieldDef[] {
					new StringFieldDef("telNumber"),
					new StringFieldDef("waitExecution"),
					new StringFieldDef("pinValue")
				}
		);
		return recorDefChangePin;
	}
	
}
