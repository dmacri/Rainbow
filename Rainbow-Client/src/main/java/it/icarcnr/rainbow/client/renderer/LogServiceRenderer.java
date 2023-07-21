package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class LogServiceRenderer implements Renderer{
	
	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);
//	private static final String doubleClickToViewLog = "ext:qtitle='" + Format.htmlEncode("Double click to view log<br>") + "'"+" ext:qtip='" + Format.htmlEncode(" "+ "'");
	private static final String doubleClickToViewLog = "ext:qtip='"+iRenderer.LogServiceRenderer_Double_click_to_view_log()+"'";

	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = record.getAsString("status");
		boolean logPresent = record.getAsBoolean("logPresent");
		
		String message = compute(value, cellMetadata, logPresent, IUserInterfaceConstants.STATUS_COLOUR.get(status));	

		return message;
	}

	private String compute(Object value, CellMetadata cellMetadata, boolean logPresent, String colour ){
		String message= "";
		if(logPresent){
			String text = (value != null ? value.toString() : null);
			if (text != null) {
				StringBuilder html_attr = new StringBuilder();
				html_attr.append(doubleClickToViewLog);
				html_attr.append(" style=' ");
				if(colour!=null){
					html_attr.append(" background-color:"+colour+"; ");
				}
				html_attr.append(" cursor:hand;cursor:pointer; text-align:center; ");
				html_attr.append(" ' ");
				
				cellMetadata.setHtmlAttribute(html_attr.toString());
			}
			message = "<img src='images/silk/book_open.gif' border='0' style='vertical-align:middle;'"+" "+doubleClickToViewLog+">" ;

		}else{
			cellMetadata.setHtmlAttribute("style=\"background-color:"+colour+"; text-align:center;  \"");
			message = "-";
		}
		return message;
	}

}


