package it.icarcnr.integration.dao.util;

import it.icarcnr.integration.dao.generated.NetworkFunction;

import java.util.List;

import javax.persistence.Query;


public class JPAUtil {
	public static void setPagination(Query query, final int... rowStartIdxAndCount){
		if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
			int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
			if (rowStartIdx > 0) {
				query.setFirstResult(rowStartIdx);
			}
			if (rowStartIdxAndCount.length > 1) {
				int rowCount = Math.max(0, rowStartIdxAndCount[1]);
				if (rowCount > 0) {
					query.setMaxResults(rowCount);
				}
			}
		}
	}
	
	public static void addSecurityFrom(List<Integer> enabledNetworkFunctionList, StringBuilder queryString) {
		if(enabledNetworkFunctionList != null  && !enabledNetworkFunctionList.isEmpty()){
			queryString.append(" ,"+NetworkFunction.class.getName()+" as networkFunction_security ");
		}
	}

	public static Boolean addSecurityCondition(List<Integer> enabledNetworkFunctionList,
			StringBuilder queryString,
			String networkRoot,
			String functionRoot,
			Boolean firstWhereCondition) {
		if(enabledNetworkFunctionList != null  && !enabledNetworkFunctionList.isEmpty()){

			StringBuilder condition = new StringBuilder(" ( ");
			condition.append(" networkFunction_security.id in (:enabledNetworkFunctionList) ");
			
			if(networkRoot!=null){
				condition.append("AND networkFunction_security.network.id = "+networkRoot+"network.id ");
			}
			
			if(functionRoot!=null){
				condition.append("AND networkFunction_security.function.id = "+functionRoot+"function.id ");
			}

			condition.append(" ) ");

			firstWhereCondition = addWhereCondition(queryString, firstWhereCondition, condition.toString());
		}

		return firstWhereCondition;
	}
	
	public static void addSecurityParameter(List<Integer> enabledNetworkFunctionList, Query query) {
		if(enabledNetworkFunctionList != null  && !enabledNetworkFunctionList.isEmpty()){
			query.setParameter("enabledNetworkFunctionList", enabledNetworkFunctionList);
		}
	}

	/**
	 * @param queryString
	 * @param firstWhereCondition
	 * @param condition
	 * @return
	 */
	public static Boolean addWhereCondition(StringBuilder queryString,
			Boolean firstWhereCondition, String condition) {
		if(firstWhereCondition){
			queryString.append(" WHERE ");
			firstWhereCondition = false;
		}else{
			queryString.append(" AND ");
		}
		queryString.append(condition);
		return firstWhereCondition;
	}

}
