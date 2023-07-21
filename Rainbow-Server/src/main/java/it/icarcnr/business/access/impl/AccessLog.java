/**
 * 
 */
package it.icarcnr.business.access.impl;

import it.icarcnr.business.access.service.IAccessLog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


public class AccessLog implements IAccessLog{
	
	private static final Logger vmcLog = Logger.getRootLogger();
	private final static Log accessLog = LogFactory.getLog(AccessLog.class);
	
	private static AccessLog instance;
	
	private AccessLog(){
		
	}
	
	public static AccessLog getInstance(){
		if(instance == null){
			instance = new AccessLog();
		}
		return instance;
	}

	public void log(Object message){
		try {
			accessLog.info(message);
		} catch (Exception e) {
			accessLog.error(e.getMessage(), e);
			vmcLog.error(e.getMessage(), e);
		}

	}

}
