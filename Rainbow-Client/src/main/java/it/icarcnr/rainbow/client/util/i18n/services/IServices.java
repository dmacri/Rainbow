package it.icarcnr.rainbow.client.util.i18n.services;

import com.google.gwt.i18n.client.Constants;

public interface IServices extends Constants {
	
	/**
	  Services.ServiceStatus.java
	 */	
	String warning();
	String takeCharge();
	String ServiceStatus_Alarm_already_taken_in_care();
	String ServiceStatus_All();
	String ServiceStatus_Criteria_for();
	String ServiceStatus_Custom();
	String ServiceStatus_Date();
	String ServiceStatus_Displaying_sevices_of();
	String ServiceStatus_Enter_page_size();
	String ServiceStatus_Log_of();
	String ServiceStatus_Log_View(); 
	String ServiceStatus_No_services_to_display();
	String ServiceStatus_Page_size();
	String ServiceStatus_Select_page_size(); 
	String ServiceStatus_Service();
	String ServiceStatus_Reference();
	String ServiceStatus_Source();
	String ServiceStatus_Status();
	String ServiceStatus_Tacke_charge();
	String ServiceStatus_NodeFrom();
	String ServiceStatus_NodeTo();
	String ServiceStatus_Value();
	String ServiceStatus_Delta_Value();
	String ServiceStatus_Value_Criteria_for();
	String ServiceStatus_Network();
	String ServiceStatus_Function();
	/**
	  Services.CriteriaServiceView.java
	 */
	String CriteriaServiceView_all_days();
	String CriteriaServiceView_Time_Interval();
	String CriteriaServiceView_Critical_Value(); 
	String CriteriaServiceView_Friday();
	String CriteriaServiceView_from_Monday_to_Friday();
	String CriteriaServiceView_Major_Value(); 
	String CriteriaServiceView_Monday();
	String CriteriaServiceView_Sampling_Period();
	String CriteriaServiceView_Saturday();
	String CriteriaServiceView_Saturday_and_Sunday();
	String CriteriaServiceView_Sunday(); 
	String CriteriaServiceView_Thursday();
	String CriteriaServiceView_Tuesday();
	String CriteriaServiceView_Type_Check();
	String CriteriaServiceView_Threshold_Type();
	String CriteriaServiceView_Wednesday();
	String CriteriaServiceView_Below();
	String CriteriaServiceView_Above();
	String CriteriaServiceView_Range();
	String CriteriaServiceView_To_Be_Defined();
	
	String CriteriaServiceView_Static_Threshold();
	String CriteriaServiceView_Dynamic_Threshold();
	String CriteriaServiceView_Status();
	String CriteriaServiceView_Type_Suspended();
	String CriteriaServiceView_Suspended_By_Operator();
	String CriteriaServiceView_Suspended_By_System();
	String CriteriaServiceView_Threshold_Active();
	
	
	/**
	 * Services.JobStatus.java
	 */
	String JobStatus_Actions();
	String JobStatus_Acknoledgment_failed();
	String JobStatus_Acknoledgment_forbidden();
	String JobStatus_Acknowledgement();
	String JobStatus_Validation();
	String JobStatus_Are_you_sure_you_want_to_do_that();
	String JobStatus_Comment();
	String JobStatus_Date();
	String JobStatus_Error();
	String JobStatus_Service();
	String JobStatus_Source();
	String JobStatus_Start_date();
	String JobStatus_Status();
	String JobStatus_Taking_Charge();
	String JobStatus_Reference();
	String JobStatus_NodeFrom();
	String JobStatus_NodeTo();
	String JobStatus_User();
	String JobStatus_Warning();
	String JobStatus_Close_Job();
	String JobStatus_Suspend_Threshold();
	String JobStatus_Change_Comment();
	String JobStatus_It_Is_Not_Possible_To_Close(); 
	/**
	 * Services.LogViewPanel
	 */
	String LogViewPanel_HTTP_Status_401_User_is_not_authorized_to_access_this_action();
	/**
	 *Services.StatusChangeHistory 
	 */
	String StatusChangeHistory_An_error_was_encountered_while_loading();
	String StatusChangeHistory_Collapse_All();
	String StatusChangeHistory_Date();
	String StatusChangeHistory_day_days();
	String StatusChangeHistory_From_Satus();
	String StatusChangeHistory_Loading();
	String StatusChangeHistory_Noitemstoview();
	String StatusChangeHistory_Real_Time_Detected();
	String StatusChangeHistory_Service_Status_Change_History();
	String StatusChangeHistory_Target_Service();
	String StatusChangeHistory_To_Status();
	String StatusChangeHistory_Value();
	String StatusChangeHistory_View_last(); 
	/**
	 *#Services.TakeCarePanel 
	 */
	
	String TakeCarePanel_Alarm_already_taken_in_care();
	String TakeCarePanel_Comment();
	String TakeCarePanel_Error();
	String TakeCarePanel_HTTP_Status_401();
	String TakeCarePanel_Save();
	String TakeCarePanel_Save_();
	String TakeCarePanel_Warning();
	String TakeCarePanel_Cancel();
	String TakeCarePanel_List_Suspended_Threshold();
	String TakeCarePanel_Threshold_Disabled_Can_Not_Suspend();
	String TakeCarePanel_The_Operation_Execute_Will_Be_Active_At_The_Next_Sampling();
	String TakeCarePanel_Suspended_Threshold();
	String TakeCarePanel_Service_Suspended_By_System();
	

}
