package it.icarcnr.business.login.service;

import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.integration.dao.generated.User;

public interface ILoginUserBL {
	
	public UserLoginBean login(String userName, String password) throws Exception;
	
	public void updatePassword(Integer id, String newPassword) throws Exception;
	
	public User findById(Integer userId) throws Exception;
	
	public Boolean pswControl(Integer id, String newPassword) throws Exception;
}
