package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;

import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

public class JobCursorRenderer implements Renderer {

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {
		
		
			cellMetadata.setHtmlAttribute("style=\"background-color:"+IUserInterfaceConstants.TAKEN_CARE_COLOUR+";cursor:hand; cursor:pointer;\"");
		
		
		if(value!=null){
			return value.toString();
		}
		return "";
	}
}
