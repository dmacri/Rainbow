/**
 * 
 */
package it.icarcnr.business.function.impl;

import it.icarcnr.business.function.service.IFunctionBL;
import it.icarcnr.integration.dao.function.impl.FunctionAdvancedDAO;
import it.icarcnr.integration.dao.function.service.IFunctionAdvancedDAO;
import it.icarcnr.integration.dao.generated.Function;
import it.icarcnr.integration.dao.generated.Sensore;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FunctionBL implements IFunctionBL {

	private static final Log log = LogFactory.getLog(FunctionBL.class);

	/* (non-Javadoc)
	 * @see it.icarcnr.businesslogic.function.service.IFunctionBL#findById(java.lang.Integer)
	 */
	public Function findById(List<Integer> enabledNetworkFunctionList, Integer functionId) throws Exception {
		final String method = "findById(List<Integer> enabledNetworkFunctionList, Integer functionId)";
		log.info(method);
		try{
			IFunctionAdvancedDAO iFunctionAdvancedDAO = new FunctionAdvancedDAO();
			return iFunctionAdvancedDAO.findById(enabledNetworkFunctionList, functionId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see it.icarcnr.business.function.service.IFunctionBL#findAll(java.util.List, Integer networkId)
	 */
	public List<Function> findAll(List<Integer> enabledNetworkFunctionList, Integer networkId) throws Exception {
		final String method = "findAll(List<Integer> enabledNetworkFunctionList, networkId)";
		log.info(method);
		try{
			IFunctionAdvancedDAO iFunctionAdvancedDAO = new FunctionAdvancedDAO();
			return iFunctionAdvancedDAO.findAll(enabledNetworkFunctionList, networkId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}
	public List<Sensore> findBySensore(List<Integer> enabledNetworkFunctionList, Integer sensorId) {
		final String method = "findAll(List<Integer> enabledNetworkFunctionList, networkId)";
		log.info(method);
		try{
			IFunctionAdvancedDAO iFunctionAdvancedDAO = new FunctionAdvancedDAO();
			return iFunctionAdvancedDAO.findBySensore(enabledNetworkFunctionList,sensorId);
		}catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}

}
