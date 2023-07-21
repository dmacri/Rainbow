/**
 * 
 */
package it.icarcnr.business.cronjob.service;

import it.icarcnr.integration.dao.generated.Cronjob;


public interface ICronJobBL {
	public void update(Cronjob cronjob) throws Exception;
	
	public Cronjob findByNameJob(String namejob) throws Exception;
	
	public Cronjob findByIdJob(String idCronjob) throws Exception;
}
