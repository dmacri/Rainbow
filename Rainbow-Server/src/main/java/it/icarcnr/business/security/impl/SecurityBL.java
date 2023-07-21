package it.icarcnr.business.security.impl;

import it.icarcnr.business.security.service.ISecurityBL;
import it.icarcnr.integration.dao.generated.IUserDAO;
import it.icarcnr.integration.dao.generated.User;
import it.icarcnr.integration.dao.generated.UserDAO;
import it.icarcnr.integration.dao.security.impl.GroupAnfAdvancedDAO;
import it.icarcnr.integration.dao.security.service.IGroupAnfAdvancedDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityBL implements ISecurityBL {

	private static final Log log = LogFactory.getLog(SecurityBL.class);

	/* (non-Javadoc)
	 * @see it.icarcnr.businesslogic.security.service.ISecurityBL#getEnabledNetworkFunctionList(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public List<Integer> getEnabledNetworkFunctionList(Integer userId,
			String actionPath, Integer networkId, Integer functionId)
			throws Exception {
		final String method = "getEnabledNetworkFunctionList(Integer userId, String actionPath, Integer networkId, Integer functionId)";
		log.info(method);
		try{
			IUserDAO iUserDAO = new UserDAO();
			User user = iUserDAO.findById(userId);
			IGroupAnfAdvancedDAO iGroupAnfAdvancedDAO = new GroupAnfAdvancedDAO();
			return iGroupAnfAdvancedDAO.getEnabledNetworkFunctionList(user.getGroup().getId(),actionPath,networkId,functionId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.businesslogic.security.service.ISecurityBL#hasPermission(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	public boolean hasPermission(Integer userId, String actionPath,
			Integer networkId, Integer functionId) throws Exception {
		final String method = "hasPermission(Integer userId, String actionPath, Integer networkId, Integer functionId)";
		log.info(method);
		try{
			IUserDAO iUserDAO = new UserDAO();
			User user = iUserDAO.findById(userId);
			IGroupAnfAdvancedDAO iGroupAnfAdvancedDAO = new GroupAnfAdvancedDAO();
			return iGroupAnfAdvancedDAO.hasPermission(user.getGroup().getId(), actionPath, networkId, functionId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.businesslogic.security.service.ISecurityBL#hasPermission(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public boolean hasPermission(Integer userId, Integer areaId, Integer networkId, Integer functionId) throws Exception {
		final String method = "hasPermission(Integer userId, Integer areaId, Integer networkId, Integer functionId)";
		log.info(method);
		try{
			IUserDAO iUserDAO = new UserDAO();
			User user = iUserDAO.findById(userId);
			IGroupAnfAdvancedDAO iGroupAnfAdvancedDAO = new GroupAnfAdvancedDAO();
			return iGroupAnfAdvancedDAO.hasPermission(user.getGroup().getId(), areaId, networkId, functionId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}


}
