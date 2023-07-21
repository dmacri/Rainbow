package it.icarcnr.integration.dao.cronjob.service;

import it.icarcnr.integration.dao.generated.Cronjob;

import java.util.List;

public interface ICronjobAdvancedDAO {
	
	public List<Cronjob> findByNullEndExecution ();
	
	public List<Cronjob> findByParentCronjob(List<Integer> enabledNetworkFunctionList, Integer utilityGroupId, Cronjob cronjob, String view);

}
