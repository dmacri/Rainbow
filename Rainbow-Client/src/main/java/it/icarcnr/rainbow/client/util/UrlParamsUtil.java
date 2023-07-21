
package it.icarcnr.rainbow.client.util;

import java.util.Set;

import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.gwtext.client.core.UrlParam;


public class UrlParamsUtil {

	public static String toUrlString(UrlParam[] params) {
		StringBuilder sb = new StringBuilder();
		boolean moreThanOneElement = false;
		if(params!=null){
			for (UrlParam urlParam : params) {
				if (moreThanOneElement){
					sb.append("&");
				}
				String encodedName = URL.encodeComponent(urlParam.getName());
				sb.append(encodedName);
				sb.append("=");
				String encodedValue = URL.encodeComponent(urlParam.getValue());
				sb.append(encodedValue);
				moreThanOneElement = true;
			}
		}
		return sb.toString();
	}
	
	public static UrlParam[] getUrlParams(JSONObject parameters){
		UrlParam[] params = null;
		if(parameters!=null){
			Set<String> keys = parameters.keySet();
			if(keys!=null && !keys.isEmpty()){
				params = new UrlParam[keys.size()];
				int i = 0;
				for (String key : keys) {
					JSONValue jsonValue = parameters.get(key);
					if(jsonValue.isString()!=null){
						params[i]=new UrlParam(key,jsonValue.isString().stringValue());
					}else{
						params[i]=new UrlParam(key,jsonValue.toString());
					}
					i++;
				}
			}
		}
		return params;
	}
	
}

