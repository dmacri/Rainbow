package it.icarcnr.rainbow.client.util.basecomponents;

import it.icarcnr.rainbow.client.util.constants.IGlobalConstants;

import java.util.Set;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;


public abstract class BasePanel extends Panel {  

	protected Window windowParent;
	protected BasePanel panelParent;

	protected JSONObject parameters;
	
	protected Record record;
	protected Record[] selectedRecords;

	protected Timer timer;
	
	public abstract void init();

	public void refreshData() {
		// TODO Auto-generated method stub
	}
	
	public void loadData() {
		// TODO Auto-generated method stub
	}
	
	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
	
	public Record[] getSelectedRecords() {
		return selectedRecords;
	}

	public void setSelectedRecords(Record[] selectedRecords) {
		this.selectedRecords = selectedRecords;
	}
	
	public Window getWindowParent() {
		return windowParent;
	}

	public void setWindowParent(Window windowParent) {
		this.windowParent = windowParent;
	}

	public BasePanel getPanelParent() {
		return panelParent;
	}

	public void setPanelParent(BasePanel panelParent) {
		this.panelParent = panelParent;
	}

	public JSONObject getParameters() {
		return parameters;
	}

	public void setParameters(JSONObject parameters) {
		this.parameters = parameters;
	}
	

	public UrlParam[] getUrlParams(){
		UrlParam[] params = null;
		if(parameters!=null){
			Set<String> keys = parameters.keySet();
			if(keys!=null && !keys.isEmpty()){
				params = new UrlParam[keys.size()];
				int i = 0;
				for (String key : keys) {
					JSONValue jsonValue = parameters.get(key);
					if(jsonValue.isString()!=null){
						params[i]=new UrlParam(key,jsonValue.isString().stringValue());
					}else{
						params[i]=new UrlParam(key,jsonValue.toString());
					}
					i++;
				}
			}
		}
		return params;
	}
	
	public void addParameter(String key, JSONValue jsonValue){
		if(parameters == null){
			parameters = new JSONObject();
		}
		parameters.put(key, jsonValue);
	}
	
	public void addParameters(JSONObject jsonObject){
		if(parameters == null){
			parameters = new JSONObject();
		}
		if(jsonObject!=null){
			Set<String> keySet = jsonObject.keySet();
			for (String key : keySet) {
				parameters.put(key, jsonObject.get(key));
			}
		}
	}
	
	public void clearParameters(){
		parameters = null;
	}

	/**
	 * Schedules  a refreshData timer to elapse in the future.
	 * 
	 * @param delayMillis how long to wait before the timer elapses, in
	 *          milliseconds
	 */
	public void scheduleRefreshData(final int delayMillis) {
		timer = new Timer(){
			@Override
			public void run() {
				refreshData();
				schedule(delayMillis);

			}
		};
		timer.schedule(delayMillis);
	}

	/**
	 * Schedules  a refreshData timer to elapse in the future.
	 * 
	 */
	public void scheduleRefreshData() {
		scheduleRefreshData(IGlobalConstants.DELAY_REFRESH);
	}
	
	public void unscheduleRefreshData() {
		if (timer!=null) {
			timer.cancel();
			timer=null;
		}
	}
	
}
