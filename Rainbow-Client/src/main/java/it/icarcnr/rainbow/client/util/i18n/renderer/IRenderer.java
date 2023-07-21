package it.icarcnr.rainbow.client.util.i18n.renderer;

import com.google.gwt.i18n.client.Constants;

public interface IRenderer extends Constants {
	
	/**
	 * Renderer.JobAknowledgmentRenderer
	 */
	String JobAknowledgmentRenderer_ext_qtip_Double_click_to_close_job();
	String JobAknowledgmentRenderer_ext_qtip_Single_click_to_close_job();
	String JobAknowledgmentRenderer_ext_qtip_Single_click_to_execute_service_opearations();
	
	/**
	 * #Renderer.ServiceDeltaStatusRenderer
	 */
	String ServiceDeltaStatusRenderer_Critical();
	String ServiceDeltaStatusRenderer_Double_click_for_more_informations();
	String ServiceDeltaStatusRenderer_Single_click_for_more_informations();
	String ServiceDeltaStatusRenderer_Major();
	String ServiceDeltaStatusRenderer_to_be_defined();
	
/**
 * 	#Renderer.ServiceExtendedDescriptionRenderer
 */
	String ServiceExtendedDescriptionRenderer_Sampling_Period();
	String ServiceExtendedDescriptionRenderer_Unavailable();
	
/**
 * 	#Renderer.ServiceValueStatusRenderer
 */
	String ServiceValueStatusRenderer_Critical_Value();
	String ServiceValueStatusRenderer_Double_click_for_more_informations();
	String ServiceValueStatusRenderer_Single_click_for_more_informations();
	String ServiceValueStatusRenderer_Major_Value();
	String ServiceValueStatusRenderer_to_be_defined();
	
	/**
	 * #Renderer.TakeCareRenderer
	 */
	String TakeCareRenderer_Double_click_to_take_charge_service();
	String TakeCareRenderer_Single_click_to_take_charge_service();
	String TakeCareRenderer_Service_taken_over();
	
	/**
	 * #Renderer.LogServiceRenderer
	 */
	String LogServiceRenderer_Double_click_to_view_log();
	String LogServiceRenderer_Single_click_to_view_log();
	
	/**
	 * #Renderer.FromServiceRenderer
	 */
	String FromServiceRenderer_Counter_Way();
	
	/**
	 * #Renderer.NetworkServiceStatusSummaryRenderer
	 */
	String NetServiceStatusSummary_Single_click_to_view_Graph();
}
