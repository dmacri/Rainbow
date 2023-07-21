/**
 * 
 */
package it.icarcnr.business.config.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class IApplicationGlobalConstants {

	public static final String UTILITY_LOG_ABSOLUTE_PATH ;
	public static final String VASMONCAT_MANUAL_ABSOLUTE_PATH;
	public static final String UTILITY_GEO_FILES_PATH;
	public static final String UTILITY_GEOPREFIX_PK3_FILES_PATH;
	public static final String UTILITY_GEOPREFIX_PK0_FILES_PATH;
	public static final Integer MAX_NUMBER_OLD_PSW; 
	public static final Integer MAX_ATTEMPS_LOGIN;
	public static final String VOIP_CONTROL_URL ;
	public static final Integer CNC_UPPER_BOUND;
	public static final String GEOAREA_UDB_PUBBLIC_OUTCOMES_FILE_NAME;


	private static final Log log = LogFactory.getLog(IApplicationGlobalConstants.class);	

	static {
		Properties props = new Properties();
		String utilityLogAbolutePath = "/appl/log/log_web_appl";
		String utilityGeoFilesPath = "/var/home/italtel/script/core/global/geoarea/geo_align/files";
		String utilityGeoPrefixPk3FilesPath = "/var/home/italtel/script/core/global/geoarea/geo_prefix/pk3/files";
		String utilityGeoPrefixPk0FilesPath = "/var/home/italtel/script/core/global/geoarea/geo_prefix/pk0/files";
		String vasMonCatManualAbsolutePath = "/appl/manual";
		Integer  maxNumberOldPsw = 7 ; // numero massimo di psw salvate
		Integer max_attemps_login = 3;
		String voip_control_url = "https://65.4.60.21/ManagementConsole/com.italtel.voipcontrol/services/SessionShare";
		Integer cnc_upper_bound = 3;
		String geoarea_udb_public_outcomes_file_name = "esiti_cli_udb_public";
		try {
		    InputStream inputStream = IApplicationGlobalConstants.class.getResourceAsStream("/"+"ApplicationResources.properties");
			props.load( inputStream);
			String enviroment = props.getProperty("enviroment");
			utilityLogAbolutePath = props.getProperty(enviroment+".path.absolute.utility.log.folder");
			utilityGeoFilesPath=props.getProperty(enviroment+".path.absolute.utility.geo.files");
			utilityGeoPrefixPk3FilesPath = props.getProperty(enviroment+".path.absolute.utility.geoprefix.pk3.files");
			utilityGeoPrefixPk0FilesPath = props.getProperty(enviroment+".path.absolute.utility.geoprefix.pk0.files");
			vasMonCatManualAbsolutePath = props.getProperty(enviroment+".path.absolute.manual.folder");
			maxNumberOldPsw = Integer.valueOf(props.getProperty("max.number.old.psw"));
			max_attemps_login = Integer.valueOf(props.getProperty("max.number.loggin"));
			voip_control_url = props.getProperty(enviroment+".voipcontrol.url");
			cnc_upper_bound = Integer.valueOf(props.getProperty("cnc.upper.bound"));
			geoarea_udb_public_outcomes_file_name = props.getProperty("utility.geoarea.file.name.output.esiti.cli.public.udb");
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}finally{
			UTILITY_LOG_ABSOLUTE_PATH = utilityLogAbolutePath;
			UTILITY_GEO_FILES_PATH = utilityGeoFilesPath;
			UTILITY_GEOPREFIX_PK3_FILES_PATH = utilityGeoPrefixPk3FilesPath;
			UTILITY_GEOPREFIX_PK0_FILES_PATH = utilityGeoPrefixPk0FilesPath;
			VASMONCAT_MANUAL_ABSOLUTE_PATH = vasMonCatManualAbsolutePath;
			MAX_NUMBER_OLD_PSW = maxNumberOldPsw;
			MAX_ATTEMPS_LOGIN = max_attemps_login;
			VOIP_CONTROL_URL = voip_control_url;
			CNC_UPPER_BOUND = cnc_upper_bound;
			GEOAREA_UDB_PUBBLIC_OUTCOMES_FILE_NAME = geoarea_udb_public_outcomes_file_name;
		}

	}
}
