package it.icarcnr.rainbow.client.maps;


import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

import it.icarcnr.rainbow.client.main.RainbowMain;
import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.util.i18n.maps.IMaps;

public class MapsContainer extends ScreenPanel {
	static IMaps iMaps = (IMaps)GWT.create(IMaps.class);
    private MapsServiceNew serviceMaps;
    private MapsParameter mapsParameter    ; 
	private TabPanel appTabPanel;
	private RainbowMain mainPanel;
    
    public MapsContainer(TabPanel appTabPanel,RainbowMain mainPanel){
        this.appTabPanel=appTabPanel;
        this.mainPanel=mainPanel;
    }
        
	@Override
	public void init() {
		setLayout(new BorderLayout());
		serviceMaps= new MapsServiceNew(appTabPanel,mainPanel);
		serviceMaps.setPanelParent(this);
		serviceMaps.init();
	
		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));
		mapsParameter = new MapsParameter();
		mapsParameter.setPanelParent(this);
		mapsParameter.addParameters(parameters);
		mapsParameter.init();
	
	   //setup the west regions layout properties
		BorderLayoutData eastLayoutData = new BorderLayoutData(RegionPosition.EAST);
	        eastLayoutData.setMargins(new Margins(5, 5, 0, 5));
	        eastLayoutData.setCMargins(new Margins(5, 5, 5, 5));
	        eastLayoutData.setMinSize(160);
		eastLayoutData.setMaxSize(500);	
		eastLayoutData.setSplit(true);
	
	    add(serviceMaps,centerLayoutData);
		add(mapsParameter,eastLayoutData);
//		scheduleRefreshData();
   	    doLayout(true);
	
	  }	    
			public void drawMaps(String choose) {
				if(choose!=null){
				if(choose.equals(iMaps.MapsParameter_Temperatura())){
				serviceMaps.createEmptyMaps(null);
				serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriTemperatura());
				
				}else if(choose.equals(iMaps.MapsParameter_Umidita())){
				serviceMaps.createEmptyMaps(null);
				serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriUmidita());
						
				}else if(choose.equals(iMaps.MapsParameter_Luminosita())){
				serviceMaps.createEmptyMaps(null);
				serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriLuminosita());
					
				} else if(choose.equals(iMaps.MapsParameter_Batteria())){
				serviceMaps.createEmptyMaps(null);
				serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriBatteria());	
					
				}else if(choose.equals(iMaps.MapsParameter_Rumore())){
				serviceMaps.createEmptyMaps(null);
				serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriRumore());	
				
				}else if(choose.equals(iMaps.MapsParameter_Pressione_Atmosferica())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriPressioneAtmosferica());	
					}
				else if(choose.equals(iMaps.MapsParameter_Concentrazione_CO())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriConcentrazioneCo());	
					}
				else if(choose.equals(iMaps.MapsParameter_Concentrazione_CO_Due())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriConcentrazioneCoDue());	
			    	}
				else if(choose.equals(iMaps.MapsParameter_Concentrazione_NO_Due())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriConcentrazioneNoDue());	
					}
				else if(choose.equals(iMaps.MapsParameter_Concentrazione_O_Tre())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriConcentrazioneOTre());	
					}
				else if(choose.equals(iMaps.MapsParameter_Index_Summer())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriIndiceSSi());	
					}
				else if(choose.equals(iMaps.MapsParameter_Index_Humidity())){
					serviceMaps.createEmptyMaps(null);
					serviceMaps.choiseSensorListFromMenu(serviceMaps.getSensoriConcentrazioneHumidex());	
					}
				
				
				}
	   }
			@Override
			public void refreshData() {
				serviceMaps.refreshData();
				drawMaps(mapsParameter.getHoldSchoice());
				mapsParameter.refreshWatch();
				
			}
	
			public void reset(){
				serviceMaps.setParameters(null);
				serviceMaps.addParameters(parameters);
//				mapsParameter.reset();
//		     	serviceMaps.reset();
			}
			
	


		
}
