package it.icarcnr.integration.dao.login.service;

import it.icarcnr.integration.dao.generated.User;
import it.icarcnr.integration.dao.generated.UserPassword;

public interface IUserAdvancedDAO {
	
	public User login(String name, String password);
	
	public UserPassword oldestPSW(Integer id);
	
	public Long findCountUserPasswordByID(Integer id);

}
