package it.icarcnr.integration.dao.util;

import it.icarcnr.integration.dao.generated.CriteriaDAO;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getDayOfWeek(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		String dayOfWeekString = null;
		switch (dayOfWeek) {
		case Calendar.SUNDAY:
			dayOfWeekString = CriteriaDAO.SUNDAY;
			break;
		case Calendar.MONDAY:
			dayOfWeekString = CriteriaDAO.MONDAY;
			break;
		case Calendar.TUESDAY:
			dayOfWeekString = CriteriaDAO.TUESDAY;
			break;
		case Calendar.WEDNESDAY:
			dayOfWeekString = CriteriaDAO.WEDNESDAY;
			break;
		case Calendar.THURSDAY:
			dayOfWeekString = CriteriaDAO.THURSDAY;
			break;
		case Calendar.FRIDAY:
			dayOfWeekString = CriteriaDAO.FRIDAY;
			break;
		case Calendar.SATURDAY:
			dayOfWeekString = CriteriaDAO.SATURDAY;
			break;
		default:
			break;
		}
		return dayOfWeekString;
	}
}
