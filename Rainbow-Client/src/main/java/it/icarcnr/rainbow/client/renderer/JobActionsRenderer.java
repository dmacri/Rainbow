package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class JobActionsRenderer implements Renderer {

	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		boolean acknowledgementPermission = record.getAsBoolean("acknowledgementPermission");
		boolean userDetected = record.getAsBoolean("userDetected");
		String stringValue="";

		if(acknowledgementPermission){
			if(userDetected){
				String executeOpeations = "ext:qtitle=''"+" ext:qtip='"+iRenderer.JobAknowledgmentRenderer_ext_qtip_Single_click_to_execute_service_opearations()+"'";
				String html_attr = executeOpeations + " style='background-color:"+IUserInterfaceConstants.TAKEN_CARE_COLOUR+";cursor:hand;cursor:pointer; text-align:center;'";
				cellMetadata.setHtmlAttribute(html_attr);
				stringValue =  "<img src='images/custom/icons/gear.gif' border='0'style=' vertical-align:middle;'"+" "+executeOpeations+"/>" ;
				return stringValue;

			}else{
				String html_attr =  " style='background-color:"+IUserInterfaceConstants.TAKEN_CARE_COLOUR+";text-align:center;'";
				cellMetadata.setHtmlAttribute(html_attr);
				stringValue =  "<img src='images/custom/icons/gear.gif' border='0'style='vertical-align:middle;'/>" ;
				return stringValue;
			}


		}else{

			cellMetadata.setHtmlAttribute("style=\"background-color:"+IUserInterfaceConstants.TAKEN_CARE_COLOUR+";\"");
			stringValue = "" ;
			return stringValue;

		}

	}
}
