package it.icarcnr.business.report.util;

import java.awt.Color;

public interface IChartsReportConstants {
	Color [] CHART_LINE_COLOURS = {
			/*"#0000FF"*/new Color(0,0,255),
			/*"#006400"*/new Color(0,100,0),
			/*"#F16243"*/new Color(241,98,67),
			/*"#068481"*/new Color(6,132,129),
			/*"#104E8B"*/new Color(16,78,139),
			/*"#F6CC3A"*/new Color(246,204,58),
			/*"#6B238E"*/new Color(107,35,142),
			/*"#EE1289"*/new Color(238,18,137),
			/*"#8B4500"*/new Color(139,69,0),
			/*"#8B6508"*/new Color(139,101,8),
			/*"#8B8878"*/new Color(139,136,120),
			/*"#FF7F00"*/new Color(255,127,0),
			/*"#23238E"*/new Color(35,35,142)};
	
	Color MAJOR_LINE_COLOR = new Color(246,204,58);/*"#F6CC3A"*/
	Color CRITICAL_LINE_COLOR = new Color(241,98,67);/*"#F16243"*/
}
