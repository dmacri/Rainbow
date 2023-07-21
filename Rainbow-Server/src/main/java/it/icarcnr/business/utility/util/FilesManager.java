package it.icarcnr.business.utility.util;

import it.icarcnr.business.config.util.IApplicationGlobalConstants;
import it.icarcnr.util.IDateFormatUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FilesManager {

	private static final Log log = LogFactory.getLog(FilesManager.class);
	

	public static final Integer NUMBER_OF_RANDOM_STRING = 3;

	public static synchronized File createFileName(String ext) throws Exception { // ext is the name of utility
		final String method = "execute";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(IDateFormatUtil.GENERATED_FILE_DATE_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
			String dateStr = sdf.format(date);
			String name = String.format("%s_%s_%s", ext.replaceAll(" ", ""), dateStr, RandomStringUtils.randomAlphanumeric(FilesManager.NUMBER_OF_RANDOM_STRING));

			File dir = new File(IApplicationGlobalConstants.UTILITY_LOG_ABSOLUTE_PATH);
			File file = new File(dir, name);
			while (file.exists()){// if file exist re-generate name
				date = new Date();
				dateStr = sdf.format(date);
				name = String.format("%s_%s_%s", ext, dateStr, RandomStringUtils.randomAlphanumeric(FilesManager.NUMBER_OF_RANDOM_STRING));
				file = new File(dir, name);
			}
			file.createNewFile(); // if !file.exists
			return file;
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}

	}


	public static String[] listFiles(String directoryAbsolutePath) throws Exception {
		final String method = "execute";
		try {
			File filesDir = new File(directoryAbsolutePath);
			String[] files = filesDir.list();
			return files;
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}


	public static String fileReader(String pathFile, String newLineString) throws Exception {
		final String method = "execute";
		try {
			String str = new String();
			String outputFile = new String();
			FileReader filereader = new FileReader(pathFile);
			BufferedReader fileBufferedReader = new BufferedReader(filereader);
			str=fileBufferedReader.readLine();
			StringBuilder s = new StringBuilder();
			while(str!=null){
				s.append(str);
				str=fileBufferedReader.readLine();
				if(str!=null && newLineString!=null){
					s.append(newLineString);
				}
			}
			if(s.toString()!=null){
				outputFile=s.toString();
			}else{
				outputFile="";
			}
			return outputFile;
		} catch (Exception e) {
			log.error(method,e);
			throw e;
		}
	}


	public static String escapeHTML(String s){
		int length = s.length();
		int newLength = length;
		boolean someCharacterEscaped = false;

		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32){
				switch(c){
				case '\r':
				case '\n':
				case '\t':
				case '\f':{
				} break;
				default: {
					newLength -= 1;
					someCharacterEscaped = true;
				}
				}
			} else {
				switch(c){
				case '\"':{
					newLength += 5;
					someCharacterEscaped = true;
				} break;
				case '&':
				case '\'':{
					newLength += 4;
					someCharacterEscaped = true;
				} break;
				case '<':
				case '>':{
					newLength += 3;
					someCharacterEscaped = true;
				} break;
				}
			}
		}
		if (!someCharacterEscaped){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32){
				switch(c){
				case '\r':
				case '\n':
				case '\t':
				case '\f':{
					sb.append(c);
				} break;
				default: {
					// Remove this character
				}
				}
			} else {
				switch(c){
				case '\"':{
					sb.append("&quot;");
				} break;
				case '\'':{
					sb.append("&#39;");
				} break;
				case '&':{
					sb.append("&amp;");
				} break;
				case '<':{
					sb.append("&lt;");
				} break;
				case '>':{
					sb.append("&gt;");
				} break;
				default: {
					sb.append(c);
				}
				}
			}
		}
		return sb.toString();
	}


}

