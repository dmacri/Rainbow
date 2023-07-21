package it.icarcnr.business.utility.service;

import it.icarcnr.integration.dao.generated.Action;
import it.icarcnr.integration.dao.generated.Cronjob;
import it.icarcnr.integration.dao.generated.Utility;
import it.icarcnr.integration.dao.generated.UtilityAnf;

import java.util.Date;
import java.util.List;

public interface IUtilityBL {
	
	public List<Utility> getUtilities (List<Integer> enabledNetworkFunctionList, String actionPath, Integer networkId)throws Exception;
	
	public List<UtilityAnf> getUtilityAnf(List<Integer> enabledNetworkFunctionList, Integer networkId, Integer idUtility, String actionPath) throws Exception;
	
	public List<Action> getUtilityParentActions(List<Integer> enabledNetworkFunctionList) throws Exception;
	
	public void setCronjob(Integer idUser, String namejob, String nametrigger, Date date, Utility groupjob, String output, List<String> command, Cronjob parent) throws Exception;
	
	public List<Cronjob> getParentCronjobs(List<Integer> enabledNetworkFunctionList, Integer utilityGroupId, Cronjob cj, String view) throws Exception;

}
