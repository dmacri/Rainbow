package it.icarcnr.business.menutree.util;

import java.util.HashMap;
import java.util.Map;

public interface IUserInterfaceConstants {
	
	String GRAPH_ICON_PATH = "images/custom/graph/";
	String GIF_EXTENSION = ".gif";
	/**
	 * Menu Tree css-icons 
	 */

	String[] nodeTypeIconCls = {null,
			"udb-italtel-icon", // type id 1
			"sip-server-icon", // type id 2
			"load-balancer-icon",  // type id 3
			"real-server-icon",	// type id 4
			"sd-icon", //type id 5
			"internode-icon", // id 6
			"district-icon", // id 7
			"internode-icon", // id 8
			"pop-icon", // id 9
			"vec-icon", // id 10
			"iudb-icon", // id 11
			"wafer-icon", // id 12 
			"pk0-pop-icon", // id 13
			"disaster-recovery", // id 14
			"pop-icon", // id 15 
			"mrf-icon", // id 16
			"socket-icon", // id 17
			"processor-icon", // id 18
			"ram-icon", // id 19
			"ascc-icon", // id 20
			"system-icon", // id 21
			"pk0-pop-icon", // id 22
			"sip-server-icon", // id 23
			"call-operator-icon", //id 24
			"location-icon",// id 25
			null, 
			"softswitch-icon", // id 27
			"opm-icon", // id 28
			"udb-ipcc-icon", //id 29
			"softswitch-icon", // id 30
			"udb-italtel-icon", // id 31
			"pop-icon",//id 32
			"pop-icon", // id 33
			"application-server-icon", //id 34
			"user-icon", // id 35
			"call-operator-icon", //id 36
			"db-icon", // id 37
			"db-icon", //id 38
			"dfip-icon" // id 39
	};
	String[] functionIconCls = {null,"service-status-icon","automatism-icon","telecom-italia-icon","dhl-icon",null,"cec-pac-icon","la-7-icon","asl-cz-icon","operation-icon"};
	String[] networkIconCls = {null,
			"pk-network-icon", // id 1
			"pk-network-icon", // id 2
			"poste-network-icon",// id 3
			"ipcc-icon",// id 4
			"performance-icon",//id 5
			"collabora-network-icon", // id 6
			"pk-network-icon"// id 7
			};
	
	
	String[] nodeTypeIcon = {"node.gif","udb-italtel-icon.gif","sip-server-icon.gif","load-balancer-icon.gif","real-server-icon.gif",
			"sd-icon.gif","internode-icon.gif","district-icon.gif","internode-icon.gif","pop-icon.gif",
			"pop-icon.gif","udb-italtel-icon.gif","wafer-icon.gif","pop-icon.gif","disaster-recovery.gif",
			"pop-icon.gif","mrf-icon.gif","socket-icon.gif","processor-icon.gif","ram-icon.gif",
			"ascc-icon.gif","system-icon.gif","pop-icon.gif","sip-server-icon.gif","call_operator.gif",
			"geo_location.gif", null, "pk-network-icon.gif","pk-network-icon.gif","udb-italtel-icon.gif"
	};
	/**
	 * Graph Rappresentation icons
	 */
	String[] networkIcon = {"node.gif","pk-network-icon.gif","pk-network-icon.gif","poste-network-icon.gif","ipcc-icon.gif","system-icon.gif","collabora.gif","pk-network-icon.gif"};
	String[] functionIcon = {"general_service.gif","general_service.gif","automatism.gif","telecom_icon.gif","dhl_icon.gif",null,"cec_pac.gif","la7.gif","asl_cz.gif"};




	Map<String, String> utilityTitle = new HashMap<String, String>(){
		{
			put("/utility/checkIpLock","Check IP Lock");
			put("/utility/tiumAlignment","Allineamento UDB-TiUM");
			put("/utility/recoveryService","Recovery Servizi");
			put("/utility/infoCliPK3","Info CLI");
			put("/utility/InfoCliPK0","Info CLI");
			put("/utility/infoCliPoste","Info CLI");
			put("/utility/infoCliContactCenter","Info CLI");
			put("/utility/InfoCliMSAN","Info CLI");			
			put("/utility/TiUMManager","TiUM Manager");
			put("/utility/GeoAreaPk3","GEO Area");
			put("/utility/GeoAreaPk0","GEO Area");
			put("/utility/CheckCnc","Check CNC");
		}
	};

	Map<String, String> utilityIconCls = new HashMap<String, String>(){
		{
			put("/utility/checkIpLock","utility-icon");
			put("/utility/tiumAlignment","utility-icon");
			put("/utility/recoveryService","utility-icon");
			put("/utility/infoCliPK3","utility-icon");
			put("/utility/InfoCliPK0","utility-icon");
			put("/utility/infoCliPoste","utility-icon");
			put("/utility/infoCliContactCenter","utility-icon");
			put("/utility/InfoCliMSAN","utility-icon");
			put("/utility/TiUMManager","utility-icon");
			put("/utility/GeoAreaPk3","utility-icon");
			put("/utility/GeoAreaPk0","utility-icon");
			put("/utility/CheckCnc","utility-icon");
		}
	};
	
	Map<String, String> utilityClassName = new HashMap<String, String>(){
		{
			put("/utility/checkIpLock","it.icarcnr.rainbow.client.utility.iplock.CheckIpLockContainer");
			put("/utility/tiumAlignment","it.icarcnr.rainbow.client.utility.tiumalignment.TiumAlignmentContainer");
			put("/utility/recoveryService","it.icarcnr.rainbow.client.utility.recovery.RecoveryServiceContainer");
			put("/utility/infoCliPK3","it.icarcnr.rainbow.client.utility.infocli.InfoCliPK3Container");
			put("/utility/InfoCliPK0","it.icarcnr.rainbow.client.utility.infocli.InfoCliPK0Container");
            put("/utility/infoCliPoste","it.icarcnr.rainbow.client.utility.infocli.InfoCliPosteContainer");
            put("/utility/infoCliContactCenter","it.icarcnr.rainbow.client.utility.infocli.InfoCliContactCenterContainer");
            put("/utility/InfoCliMSAN","it.icarcnr.rainbow.client.utility.infocli.InfoCliMSANContainer");
            put("/utility/TiUMManager","it.icarcnr.rainbow.client.utility.tiummanager.TiUMManagerContainer");
            put("/utility/GeoAreaPk3","it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPK3Container");
            put("/utility/GeoAreaPk0","it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPk0Container");
            put("/utility/CheckCnc","it.icarcnr.rainbow.client.utility.cnc.CheckCncContainer");
		}
	};

}
