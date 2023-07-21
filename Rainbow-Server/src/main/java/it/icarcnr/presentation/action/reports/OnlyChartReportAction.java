//package it.icarcnr.presentation.action.reports;
//
//import it.icarcnr.business.charts.impl.ServiceChartBL;
//import it.icarcnr.business.charts.service.IServiceChartBL;
//import it.icarcnr.business.charts.util.IChartsConstants.ChartMode;
//import it.icarcnr.business.release.impl.ServiceReleaseBL;
//import it.icarcnr.business.release.service.IServiceReleaseBL;
//import it.icarcnr.business.release.util.IReleaseConstants;
//import it.icarcnr.business.report.bean.ChartBean;
//import it.icarcnr.business.report.impl.ChartReportBL;
//import it.icarcnr.business.report.service.IChartReportBL;
//import it.icarcnr.business.report.util.IReportConstants;
//import it.icarcnr.business.report.util.JasperExporter;
//import it.icarcnr.integration.dao.generated.EntityManagerHelper;
//import it.icarcnr.integration.dao.generated.Release;
//import it.icarcnr.presentation.action.util.IErrorMessageConstants;
//import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;
//import it.icarcnr.presentation.action.util.SecurityUtil;
//import it.icarcnr.util.IDateFormatUtil;
//import it.icarcnr.util.JSONUtil;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//
//public class OnlyChartReportAction extends Action{
//
//	private static final Log log = LogFactory.getLog(OnlyChartReportAction.class);
//
//	@Override
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//
//		final String realPath = request.getSession().getServletContext().getRealPath(File.separator);
//
//
//		final String method = "execute";
//
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
//			
//			Boolean radioService = Boolean.valueOf(request.getParameter("radioService"));
//
//			ChartMode chartMode;
//			if(radioService){
//				chartMode = ChartMode.CHART_BY_SERVICE;
//			}else{
//				chartMode = ChartMode.CHART_BY_NODE;
//			}
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat(IDateFormatUtil.DATE_FORMAT);
//			SimpleDateFormat timeDateFormat = new SimpleDateFormat(IDateFormatUtil.TIME_DATE_FORMAT);
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
//			List<ChartBean> chartBeans = iServiceChart.getChartDataBean(enabledNetworkFunctionList, serviceIdList, startDate, endDate, networkId,chartMode);
//			Release versionRelease = iServiceRelease.findById(IReleaseConstants.CURRENT_VERSION_ID);
//
//
//			JRBeanCollectionDataSource jrDataSource= null;
//
//
//			String nameService = "";
//			StringBuilder sourceAndNameNode = new StringBuilder();
//			SimpleDateFormat dateTimeFormatShort = new SimpleDateFormat(IDateFormatUtil.DATE_TIME_FORMAT_SHORT);
//			SimpleDateFormat fileNameDateTimeFormat = new SimpleDateFormat( IDateFormatUtil.FILE_NAME_DATE_TIME_FORMAT);
//			String from = dateTimeFormatShort.format(startDate);
//			String to = dateTimeFormatShort.format(endDate);;
//			String fileNameFromDate = fileNameDateTimeFormat.format(startDate);
//			String fileNameToDate = fileNameDateTimeFormat.format(endDate);
//			String labelNameType = null;
//			String labelElements = null;
//
//			List<ChartBean.ServiceValue> serviceValues = new ArrayList<ChartBean.ServiceValue>();
//			for (ChartBean chartBean : chartBeans) {
//				StringBuilder nodeDescription=null;
//				serviceValues.addAll(chartBean.getServiceValueList());
//				
//				if(radioService){
//					labelNameType = "Servizio:";
//					labelElements ="Nodo:"; 
//					nameService = chartBean.getService();
//					nodeDescription = new StringBuilder(chartBean.getNodeByIdNode());
//					 if(chartBean.getNodeByIdNodeReceiver()!=null){
//							
//							nodeDescription.append( " --> ");
//							nodeDescription.append(chartBean.getNodeByIdNodeReceiver());
//						}
//					 
//						if(!chartBean.getNodeByIdNode().equals(chartBean.getSource())){
//							nodeDescription.append("(");
//							nodeDescription.append(chartBean.getSource());
//							nodeDescription.append(")");
//						}
//						if(sourceAndNameNode.length()>0){
//							sourceAndNameNode.append(", ");
//						}
//						sourceAndNameNode.append(nodeDescription);
//					}else{
//						labelNameType= "Nodo:";
//						labelElements = "Servizio:";
//						nameService = chartBean.getSource();
//						nodeDescription = new StringBuilder(chartBean.getService());
//						
//						 if(chartBean.getNodeByIdNodeReceiver()!=null){
//								nodeDescription.append(chartBean.getNodeByIdNode());
//								nodeDescription.append(" verso ");
//								nodeDescription.append(chartBean.getNodeByIdNodeReceiver());
//							}
//						
//						if(!chartBean.getNodeByIdNode().equals(chartBean.getSource())){
//							nodeDescription.append("(");
//							nodeDescription.append(chartBean.getSource());
//							nodeDescription.append(")");
//						}
//						if(sourceAndNameNode.length()>0){
//							sourceAndNameNode.append(", ");
//						}
//						sourceAndNameNode.append(nodeDescription);
//						
//					}
//				
//			}
//
//			if(chartBeans!=null && !chartBeans.isEmpty()){
//				ChartBean chartBean = chartBeans.iterator().next();
//				List<ChartBean.ServiceValue> serviceValueList = chartBean.getServiceValueList();
//				if(serviceValueList!=null && !serviceValueList.isEmpty()){
//					
//					jrDataSource = new  JRBeanCollectionDataSource(serviceValues);
//				}
//			}
//
//			String jasperTemplateFileName = realPath + "report" + File.separator + "onlyChart.jrxml";
//			String logoTelecom = realPath + "images" + File.separator + "logo_TELECOM_ITALIA.jpg" ;
//			String logoItaltel = realPath + "images" + File.separator +"italtel_logo_Roots_col.jpg";
//
//			HashMap<String, Object> paramerMap = new HashMap<String, Object>();
//
//			IChartReportBL iChartReportBL = new ChartReportBL();
//
//			JFreeChart jFreeChart = iChartReportBL.getJFreeChart(chartBeans,startDate,endDate,chartMode);
//
//
//			//				BufferedImage image  =jFreeChart.createBufferedImage(1024,768);
//			//				BufferedImage image  =jFreeChart.createBufferedImage(1440,900);
//			BufferedImage image  =jFreeChart.createBufferedImage(1152,868);
//			ByteArrayOutputStream byteArray = new ByteArrayOutputStream() ;
//
//			ChartUtilities.writeBufferedImageAsPNG(byteArray, image, true, 1);
//			InputStream bigInputStream =  new ByteArrayInputStream(byteArray.toByteArray());
//
//
//
//			paramerMap.put("chartImage", bigInputStream);
//			paramerMap.put("version", versionRelease.getVersion());
//			paramerMap.put("networkDescription", networkDescription);
//			paramerMap.put("functionDescription", functionDescription);
//			paramerMap.put("nodeTypeDescription", nodeTypeDescription);
//			paramerMap.put("labelNameType", labelNameType);
//			paramerMap.put("labelElements", labelElements);
//			paramerMap.put("sourceAndNameNode", sourceAndNameNode.toString());
//			paramerMap.put("nameService",nameService);
//			paramerMap.put("from", from);
//			paramerMap.put("to", to);
//			paramerMap.put("logoTelecom", logoTelecom);
//			paramerMap.put("logoItaltel", logoItaltel);
//
//
//
//			JasperPrint jasperPrint = getJasperPrint(jasperTemplateFileName, paramerMap, jrDataSource);
//
//			StringBuilder fileName = new StringBuilder();
//			fileName.append(nameService);
//			fileName.append("_");
//			fileName.append(fileNameFromDate);
//			fileName.append("_a_");
//			fileName.append(fileNameToDate);
//			fileName.append("_grafico.pdf");
//
//			response.reset();		
//			response.setContentType("application/pdf");
//			response.setHeader("Content-disposition","attachment; filename= \""+fileName.toString()+"\"");
//
//			JasperExporter.exportToPDF(response.getOutputStream(), jasperPrint);
//
//
//		} catch (Exception e) {
//			log.error(method,e);
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,IErrorMessageConstants.INTERNAL_SERVER_ERROR);
//		}
//		finally{
//			EntityManagerHelper.closeEntityManager();
//		}
//		return null;
//	}
//
//	public static JasperPrint getJasperPrint(String jasperTemplateFileName,HashMap<String, Object> parameters,JRDataSource jrDataSource) throws JRException {
//		final String method = "getJasperPrint(String jasperTemplateFileName,HashMap<String, Object> parameters,JRDataSource jrDataSource)";
//
//		String jasperCompiledFileName = jasperTemplateFileName.replace(IReportConstants.JRXML_EXTENSION, IReportConstants.JASPER_EXTENSION);
//		JasperPrint jasperPrint = null;
//		try {
//			boolean exists = (new File(jasperCompiledFileName)).exists();
//			if (!exists) {
//
//				JasperCompileManager.compileReportToFile(jasperTemplateFileName,jasperCompiledFileName);
//			}
//
//			jasperPrint = JasperFillManager.fillReport(jasperCompiledFileName,parameters,jrDataSource);
//		} catch (JRException e) {
//			log.error(method, e);
//			throw e;
//		}
//		return jasperPrint;
//	}
//
//
//}
