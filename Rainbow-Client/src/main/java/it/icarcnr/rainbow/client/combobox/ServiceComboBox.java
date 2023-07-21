package it.icarcnr.rainbow.client.combobox;

import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.combobox.IComboBox;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.form.ComboBox;

public class ServiceComboBox extends ComboBox {

	private Integer nodeTypeId = null;
	private Integer nodeId = null;

	static IComboBox iComboBox = (IComboBox)GWT.create(IComboBox.class);

	public ServiceComboBox() {
		super();
		initComboBox();
	}


	private void initComboBox() {
		Store store = getCriteriaCBStore();
		setStore(store);  
		setForceSelection(true);  
		setMinChars(3);  
		setFieldLabel(iComboBox.ServiceComboBox_Service()); //$NON-NLS-1$
		setDisplayField("description");  //$NON-NLS-1$
		setMode(ComboBox.LOCAL);  
		setTriggerAction(ComboBox.ALL);  
		setEmptyText(iComboBox.ServiceComboBox_Enter_service());   //$NON-NLS-1$
		setLoadingText(iComboBox.ServiceComboBox_Searching());   //$NON-NLS-1$
		setTypeAhead(true);  
		setSelectOnFocus(true);  
		setWidth(350);  
		setHideTrigger(false);
		loadData();  
	}

	private void loadData() {
		UrlParam[] urlParams = null;
		if(nodeTypeId!=null && nodeId!=null){
			urlParams = new UrlParam[2];
			urlParams[0]= new UrlParam("nodeTypeId",nodeTypeId); //$NON-NLS-1$
			urlParams[1] = new UrlParam("nodeId",nodeId); //$NON-NLS-1$
		}else if(nodeTypeId!=null){
			urlParams = new UrlParam[1];
			urlParams[0]= new UrlParam("nodeTypeId",nodeTypeId); //$NON-NLS-1$
		}else if(nodeId!=null){
			urlParams = new UrlParam[1];
			urlParams[0]= new UrlParam("nodeId",nodeId); //$NON-NLS-1$
		}
		getStore().load(urlParams);
	}

	private Store getCriteriaCBStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.SERVICE_COMBO_BOX_ACTION_PATH);       
		RecordDef dataRecordDef = getCriteriaCBRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		return st;
	}


	private RecordDef getCriteriaCBRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new IntegerFieldDef("id"), //$NON-NLS-1$
						new StringFieldDef("description") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}

	public void refreshData(Integer nodeTypeId, Integer nodeId){
		this.nodeTypeId = nodeTypeId;
		this.nodeId = nodeId;
		loadData();
	}
}
