package it.icarcnr.rainbow.client.main;

import it.icarcnr.rainbow.client.maps.MapsBean;
import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;

import java.util.HashMap;

import com.gwtext.client.widgets.TabPanel;

public class ScreenPanelRegistrator {
	
	private static final HashMap<String,ScreenPanel> screenPanelMap = new HashMap<String,ScreenPanel>();

	public static HashMap<String, ScreenPanel> getScreenPanelMap() {
		return screenPanelMap;
	}

	public static void loadScreenPanelRegistratorMap(TabPanel appTabPanel,RainbowMain mainPanel,MapsBean valueFromMaps){
		synchronized(screenPanelMap) {
			if(screenPanelMap.isEmpty()){
				screenPanelMap.put("it.icarcnr.rainbow.client.services.ServicesContainer", new it.icarcnr.rainbow.client.services.ServicesContainer());
				screenPanelMap.put("it.icarcnr.rainbow.client.maps.MapsContainer", new it.icarcnr.rainbow.client.maps.MapsContainer(appTabPanel,mainPanel));
				screenPanelMap.put("it.icarcnr.rainbow.client.services.StatusChangeHistoryContainer", new it.icarcnr.rainbow.client.services.StatusChangeHistoryContainer());
				screenPanelMap.put("it.icarcnr.rainbow.client.charts.ChartsContainer", new it.icarcnr.rainbow.client.charts.ChartsContainer(valueFromMaps));
				screenPanelMap.put("it.icarcnr.rainbow.client.graph.NetworkGraphContainer", new it.icarcnr.rainbow.client.graph.NetworkGraphContainer());

			    screenPanelMap.put("it.icarcnr.rainbow.client.voipcontrol.VoipControlContainer", new it.icarcnr.rainbow.client.voipcontrol.VoipControlContainer());

				screenPanelMap.put("it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPK3Container", new it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPK3Container());
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPk0Container", new it.icarcnr.rainbow.client.utility.geoarea.GeoAreaPk0Container());
				
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.cnc.CheckCncContainer", new it.icarcnr.rainbow.client.utility.cnc.CheckCncContainer());
				
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.iplock.CheckIpLockContainer", new it.icarcnr.rainbow.client.utility.iplock.CheckIpLockContainer());
				
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.tiummanager.TiUMManagerContainer", new it.icarcnr.rainbow.client.utility.tiummanager.TiUMManagerContainer());
				
//				screenPanelMap.put("it.icarcnr.rainbow.client.utility.recovery.RecoveryServiceContainer", new it.icarcnr.rainbow.client.utility.recovery.RecoveryServiceContainer());
				
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.tiumalignment.TiumAlignmentContainer", new it.icarcnr.rainbow.client.utility.tiumalignment.TiumAlignmentContainer());
				
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.infocli.InfoCliPK3Container", new it.icarcnr.rainbow.client.utility.infocli.InfoCliPK3Container());
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.infocli.InfoCliPosteContainer", new it.icarcnr.rainbow.client.utility.infocli.InfoCliPosteContainer());
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.infocli.InfoCliContactCenterContainer", new it.icarcnr.rainbow.client.utility.infocli.InfoCliContactCenterContainer());
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.infocli.InfoCliPK0Container", new it.icarcnr.rainbow.client.utility.infocli.InfoCliPK0Container());
				screenPanelMap.put("it.icarcnr.rainbow.client.utility.infocli.InfoCliMSANContainer", new it.icarcnr.rainbow.client.utility.infocli.InfoCliMSANContainer());

			}
		}		
	}

}
