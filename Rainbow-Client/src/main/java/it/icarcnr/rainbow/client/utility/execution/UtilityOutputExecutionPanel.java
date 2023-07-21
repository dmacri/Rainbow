package it.icarcnr.rainbow.client.utility.execution;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IUtilityConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.layout.FitLayout;

public class UtilityOutputExecutionPanel extends BasePanel {

	static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	StringBuilder bodyHTML;

	@Override
	public void init() {

		setLayout(new FitLayout());
		setAutoScroll(true);
		setBorder(false);
		bodyHTML = new StringBuilder();

	}
	
	/**
	 * Set outputExecution as html output
	 * @param record     A record that contains two fields: 
	 * outputFormat (type StringFieldDef) and
	 * outputExecution (type ObjectFieldDef)
	 */

	public void setOutput(Record record) {
		bodyHTML = new StringBuilder();
		updateOutput(record);
	}
	
	
	/**
	 * Append outputExecution to the current html output
	 * @param record     A record that contains two fields: 
	 * outputFormat (type StringFieldDef) and
	 * outputExecution (type ObjectFieldDef)
	 */
	public void updateOutput(Record record) {
		if (record!=null){
			String outputFormat = record.getAsString("outputFormat");
			
			if (IUtilityConstants.HTML_OUTPUT_FORMAT.equals(outputFormat) || IUtilityConstants.TXT_OUTPUT_FORMAT.equals(outputFormat)){
				String outputExecution = record.getAsString("outputExecution");
				if(outputExecution!=null){
					bodyHTML.append(outputExecution);
				}
			}else if (IUtilityConstants.JSON_OUTPUT_FORMAT.equals(outputFormat)){
				JavaScriptObject javaScriptObjectOE = (JavaScriptObject)record.getAsObject("outputExecution");
				if (javaScriptObjectOE!=null ){
					JSONObject jsonOutputExecution = new JSONObject(javaScriptObjectOE);
					if(jsonOutputExecution!=null){
						Set<String> keySet = jsonOutputExecution.keySet();
						for (String key : keySet) {
							bodyHTML.append("<b>");
							bodyHTML.append(key);
							bodyHTML.append("</b>");
							bodyHTML.append(": ");
							bodyHTML.append(jsonOutputExecution.get(key));
							bodyHTML.append("<br>");					
						}
					}
				}
			}
			setHtml(bodyHTML.toString());
		}
	}
}
