package it.icarcnr.rainbow.client.combobox;

import it.icarcnr.rainbow.client.util.i18n.combobox.IComboBox;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.form.ComboBox;

public class FilesComboBox extends ComboBox {
	
	public enum LoadOption {ALL_SELECT,SINGLE_SELECT};
	
	private UrlParam[] baseParams = null;
	
	private Boolean startLoad = Boolean.TRUE;

	private LoadOption loadOption = LoadOption.ALL_SELECT;
	
	private String action;
	
	static IComboBox iComboBox = (IComboBox)GWT.create(IComboBox.class);

	
	public FilesComboBox(String action) {
		super();
		this.action=action;
		initComboBox();
	}
	
	
	public FilesComboBox(String action, UrlParam[] baseParams) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		initComboBox();
	}
	
	public FilesComboBox(String action, UrlParam[] baseParams, LoadOption loadOption) {
		super();
		this.action=action;
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initComboBox();
	}
	
	public FilesComboBox(String action, UrlParam[] baseParams, LoadOption loadOption, Boolean startLoad) {
		super();
		this.action=action;
		if(startLoad!=null){
			this.startLoad = startLoad;
		}
		this.baseParams = baseParams;
		if(loadOption!=null){
			this.loadOption = loadOption;
		}
		initComboBox();
	}


	private void initComboBox() {
		
		Store store = getCBStore();
		setStore(store);  

		setForceSelection(true);  
		setMinChars(3);  
		setFieldLabel(iComboBox.FilesComboBox_Files()); //$NON-NLS-1$
//		setLabelStyle("font-size:12px");
		setDisplayField("description");  //$NON-NLS-1$
		setMode(ComboBox.LOCAL);  
		setTriggerAction(ComboBox.ALL);  
		setEmptyText(iComboBox.FilesComboBox_Select_File());   //$NON-NLS-1$
		setLoadingText(iComboBox.FilesComboBox_Searching());   //$NON-NLS-1$
		setTypeAhead(true);  
		setSelectOnFocus(true);  
		setWidth(-1);  
		setHideTrigger(false);

		
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
		st.setDefaultSort("description", SortDir.ASC);
		return st;
	}


	private RecordDef getCBRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new StringFieldDef("id"), //$NON-NLS-1$
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

