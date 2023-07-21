package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class ServiceToRenderer implements Renderer {
	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);
	private static final String tooltip = "ext:qtip='"+iRenderer.FromServiceRenderer_Counter_Way()+"'";

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = record.getAsString("status");
		String message = compute(value, cellMetadata, record, IUserInterfaceConstants.STATUS_COLOUR.get(status));
		return message;
	}
	
	private String compute(Object value, CellMetadata cellMetadata,Record record,String colour){


		String nodeFrom = record.getAsString("nodeFrom");
		String nodeTo = record.getAsString("nodeTo");
		String message = "";

//		if(nodeFrom.equals(nodeTo)){
//			StringBuilder html_attr = new StringBuilder();
//			html_attr.append(tooltip);
//			html_attr.append(" style=' ");
//			if(colour!=null){
//				html_attr.append(" background-color:"+colour+"; ");
//			}
//			html_attr.append("  text-align:center; ");
//			html_attr.append(" ' ");
//
//			cellMetadata.setHtmlAttribute(html_attr.toString());
//
//			message = " Contatore unidirezionale " ;
//		}
//		else{
			cellMetadata.setHtmlAttribute("style=\"background-color:"+colour+"; \"");
			message = nodeTo;
//		}

		return message;
	}

}
