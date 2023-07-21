package it.icarcnr.presentation.action.reports;

import it.icarcnr.business.charts.impl.ServiceChartBL;
import it.icarcnr.business.charts.service.IServiceChartBL;
import it.icarcnr.business.charts.util.IChartsConstants.ChartMode;
import it.icarcnr.business.release.impl.ServiceReleaseBL;
import it.icarcnr.business.release.service.IServiceReleaseBL;
import it.icarcnr.business.release.util.IReleaseConstants;
import it.icarcnr.business.report.bean.ChartBean;
import it.icarcnr.business.report.util.IReportConstants;
import it.icarcnr.business.report.util.JasperExporter;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.Release;
import it.icarcnr.presentation.action.util.IErrorMessageConstants;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
import it.icarcnr.presentation.action.util.SecurityUtil;
import it.icarcnr.util.IDateFormatUtil;
import it.icarcnr.util.JSONUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CsvReportAction extends Action{

	private static final Log log = LogFactory.getLog(CsvReportAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		final String realPath = request.getSession().getServletContext().getRealPath(File.separator);
		final String method = "execute";

//		try {
//			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);
//
//			List<Integer> serviceIdList = null;
//
//			String serviceIdListString = request.getParameter(IParameterHttpServletRequestContants.SERVICE_ID);
//			if(serviceIdListString!= null){
//				serviceIdList = JSONUtil.getIntegerList(serviceIdListString);
//			}
//
//			Integer networkId = request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID)):null;
//			String networkDescription = request.getParameter("networkDescription");
//			String functionDescription = request.getParameter("functionDescription");
//			String nodeTypeDescription = request.getParameter("nodeTypeDescription");
//			Boolean radioService = Boolean.valueOf(request.getParameter("radioService"));
//			
//			ChartMode chartMode;
//			if(radioService){
//				chartMode = ChartMode.CHART_BY_SERVICE;
//			}else{
//				chartMode = ChartMode.CHART_BY_NODE;
//			}
//
//
//			JRBeanCollectionDataSource jrDataSource= null;
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat(IDateFormatUtil.DATE_FORMAT);
//			SimpleDateFormat timeDateFormat = new SimpleDateFormat(IDateFormatUtil.TIME_DATE_FORMAT);
//			
//			Date dateStart = dateFormat.parse(request.getParameter("dateStart"));
//			Date timeStart = timeDateFormat.parse(request.getParameter("timeStart"));
//			Date dateEnd = dateFormat.parse(request.getParameter("dateEnd"));
//			Date timeEnd = timeDateFormat.parse(request.getParameter("timeEnd"));
//
//			Date startDate = new Date(dateStart.getTime()+timeStart.getTime()+60*60*1000);
//			Date endDate = new Date(dateEnd.getTime()+timeEnd.getTime()+60*60*1000);
//
//			IServiceChartBL iServiceChart = new ServiceChartBL();	
//			IServiceReleaseBL iServiceRelease = new ServiceReleaseBL();
//
//			List<ChartBean> chartBeans = iServiceChart.getChartDataBean(enabledNetworkFunctionList,serviceIdList, startDate, endDate, networkId,chartMode);
//			Release versionRelease = iServiceRelease.findById(IReleaseConstants.CURRENT_VERSION_ID);
//
//			String nameService = "";
//			SimpleDateFormat dateTimeFormatShort = new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT_SHORT);
//			SimpleDateFormat fileNameDateTimeFormat = new SimpleDateFormat( IDateFormatUtil.FILE_NAME_DATE_TIME_FORMAT);
//			
//			String from = dateTimeFormatShort.format(startDate);
//			String to = dateTimeFormatShort.format(endDate);;
//			String fileNameFromDate = fileNameDateTimeFormat.format(startDate);
//			String fileNameToDate = fileNameDateTimeFormat.format(endDate);
//			
//			String labelNameType = null;
//			String labelElements = null;
//
//			String sourceNode = "";
//
//			List<ChartBean.ServiceValue> serviceValues = new ArrayList<ChartBean.ServiceValue>();
//
//			for (ChartBean chartBean : chartBeans) {
//				serviceValues.addAll(chartBean.getServiceValueList());
//			}
//
//
//			if(chartBeans!=null && !chartBeans.isEmpty()){
//				ChartBean chartBean = chartBeans.iterator().next();
//				List<ChartBean.ServiceValue> serviceValueList = chartBean.getServiceValueList();
//
//				if(serviceValueList!=null && !serviceValueList.isEmpty()){
//					if(radioService){
//						labelNameType = "Servizio:";
//						labelElements ="Nodi:"; 
//						nameService = chartBean.getService();
//						
//					}else{
//						labelNameType= "Nodo:";
//						labelElements = "Servizi:";
//						nameService = chartBean.getSource();
//												
//					}
//					
//
//					jrDataSource = new  JRBeanCollectionDataSource(serviceValues);
//
//				}
//			}
//
//
//
//			HashMap<String, Object> paramerMap = new HashMap<String, Object>();
//			paramerMap.put("version", versionRelease.getVersion());
//			paramerMap.put("networkDescription", networkDescription);
//			paramerMap.put("labelElements", labelElements);
//			paramerMap.put("labelNameType", labelNameType);
//			paramerMap.put("nameService",nameService);
//			paramerMap.put("functionDescription", functionDescription);
//			paramerMap.put("nodeTypeDescription", nodeTypeDescription);
//			paramerMap.put("from", from);
//			paramerMap.put("to", to);
//			paramerMap.put("sourceNode", sourceNode);
		
		
		//  ------------ CSV Rainbow
		
		
		try {
			List<Integer> enabledNetworkFunctionList = SecurityUtil.getEnabledNetworkFunctionList(request);

			List<Integer> serviceIdList = null;

			String serviceIdListString = request.getParameter(IParameterHttpServletRequestContants.SERVICE_ID);
			if(serviceIdListString!= null){
				serviceIdList = JSONUtil.getIntegerList(serviceIdListString);
			}

			Integer sensorId = request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)):null;
			String nodeSensorDescription = request.getParameter("networkDescription");
			String functionDescription = request.getParameter("functionDescription");
		
			
		
			JRBeanCollectionDataSource jrDataSource= null;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(IDateFormatUtil.DATE_FORMAT);
			SimpleDateFormat timeDateFormat = new SimpleDateFormat(IDateFormatUtil.TIME_DATE_FORMAT);
			
			Date dateStart = dateFormat.parse(request.getParameter("dateStart"));
			Date timeStart = timeDateFormat.parse(request.getParameter("timeStart"));
			Date dateEnd = dateFormat.parse(request.getParameter("dateEnd"));
			Date timeEnd = timeDateFormat.parse(request.getParameter("timeEnd"));

			Date startDate = new Date(dateStart.getTime()+timeStart.getTime()+60*60*1000);
			Date endDate = new Date(dateEnd.getTime()+timeEnd.getTime()+60*60*1000);

			IServiceChartBL iServiceChart = new ServiceChartBL();	
			IServiceReleaseBL iServiceRelease = new ServiceReleaseBL();

//			List<ChartBean> chartBeans = iServiceChart.getChartDataBean(enabledNetworkFunctionList,serviceIdList, startDate, endDate, networkId,chartMode);
			
			List<it.icarcnr.integration.dao.servicestatus.bean.CriteriaSensorValue> chartBean = iServiceChart.getChartDataBean(sensorId,startDate, endDate);
			
			Release versionRelease = iServiceRelease.findById(IReleaseConstants.CURRENT_VERSION_ID);

			
			SimpleDateFormat dateTimeFormatShort = new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT_SHORT);
			SimpleDateFormat fileNameDateTimeFormat = new SimpleDateFormat( IDateFormatUtil.FILE_NAME_DATE_TIME_FORMAT);
			
			String from = dateTimeFormatShort.format(startDate);
			String to = dateTimeFormatShort.format(endDate);;
			String fileNameFromDate = fileNameDateTimeFormat.format(startDate);
			String fileNameToDate = fileNameDateTimeFormat.format(endDate);
			
			String labelNameType = null;
			String labelElements = null;

			String sourceNode = "";


			jrDataSource = new  JRBeanCollectionDataSource(chartBean);

			HashMap<String, Object> paramerMap = new HashMap<String, Object>();
			paramerMap.put("version", versionRelease.getVersion());
			paramerMap.put("networkDescription", nodeSensorDescription);
			paramerMap.put("labelElements", labelElements);
			paramerMap.put("labelNameType", labelNameType);
			paramerMap.put("functionDescription", functionDescription);

			paramerMap.put("from", from);
			paramerMap.put("to", to);
		



			String jasperTemplateFileName = realPath + "report" + File.separator + "csvReport.jrxml";

			JasperPrint jasperPrint = getJasperPrint(jasperTemplateFileName, paramerMap, jrDataSource);

			StringBuilder fileName = new StringBuilder();
			fileName.append(nodeSensorDescription);
			fileName.append("_");
			fileName.append(fileNameFromDate);
			fileName.append("_da_");
			fileName.append(fileNameToDate);
			fileName.append("_datiCSV.csv");


			response.reset();
			response.setContentType("application/csv");
			response.setHeader("Content-disposition","attachment; filename= \""+fileName.toString()+"\"");

			JasperExporter.exportToCsv(response.getOutputStream(), jasperPrint);


		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}

	public static JasperPrint getJasperPrint(String jasperTemplateFileName,HashMap<String, Object> parameters, JRDataSource jrDataSource) throws JRException {
		final String method = "getJasperPrint(String jasperTemplateFileName,JRDataSource jrDataSource)";

		String jasperCompiledFileName = jasperTemplateFileName.replace(IReportConstants.JRXML_EXTENSION, IReportConstants.JASPER_EXTENSION);
		JasperPrint jasperPrint = null;
		try {
			boolean exists = (new File(jasperCompiledFileName)).exists();
			if (!exists) {

				JasperCompileManager.compileReportToFile(jasperTemplateFileName,jasperCompiledFileName);
			}

			jasperPrint = JasperFillManager.fillReport(jasperCompiledFileName,parameters,jrDataSource);
		} catch (JRException e) {
			log.error(method, e);
			throw e;
		}
		return jasperPrint;
	}

}
