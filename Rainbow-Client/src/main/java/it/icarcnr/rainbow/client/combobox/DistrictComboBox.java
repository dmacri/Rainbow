package it.icarcnr.rainbow.client.combobox;

import it.icarcnr.rainbow.client.util.i18n.combobox.IComboBox;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.form.ComboBox;


public class DistrictComboBox extends ComboBox{

	public enum LoadOption {ALL_SELECT,SINGLE_SELECT};

	private UrlParam[] baseParams = null;

	private Boolean startLoad = Boolean.TRUE;

	private LoadOption loadOption = LoadOption.ALL_SELECT;

	private String action;

	static IComboBox iDistrictComboBox = (IComboBox)GWT.create(IComboBox.class);



	public DistrictComboBox(String action) {
		super();
		this.action=action;
		initDistrictComboBox();
	}


	public DistrictComboBox(String action, UrlParam[] baseParams) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		initDistrictComboBox();
	}

	public DistrictComboBox(String action, UrlParam[] baseParams, LoadOption loadOption) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initDistrictComboBox();
	}

	public DistrictComboBox(String action, UrlParam[] baseParams, LoadOption loadOption, Boolean startLoad) {
		super();
		this.action=action;
		if(startLoad!=null){
			this.startLoad = startLoad;
		}
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initDistrictComboBox();
	}


	private void initDistrictComboBox() {

		Store store = getDistrictStore();
		setStore(store);  

		setForceSelection(true);  
		setMinChars(2);  
		setFieldLabel(iDistrictComboBox.CoboBoxDistrict_Districts()); //$NON-NLS-1$
//		setLabelStyle("font-size:12px");
		setDisplayField("description");  //$NON-NLS-1$
		setMode(ComboBox.REMOTE);  
		setTriggerAction(ComboBox.ALL);  
		setEmptyText(iDistrictComboBox.CoboBoxDistrict_Insert_District());   //$NON-NLS-1$
		setLoadingText(iDistrictComboBox.ComboBoxDistrict_Searching());   //$NON-NLS-1$
		setTypeAhead(true);  
		setSelectOnFocus(false);  
		setWidth(100);  
		setHideTrigger(false);
		setPageSize(10); 

		
		setStore();
		if(startLoad){
			loadData();  
		}

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


	private Store getDistrictStore() {
		HttpProxy dataProxyLoad = new HttpProxy(action);       
		RecordDef dataRecordDef = getDistrictRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setBaseParams(baseParams);
		st.setRemoteSort(true);
		st.setDefaultSort("description", SortDir.ASC);
		return st;
	}


	private RecordDef getDistrictRecordDef(){	
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
