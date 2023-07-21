package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.IDateFormatUtil;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class DateTimeFormatRenderer implements Renderer {

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
		if(value instanceof Date){
			Date date = (Date)value;  
			String dateString = DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormatShort).format(date);
			return dateString;  
		}
		return null;
	}
}
