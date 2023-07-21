package it.icarcnr.rainbow.client;

import it.icarcnr.rainbow.client.login.LoginPanel;
import it.icarcnr.rainbow.client.login.PasswordChangePanel;
import it.icarcnr.rainbow.client.main.RainbowMain;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.WindowListenerAdapter;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

public class RainbowClient implements EntryPoint {
	static Ii18nGlobalConstants myConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	private static final String CHECK_LOGGED_ACTION_PATH = "../login/checkLoggedUser.do"; //$NON-NLS-1$
	
	private LoginPanel loginPanel;

	protected Viewport viewport=null;

	public void onModuleLoad() {
		checkLogged();
	}

	public void showLogin() {
		
		Panel mainPanel = new Panel();
		mainPanel.setLayout(new FitLayout());
		mainPanel.setBorder(false);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
		verticalPanel.setVerticalAlignment( VerticalPanel.ALIGN_MIDDLE );
		verticalPanel.setBorderWidth(0);
		
//		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
//		centerLayoutData.setMargins(new Margins(1, 0, 1, 1));
//		centerLayoutData.setMinSize(500);
//		centerLayoutData.setMaxSize(500);
		
		loginPanel = new LoginPanel();
		loginPanel.init();
		
		Panel rowpanel=new Panel();
		rowpanel.setBorder(false);
		rowpanel.setWidth(500);
		rowpanel.setLayout(new RowLayout());
		rowpanel.setBaseCls("grey-panel-body"); //$NON-NLS-1$
		Panel headPanel = new Panel();
		headPanel.setBorder(false);
		headPanel.setHeight(277);
		headPanel.setHtml("<img src='images/custom/icar.png' border='0' width='500' height='277' />"); //$NON-NLS-1$
		rowpanel.add(headPanel);	
		RowLayoutData layoutLogin = new RowLayoutData(100);
		rowpanel.add(loginPanel,layoutLogin);
		Panel bottomPanel = new Panel();
		bottomPanel.setHeight(66);
		bottomPanel.setBorder(false);
		bottomPanel.setHtml("<img src='images/custom/bottomlogin.png' border='0' width='500' height='40' usemap='#MapBottom' /><map name='MapBottom' id='MapBottom'><area shape='rect' coords='31,19,139,39' href='https://192.168.4.56/ManagementConsole' alt='voipcontrol' target='_blank' /></map>"); //$NON-NLS-1$
		rowpanel.add(bottomPanel);
		verticalPanel.add(rowpanel);
		mainPanel.add(verticalPanel);

		loginPanel.addFormListener(new FormListenerAdapter(){

			public void onActionComplete(Form form, int httpStatus,
					String responseText) {
				Store store = getSubmitResponseStore();
				store.loadJsonData(responseText, true);
				Record [] records = store.getRecords();
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < records.length; i++) {
					Record r = records[i];
					if(i>0){
						message.append(r.getAsString("\n")); //$NON-NLS-1$
					}
					message.append(r.getAsString("msg")); //$NON-NLS-1$
				}
				if(message.length()>0){
					MessageBox.alert(myConstants.rainbowClient_Warning(),message.toString(), new AlertCallback() { //$NON-NLS-1$
						@Override
						public void execute() {
							launchApplication();
						}
					});		
				}else{
					launchApplication();
				}
		
			}

			public void onActionFailed(Form form, int httpStatus,
					String responseText) {				
				Store store = getSubmitResponseStore();
				store.loadJsonData(responseText, true);
				Record [] records = store.getRecords();
				if(records!=null && records.length>0){
					Record record = records[0];
					String id = record.getAsString("id"); //$NON-NLS-1$
					if("firstAccess".equals(id) || "expiredPassword".equals(id) ){ //$NON-NLS-1$ //$NON-NLS-2$
						final Window window = new Window();  
						window.setTitle(record.getAsString("msg"));   //$NON-NLS-1$
						window.setClosable(true);  
						window.setWidth(500);  
						window.setHeight(300);  
						window.setPlain(true);  
						window.setLayout(new FitLayout());
						window.setCloseAction(Window.CLOSE);
						PasswordChangePanel passwordChangePanel = new PasswordChangePanel();
						passwordChangePanel.setWindowParent(window);
						passwordChangePanel.init();

						window.add(passwordChangePanel);
						window.addListener(new WindowListenerAdapter(){
							@Override
							public void onClose(Panel panel) {
								loginPanel.resetPassword();
							}
						});
						window.show();
					}else{
						final String msg = record.getAsString("msg"); //$NON-NLS-1$
		                MessageBox.show(new MessageBoxConfig() {  
		                    {  
		                        setTitle(myConstants.rainbowClient_Warning());   //$NON-NLS-1$
//		                        setIconCls(MessageBox.WARNING);
		                        setMsg(msg);  
		                        setMinWidth(150);
		                        setButtons(MessageBox.OK);   
		                    }  
		                }); 
//						MessageBox.alert("Warning",msg);
						loginPanel.resetPassword();
					}
				}
			}
		});
		loginPanel.addButtonlistener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				loginPanel.submit();				
			}

		});

		viewport = new Viewport(mainPanel);

	}

	protected void checkLogged() {
		Store st = getCheckLoggedStore();
		st.load();	
	}



	protected void launchApplication() {
		RainbowMain rainbowMain = new RainbowMain();
		rainbowMain .init();
		viewport = new Viewport(rainbowMain);
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}



	private Store getCheckLoggedStore() {
		HttpProxy dataProxyLoad = new HttpProxy(CHECK_LOGGED_ACTION_PATH);       
		RecordDef dataRecordDef = getCheckLoggedRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length > 0){
					checkLoggedAction(records[0]);
				}
			}
		});
		return st;
	}

	private RecordDef getCheckLoggedRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("alive") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}

	private void checkLoggedAction(Record record) {
		boolean isAlive = record.getAsBoolean("alive"); //$NON-NLS-1$
		if (isAlive){
			launchApplication();
		}else{
			showLogin();
		}
	}

	/**
	 * @return
	 */
	private Store getSubmitResponseStore() {
		RecordDef errorRecordDef = new RecordDef(new FieldDef[]{  
				new StringFieldDef("id"),   //$NON-NLS-1$
				new StringFieldDef("msg")   //$NON-NLS-1$
		});
		JsonReader errorReader = new JsonReader("errors",errorRecordDef); //$NON-NLS-1$
		errorReader.setSuccessProperty("success"); //$NON-NLS-1$
		Store store = new Store(errorReader);
		return store;
	}

}
