//package it.icarcnr.business.job.service;
//
//import it.icarcnr.business.job.bean.TakingChargeBean;
//import it.icarcnr.integration.dao.generated.Job;
//
//import java.util.List;
//
//public interface IJobBL{
//	
//	public Job takeCare(List<Integer> enabledNetworkFunctionList, Integer serviceId, Integer userId, String comment) throws Exception;
//
//	public Boolean close(List<Integer> enabledNetworkFunctionList, Integer userId, Integer jobId)throws Exception;
//
//	public List<TakingChargeBean> getTakingChargeBeans(List<Integer> enabledNetworkFunctionList, String status, int... rowStartIdxAndCount) throws Exception;
//	
//	public void changeCommentJob(List<Integer> enabledNetworkFunctionList,Integer jobId, String comment)	throws Exception;
//
//}
