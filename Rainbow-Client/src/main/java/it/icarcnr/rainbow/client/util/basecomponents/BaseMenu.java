/**
 * 
 */
package it.icarcnr.rainbow.client.util.basecomponents;

import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.menu.Menu;


public abstract class BaseMenu extends Menu {
	
	protected Record record = null;
	
	protected Record[] selectedRecords = null;
	
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
	
	public abstract void init();

}
