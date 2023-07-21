/**
 * 
 */
package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.constants.IUserInterfaceConstants;
import it.icarcnr.rainbow.client.util.i18n.renderer.IRenderer;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.grid.CellMetadata;
import com.gwtext.client.widgets.grid.Renderer;

/**
 * @author Faber
 *
 */
public class ServiceExtendedDescriptionRenderer implements Renderer {

	
	static IRenderer iRenderer = (IRenderer)GWT.create(IRenderer.class);

	@Override
	public String render(Object value, CellMetadata cellMetadata,
			Record record, int rowIndex, int colNum, Store store) {

		String status = record.getAsString("status");
		String extendedDescription = record.getAsString("extendedDescription")!=null ? record.getAsString("extendedDescription") : iRenderer.ServiceExtendedDescriptionRenderer_Unavailable();
		Integer samplingPeriod = record.getAsInteger("samplingPeriod");
		
		setCellMetadata(value, cellMetadata, extendedDescription, IUserInterfaceConstants.STATUS_COLOUR.get(status),samplingPeriod);

		return value.toString();

	}
	private void setCellMetadata(Object value, CellMetadata cellMetadata, String extendedDescription, String colour, Integer samplingPeriod){
		String text = (value != null ? value.toString() : null);
		if (text != null) {
			String units="";
			if(samplingPeriod>=360 && samplingPeriod<1440){
				samplingPeriod/=60;
				units = "h";
			}else if(samplingPeriod>=1440){
				samplingPeriod/=1440;
				units = "gg"; 
			}else{
				units = "min";
			}
			String html_attr = "ext:qtitle=''";
			html_attr += " ext:qtip='" + extendedDescription.replace("'", "\"") +". " +iRenderer.ServiceExtendedDescriptionRenderer_Sampling_Period() +samplingPeriod +" "+ units+". " + "<br>'";
			
			html_attr += " style='background-color:"+colour+";cursor:hand;cursor:pointer;'";
			cellMetadata.setHtmlAttribute(html_attr);
		}

	}


}
