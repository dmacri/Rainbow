package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IServiceConstants;
import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class ServiceStatusRenderer implements Renderer {

	private static Ii18nGlobalConstants iConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = value != null ? (String)value : "";
		String colour = IUserInterfaceConstants.STATUS_COLOUR.get(status);
		if(colour!=null){
			cellMetadata.setHtmlAttribute("style=\"background-color:"+colour+";\"");
		}

		String statusString ="";
		
		if(IServiceConstants.SUSPENDED_STATUS.equals(status)){
			statusString = iConstants.Service_Status_Suspended();
		}else if(IServiceConstants.NORMAL_STATUS.equals(status)){
			statusString = iConstants.Service_Status_Normal();
		}else if(IServiceConstants.MAJOR_STATUS.equals(status)){
			statusString = iConstants.Service_Status_Major();
		}else if(IServiceConstants.CRITICAL_STATUS.equals(status)){
			statusString = iConstants.Service_Status_Critical();
		}
		
		return statusString;
	}
}


