package it.icarcnr.rainbow.client.util.i18n.charts;

import com.google.gwt.i18n.client.Constants;


public interface IChart extends Constants{
	
	/**
	  Chart.ChartParameter.java
	 */
	String ChartParameter_Clear();
	String ChartParameter_Complete_Pdf();
	String ChartParameter_Criteria();
	String ChartParameter_Data_in_Csv();
	String ChartParameter_Draw_chart();
	String ChartParameter_End_Date();
	String ChartParameter_End_Time();
	String ChartParameter_Export();
	String ChartParameter_Only_Chart_Pdf();
	String ChartParameter_Start_Date();
	String ChartParameter_Start_date_i_equal_to_end_date_so_start_time_must_come_before_end_time();
	String ChartParameter_Start_date_must_come_before_end_date();
	String ChartParameter_Start_Time();
	String ChartParameter_Warning();
	String ChartParameter_You_must_select_at_least_one_node();
	String ChartParameter_You_must_select_at_least_one_service();
	String ChartParameter_Range_of_more_than_31_days();
	/**
	  Chart.ServiceChart.java
	 */
	String ServiceChart_Critical();
	String ServiceChart_Major();
	String ServiceChart_Drawing_chart();
	String ServiceChart_Error();
	String ServiceChart_Loading_data();
	String ServiceChart_No_data_to_draw();
	String ServiceChart_No_graphic();
	String ServiceChart_System_busy_Please_try_later();
	String ServiceChart_Value();

}
