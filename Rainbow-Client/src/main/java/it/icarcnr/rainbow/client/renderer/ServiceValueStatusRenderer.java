package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class ServiceValueStatusRenderer implements Renderer {


	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);

	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
				
		Map<String, String> thresholdStringValues = ServiceRendererUtil.getStringThresholdValues(record);


		String status = record.getAsString("valueStatus") !=null ? record.getAsString("valueStatus") : "";

		setCellMetadata(value, cellMetadata, thresholdStringValues.get("criticalValueString"), thresholdStringValues.get("majorValueString"), IUserInterfaceConstants.STATUS_COLOUR.get(status));	

		if(value!=null){
			return value.toString();
		}
		return "";
	}

	private void setCellMetadata(Object value, CellMetadata cellMetadata, String criticalValue, String majorValue, String colour ){
		String text = (value != null ? value.toString() : null);

		if (text != null) {

			StringBuilder html_attr = new StringBuilder();	
			html_attr.append(" ext:qtitle=''");
			html_attr.append(" ext:qtip='" + iRenderer.ServiceValueStatusRenderer_Major_Value());
			html_attr.append("<b>"+" "+majorValue+"</b><br>"+iRenderer.ServiceValueStatusRenderer_Critical_Value());
			html_attr.append("<b>"+" "+criticalValue+ "</b><br>" + iRenderer.ServiceValueStatusRenderer_Double_click_for_more_informations()+"'");

			html_attr.append(" style=' ");
			if(colour!=null){
				html_attr.append(" background-color:"+colour+"; ");
			}
			html_attr.append(" cursor:hand;cursor:pointer; text-align:center; ");
			html_attr.append(" ' ");

			cellMetadata.setHtmlAttribute(html_attr.toString());
		}
	}
}


