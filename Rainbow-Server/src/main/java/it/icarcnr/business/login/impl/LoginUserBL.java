package it.icarcnr.business.login.impl;

import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.business.login.bean.UserLoginBean;
import it.icarcnr.business.login.service.ILoginUserBL;
import it.icarcnr.business.util.EncoderUtil;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.IUserDAO;
import it.icarcnr.integration.dao.generated.IUserPasswordDAO;
import it.icarcnr.integration.dao.generated.User;
import it.icarcnr.integration.dao.generated.UserDAO;
import it.icarcnr.integration.dao.generated.UserPassword;
import it.icarcnr.integration.dao.generated.UserPasswordDAO;
import it.icarcnr.integration.dao.login.impl.UserAdvancedDAO;
import it.icarcnr.integration.dao.login.service.IUserAdvancedDAO;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginUserBL implements ILoginUserBL {
	
	private static final Log log = LogFactory.getLog(LoginUserBL.class);

	public UserLoginBean login(String userName, String password) throws Exception {
		final String method = "login(String userName, String password)";
		log.info(method);
		try{
			Date currentDate = Calendar.getInstance().getTime();
			UserLoginBean userLoginBean = null;
			if(userName!=null && password!=null){
				IUserAdvancedDAO iUserAdvancedDAO = new UserAdvancedDAO();
				String encodedPassword = EncoderUtil.getMD5EncodedString(password);
				User user = iUserAdvancedDAO.login(userName, encodedPassword);
				if(user!=null){
					userLoginBean = new UserLoginBean();
					userLoginBean.setId(user.getId());
					userLoginBean.setName(user.getName());
					userLoginBean.setSurname(user.getSurname());
					userLoginBean.setUsername(user.getUsername());
					Date expiryDate = user.getExpire();
					userLoginBean.setExpiryDate(expiryDate);
					userLoginBean.setFirstAccess(Boolean.TRUE);
					userLoginBean.setExpired(Boolean.FALSE);
					if(expiryDate!=null){
						userLoginBean.setFirstAccess(Boolean.FALSE);
						if(expiryDate.before(currentDate)){
							userLoginBean.setExpired(Boolean.TRUE);
						}
					}
				}
			}
			return userLoginBean;
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	
	
	private void moveInfo(User user, Date currentDate) throws Exception {
	// Muove i dati da user a userpassword per lo store	
		final String method = "moveInfo(User user, Date currentDate)";
		try {
		      UserPassword userPassword = new UserPassword();
		      userPassword.setUser(user);
	          userPassword.setValue(user.getPassword());
	          Timestamp currentTimestamp=new Timestamp(currentDate.getTime()); 
	          userPassword.setDate(currentTimestamp);
	          IUserPasswordDAO iUserPasswordDAO=new UserPasswordDAO();
	          
	          iUserPasswordDAO.save(userPassword); // Storage elem into UserPassword
	          
	          IUserAdvancedDAO userAdvancedDAO = new UserAdvancedDAO();
	          Long numUserPsw =userAdvancedDAO.findCountUserPasswordByID(user.getId());
	          
	          if (numUserPsw > IApplicationGlobalConstants.MAX_NUMBER_OLD_PSW){
	        	  IUserAdvancedDAO iuserAdvancedDAO=new UserAdvancedDAO();
	        	UserPassword userPasswordOld=iuserAdvancedDAO.oldestPSW(user.getId());
	        	if (userPasswordOld!=null){ 
	        		iUserPasswordDAO.delete(userPasswordOld); // delete elem for UserPassword
	        	}
	          }
	   } catch (Exception e) {
		   log.error(method,e);
		   throw e;
	   }
		
	}
	
	
	public Boolean pswControl(Integer id, String newPassword) throws Exception {
		final String method = "pswControl(Integer id, String newPassword)";	
		
		Boolean flag=new Boolean(false);
		String encodedNewPassword = EncoderUtil.getMD5EncodedString(newPassword);
		try {
		   IUserPasswordDAO iUserPswDAO=new UserPasswordDAO();
		   IUserDAO iUserDAO = new UserDAO();
		   List<UserPassword> usersPsw =  (List<UserPassword>) iUserPswDAO.findByProperty("user", iUserDAO.findById(id));
		   for (UserPassword userPassword : usersPsw) {
			 if (userPassword.getValue().equals(encodedNewPassword)){
				 flag=flag.TRUE; // flag is true if newPassword is present into userPassword
			     break;
			 }
		   }
		 } catch (Exception e) {
			   log.error(method,e);
			   throw e;
		   }
		return flag; // return a Boolean true if flag is true
	}
	
	
	public void updatePassword(Integer id, String newPassword) throws Exception {
		final String method = "updatePassword(Integer id, String newPassword)";	
		try {
			Calendar calendar = Calendar.getInstance();
			Date currentDate = Calendar.getInstance().getTime();
			calendar.add(Calendar.MONTH, 2);
			Date expireDate = calendar.getTime();
			IUserDAO iUserDAO = new UserDAO();
			String encodedNewPassword = EncoderUtil.getMD5EncodedString(newPassword);
			
			EntityManagerHelper.beginTransaction();
			
			User user = iUserDAO.findById(id);
			if(user!=null){
				moveInfo(user,currentDate);
				user.setPassword(encodedNewPassword);
				user.setExpire(expireDate);
			}
			iUserDAO.update(user);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			if ( EntityManagerHelper.getEntityManager().getTransaction().isActive() ){
				EntityManagerHelper.rollback();
			}
			log.error(method,e);
			throw e;
		}
	}

	public User findById(Integer userId) throws Exception {
		final String method = "findById(Integer userId)";
		log.info(method);
		try{
			IUserDAO iUserDao = new UserDAO();
			return iUserDao.findById(userId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	

}
