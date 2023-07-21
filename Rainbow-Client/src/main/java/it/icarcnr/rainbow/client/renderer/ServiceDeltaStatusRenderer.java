package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class ServiceDeltaStatusRenderer implements Renderer {

	
	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);

	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
		
		Map<String, String> thresholdStringValues = ServiceRendererUtil.getStringThresholdDeltaValues(record);

		String deltaStatus = record.getAsString("deltaValueStatus")!=null ? record.getAsString("deltaValueStatus") : "" ;
		String status = record.getAsString("status")!=null ? record.getAsString("status") : "" ;
		
		if(deltaStatus!=null && deltaStatus.length()>0){
			setCellMetadata(value, cellMetadata, thresholdStringValues.get("criticalDeltaValueString"), thresholdStringValues.get("majorDeltaValueString"), IUserInterfaceConstants.STATUS_COLOUR.get(deltaStatus));
		}else{
			String colour = IUserInterfaceConstants.STATUS_COLOUR.get(status);
			
			StringBuilder html_attr = new StringBuilder();
			html_attr.append(" style=' ");
			if(colour!=null){
				html_attr.append(" background-color:"+colour+"; ");
			}
			html_attr.append(" text-align:center; ");
			html_attr.append(" ' ");
			
			cellMetadata.setHtmlAttribute(html_attr.toString());
		}

		if(value!=null){
			return value.toString();
		}
		return "-";
	}

	private void setCellMetadata(Object value, CellMetadata cellMetadata, String criticalDeltaValue, String majorDeltaValue, String colour ){
		String text = (value != null ? value.toString() : null);
		if (text != null) {
			StringBuilder html_attr = new StringBuilder();	
			html_attr.append(" ext:qtitle=''");
			html_attr.append(" ext:qtip='" +iRenderer.ServiceDeltaStatusRenderer_Major()+" &#916Value: <b>");
			html_attr.append(" "+majorDeltaValue+"</b><br>"+iRenderer.ServiceDeltaStatusRenderer_Critical()+" &#916Value:<b>");
			html_attr.append(" "+criticalDeltaValue+ "</b><br>" +iRenderer.ServiceDeltaStatusRenderer_Single_click_for_more_informations()+"'");

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


