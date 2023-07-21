package it.icarcnr.rainbow.client.renderer;


import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class TakeCareRenderer implements Renderer {
	
	private static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);
	private final static String serviceTakenOverTooltip = "ext:qtitle=''" + " ext:qtip='"+iRenderer.TakeCareRenderer_Service_taken_over()+"'";
	private final static String toTakeCareTooltip =  "ext:qtitle=''" + " ext:qtip='"+iRenderer.TakeCareRenderer_Single_click_to_take_charge_service()+"'";
	
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = record.getAsString("status");
		boolean isTakingCharge = value != null ? ((Boolean)value).booleanValue() : false;
		boolean tackingChargePermission = record.getAsBoolean("tackingChargePermission");
		String message= "";
		
		String colour = IUserInterfaceConstants.STATUS_COLOUR.get(status);
		if(colour!=null){
			message = compute(value, cellMetadata, isTakingCharge, tackingChargePermission, colour);	
		}
		
		return message;
	}
	
	private String compute(Object value, CellMetadata cellMetadata, boolean isTakingCharge, boolean tackingChargePermission, String colour ){
		String message = "";
		if(isTakingCharge){
			String text = (value != null ? value.toString() : null);
			if (text != null) {
				String html_attr = serviceTakenOverTooltip + "  style='background-color:"+colour+"; text-align:center;'";
				cellMetadata.setHtmlAttribute(html_attr);
			}
			message = "<img src='js/ext/resources/images/default/menu/checked.gif' border='0' style='vertical-align:middle;'"+" "+serviceTakenOverTooltip+">";
		}else if(tackingChargePermission){
			String text = (value != null ? value.toString() : null);

			if (text != null) {
	            String html_attr = toTakeCareTooltip + "  style='background-color:"+colour+";cursor:hand;cursor:pointer; text-align:center;'";
				cellMetadata.setHtmlAttribute(html_attr);
			}
			message = "<img src='js/ext/resources/images/default/menu/unchecked.gif' border='0' style='vertical-align:middle;'"+" "+toTakeCareTooltip+">";
		}
		else{
			cellMetadata.setHtmlAttribute("style=\"background-color:"+colour+";\"");
			message="";
		}
		return message;
	}

}
