package it.icarcnr.business.network.util;

import it.icarcnr.business.network.bean.AlarmedNodeBean;
import it.icarcnr.integration.dao.util.IServiceConstants;

import java.util.Comparator;


public class AlarmedNodeBeanComparator implements Comparator<AlarmedNodeBean> {
	

	public int compare(AlarmedNodeBean alarmedNodeBean1, AlarmedNodeBean alarmedNodeBean2) {
		
		
		Integer severityAlarmedNodeBean1 = Integer.valueOf(IServiceConstants.Status.valueOf(alarmedNodeBean1.getStatus().toUpperCase()).getSeverity());
		Integer severityAlarmedNodeBean2 = Integer.valueOf(IServiceConstants.Status.valueOf(alarmedNodeBean2.getStatus().toUpperCase()).getSeverity());
		
		if(alarmedNodeBean1.getStatus().equals(alarmedNodeBean2.getStatus())){
			return alarmedNodeBean1.getFunctionId().compareTo(alarmedNodeBean2.getFunctionId());
		}else{
			return severityAlarmedNodeBean2.compareTo(severityAlarmedNodeBean1); 
			//
		}
	}
//
}
