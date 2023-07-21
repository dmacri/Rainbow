package it.icarcnr.rainbow.client.util.constants;

import java.util.HashMap;
import java.util.Map;

public interface IUserInterfaceConstants {
	
	String TAKEN_CARE_COLOUR = "#7CD3DE";
	
	String WHITE_COLOUR = "#FFFFFF";
	String GREEN_COLOUR = "#ACD373";
	
	Map<String,String> STATUS_COLOUR = new HashMap<String,String>(){
		{
		put(IServiceConstants.SUSPENDED_STATUS , "#C2C2C2");
		put(IServiceConstants.NORMAL_STATUS , "#ACD373");
		put(IServiceConstants.MAJOR_STATUS , "#F6CC3A");
		put(IServiceConstants.CRITICAL_STATUS , "#F16243");
		}
	};

}
