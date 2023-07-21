package it.icarcnr.business.charts.service;


import it.icarcnr.business.report.bean.ChartBean;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaSensorValue;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IServiceChartBL {
		
	public List<CriteriaSensorValue> getChartDataBean(Integer sensorId, Date startDate,Date endDate) throws Exception;
	
	public Collection<Double> getCollectionValues(ChartBean.ServiceValue serviceValue) throws Exception;
	
	
	
}
