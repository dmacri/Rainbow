package it.icarcnr.business.maps.impl;

import it.icarcnr.business.maps.bean.MapsBean;
import it.icarcnr.integration.dao.servicestatus.impl.CriteriaHistoryAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaHistoryAdvancedDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceMapsBL {

	private static final Log log = LogFactory.getLog(ServiceMapsBL.class);

	public List<MapsBean> getMapsDataList() throws Exception {
		final String method = " getMapsDataList()";
		log.info(method);
		try{
			ICriteriaHistoryAdvancedDAO iCriteriaHistoryAdvancedDAO=new CriteriaHistoryAdvancedDAO();
			 List<MapsBean> mapsValue=iCriteriaHistoryAdvancedDAO.getAllLatestMapsValue();
			return mapsValue;

		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	









}
