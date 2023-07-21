package it.icarcnr.rainbow.client.dashboard;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;

import java.util.ArrayList;
import java.util.List;

import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.portal.Portal;



public class NetworkServicesStatusContainer extends BasePanel{

	Store checkPermissionStore;
	Portal portal;
	List<NetworkServicesStatus> networkServicesStatusList;
	
	@Override
	public void init() {
		portal = new Portal();  
		portal.setHeight(300);
		portal.setWidth(400);

		setLayout(new FitLayout());
		setBorder(false);  
		setPaddings(0); 
		add(portal);
		
		networkServicesStatusList = new ArrayList<NetworkServicesStatus>();

		checkPermissionStore = getCheckPermissionStore();
		checkPermissionStore.load();

	}


	private Store getCheckPermissionStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECK_PORTAL_SERVICE_STATUS_ACTION);       
		RecordDef dataRecordDef = getCheckPermissionRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length>0){
					for(int i =0; i<records.length;i++){
						Record record = records[i];
						boolean permission = record.getAsBoolean("permission");
						String title = record.getAsString("title");
						Integer idNetwork = record.getAsInteger("idNetwork");
						if(permission){
							loadPortlets(title, idNetwork);
						}
					}
				}
			}
		});
		return st;
	}

	protected void loadPortlets(String title, Integer idNetwork) {
		NetworkServicesStatus networkServicesStatus = new NetworkServicesStatus();
		networkServicesStatusList.add(networkServicesStatus);
		portal.add(networkServicesStatus.getGridPortlet(title,idNetwork), new ColumnLayoutData(0.31));
		doLayout(true);
	}

	private RecordDef getCheckPermissionRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("permission"),
						new StringFieldDef("title"),
						new IntegerFieldDef("idNetwork"),
				}  
		); 
		return recordDef;
	}
	@Override
	public void refreshData() {
		for (NetworkServicesStatus summaryServiceStatus : networkServicesStatusList) {
			summaryServiceStatus.refreshData();
		}
	}


}


