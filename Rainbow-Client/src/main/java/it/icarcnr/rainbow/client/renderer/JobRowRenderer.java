package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class JobRowRenderer implements Renderer {

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
		boolean userDetected = record.getAsBoolean("userDetected");

		cellMetadata.setHtmlAttribute("style=\"background-color:"+IUserInterfaceConstants.TAKEN_CARE_COLOUR+";\"");



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
