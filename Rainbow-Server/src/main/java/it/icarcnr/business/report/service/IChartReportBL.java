package it.icarcnr.business.report.service;

import it.icarcnr.business.charts.util.IChartsConstants.ChartMode;
import it.icarcnr.business.report.bean.ChartBean;

import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;

public interface IChartReportBL {

	public JFreeChart getJFreeChart(List<ChartBean> chartBeans,Date startDate, Date endDate, ChartMode chartMode) throws Exception;
	
}
