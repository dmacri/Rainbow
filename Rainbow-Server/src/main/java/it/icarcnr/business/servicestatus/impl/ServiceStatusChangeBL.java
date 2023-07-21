package it.icarcnr.business.servicestatus.impl;

import it.icarcnr.business.servicestatus.bean.CriteriaStatusChangeBean;
import it.icarcnr.business.servicestatus.bean.CriteriaStatusChangeSequenceBean;
import it.icarcnr.business.servicestatus.bean.ServiceStatusChangeBean;
import it.icarcnr.business.servicestatus.service.ICriteriaStatusBL;
import it.icarcnr.business.servicestatus.service.IServiceStatusChangeBL;
import it.icarcnr.integration.dao.generated.Criteria;
import it.icarcnr.integration.dao.generated.CriteriaChangeStatus;
import it.icarcnr.integration.dao.generated.Request;
import it.icarcnr.integration.dao.generated.Service;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaHistoryBean;
import it.icarcnr.integration.dao.servicestatus.impl.CriteriaStatusChageAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.impl.ServiceStatusChangeAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaStatusChangeAdvancedDAO;
import it.icarcnr.integration.dao.servicestatus.service.IServiceStatusChangeAdvancedDAO;
import it.icarcnr.integration.dao.util.IServiceConstants;
import it.icarcnr.util.IDateFormatUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceStatusChangeBL implements IServiceStatusChangeBL{

	private static final Log log = LogFactory.getLog(ServiceStatusChangeBL.class);


	public List<ServiceStatusChangeBean> getServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception {
		final String method = "etServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap)";
		log.info(method);
		try{
			List<ServiceStatusChangeBean> serviceStatusChangeBeans = new ArrayList<ServiceStatusChangeBean>();
			ServiceBL serviceBL = new ServiceBL();
			IServiceStatusChangeAdvancedDAO iServiceStatusChangeAdvancedDAO = new ServiceStatusChangeAdvancedDAO();
			ICriteriaStatusChangeAdvancedDAO iCriteriaStatusChangeAdvancedDAO = new CriteriaStatusChageAdvancedDAO();

			List<Service> services = iServiceStatusChangeAdvancedDAO.getServiceStatusChangeList(enabledNetworkFunctionList, currentDate, startDetectionDate,criteriaMap);

			for (Service service : services) {
				
				Integer serviceId = service.getId();
				Integer networkId = service.getNodeByIdNode().getNetwork().getId();
				ServiceStatusChangeBean serviceStatusChangeBean = new ServiceStatusChangeBean();
				serviceStatusChangeBean.setServiceId(serviceId);
				serviceStatusChangeBean.setNetworkId(networkId);

				serviceStatusChangeBean.setServiceDescription(service.getRequest().getDescription());
				serviceStatusChangeBean.setSource(serviceBL.getSources(service));
				serviceStatusChangeBean.setNetwork(service.getNodeByIdNode().getNetwork().getDescription());
				serviceStatusChangeBean.setServiceType(service.getFunction().getName());

				if(service.getNodeByIdNode()!=service.getNodeByIdReceiver()){
					serviceStatusChangeBean.setNodeTo(service.getNodeByIdReceiver().getDescription());
					serviceStatusChangeBean.setNodeFrom(service.getNodeByIdNode().getDescription());
					serviceStatusChangeBean.setReference(service.getNodeByIdNode().getDescription());
				}else{
					serviceStatusChangeBean.setReference(service.getNodeByIdNode().getDescription());
					serviceStatusChangeBean.setNodeTo("<center>-</center>");
					serviceStatusChangeBean.setNodeFrom("<center>-</center>");

				}


				List<CriteriaChangeStatus> criteriaChangeStatusList = iCriteriaStatusChangeAdvancedDAO.getCriteriaStatusChangeByService(criteriaMap, currentDate, startDetectionDate, serviceId);

				List<CriteriaStatusChangeSequenceBean> criteriaStatusChangeSequenceBeans = new ArrayList<CriteriaStatusChangeSequenceBean>();
				while(!criteriaChangeStatusList.isEmpty()){
					CriteriaStatusChangeSequenceBean criteriaStatusChangeSequenceBean = extractFirstSequence(criteriaChangeStatusList,startDetectionDate,serviceId,networkId);
					criteriaStatusChangeSequenceBeans.add(0,criteriaStatusChangeSequenceBean);
				}
				serviceStatusChangeBean.setCriteriaStatusChangeSequenceBeans(criteriaStatusChangeSequenceBeans);

				addServiceStatusChangeBeanOrderedByDate(serviceStatusChangeBeans, serviceStatusChangeBean);


			}

			return serviceStatusChangeBeans;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}


	public List<Request> getStatusChangeRequestList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception {
		final String method = "getStatusChangeRequestList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap)";
		log.info(method);
		try{

			IServiceStatusChangeAdvancedDAO iServiceStatusChangeAdvancedDAO = new ServiceStatusChangeAdvancedDAO();

			return iServiceStatusChangeAdvancedDAO.getStatusChangeRequestList(enabledNetworkFunctionList, currentDate, startDetectionDate,criteriaMap);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	/**
	 * @param serviceStatusChangeBeans
	 * @param serviceStatusChangeBean
	 */
	private void addServiceStatusChangeBeanOrderedByDate( List<ServiceStatusChangeBean> serviceStatusChangeBeans,ServiceStatusChangeBean serviceStatusChangeBean) {

		CriteriaStatusChangeSequenceBean  lastSequenceSSCBToAdd = serviceStatusChangeBean.getCriteriaStatusChangeSequenceBeans().get(0);
		CriteriaStatusChangeBean lastSequenceEventSSCBToAdd = lastSequenceSSCBToAdd.getCriteriaStatusChangeBeans().get(lastSequenceSSCBToAdd.getCriteriaStatusChangeBeans().size()-1);

		boolean found = false;
		Iterator<ServiceStatusChangeBean> itereator = serviceStatusChangeBeans.iterator();
		int i = 0;
		while (itereator.hasNext() && !found) {
			ServiceStatusChangeBean sscb = (ServiceStatusChangeBean) itereator.next();
			CriteriaStatusChangeSequenceBean  lastSequence = sscb.getCriteriaStatusChangeSequenceBeans().get(0);
			CriteriaStatusChangeBean lastSequenceEvent = lastSequence.getCriteriaStatusChangeBeans().get(lastSequence.getCriteriaStatusChangeBeans().size()-1);
			if (lastSequenceEventSSCBToAdd.getDate().after(lastSequenceEvent.getDate())){
				found = true;
			}else{
				i++;
			}
		}

		serviceStatusChangeBeans.add(i,serviceStatusChangeBean);

	}

	private CriteriaStatusChangeSequenceBean extractFirstSequence(List<CriteriaChangeStatus> criteriaChangeStatusList,Date startDetectionDate, Integer serviceId,Integer networkId) throws Exception{
		List<CriteriaChangeStatus> criteriaChangeStatusToRemove = new ArrayList<CriteriaChangeStatus>();
		IServiceStatusChangeAdvancedDAO iServiceStatusChangeBL = new ServiceStatusChangeAdvancedDAO();
		ICriteriaStatusBL iCriteriaStatusBL = new CriteriaStatusBL();
		boolean startSequence = true;
		boolean endSequence = false;
		Date startSequenceDate = null;
		String operationType = null;
		StringBuilder descriptionSequence = new StringBuilder();
		SimpleDateFormat dateTimeFormat= new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT);
		CriteriaStatusChangeSequenceBean criteriaStatusChangeSequenceBean = null;
		
		for (int i = 0; !endSequence && i < criteriaChangeStatusList.size(); i++) {
			CriteriaChangeStatus criteriaChangeStatus = criteriaChangeStatusList.get(i);
			if(startSequence){
				criteriaStatusChangeSequenceBean = new CriteriaStatusChangeSequenceBean();
				if(IServiceConstants.ON_TIME.equals(criteriaChangeStatus.getServiceOperation().getOperation().getName())){
					descriptionSequence.append("Value");
					operationType = IServiceConstants.ON_TIME;
				}else if(IServiceConstants.STEP_BY_STEP.equals(criteriaChangeStatus.getServiceOperation().getOperation().getName())){
					descriptionSequence.append("&#916Value");
					operationType = IServiceConstants.STEP_BY_STEP;
				}
				startSequenceDate = criteriaChangeStatus.getDate();
				List<CriteriaStatusChangeBean> criteriaStatusChangeBeans = new ArrayList<CriteriaStatusChangeBean>();
				criteriaStatusChangeSequenceBean.setCriteriaStatusChangeBeans(criteriaStatusChangeBeans);
				startSequence = false;
			}
			if(operationType.equals(criteriaChangeStatus.getServiceOperation().getOperation().getName())){
				criteriaChangeStatusToRemove.add(criteriaChangeStatus);
				CriteriaStatusChangeBean criteriaStatusChangeBean = new CriteriaStatusChangeBean();
				
				Criteria criteria = iCriteriaStatusBL.getActiveCriteria(criteriaChangeStatus.getDate(),criteriaChangeStatus.getServiceOperation().getId());
				criteriaStatusChangeBean.setTypeCheck(criteria.getTypeCheck());
				criteriaStatusChangeBean.setOperation(criteriaChangeStatus.getServiceOperation().getOperation().getName());

				CriteriaHistoryBean criteriaHistory = iServiceStatusChangeBL.getServiceHistory(criteriaChangeStatus.getServiceOperation().getId(),criteriaChangeStatus.getDate(), networkId);
				

				if(operationType.equals(IServiceConstants.ON_TIME)){

					//workaround  threshold must be defined - to be: set to null and not to -1
					Double majorValue = criteriaHistory.getValueCheckMajor();
					if(majorValue!=null && majorValue == -1){
						majorValue = null;
					}
					criteriaStatusChangeBean.setMajorValue(majorValue);
					Double criticalValue = criteriaHistory.getValueCheckCritical();
					if(criticalValue!=null && criticalValue == -1){
						criticalValue = null;
					}
					criteriaStatusChangeBean.setCriticalValue(criticalValue);

					Double secondaryMajorValue = criteriaHistory.getValueCheckSecondaryMajor();
					if(secondaryMajorValue!=null && secondaryMajorValue == -1){
						secondaryMajorValue = null;
					}
					criteriaStatusChangeBean.setSecondaryMajorValue(secondaryMajorValue);

					Double secondaryCriticalValue = criteriaHistory.getValueCheckSecondaryCritical();
					if(secondaryCriticalValue!=null && secondaryCriticalValue == -1){
						secondaryCriticalValue = null;
					}
					criteriaStatusChangeBean.setSecondaryCriticalValue(secondaryCriticalValue);
				}else if(operationType.equals(IServiceConstants.STEP_BY_STEP)){
					criteriaStatusChangeBean.setMajorDeltaValue(criteriaHistory.getValueCheckMajor());
					criteriaStatusChangeBean.setCriticalDeltaValue(criteriaHistory.getValueCheckCritical());
					criteriaStatusChangeBean.setSecondaryMajorDeltaValue(criteriaHistory.getValueCheckSecondaryMajor());
					criteriaStatusChangeBean.setSecondaryCriticalDeltaValue(criteriaHistory.getValueCheckSecondaryCritical());
				}
				criteriaStatusChangeBean.setDate(criteriaChangeStatus.getDate());
				criteriaStatusChangeBean.setFromStatus(criteriaChangeStatus.getStatusFrom());
				criteriaStatusChangeBean.setToStatus(criteriaChangeStatus.getStatusTo());
				criteriaStatusChangeBean.setValue(criteriaChangeStatus.getValueAfter());

				criteriaStatusChangeSequenceBean.getCriteriaStatusChangeBeans().add(criteriaStatusChangeBean);

				if(IServiceConstants.NORMAL.equals(criteriaChangeStatus.getStatusTo()) || i == criteriaChangeStatusList.size()-1 ){
					if(isDisserviceDuring(criteriaChangeStatus.getStatusTo())){
						descriptionSequence.append(" - Disservizio dal <b>"+dateTimeFormat.format(startSequenceDate)+"</b>");
					}else{
						descriptionSequence.append(" - Dal <b>"+dateTimeFormat.format(startSequenceDate)+"</b>");
						descriptionSequence.append(" al <b>"+dateTimeFormat.format(criteriaChangeStatus.getDate())+"</b>");
					}
					criteriaStatusChangeSequenceBean.setDescription(descriptionSequence.toString());
					criteriaStatusChangeSequenceBean.setOperationType(operationType);
					//					move to end method setDescription and setOperationType
					endSequence = true;
				}
			}
		}

		criteriaChangeStatusList.removeAll(criteriaChangeStatusToRemove);

		return criteriaStatusChangeSequenceBean;

	}

	public Long getTotalServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap) throws Exception {
		final String method = "getTotalServiceStatusChangeList(List<Integer> enabledNetworkFunctionList, Date currentDate, Date startDetectionDate, Map criteriaMap)";
		log.info(method);
		try{
			IServiceStatusChangeAdvancedDAO iServiceStatusChangeAdvancedDAO = new ServiceStatusChangeAdvancedDAO();
			Long total  = iServiceStatusChangeAdvancedDAO.getTotalServiceStatusChangeList(enabledNetworkFunctionList, currentDate, startDetectionDate ,criteriaMap);
			return total;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}


	/**
	 * @param string
	 * @return
	 */
	private boolean isDisserviceDuring(String status) {
		if(IServiceConstants.CRITICAL.equals(status) || IServiceConstants.MAJOR.equals(status)){
			return true;
		}
		return false;
	}

}
