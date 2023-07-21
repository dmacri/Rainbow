package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.HttpStoreLoadException;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

public class LogViewPanel extends BasePanel {

	private Integer serviceId;
	private String title;
	
	static IServices iServices = (IServices)GWT.create(IServices.class);
	
	public LogViewPanel(Integer serviceId,String title) {
		this.serviceId = serviceId;
		this.title = title;
	}
	
	@Override
    public void init() {
		final Store store = getStore();
		store.setBaseParams(getUrlParams());
		store.load(getParams());

		setLayout(new FitLayout());
//		panel.setTitle(title);
		setAutoScroll(true);
	}


	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.LOG_VIEW_ACTION);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.addStoreListener(new StoreListenerAdapter() {
			
			@Override
			public void onLoadException(Throwable error) {
				if(error instanceof HttpStoreLoadException){
					HttpStoreLoadException httpStoreLoadException = (HttpStoreLoadException)error;
					if(httpStoreLoadException.getHttpStatus()== Response.SC_UNAUTHORIZED){
					    setHtml(iServices.LogViewPanel_HTTP_Status_401_User_is_not_authorized_to_access_this_action()); //$NON-NLS-1$
					}else{
					    setHtml(httpStoreLoadException.getMessage());
					}
				}else{
				    setHtml(error.getMessage());
				}
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record=records[0];
				    setHtml(record.getAsString("content")); //$NON-NLS-1$
				    
				}
			}

		});
		return st;
	}

	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{ 
						new StringFieldDef("content"), //$NON-NLS-1$
//						new StringFieldDef("title")
				}  
		); 
		return recordDef;
	}

	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[1];
		params [0] = new UrlParam("serviceId", this.serviceId); //$NON-NLS-1$
		return params;
	}

	/*protected void closeWindow() {
		Window window = getWindowParent();
		window.close();
	}*/

}
