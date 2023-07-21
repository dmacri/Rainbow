package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class ServiceStatusRowRenderer implements Renderer {

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = record.getAsString("status");
		
		String colour = IUserInterfaceConstants.STATUS_COLOUR.get(status);
		if(colour!=null){
			cellMetadata.setHtmlAttribute("style=\"background-color:"+colour+";\"");		
		}
		
		if(value instanceof Date){
			Date date = (Date)value; 
			
			String dateString = DateTimeFormat.getFormat(IDateFormatUtil.i18nDateTimeFormat).format(date);
			

			return dateString;  
		}else if(value!=null){
			return value.toString();
		}
		return "";
		
	}

}
