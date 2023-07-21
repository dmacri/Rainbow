package it.icarcnr.rainbow.client.combobox;

import it.icarcnr.rainbow.client.util.i18n.combobox.IComboBox;

import com.google.gwt.core.client.GWT;
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
import com.gwtext.client.widgets.form.ComboBox;

public class NodeSensorComboBox extends ComboBox {
	
	public enum LoadOption {ALL_SELECT,SINGLE_SELECT};
	
	private UrlParam[] baseParams = null;
	
	private Boolean startLoad = Boolean.TRUE;

	private LoadOption loadOption = LoadOption.ALL_SELECT;
	
	private String action;
	
	static IComboBox iComboBox = (IComboBox)GWT.create(IComboBox.class);

	
	public NodeSensorComboBox(String action,String valueFromMaps) {
		super();
		this.action=action;
		initComboBox(valueFromMaps);
	}
	
	
	public NodeSensorComboBox(String action, UrlParam[] baseParams,String valueFromMaps) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		initComboBox(valueFromMaps);
	}
	
	public NodeSensorComboBox(String action, UrlParam[] baseParams, LoadOption loadOption,String valueFromMaps) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initComboBox(valueFromMaps);
	}
	
	public NodeSensorComboBox(String action, UrlParam[] baseParams, LoadOption loadOption, Boolean startLoad,String valueFromMaps) {
		super();
		this.action=action;
		if(startLoad!=null){
			this.startLoad = startLoad;
		}
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initComboBox(valueFromMaps);
	}


	private void initComboBox(String valueFromMaps) {
		Store store = getCBStore();
		setStore(store);  
		setForceSelection(true);  
		setMinChars(3);
		setFieldLabel(iComboBox.NetworkComboBox_Network()); 
		setDisplayField("description");  
		setMode(ComboBox.LOCAL);  
		setTriggerAction(ComboBox.ALL);  
		setEmptyText(iComboBox.NetworkComboBox_Enter_Network());  
		setLoadingText(iComboBox.NetworkComboBox_Searching());   
		setTypeAhead(true);  
		setSelectOnFocus(true);  
		setWidth(100);  
		setHideTrigger(false);
		setStore();
		if(startLoad){
			loadData();  
			}
			if(!valueFromMaps.equals(""))
			setValue(valueFromMaps);
	
		}


	private void setStore() {
		UrlParam[] basePrms = null;
		int i = 0;
		if(baseParams!=null){
			basePrms = new UrlParam[baseParams.length+1];
			for (; i < baseParams.length; i++) {
				basePrms[i] = baseParams[i];
			}
		}else{
			basePrms = new UrlParam[1];
		}
		UrlParam urlParam = new UrlParam("loadOption",loadOption.toString()); //$NON-NLS-1$
		basePrms[i]= urlParam;
		getStore().setBaseParams(basePrms);
	}


	private void loadData() {
		getStore().load();
	}


	private Store getCBStore() {
		HttpProxy dataProxyLoad = new HttpProxy(action);       
		RecordDef dataRecordDef = getCBRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setBaseParams(baseParams);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
			}
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
			}
		});
		
		return st;
	}


	private RecordDef getCBRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new IntegerFieldDef("id"), //$NON-NLS-1$
						new StringFieldDef("description") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}
	
	public void refreshData(UrlParam[] baseParams){
		this.baseParams = baseParams;
		setStore();
		loadData();
	}
}
