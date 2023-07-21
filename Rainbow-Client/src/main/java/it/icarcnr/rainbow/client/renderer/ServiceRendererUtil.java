package it.icarcnr.rainbow.client.renderer;

import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.data.Record;

public abstract class ServiceRendererUtil {

	static IServices iServices = (IServices)GWT.create(IServices.class);

	/**
	 * @param record
	 * @return
	 */
	public static Map<String, String> getStringThresholdValues(Record record) {
		
		boolean majorValueHasDefined = !record.isEmpty("majorValue"); //$NON-NLS-1$
		boolean criticalValueHasDefined = !record.isEmpty("criticalValue"); //$NON-NLS-1$
		boolean secondaryMajorValueHasDefined = !record.isEmpty("secondaryMajorValue"); //$NON-NLS-1$
		boolean secondaryCriticalValueHasDefined = !record.isEmpty("secondaryCriticalValue"); //$NON-NLS-1$

		String typeCheck = record.getAsString("valueTypeCheck"); //$NON-NLS-1$

		StringBuilder majorValueString = new StringBuilder();
		StringBuilder criticalValueString = new StringBuilder();

		String valueToBeDefined = iServices.CriteriaServiceView_To_Be_Defined();

		if(majorValueHasDefined){
			if(record.getAsDouble("majorValue") == record.getAsInteger("majorValue") ){
				majorValueString.append(String.valueOf(record.getAsInteger("majorValue"))); //$NON-NLS-1$
			}else{
				majorValueString.append(String.valueOf(record.getAsDouble("majorValue"))); //$NON-NLS-1$
			}
		}else{
			majorValueString.append(valueToBeDefined);
		}
		if(criticalValueHasDefined){
			if(record.getAsDouble("criticalValue") == record.getAsInteger("criticalValue") ){
				criticalValueString.append(String.valueOf(record.getAsInteger("criticalValue"))); //$NON-NLS-1$
			}else{
				criticalValueString.append(String.valueOf(record.getAsDouble("criticalValue"))); //$NON-NLS-1$
			}
		}else{
			criticalValueString.append(valueToBeDefined);
		}

		if(typeCheck.equals("range")){
			majorValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryMajorValueHasDefined){
				if(record.getAsDouble("secondaryMajorValue") == record.getAsInteger("secondaryMajorValue") ){
					majorValueString.append(record.getAsInteger("secondaryMajorValue")); //$NON-NLS-1$
				}else{
					majorValueString.append(record.getAsDouble("secondaryMajorValue")); //$NON-NLS-1$
				}
			}else{
				majorValueString.append(valueToBeDefined);
			}
			majorValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");

			criticalValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryCriticalValueHasDefined){
				if(record.getAsDouble("secondaryCriticalValue") == record.getAsInteger("secondaryCriticalValue") ){
					criticalValueString.append(String.valueOf(record.getAsInteger("secondaryCriticalValue"))); //$NON-NLS-1$
				}else{
					criticalValueString.append(String.valueOf(record.getAsDouble("secondaryCriticalValue"))); //$NON-NLS-1$
				}
			}else{
				criticalValueString.append(valueToBeDefined);
			}
			criticalValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");
		}

		Map<String, String> thresholdStringValues = new HashMap<String, String>(2);
		thresholdStringValues.put("majorValueString", majorValueString.toString());
		thresholdStringValues.put("criticalValueString", criticalValueString.toString());
		return thresholdStringValues;
	}
	
	
	/**
	 * @param record
	 * @return
	 */
	public static Map<String, String> getStringThresholdDeltaValues(Record record) {
		
		boolean majorValueHasDefined = !record.isEmpty("majorDeltaValue"); //$NON-NLS-1$
		boolean criticalValueHasDefined = !record.isEmpty("criticalDeltaValue"); //$NON-NLS-1$
		boolean secondaryMajorValueHasDefined = !record.isEmpty("secondaryMajorDeltaValue"); //$NON-NLS-1$
		boolean secondaryCriticalValueHasDefined = !record.isEmpty("secondaryCriticalDeltaValue"); //$NON-NLS-1$

		String typeCheck = record.getAsString("deltaValueTypeCheck"); //$NON-NLS-1$

		StringBuilder majorValueString = new StringBuilder();
		StringBuilder criticalValueString = new StringBuilder();

		String valueToBeDefined = iServices.CriteriaServiceView_To_Be_Defined();

		if(majorValueHasDefined){
			if(record.getAsDouble("majorDeltaValue") == record.getAsInteger("majorDeltaValue") ){
				majorValueString.append(String.valueOf(record.getAsInteger("majorDeltaValue"))); //$NON-NLS-1$
			}else{
				majorValueString.append(String.valueOf(record.getAsDouble("majorDeltaValue"))); //$NON-NLS-1$
			}
		}else{
			majorValueString.append(valueToBeDefined);
		}
		if(criticalValueHasDefined){
			if(record.getAsDouble("criticalDeltaValue") == record.getAsInteger("criticalDeltaValue") ){
				criticalValueString.append(String.valueOf(record.getAsInteger("criticalDeltaValue"))); //$NON-NLS-1$
			}else{
				criticalValueString.append(String.valueOf(record.getAsDouble("criticalDeltaValue"))); //$NON-NLS-1$
			}
		}else{
			criticalValueString.append(valueToBeDefined);
		}

		if("range".equals(typeCheck)){
			majorValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryMajorValueHasDefined){
				if(record.getAsDouble("secondaryMajorDeltaValue") == record.getAsInteger("secondaryMajorDeltaValue") ){
					majorValueString.append(record.getAsInteger("secondaryMajorDeltaValue")); //$NON-NLS-1$
				}else{
					majorValueString.append(record.getAsDouble("secondaryMajorDeltaValue")); //$NON-NLS-1$
				}
			}else{
				majorValueString.append(valueToBeDefined);
			}
			majorValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");

			criticalValueString.append(" ("+iServices.CriteriaServiceView_Above()+"), ");
			if(secondaryCriticalValueHasDefined){
				if(record.getAsDouble("secondaryCriticalDeltaValue") == record.getAsInteger("secondaryCriticalDeltaValue") ){
					criticalValueString.append(String.valueOf(record.getAsInteger("secondaryCriticalDeltaValue"))); //$NON-NLS-1$
				}else{
					criticalValueString.append(String.valueOf(record.getAsDouble("secondaryCriticalDeltaValue"))); //$NON-NLS-1$
				}
			}else{
				criticalValueString.append(valueToBeDefined);
			}
			criticalValueString.append(" ("+iServices.CriteriaServiceView_Below()+")");
		}

		Map<String, String> thresholdStringValues = new HashMap<String, String>(2);
		thresholdStringValues.put("majorDeltaValueString", majorValueString.toString());
		thresholdStringValues.put("criticalDeltaValueString", criticalValueString.toString());
		return thresholdStringValues;
	}
}
