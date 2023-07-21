package it.icarcnr.integration.dao.servicestatus.service;

import it.icarcnr.business.maps.bean.MapsBean;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaHistoryBean;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaSensorValue;

import java.util.Date;
import java.util.List;

public interface ICriteriaHistoryAdvancedDAO {
	
	public List<CriteriaHistoryBean> getServiceHistory (Integer serviceOperationId, Date startDate, Date endDate, Integer networkId);
    public List<CriteriaSensorValue> getSensorValue (Integer sensoreId, Date startDate, Date endDate) ;
    public List<MapsBean> getAllLatestMapsValue ();
}
