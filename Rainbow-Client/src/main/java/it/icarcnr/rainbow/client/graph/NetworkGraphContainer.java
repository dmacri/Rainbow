package it.icarcnr.rainbow.client.graph;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;

import com.gwtext.client.widgets.layout.FitLayout;

public class NetworkGraphContainer extends ScreenPanel {
	
	private NetworkGraph networkGraph;
	
	@Override
    public void init() {		
		setLayout(new FitLayout());
		
		networkGraph = new NetworkGraph();	
		networkGraph.addParameters(getParameters());
		networkGraph.setPanelParent(this);
		networkGraph.init();
		
		add(networkGraph);

	}
	
//	@Override
//	protected void afterRender() {
//		networkGraph.init();
//	}

	
	@Override
	public void refreshData() {
		if(networkGraph!=null){
			networkGraph.refreshData();
		}
	}
	
	@Override
	public void loadData() {
		if(networkGraph!=null){
			networkGraph.clearParameters();
			networkGraph.addParameters(getParameters());
			networkGraph.loadData();
		}
	}

}
