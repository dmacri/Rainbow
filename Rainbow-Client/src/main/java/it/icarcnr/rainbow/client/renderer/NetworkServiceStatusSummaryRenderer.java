package it.icarcnr.rainbow.client.renderer;


import it.icarcnr.rainbow.client.util.constants.IServiceConstants;
import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class NetworkServiceStatusSummaryRenderer implements Renderer {
	
	private static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);
	private final static String clickToViewGraph =  "ext:qtitle=''" + " ext:qtip='"+iRenderer.NetServiceStatusSummary_Single_click_to_view_Graph()+"'";

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
		
		Boolean viewGraphPermission = record.getAsBoolean("viewGraphPermission");
		String colour = IUserInterfaceConstants.GREEN_COLOUR;
		int valueCritical = record.getAsInteger(IServiceConstants.CRITICAL_STATUS);
		int valueMajor = record.getAsInteger(IServiceConstants.MAJOR_STATUS);
		int valueSuspended = record.getAsInteger(IServiceConstants.SUSPENDED_STATUS);
		int valueNormal = record.getAsInteger(IServiceConstants.NORMAL_STATUS);
		
		switch (colNum) {
		case 0:
			if(valueCritical!=0){
				colour = IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.CRITICAL_STATUS);
			}else{
				colour = IUserInterfaceConstants.GREEN_COLOUR;
			}
			break;
		case 1:
			if(valueMajor!=0){
				colour = IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.MAJOR_STATUS);
			}else{
				colour = IUserInterfaceConstants.GREEN_COLOUR;
			}
			break;
		case 2:
			if(valueNormal!=0){
				colour = IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.NORMAL_STATUS);
			}else{
				colour = IUserInterfaceConstants.GREEN_COLOUR;
			}			
			break;
		case 3:
			if(valueSuspended!=0){
				colour = IUserInterfaceConstants.STATUS_COLOUR.get(IServiceConstants.SUSPENDED_STATUS);
			}else{
				colour = IUserInterfaceConstants.GREEN_COLOUR;
			}			
			break;

		default:
			break;
		}
		
		if(viewGraphPermission){
			String htmlAttr = clickToViewGraph + " style='background-color:"+colour+";cursor:hand;cursor:pointer; text-align:center;font-weight: bold;'";
			cellMetadata.setHtmlAttribute(htmlAttr);
		}else{
			String htmlAttr = " style='background-color:"+colour+"; text-align:center;font-weight: bold;'";
			cellMetadata.setHtmlAttribute(htmlAttr);
		}

		return (value != null ? value.toString() : null);
	}

}
