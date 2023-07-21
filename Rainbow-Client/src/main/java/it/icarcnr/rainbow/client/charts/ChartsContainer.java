package it.icarcnr.rainbow.client.charts;



import java.util.ArrayList;



import it.icarcnr.rainbow.client.maps.MapsBean;
import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;

import com.google.gwt.json.client.JSONObject;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class ChartsContainer extends ScreenPanel {
	private ServiceChart serviceChart;
	private ChartParameter chartParameter;
	private MapsBean valueFromMaps;
	
	public ChartsContainer(MapsBean object){
		this.valueFromMaps=object;
		
	}
	@Override
	public void init() {
	
		setLayout(new BorderLayout());
		serviceChart= new ServiceChart(getValueFromMaps());
		serviceChart.setPanelParent(this);
		serviceChart.addParameters(parameters);
		serviceChart.init();
		chartParameter = new ChartParameter(getValueFromMaps());
		chartParameter.setPanelParent(this);
		chartParameter.addParameters(parameters);
		chartParameter.init();
		if(valueFromMaps.getDateSampling()!=null){
			chartParameter.drawChart();
		}
		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));
		//setup the west regions layout properties
		BorderLayoutData eastLayoutData = new BorderLayoutData(RegionPosition.EAST);
		eastLayoutData.setMargins(new Margins(5, 5, 0, 5));
		eastLayoutData.setCMargins(new Margins(5, 5, 5, 5));
		eastLayoutData.setMinSize(160);
		eastLayoutData.setMaxSize(400);
		eastLayoutData.setSplit(true);
		try{
		add(serviceChart,centerLayoutData);
		add(chartParameter,eastLayoutData);
		}catch(Exception e){
		e.printStackTrace();
		}
	}

	public void drawChart(JSONObject parameters) {
		serviceChart.setParameters(null);
		serviceChart.addParameters(parameters);
        serviceChart.drawChart();
	}
	
	public void reset(){
		serviceChart.setParameters(null);
		serviceChart.addParameters(parameters);
		chartParameter.reset();
     	serviceChart.reset();
	}
	
	@Override
	public void loadData() {
		reset();
		super.loadData();
	}
	public MapsBean getValueFromMaps() {
		return valueFromMaps;
	}
	public void setValueFromMaps(MapsBean valueFromMaps) {
		this.valueFromMaps = valueFromMaps;
	}
	
	public void activeLitleMaps(final MapsBean mapsBean){
		chartParameter.generateMapsFromChart(mapsBean);
		
	}

	

	

	
	
}
