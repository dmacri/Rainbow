package it.icarcnr.presentation.action.maps;


import it.icarcnr.business.maps.bean.MapsBean;
import it.icarcnr.business.maps.impl.ServiceMapsBL;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;

import it.icarcnr.presentation.action.charts.ServiceChartAction;
import it.icarcnr.presentation.action.util.HttpServletResponseUtil;
import it.icarcnr.presentation.action.util.IParameterHttpServletRequestContants;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceMapsAction extends Action {

	private static final Log log = LogFactory.getLog(ServiceChartAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		final String method = "execute";
		try {
			JSONObject result = new JSONObject();

			Integer sensorId = request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)!=null?Integer.valueOf(request.getParameter(IParameterHttpServletRequestContants.SENSOR_ID)):null;
			JSONObject valueRecord=null;
			JSONArray valuesRecord = new JSONArray();
		    ServiceMapsBL serviceMapsBL=new ServiceMapsBL();
		 	List<MapsBean> mapsList=serviceMapsBL.getMapsDataList();	
         
		 	for (MapsBean mapsBean : mapsList) {
		 		valueRecord= new JSONObject();
		 		valueRecord.put("idSensore",mapsBean.getIdSensore());
		 		valueRecord.put("idNodeSensore",mapsBean.getIdNodeSensore());
		 		valueRecord.put("descrizioneNode",mapsBean.getDescrizioneNode());
		  		valueRecord.put("dataTime",mapsBean.getDateSampling());
		 		valueRecord.put("value",mapsBean.getValue());
		 		valueRecord.put("nameSensor",mapsBean.getNameDescription());
		 		valueRecord.put("livello",mapsBean.getLivello().toString());
		 		valueRecord.put("lat",mapsBean.getLat().toString() );
		 		valueRecord.put("lng",mapsBean.getLng() );
		 		valueRecord.put("colorLevel",mapsBean.getColorLevel());
		 		valuesRecord.put(valueRecord);
			   	}
			result.put("root", valuesRecord);
              
              
              
		HttpServletResponseUtil.setJsonResponse(response);
           System.out.println(result.toString());
			response.getOutputStream().println(result.toString());
			response.flushBuffer();
		} catch (Exception e) {
			log.error(method,e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Internal server error");
			//			throw e;
		}
		finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}

}
