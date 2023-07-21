package it.icarcnr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JSONUtil {


	private static final Log log = LogFactory.getLog(JSONUtil.class);

	/**
	 * Return a List of Integer from a source JSONArray text or Integer text.
	 * @param source     A string that begins with
	 * <code>[</code>&nbsp;<small>(left bracket)</small>
	 *  and ends with <code>]</code>&nbsp;<small>(right bracket)</small> or Integer text
	 * @return    a List of Integer.
	 */
	public static List<Integer> getIntegerList(String source) {
		List<Integer> integerList = null;
		JSONArray jsonArray = isJSONArray(source);
		if(jsonArray!=null){
			integerList = new ArrayList<Integer>(jsonArray.length());
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					integerList.add(jsonArray.getInt(i));
				}
			} catch (JSONException e) {
				integerList= null;
			}
		}else{
			integerList = new ArrayList<Integer>(1);
			integerList.add(Integer.valueOf(source));
		}
		return integerList;
	}


	/**
	 * Return a Map<Integer, List<Integer>> from a source JSONObject text.
	 * @param source    A string beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 * @exception JSONException If there is a syntax error in the source
	 *  string or a duplicated key.
	 * @return    a List of Integer.
	 */
	public static Map<Integer, List<Integer>> getIntegerListIntegerMap(String source) {
		Map<Integer, List<Integer>> integerListIntegerMap = null;
		JSONObject jsonObject = isJSONObject(source);
		if(jsonObject!=null){
			integerListIntegerMap = new HashMap<Integer, List<Integer>>();
			String[] keys = JSONObject.getNames(jsonObject);
			try {
				for (String key : keys) {
					JSONArray jsonArray = jsonObject.getJSONArray(key);
					List<Integer> integerList = new ArrayList<Integer>(jsonArray.length());
					for (int i = 0; i < jsonArray.length(); i++) {
						integerList.add(jsonArray.getInt(i));
					}
					integerListIntegerMap.put(Integer.valueOf(key), integerList);
				}
			} catch (JSONException e) {
				integerListIntegerMap= null;
			}
		}
		return integerListIntegerMap;
	}

	/**
	 * Return a List of String from a source JSONArray text or String text.
	 * @param source     A string that begins with
	 * <code>[</code>&nbsp;<small>(left bracket)</small>
	 *  and ends with <code>]</code>&nbsp;<small>(right bracket)</small> or text
	 * @return    a List of String.
	 */
	public static List<String> getStringList(String source) {
		List<String> stringList = null;
		JSONArray jsonArray = isJSONArray(source);
		if(jsonArray!=null){
			stringList = new ArrayList<String>(jsonArray.length());
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					stringList.add(jsonArray.getString(i));
				}
			} catch (JSONException e) {
				stringList= null;
			}
		}else{
			stringList = new ArrayList<String>(1);
			stringList.add(source);
		}
		return stringList;
	}

	/**
	 * Return a JSONArray from a source JSONArray text.
	 * @param source     A string that begins with
	 * <code>[</code>&nbsp;<small>(left bracket)</small>
	 *  and ends with <code>]</code>&nbsp;<small>(right bracket)</small>.
	 * @return     a reference to a JSONArray if this source is a JSONArray text or
	 *         <code>null</code> otherwise.
	 */
	public static JSONArray isJSONArray(String source) {
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(source);
		} catch (JSONException e) {
		}
		return jsonArray;
	}

	/**
	 * Construct a JSONObject from a source JSON text string.
	 * This is the most commonly used JSONObject constructor.
	 * @param source    A string beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 * @exception JSONException If there is a syntax error in the source
	 *  string or a duplicated key.
	 */
	public static JSONObject isJSONObject(String source) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(source);
		} catch (JSONException e) {
		}
		return jsonObject;
	}
	
	public static JSONObject createJsonParameters(Map<String,Object> parameterMap, String jsonDefaultParameters) {
		String method = "createJsonParameters(Map<String,Object> parameterMap)";
		JSONObject jsonobjParameters = new JSONObject();
		if(jsonDefaultParameters!=null){
			jsonobjParameters = createJsonParameters(jsonDefaultParameters);
		}
		if(parameterMap != null){
			Set<String> parameterMapKeySet = parameterMap.keySet();
			try {
				for (String parameterMapKey : parameterMapKeySet) {
					Object parameterMapValue = parameterMap.get(parameterMapKey);
					
					if(parameterMapValue instanceof List){
						List list = (List)parameterMapValue;
						JSONArray jsonArray = new JSONArray();
						for (Object element : list) {
							jsonArray.put(element);
						}
						jsonobjParameters.put(parameterMapKey,jsonArray);
					}
					
					else if(parameterMapValue instanceof Map){
						Map map = (Map)parameterMapValue;
						Set<Object> keySet = map.keySet();
						JSONObject jsonObject = new JSONObject();
						for (Object key : keySet) {
							Object mapValue = map.get(key);
							if(mapValue instanceof List){
								List list = (List)mapValue;
								JSONArray jsonArray = new JSONArray();
								for (Object element : list) {
									jsonArray.put(element);
								}
								jsonObject.put(key.toString(), jsonArray);
							}else{
								jsonObject.put(key.toString(), mapValue);
							}
						}
						jsonobjParameters.put(parameterMapKey,jsonObject);
					}
					
					else{
						jsonobjParameters.put(parameterMapKey,parameterMapValue);
					}
				}
			} catch (JSONException e) {
				log.error(method, e);
			}
		}
		return jsonobjParameters;
	}

	public static JSONObject createJsonParameters(String json) {
		String method = "createJsonParameters(String json)";
		JSONObject jsonobjParameters = new JSONObject();
		if(json != null){
			try {
				jsonobjParameters = new JSONObject(json);
			} catch (JSONException e) {
				log.error(method, e);
			}
		}
		return jsonobjParameters;
	}

}
