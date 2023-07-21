package it.icarcnr.rainbow.client.utility.tiummanager;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IUtilityConstants;
import it.icarcnr.rainbow.client.util.constants.IUtilityMatchingConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
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
import com.gwtext.client.pagebus.SubscriptionCallback;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Container;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;


public class TiUMManagerPanel extends BasePanel{

	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	private String outputExecution;
	private String telNumber;
	private String nameJob;
	private Boolean numberNoCheck;
	String notifyValue;

	ScrollPanel scrollPanel2;
	Panel panel2;
	Panel panel3;

	Button enableButton;
	Button disableButton;
	Button createButton;
	Button setButton;
	Button changeButton;
	Button destroyButton;
	Button deleteButton;

	boolean fg=false;

	@Override
	public void init() {

		panel2 = new Panel();
		panel3 = new Panel();

		outputExecution=getParameters().get("waitExecution").isString().stringValue();
		telNumber=getParameters().get("telNumber").isString().stringValue();
		numberNoCheck=getParameters().get("numberNoCheck").isBoolean().booleanValue();
		nameJob=getParameters().get("nameJob").isString().stringValue();

		PageBus.subscribe(ISubjectToPublishTheMessageTo.UTILITY_AREA, new SubscriptionCallback() {		
			@Override
			public void execute(String subject, Object message) {
				String sResult = message.toString();
				outputExecution=outputExecution+sResult;
				disableButtons(sResult);
				refreshOutput(outputExecution);
			}

		});

		this.setLayoutData(new FitLayout()); 

		Panel panel1 = new Panel();
		panel1.setLayout(new FitLayout());
		panel1.setHeight(15);
		panel1.setHtml(iUtility.Output_execution());
		this.add(panel1);

		panel2.setFrame(true);
		panel2.setLayout(new FitLayout());
		//     panel2.setStyle("color: black font-size: 6pt");

		this.addListener(new PanelListenerAdapter(){
			@Override
			public void onAfterLayout(Container self) {
				scrollPanel2.scrollToBottom();
			}
		});
		panel2.setAutoScroll(false);

		// Setting First Txt *************************
		panel2.setHtml(outputExecution);
		panel3.setFrame(true);
		panel3.setLayout(new FitLayout());

		panel3.setButtonAlign(Position.CENTER);
		createButton = new Button (iUtility.Create_Profile()); //$NON-NLS-1$
		panel3.addButton(createButton);

		createButton.addListener(new ButtonListenerAdapter() {
			public void onClick(final Button button, EventObject e) {

				MessageBox.confirm(iUtility.Confirm_Create_Profile(), iUtility.Are_You_Sure_Create_Profile(), 
						new MessageBox.ConfirmCallback() {
					@Override
					public void execute(String btnID) {
						if (btnID.equals(IUtilityConstants.YES)){
							TiUMManagerPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
							Store stCreateProfile = getStoreCreateProfile();
							stCreateProfile.load(getParams());
						}
					}
				});
			}  
		});  

		panel3.setButtonAlign(Position.CENTER);
		setButton = new Button (iUtility.Set_Default_Pin());
		panel3.addButton(setButton);

		setButton.addListener(new ButtonListenerAdapter() {
			@Override
			public void onClick(Button button, EventObject e) {
				MessageBox.confirm(iUtility.Confirm_Set_Default_Pin(), iUtility.Are_You_Sure_set_Default_Pin(), 
						new MessageBox.ConfirmCallback() {
					@Override
					public void execute(String btnID) {
						if (btnID.equals(IUtilityConstants.YES)){
							TiUMManagerPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
							Store stDefaultPin = getStoreDefPin();
							stDefaultPin.load(getParams());
						}
					}
				});
			}
		});



		//   Enable and disable Notify
		if (outputExecution.indexOf(IUtilityMatchingConstants.NotifyYes) != -1){
			enableButton = new Button (iUtility.Disable_Notify()); //$NON-NLS-1$
			notifyValue="no";
		}
		else
			if (outputExecution.indexOf(IUtilityMatchingConstants.NotifyNo) != -1){
				enableButton = new Button (iUtility.Enable_Notify());
				notifyValue="yes";
			} 		
		panel3.addButton(enableButton);

		enableButton.addListener(new ButtonListenerAdapter() {
			public void onClick(final Button button, EventObject e) {
				MessageBox.confirm(iUtility.Confirm_Enable_Notify(), iUtility.Are_You_Sure_Enable_Notify(), 
						new MessageBox.ConfirmCallback() {
					@Override
					public void execute(String btnID) {
						if (btnID.equals(IUtilityConstants.YES)){
							TiUMManagerPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
							Store stEnableNotify = getStoreEnableNotify();
							stEnableNotify.load(getParams());
							if (TiUMManagerPanel.this.notifyValue.equals(IUtilityConstants.NO)){
								enableButton.setText(iUtility.Enable_Notify());
								TiUMManagerPanel.this.notifyValue=IUtilityConstants.YES;
							}else
								if (TiUMManagerPanel.this.notifyValue.equals(IUtilityConstants.YES)){
									enableButton.setText(iUtility.Disable_Notify());
									TiUMManagerPanel.this.notifyValue=IUtilityConstants.NO;
								}
						}
					}
				});
			}  
		});  // end enable and disable notify



		panel3.setButtonAlign(Position.CENTER);
		changeButton = new Button(iUtility.Change_Pin());
		panel3.addButton(changeButton);

		changeButton.addListener(new ButtonListenerAdapter() {
			@Override
			public void onClick(Button button, EventObject e) {
				final Window window = new Window();  
				window.setClosable(true);  
				window.setLayout(new FitLayout());
				window.setWidth(350);  
				window.setHeight(140);
				window.setPlain(true);  

				window.setMaximizable(true);  
				window.setResizable(true);  
				window.setIconCls("automatism-icon"); //(\public\images.css)

				window.setModal(false);  
				window.setLayout(new FitLayout());
				window.setCloseAction(Window.CLOSE);
				window.setTitle(iUtility.Change_Pin());

				ChangePinPanel changePinPanel = new ChangePinPanel();
				changePinPanel.setWindowParent(window);
				changePinPanel.setPanelParent(TiUMManagerPanel.this);
				changePinPanel.addParameter("telNumber", new JSONString(telNumber));
				changePinPanel.addParameter("nameJob", new JSONString(nameJob));
				changePinPanel.setPanelParent(TiUMManagerPanel.this);
				changePinPanel.init();
				window.add(changePinPanel);
				window.show();
			}				
		});

		panel3.setButtonAlign(Position.CENTER);
		destroyButton = new Button(iUtility.Delete_Button());
		panel3.addButton(destroyButton);

		destroyButton.addListener(new ButtonListenerAdapter() {
			@Override
			public void onClick(Button button,EventObject e) {
				MessageBox.confirm(iUtility.Confirm_Delete_Profile(), iUtility.Are_You_Sure_Delete_Profile(), new MessageBox.ConfirmCallback() {
					@Override
					public void execute(String btnID) {
						if (btnID.equals(IUtilityConstants.YES)){
							TiUMManagerPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
							Store stDeleteProfile = getStoreDeleteProfile();
							stDeleteProfile.load(getParams());
						}
					}
				});
			}
		});

		panel3.setButtonAlign(Position.CENTER);
		deleteButton = new Button(iUtility.Delete_Create_Button());
		panel3.addButton(deleteButton);

		deleteButton.addListener(new ButtonListenerAdapter() { 
			@Override
			public void onClick(Button button,EventObject e) {
				MessageBox.confirm(iUtility.Confirm_Delete_Create_Profile(),iUtility.Are_You_Sure_Delete_Create_Profile(), 
						new MessageBox.ConfirmCallback() {
					@Override
					public void execute(String btnID) {
						if (btnID.equals(IUtilityConstants.YES)){
							TiUMManagerPanel.this.getEl().mask(iUtility.Loading(), "x-mask-loading");
							Store stDeleteProfile = getStoreDeleteProfile();
							stDeleteProfile.load(getParams());
							fg=true;
						}
					}
				});	
			}	
		});


		this.setLayout(new RowLayout());
		scrollPanel2=new ScrollPanel();
		scrollPanel2.add(panel2);
		this.add(scrollPanel2,new RowLayoutData("80%"));
		this.add(panel3,new RowLayoutData("20%"));

		disableButtons(outputExecution);
	}


	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[3];
		params [0] = new UrlParam("telNumber", telNumber);
		params [1] = new UrlParam("nameJob", nameJob);
		params [2] = new UrlParam("notifyValue", notifyValue);
		return params;
	}


	private Store getStoreEnableNotify() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.TIUMENABLENOTIFY_ACTION_PATH);       
		RecordDef dataRecordDef = getGenericRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store stEnableNotify = new Store(dataProxyLoad,readerLoad);
		stEnableNotify.setRemoteSort(true);
		stEnableNotify.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				TiUMManagerPanel.this.getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}

			public void onLoad(Store store, Record[] records) {	
				TiUMManagerPanel.this.getEl().unmask();
				if (records!=null && records.length > 0 ) {
					Record record = records[0];
					String waitExecution = record.getAsString("waitExecution");
//					disableButtons(waitExecution);
					if(waitExecution == null || waitExecution.equals("")){
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else{ 
						outputExecution = outputExecution + waitExecution;
						refreshOutput(outputExecution);
					}
				}
			}
		});
		return stEnableNotify;	
	}	


	private Store getStoreDefPin() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.TIUMSETDEFAULTPIN_ACTION_PATH);       
		RecordDef dataRecordDef = getGenericRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store stDefaultPin = new Store(dataProxyLoad,readerLoad);
		stDefaultPin.setRemoteSort(true);
		stDefaultPin.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				TiUMManagerPanel.this.getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}

			public void onLoad(Store store, Record[] records) {	
				TiUMManagerPanel.this.getEl().unmask();
				if (records!=null && records.length > 0 ) {
					Record record = records[0];
					String waitExecution = record.getAsString("waitExecution");
					disableButtons(waitExecution);
					if(waitExecution == null || waitExecution.equals("")){
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else{ 
						outputExecution = outputExecution + waitExecution;
						refreshOutput(outputExecution);
					}
				}
			}
		});
		return stDefaultPin;	
	}		

	private RecordDef getGenericRecorDef() { // Recordef Generic, Valid for many button
		RecordDef recorDefSetPin = new RecordDef(
				new FieldDef[] {
						new StringFieldDef("telNumber"),
						new StringFieldDef("waitExecution"),
						new StringFieldDef("notifyValue")
				}
		);
		return recorDefSetPin;
	}

	private Store getStoreCreateProfile() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.TIUMCREATEPROFILE_ACTION_PATH);       
		RecordDef dataRecordDef = getGenericRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store stCreateProfile = new Store(dataProxyLoad,readerLoad);
		stCreateProfile.setRemoteSort(true);
		stCreateProfile.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				TiUMManagerPanel.this.getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}

			public void onLoad(Store store, Record[] records) {
				TiUMManagerPanel.this.getEl().unmask();
				if (records!=null && records.length > 0 ) {
					Record record = records[0];
					String waitExecution = record.getAsString("waitExecution");
					disableButtons(waitExecution);
					if(waitExecution == null || waitExecution.equals("")){
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else{ 
						outputExecution = outputExecution + waitExecution;
						disableButtons(waitExecution);
						refreshOutput(outputExecution);
					}
				}
			}
		});
		return stCreateProfile;	
	}		


	private void disableButtons(String waitExecution) {

		if (waitExecution.indexOf(IUtilityMatchingConstants.Delete_VoiceMail) != -1){
			createButton.enable();
			setButton.disable();
			enableButton.disable();
			changeButton.disable();
			destroyButton.disable();
			deleteButton.disable();
		}
		else
			if (waitExecution.indexOf(IUtilityMatchingConstants.Create_VoiceMail) != -1){
				createButton.disable();
				setButton.disable();
				enableButton.enable();
				changeButton.enable();
				destroyButton.enable();
				deleteButton.enable();
			}
			else
				if (waitExecution.indexOf(IUtilityMatchingConstants.Set_Default_Pin) != -1){
					createButton.disable();
					setButton.disable();
					changeButton.enable();
					destroyButton.enable();
					deleteButton.enable();
				}

				else
					if (waitExecution.indexOf(IUtilityMatchingConstants.Change_Pin) != -1){
						createButton.disable();
						setButton.enable();
						changeButton.enable();
						destroyButton.enable();
						deleteButton.enable();
					}
					else
						if (numberNoCheck){
							createButton.enable();
							setButton.disable();
							changeButton.disable();
							destroyButton.disable();
							deleteButton.disable();
						}
						else if(!numberNoCheck){ // Numero presente
							createButton.disable();
							if ( waitExecution.indexOf(IUtilityMatchingConstants.Set_Default_Pin) != -1){
								setButton.disable();
							}else
							{setButton.enable();
							}
							changeButton.enable();
							destroyButton.enable();
							deleteButton.enable();
						}
	}


	private Store getStoreDeleteProfile() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.TIUMDELETEPROFILE_ACTION_PATH);       
		RecordDef dataRecordDef = getGenericRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store stDeleteProfile = new Store(dataProxyLoad,readerLoad);
		stDeleteProfile.setRemoteSort(true);
		stDeleteProfile.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				TiUMManagerPanel.this.getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}

			public void onLoad(Store store, Record[] records) {
				TiUMManagerPanel.this.getEl().unmask();
				if (records!=null && records.length > 0 ) {
					Record record = records[0];
					String waitExecution = record.getAsString("waitExecution");
					if(waitExecution == null || waitExecution.equals("")){
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else {
						panel2.setHtml(outputExecution = outputExecution + waitExecution);
						disableButtons(waitExecution);
						scrollPanel2.scrollToBottom();
						if (fg){
							Store stCreateProfile = getStoreCreateProfile();
							stCreateProfile.load(getParams());
							fg=false;
						}else{
							getPanelParent().getPanelParent().refreshData();
						}
					}
				}
			}
		});
		return stDeleteProfile;	
	}		

	private void refreshOutput(String htmlText) {
		panel2.setHtml(htmlText);
		scrollPanel2.scrollToBottom();
		getPanelParent().getPanelParent().refreshData();
	} 

}
