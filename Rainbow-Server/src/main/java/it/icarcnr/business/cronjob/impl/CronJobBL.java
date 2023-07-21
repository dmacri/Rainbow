/**
 * 
 */
package it.icarcnr.business.cronjob.impl;

import it.icarcnr.business.cronjob.service.ICronJobBL;
import it.icarcnr.integration.dao.generated.Cronjob;
import it.icarcnr.integration.dao.generated.CronjobDAO;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.ICronjobDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CronJobBL implements ICronJobBL {
	
	private static final Log log = LogFactory.getLog(CronJobBL.class);

	/* (non-Javadoc)
	 * @see it.icarcnr.business.cronjob.service.ICronJobBL#update(it.icarcnr.integration.dao.generated.Cronjob)
	 */
	public void update(Cronjob cronjob) throws Exception {
		final String method = "upadateCronJob (Cronjob cronjob)";
		log.info(method);
		ICronjobDAO iCronjobDAO = new CronjobDAO();
		try {			
			EntityManagerHelper.beginTransaction();
			iCronjobDAO.update(cronjob);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			if ( EntityManagerHelper.getEntityManager().getTransaction().isActive() ){
				EntityManagerHelper.rollback();
			}
			log.error(method,e);
			throw e;
		}

	}

 	
	public Cronjob findByNameJob(String nameJob) throws Exception {
		final String method = "findByNameJob (String nameJob)";
		log.info(method);
		try {              			
			ICronjobDAO iCronJobDAO = new CronjobDAO();
			Cronjob cronJob = null;
			List<Cronjob> cronJobList = iCronJobDAO.findByNamejob(nameJob);
			if(cronJobList.size() == 1){
				cronJob = cronJobList.get(0);
			}else if(cronJobList.size() > 1){
				throw new Exception("findByNamejob(nameJob) returned more than one result for nameJob = "+nameJob);
			}
			return cronJob;
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	
	
	public Cronjob findByIdJob(String idCronjob) throws Exception {
		final String method = "findByIdJob (Integer idCronjob)";
		log.info(method);
		try{
			ICronjobDAO cjd = new CronjobDAO();
			return cjd.findById(Integer.parseInt(idCronjob));
		
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	
}
