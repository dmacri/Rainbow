package it.icarcnr.business.release.service;

import it.icarcnr.integration.dao.generated.Release;

public interface IServiceReleaseBL {
	
	public Release findById(Integer id) throws Exception ;

}
