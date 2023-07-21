package it.icarcnr.rainbow.client.main;


import it.icarcnr.rainbow.client.dashboard.WelcomeView;
import it.icarcnr.rainbow.client.login.PasswordChangePanel;
import it.icarcnr.rainbow.client.services.StatusChangeHistory;
import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.basecomponents.FakeIFrame;
import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IServiceConstants;
import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.main.IMain;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Cookies;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Ext;
import com.gwtext.client.core.GenericConfig;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.pagebus.SubscriptionCallback;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Container;
import com.gwtext.client.widgets.DefaultsHandler;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.WindowMgr;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.TabPanelListenerAdapter;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.Item;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.menu.MenuItem;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;
import com.gwtext.client.widgets.tree.TreePanel;

public class RainbowMain extends ScreenPanel {

	private static final String LOAD_USER_INFO_ACTION_PATH = "../login/userLoginInfo.do"; 
	private static final String LOGOUT_ACTION_PATH = "../login/logout.do"; 
	private static final String SUMMARY_SERVICE_STATUS_ACTION_PATH = "../services/summaryServiceStatus.do"; 
	private static final String LAST_CHANGE_STATUS_ACTION_PATH = "../services/lastChangeStatus.do"; 
	private Panel mainMenuPanel;
	private TabPanel centerPanel;
	private ScreenManager screenManager;
	
	private Menu menu;
	private  Item manualIpccItem;
	Boolean menuManualPermission;

	private ToolbarTextItem userLogin;
	private Store userInfoStore;
	private ToolbarTextItem criticalValueText;
	private ToolbarTextItem majorValueText;
	private ToolbarTextItem normalValueText;
	private ToolbarTextItem suspendedValueText;
	private ToolbarButton changeStatusButton;
	
	
	
	private ToolTip criticalToolTip;
	private ToolTip majorToolTip;
	private ToolTip normalToolTip;
	private ToolTip suspendedToolTip;

	StringBuilder labelCritical;
	StringBuilder labelMajor;
	StringBuilder labelNormal;
	StringBuilder labelSuspended;
	
	private Date currentDate;
	private Date startDetectionDate;
		
	private Store summaryServiceStatusStore;
	private Store lastChangeStatusStore;
	
	private Store checkPermissionManualIpccStore;
	
	
	private boolean refreshOnTabChange = true;
	private boolean changeStatus = false;
	

	private static IMain iMain = (IMain)GWT.create(IMain.class);


	public boolean isRefreshOnTabChange() {
		return refreshOnTabChange;
	}

	public void setRefreshOnTabChange(boolean refreshOnTabChange) {
		this.refreshOnTabChange = refreshOnTabChange;
	}

	private Panel createWestPanel() {
		mainMenuPanel = new Panel();
		mainMenuPanel.setId("side-nav"); 
		mainMenuPanel.setTitle(iMain.rainbowMain_Service_Management()); 
		mainMenuPanel.setLayout(new FitLayout());
		mainMenuPanel.setWidth(200);
		mainMenuPanel.setCollapsible(true);

		final TreePanel treePanel = screenManager.getTreeNav();

		mainMenuPanel.add(treePanel);
		
		addListener(new TabPanelListenerAdapter(){
			@Override
			public void onAfterLayout(Container self) {
				screenManager.loadMenu();
				super.onAfterLayout(self);
			}
		});

		return mainMenuPanel;
	}


	private void showMenu(final Panel tab, EventObject e) {
		if (menu == null) {
			menu = new Menu();
			Item close = new Item(iMain.rainbowMain_Close_Tab()); 
			close.setId("close-tab-item"); 
			close.addListener(new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {
					centerPanel.remove(centerPanel.getActiveTab());
				}
			});
			menu.addItem(close);

			Item closeOthers = new Item(iMain.rainbowMain_Close_Other_Tabs()); 
			closeOthers.setId("close-others-item"); 
			closeOthers.addListener(new BaseItemListenerAdapter() {
				public void onClick(BaseItem item, EventObject e) {
					Component[] items = centerPanel.getItems();
					for (int i = 0; i < items.length; i++) {
						Panel panel = (Panel) items[i];
						if (panel.isClosable() && !panel.getId().equals(centerPanel.getActiveTab().getId())) {
							centerPanel.remove(panel);
						}
					}
				}
			});
			menu.addItem(closeOthers);
		}

		BaseItem close = menu.getItem("close-tab-item"); 
		if (!centerPanel.getActiveTab().isClosable()) {
			close.disable();
		} else {
			close.enable();
		}

		BaseItem closeOthers = menu.getItem("close-others-item"); 
		if (centerPanel.getItems().length == 1) {
			closeOthers.disable();
		} else {
			closeOthers.enable();
		}
		menu.showAt(e.getXY());
	}
	
	@Override
    public void init() {
		
		PageBus.subscribe(ISubjectToPublishTheMessageTo.MAIN_MENU, new SubscriptionCallback() {		
			@Override
			public void execute(String subject, Object message) {
				collapseMainMenu(message);
			}
		});

		//create the main panel and assign it a BorderLayout
		setLayout(new BorderLayout());
				
		
		Toolbar topToolbar = getMainTopToolbar();
		Toolbar bottomToolbar = getMainBottomToolbar();
		setTopToolbar(topToolbar);
		setBottomToolbar(bottomToolbar);

		Panel centerPanelWrappper = new Panel();
		centerPanelWrappper.setLayout(new FitLayout());
		centerPanelWrappper.setBorder(false);
		centerPanelWrappper.setBodyBorder(false);

		centerPanel = new TabPanel();
		centerPanel.setBodyBorder(false);
		centerPanel.setEnableTabScroll(true);
		centerPanel.setAutoScroll(true);
		centerPanel.setAutoDestroy(false);
		centerPanel.setActiveTab(0);

		//required for flash charts
		centerPanel.setDefaults(new DefaultsHandler() {
			public void apply(Component component) {
				if(!component.isRendered() && !Ext.isIE()){
					component.setHideMode("display"); 
					GenericConfig config = new GenericConfig();
					config.setProperty("position", "absolute");  //$NON-NLS-2$
					component.setStyle(config);
				}
			}
		});

		//hide the panel when the tab is closed
		centerPanel.addListener(new TabPanelListenerAdapter() {
			@Override
			public boolean doBeforeTabChange(TabPanel source, Panel newPanel, Panel oldPanel) {
				WindowMgr.hideAll();
				return true;
			}
			@Override
			public void onRemove(Container self, Component component) {
				component.hide();
			}
			@Override
			public void onContextMenu(TabPanel source, Panel tab, EventObject e) {
				showMenu(tab, e);
			}
			@Override
			public void onTabChange(TabPanel source, Panel tab) {
				if(refreshOnTabChange){
					refreshData();
				}else{
					refreshOnTabChange = true;
				}

			}
		});

		centerPanel.setLayoutOnTabChange(true);
		centerPanel.setTitle(iMain.rainbowMain_Main_Content()); 

		screenManager = new ScreenManager(this,centerPanel);

		//setup the west regions layout properties
		BorderLayoutData westLayoutData = new BorderLayoutData(RegionPosition.WEST);
		westLayoutData.setMargins(new Margins(5, 5, 0, 5));
		westLayoutData.setCMargins(new Margins(5, 5, 5, 5));
		westLayoutData.setSplit(true);

		//create the west panel and add it to the main panel applying the west region layout properties
		Panel westPanel = createWestPanel();
		add(westPanel, westLayoutData);

		final WelcomeView welcomeView = new WelcomeView();
		welcomeView.init();

		BorderLayoutData centerPanelLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerPanelLayoutData.setMargins(new Margins(5, 0, 5, 5));
		centerPanel.add(welcomeView, centerPanelLayoutData);
		centerPanelWrappper.add(centerPanel);

		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));
		add(centerPanelWrappper, centerLayoutData);

		scheduleRefreshData();

		doLayout(true);
	}
	
	protected void collapseMainMenu(Object message) {
		if(message instanceof Boolean){
			mainMenuPanel.setCollapsed(((Boolean)message).booleanValue());
		}
	}

	private void getPermissionManualIpcc(){
		checkPermissionManualIpccStore = getCheckPermissionManualIpccStore();
		checkPermissionManualIpccStore.load();
	}
	


	protected void changePassword() {
		final Window window = new Window();  
		window.setTitle(iMain.rainbowMain_Change_Password());   
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
		window.show();
	}

	private void logout() {
		Store st = getLogoutStore();
		st.load();

	}
	private Store getLogoutStore() {
		HttpProxy dataProxyLoad = new HttpProxy(LOGOUT_ACTION_PATH);       
		RecordDef dataRecordDef = getLogoutRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); 
		readerLoad.setTotalProperty("totalCount"); 
		readerLoad.setId("threadid"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record = records[0];
					boolean success = record.getAsBoolean("success"); 
					if(success){
						Cookies.removeCookie("JSESSIONID"); 
						com.google.gwt.user.client.Window.Location.reload();
					}
				}
			}
			@Override
			public void onLoadException(Throwable error) {
				MessageBox.alert(iMain.rainbowMain_Error(), iMain.rainbowMain_Logout_failed());  //$NON-NLS-2$
			}
		});
		return st;
	}

	private RecordDef getLogoutRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("success") 
				}  
		); 
		return recordDef;
	}

	private static void setToolTip(ToolTip tip, String title, StringBuilder content,ToolbarTextItem item){

		tip.setTitle(title);
		tip.setHtml(content.toString());  
		tip.setDismissDelay(15000);  
		tip.setWidth(200);
		tip.setHeight(200);
		//		tip.setTrackMouse(true);  
		tip.applyTo(item.getElement());

	}
	
	private static void updateToolTip(ToolTip tip, StringBuilder content){

		tip.setHtml(content.toString());  
		tip.doLayout(true);

	}

	private void loadUserInfo() {
		userInfoStore = getUserInfoStore();
		userInfoStore.load();
		
	}

	private Store getUserInfoStore() {
		HttpProxy dataProxyLoad = new HttpProxy(LOAD_USER_INFO_ACTION_PATH);       
		RecordDef dataRecordDef = getUserInfoRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); 
		readerLoad.setTotalProperty("totalCount"); 
		readerLoad.setId("threadid"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record = records[0];
					String name = record.getAsString("name"); 
					String surname = record.getAsString("surname"); 
					userLogin.setText(iMain.rainbowMain_User()+" "+name+" "+surname);  //$NON-NLS-2$

				}
			}
		});
		return st;
	}

	private RecordDef getUserInfoRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new StringFieldDef("name"), 
						new StringFieldDef("surname"), 
						new StringFieldDef("username") 
				}  
		); 
		return recordDef;
	}


	/** STORE SUMMARY_SERVICE_STATUS**/
	private Store getSummaryServiceStatusStore() {
		HttpProxy dataProxyLoad = new HttpProxy(SUMMARY_SERVICE_STATUS_ACTION_PATH);       
		RecordDef dataRecordDef = getSummaryServiceStatusRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); 
		readerLoad.setTotalProperty("totalCount"); 
		readerLoad.setId("threadid"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Integer totalCriticalServices=0;
					Integer totalMajorServices=0;
					Integer totalNomalServices=0;
					Integer totalSuspendedServices=0;
					labelCritical = new StringBuilder();
					labelMajor = new StringBuilder();
					labelNormal = new StringBuilder();
					labelSuspended = new StringBuilder();


					for(int i=0;i<records.length;i++){

						Record record = records[i];
						String networkDescr = record.getAsString("networkDescr"); 
						labelSuspended.append(networkDescr+": "); 
						labelNormal.append(networkDescr+": "); 
						labelMajor.append(networkDescr+": "); 
						labelCritical.append(networkDescr+": "); 


						Integer  criticalValue = record.getAsInteger(IServiceConstants.CRITICAL_STATUS); 
						totalCriticalServices+=criticalValue;
						labelCritical.append("<b>"+criticalValue+"</b>");  //$NON-NLS-2$

						Integer  majorValue = record.getAsInteger(IServiceConstants.MAJOR_STATUS); 
						totalMajorServices += majorValue;
						labelMajor.append("<b>"+majorValue+"</b>");  //$NON-NLS-2$

						Integer  normalValue = record.getAsInteger(IServiceConstants.NORMAL_STATUS); 
						totalNomalServices += normalValue;
						labelNormal.append("<b>"+normalValue+"</b>");  //$NON-NLS-2$
						
						Integer  suspendedValue = record.getAsInteger(IServiceConstants.SUSPENDED_STATUS); 
						totalSuspendedServices += suspendedValue;
						labelSuspended.append("<b>"+suspendedValue+"</b>");  //$NON-NLS-2$

						if (i!=records.length) {
							labelSuspended.append("<br>"); 
							labelNormal.append("<br>"); 
							labelMajor.append("<br>"); 
							labelCritical.append("<br>"); 
						}

					}
//					setToolTip(iMain.RainbowMain_Critical(), labelCritical, criticalValueText); 
//					setToolTip(iMain.RainbowMain_Major(), labelMajor,majorValueText ); 
//					setToolTip(iMain.RainbowMain_Normal(), labelNormal, normalValueText); 
//					setToolTip(iMain.RainbowMain_Suspended(), labelSuspended, suspendedValueText); 

					updateToolTip(criticalToolTip, labelCritical);
					updateToolTip(majorToolTip, labelMajor);
					updateToolTip(normalToolTip, labelNormal);
					updateToolTip(suspendedToolTip, labelSuspended);
					
					criticalValueText.setText(getSummarySeviceStatus(totalCriticalServices,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.CRITICAL_STATUS)));
					majorValueText.setText(getSummarySeviceStatus(totalMajorServices,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.MAJOR_STATUS)));
					normalValueText.setText(getSummarySeviceStatus(totalNomalServices,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.NORMAL_STATUS)));
					suspendedValueText.setText(getSummarySeviceStatus(totalSuspendedServices,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.SUSPENDED_STATUS)));

				}
			}
		});
		return st;
	}


	private RecordDef getSummaryServiceStatusRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{ 
						new StringFieldDef("networkDescr"), 
						new IntegerFieldDef(IServiceConstants.CRITICAL_STATUS), 
						new IntegerFieldDef(IServiceConstants.MAJOR_STATUS), 
						new IntegerFieldDef(IServiceConstants.NORMAL_STATUS), 
						new IntegerFieldDef(IServiceConstants.SUSPENDED_STATUS) 
				}  
		); 
		return recordDef;
	}


	@Override
	public void refreshData() {
		refreshSummary();
		Panel activePanel = centerPanel.getActiveTab();
		if(activePanel instanceof BasePanel){
			((BasePanel)activePanel).refreshData();
		}
	}

	public void refreshSummary(){
		summaryServiceStatusStore.load();
		lastChangeStatusStore.load(getParams());
	}

	private String getSummarySeviceStatus(Integer value, String colour){
		String valueString = value!=null?value.toString():""; 
		StringBuilder text = new StringBuilder();
		if(!valueString.equals("0")){
			text.append("<div style=\"" + 
					"width:40px; vertical-align:middle !important; " + 
					"font-size:13px; " + 
					//				"border:1px solid #000000 !important; " +
					//				"font-weight:bold; "+
					"background-color:"+colour+"; height:16px; text-align:center; \">" +  //$NON-NLS-2$
					valueString+
			"</div>"); 
			return text.toString();
		}else{
			text.append("<div style=\"" + 
					"width:40px; vertical-align:middle !important; " + 
					"font-size:13px; " + 
					//				"border:1px solid #000000 !important; " +
					//				"font-weight:bold; "+
					"background-color:"+IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.NORMAL_STATUS)+"; height:16px; text-align:center; \">" +  //$NON-NLS-2$
					valueString+
			"</div>"); 
			return text.toString();
		}
			
	}
	/** -------------------------------------------------------TOOLBAR TOP ---------------------------- ----------**/
	private Toolbar getMainTopToolbar() {
		
		Toolbar toolbar = new Toolbar();
		toolbar.setCls("x-toolbarMain"); 
		getPermissionManualIpcc();

//		String logo = "logo_rainbow.jpg"; 
//		String rainbowLogo = "<img src=\"images/custom/" + logo + "\" align=\"center\"><br/>"; 
//
//		toolbar.addText(rainbowLogo);
				
		String smartStreet = "smartStreet.png"; 
		String smartStreetLogo = "<img src=\"images/custom/" + smartStreet + "\" align=\"center\" ><br/>"; 

		toolbar.addText(smartStreetLogo);
		toolbar.addFill();
			
		userLogin = new ToolbarTextItem(""); 
		toolbar.addSeparator();
		toolbar.addItem(userLogin);
		toolbar.addSeparator();
		
		final ToolbarButton button = new ToolbarButton();  
        button.setText(iMain.rainbowMain_Options());  
        button.setIconCls("options-menu-icon");  
  
        //create the menu we want to assign to the button  
        Menu menu = new Menu();  
  
        Item changePasswordItem = new Item(iMain.rainbowMain_Change_Password());  
        changePasswordItem.setIconCls("change-password-icon");
        changePasswordItem.addListener(new BaseItemListenerAdapter(){
        	
        	@Override
        	public void onClick(BaseItem item, EventObject e) {
        		changePassword();
        	}
        });
        menu.addItem(changePasswordItem);  
        
        /**---------------------------------Menu Manual---------------------------**/
        Menu subMenuManual = new Menu(); 
  
        Item manualRainbowItem = new Item(iMain.rainbowMain_Manual_Rainbow());  
        manualRainbowItem.setIconCls("manual-icon"); 
        manualRainbowItem.addListener(new BaseItemListenerAdapter(){
        	
        	@Override
        	public void onClick(BaseItem item, EventObject e) {
        		FakeIFrame downloadFrame = new FakeIFrame(IActionPathConstants.DOWNLOAD_MANUAL, null);
        	}
        });     
        
        manualIpccItem = new Item(iMain.rainbowMain_Manual_Ipcc());  
        manualIpccItem.setIconCls("manual-icon"); 
        manualIpccItem.addListener(new BaseItemListenerAdapter(){
        	
        	@Override
        	public void onClick(BaseItem item, EventObject e) {
        		FakeIFrame downloadFrame = new FakeIFrame(IActionPathConstants.DOWNLOAD_IPCC_MANUAL, null);
        	}
        });
        
        subMenuManual.addItem(manualRainbowItem); 
        subMenuManual.addItem(manualIpccItem);
      
        
        MenuItem ManualItem = new MenuItem(iMain.rainbowMain_Manual(), subMenuManual);  
        ManualItem.setIconCls("manual-icon");  
        menu.addItem(ManualItem);
        
        
  
        //create a sub menu  
        Menu subMenu = new Menu();  
         Item italianItem = new Item(iMain.rainbowMain_Italian());
        italianItem.addListener(new BaseItemListenerAdapter(){
        	
        	@Override
        	public void onClick(BaseItem item, EventObject e) {
        		String url = GWT.getModuleBaseURL() + "RainbowClient.html?locale=it" ;
        		com.google.gwt.user.client.Window.open(url, "_self", ""); 
        	}
        });
        italianItem.setIconCls("italian-icon");  
  
        Item englandItem = new Item(iMain.rainbowMain_English()); 
        englandItem.addListener(new BaseItemListenerAdapter(){
        	
        	@Override
        	public void onClick(BaseItem item, EventObject e) {
        		String url = GWT.getModuleBaseURL() + "RainbowClient.html?locale=en" ;
        		com.google.gwt.user.client.Window.open(url, "_self", ""); 
                
        	}
        });
        englandItem.setIconCls("england-icon");  
          
  
        subMenu.addItem(italianItem);  
        subMenu.addItem(englandItem); 
              
  
        //add menu item that has sub menu  
        MenuItem languagesItem = new MenuItem(iMain.rainbowMain_Languages(), subMenu);  
        languagesItem.setIconCls("languages-icon");  
        menu.addItem(languagesItem);  
        languagesItem.hide();
          
  
        //assign the menu to to the button  
        button.setMenu(menu);  
        toolbar.addButton(button);


		ToolbarButton logoutButton = new ToolbarButton("logout");  
		logoutButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				MessageBox.confirm(iMain.rainbowMain_Confirm(), iMain.rainbowMain_Are_you_sure_you_want_to_do_that(),    //$NON-NLS-2$
						new MessageBox.ConfirmCallback() {  
					public void execute(String btnID) { 
						if(btnID.equals("yes")){ 
							logout();
						}
					}  
				}); 	
			}
		});
		logoutButton.setIconCls("logout-icon"); 
		toolbar.addButton(logoutButton);
		toolbar.addSeparator();
		loadUserInfo();

		return toolbar;
	}

	
	/**-------------- TOOLBAR BOTTOM------------------------- **/
	private Toolbar getMainBottomToolbar() {
		
		labelCritical = new StringBuilder();
		labelMajor = new StringBuilder();
		labelNormal = new StringBuilder();
		labelSuspended = new StringBuilder();
		
		
		Toolbar toolbar = new Toolbar();
		changeStatusButton = new ToolbarButton();

		toolbar.setCls("x-toolbarMain"); 


		ToolbarTextItem summaryText = new ToolbarTextItem(iMain.rainbowMain_Summary()); 
		
		ToolbarTextItem blankText = new ToolbarTextItem("&nbsp; &nbsp;"); 
		criticalValueText = new ToolbarTextItem(getSummarySeviceStatus(null,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.CRITICAL_STATUS)));
		criticalToolTip = new ToolTip();
		setToolTip(criticalToolTip, iMain.rainbowMain_Critical(), labelCritical, criticalValueText); 

		ToolbarTextItem criticalText = new ToolbarTextItem(iMain.rainbowMain_Critical()+"&nbsp;&nbsp;"); 
		majorValueText = new ToolbarTextItem(getSummarySeviceStatus(null,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.MAJOR_STATUS)));
		majorToolTip = new ToolTip();
		setToolTip(majorToolTip, iMain.rainbowMain_Major(), labelMajor,majorValueText ); 

		
		ToolbarTextItem majorText = new ToolbarTextItem(iMain.rainbowMain_Major()+"&nbsp;&nbsp;"); 
		normalValueText = new ToolbarTextItem(getSummarySeviceStatus(null,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.NORMAL_STATUS)));
		normalToolTip = new ToolTip();
		setToolTip(normalToolTip, iMain.rainbowMain_Normal(), labelNormal, normalValueText); 

		
		ToolbarTextItem normalText = new ToolbarTextItem(iMain.rainbowMain_Normal()+"&nbsp;&nbsp;"); 
		suspendedValueText = new ToolbarTextItem(getSummarySeviceStatus(null,IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.SUSPENDED_STATUS)));
		suspendedToolTip = new ToolTip();
		setToolTip(suspendedToolTip, iMain.rainbowMain_Suspended(), labelSuspended, suspendedValueText); 
		
		ToolbarTextItem suspendedText = new ToolbarTextItem(iMain.rainbowMain_Suspended()+"&nbsp;&nbsp;"); 



		changeStatusButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				Window window = new Window();
				window.setClosable(true);
				window.setAnimCollapse(true);
				window.setMaximizable(true);  
				window.setResizable(true);  
				window.setIconCls("x-tree-node-icon change-status-icon");  
				window.setWidth(710);  
				window.setHeight(300);  
				window.setPlain(true);  
				window.setLayout(new FitLayout());
				window.setCloseAction(Window.CLOSE);

				StatusChangeHistory changeHistory = new StatusChangeHistory();
				String date = DateTimeFormat.getFormat(IDateFormatUtil.i18nDateTimeFormat).format(startDetectionDate);
				changeHistory.addParameter("startDetectionDate",new JSONString(date)); 
				changeHistory.addParameter("enablePeriodsCB", JSONBoolean.getInstance(false)); 
				//				changeHistory.setPanelParent(panel);
				changeHistory.setWindowParent(window);
				changeHistory.init();
				window.add(changeHistory);
				window.setTitle(iMain.rainbowMain_Change_Status_All_Platforms()); 
				window.show();
				
				startDetectionDate = currentDate;
				changeStatusButton.setVisible(false);

			}
		});
		summaryServiceStatusStore = getSummaryServiceStatusStore();
		summaryServiceStatusStore.load();
		lastChangeStatusStore = getLastChangeStatusStore();
		lastChangeStatusStore.load(getParams());
		

		toolbar.addSeparator();
		toolbar.addItem(blankText);
		toolbar.addSpacer();
		toolbar.addItem(summaryText);
		toolbar.addSpacer();
		toolbar.addSeparator();
		toolbar.addSpacer();
		toolbar.addItem(blankText);
		toolbar.addItem(criticalValueText);
		toolbar.addSpacer();
		toolbar.addItem(criticalText);
		toolbar.addSpacer();
		toolbar.addItem(majorValueText);
		toolbar.addSpacer();
		toolbar.addItem(majorText);
		toolbar.addSpacer();
		toolbar.addItem(normalValueText);
		toolbar.addSpacer();
		toolbar.addItem(normalText);
		
//		Uncomment to enable SUSPENDED
		toolbar.addSpacer();
		toolbar.addItem(suspendedValueText);
		toolbar.addSpacer();
		toolbar.addItem(suspendedText);
		
		toolbar.addSpacer();
		toolbar.addSeparator();
		toolbar.addSpacer();

		changeStatusButton.setIconCls("last-change-status-icon"); 
		changeStatusButton.setTooltip(iMain.rainbowMain_Change_Status_Detected()); 
				
		changeStatusButton.setVisible(false);
		toolbar.addSpacer();
		toolbar.addSpacer();
		toolbar.addButton(changeStatusButton);
		toolbar.addFill();
		
		toolbar.addSeparator();
		String logo = "logo_icar.gif"; 
		String poweredLogo = "<img src=\"images/custom/" + logo + "\" align=\"center\"><br/>";	 
		toolbar.addText(poweredLogo);
		
		setRefreshOnTabChange(false);
		return toolbar;
	}


	private Store getLastChangeStatusStore(){
		HttpProxy dataProxyLoad = new HttpProxy(LAST_CHANGE_STATUS_ACTION_PATH);
		RecordDef dataRecordDef = getCurrentDateDef();
		JsonReader readerLoad = new JsonReader(dataRecordDef);
		readerLoad.setRoot("root"); 
		readerLoad.setTotalProperty("totalCount"); 
		readerLoad.setId("threadid"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){

			public void onLoad(Store store, Record[] records){
				if(records!=null && records.length>0){
					Record record = records[0];
					currentDate = record.getAsDate("currentDate"); 
					changeStatus = record.getAsBoolean("changeStatus"); 
					if(startDetectionDate == null){
						startDetectionDate = currentDate;
					}
					if(changeStatus){
						changeStatusButton.setVisible(true);
					}
				}
			}
		});
		return st;
	}

	private RecordDef getCurrentDateDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new DateFieldDef("currentDate",IDateFormatUtil.dateTimeFormat), 
//						new StringFieldDef("serviceDescription"),
						new BooleanFieldDef("changeStatus") 
				}  
		); 
		return recordDef;
	}

	protected UrlParam[] getParams() {
		UrlParam[] params = null;
		if(startDetectionDate!=null ){
			params = new UrlParam[1];
			params [0] = new UrlParam("startDetectionDate",DateTimeFormat.getFormat("d/MM/y H:m").format(startDetectionDate)); 


		}
		return params;
	}
	
	private Store getCheckPermissionManualIpccStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECK_MAIN_PERMISSION);       
		RecordDef dataRecordDef = getCheckPermissionRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
		@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length>0){
					Record record = records[0];
					menuManualPermission = record.getAsBoolean("menuManualPermission");	
					 if(!menuManualPermission){
				    	   manualIpccItem.hide();    	   
				       }
				}
			}
		});
		return st;
	}

	private RecordDef getCheckPermissionRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("menuManualPermission")
						
				}  
		); 
		return recordDef;
	}
	
	
	
}
