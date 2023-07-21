package it.icarcnr.rainbow.client.util;


public interface IDateFormatUtil {
	
	public static final String dateFormat = new String("d/m/Y"); // com.gwtext.client.util.DateUtil and com.gwtext.client.data.DateField format 
	public static final String i18nDateFormat = new String("dd/MM/yyyy"); //com.google.gwt.i18n.client.DateTimeFormat format
	
	public static final String dateFormatShort = new String("d/m/y");// com.gwtext.client.util.DateUtil and com.gwtext.client.data.DateField format
	public static final String i18nDateFormatShort = new String("dd/MM/yy"); //com.google.gwt.i18n.client.DateTimeFormat format
	
	public static final String dateTimeFormat = new String("d/m/Y H:i");// com.gwtext.client.util.DateUtil and com.gwtext.client.data.DateField format
	public static final String i18nDateTimeFormat = new String("dd/MM/yyyy HH:mm"); //com.google.gwt.i18n.client.DateTimeFormat format
	
	public static final String dateTimeFormatShort = new String("d/m/y H:i");// com.gwtext.client.util.DateUtil and com.gwtext.client.data.DateField format
	public static final String i18nDateTimeFormatShort= new String("dd/MM/yy HH:mm");//com.google.gwt.i18n.client.DateTimeFormat format
	
	public static final String timeDateFormat = new String("H:i");// com.gwtext.client.util.DateUtil and com.gwtext.client.data.DateField format
	public static final String i18nTimeDateFormat = new String("HH:mm");//com.google.gwt.i18n.client.DateTimeFormat format

}
