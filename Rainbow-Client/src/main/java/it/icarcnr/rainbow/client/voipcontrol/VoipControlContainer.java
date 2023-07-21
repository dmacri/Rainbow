package it.icarcnr.rainbow.client.voipcontrol;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Frame;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.layout.FitLayout;

public class VoipControlContainer extends ScreenPanel {

	static Ii18nGlobalConstants i18nGlobalConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	private Store voipControlStore;

	@Override
	public void init() {
		setLayout(new FitLayout());
		setFrame(true);
		voipControlStore = getVoipControlStore();
	}

	@Override
	protected void afterRender() {
		loadVoipControl();
	}

	private void loadVoipControl() {
		sendCollapseMainMenuMessage(Boolean.TRUE);
		getEl().mask(i18nGlobalConstants.ScreenManager_Loading_data(), "x-mask-loading");
		voipControlStore.load();
	}

	private void accessToVoIPControl(String voipControlURL, String sessionId) {
		clear();
		Frame frame = new Frame(voipControlURL+"?JSessionID="+sessionId);
		add(frame);
		doLayout(true);
	}

	private Store getVoipControlStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.VOIP_CONTROL_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);

		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length >0){
					Record record = records[0];
					boolean hasVoIPControlPermission = record.getAsBoolean("hasVoIPControlPermission"); 
					if(hasVoIPControlPermission){
						String voipControlURL = record.getAsString("voipControlURL");
						String sessionId = record.getAsString("JSessionID");
						accessToVoIPControl(voipControlURL, sessionId);
					}else{
						MessageBox.alert(i18nGlobalConstants.rainbowClient_Warning(),i18nGlobalConstants.ScreenManager_System_busy_Please_try_later());
					}
				}
			}
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
				MessageBox.alert(i18nGlobalConstants.rainbowClient_Warning(),i18nGlobalConstants.ScreenManager_System_busy_Please_try_later());
			}
		});
		return st;
	}

	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("hasVoIPControlPermission"),
						new StringFieldDef("voipControlURL"),
						new StringFieldDef("JSessionID")
				}  
		); 
		return recordDef;
	}


	private void sendCollapseMainMenuMessage(Boolean collapsed) {
		if(collapsed!=null){
			PageBus.publish(ISubjectToPublishTheMessageTo.MAIN_MENU, collapsed);
		}
	}

//		@Override
	//	public void refreshData() {
	//		sendCollapseMainMenuMessage(Boolean.TRUE);
	//	}

	//	@Override
	//	public void loadData() {
	//		loadVoipControl();
	//	}

}
