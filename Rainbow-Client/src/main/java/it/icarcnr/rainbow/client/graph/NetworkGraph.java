package it.icarcnr.rainbow.client.graph;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;

import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class NetworkGraph extends BasePanel {
	
	private NetworkGraphRepresentation networkGraphRepresentation;
	private NetworkGraphAlarmedNodes networkGraphAlarmedNodes;
	
	@Override
    public void init() {		
		setLayout(new BorderLayout());
		
		networkGraphRepresentation = new NetworkGraphRepresentation();	
		networkGraphRepresentation.addParameters(getParameters());
		networkGraphRepresentation.setPanelParent(this);

		
		networkGraphAlarmedNodes = new NetworkGraphAlarmedNodes();	
		networkGraphAlarmedNodes.addParameters(getParameters());
		networkGraphAlarmedNodes.setPanelParent(this);
		networkGraphAlarmedNodes.init();
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));
		
		//setup the west regions layout properties
		
		BorderLayoutData westLayoutData = new BorderLayoutData(RegionPosition.WEST);
		westLayoutData.setMargins(new Margins(5, 5, 0, 5));
		westLayoutData.setCMargins(new Margins(5, 5, 5, 5));
		westLayoutData.setSplit(true);
		
		add(networkGraphRepresentation,centerLayoutData);
		add(networkGraphAlarmedNodes,westLayoutData);
		
		setTitle();

	}
	
	@Override
	protected void afterRender() {
		networkGraphRepresentation.init();
	}

	
	@Override
	public void refreshData() {
		if(networkGraphRepresentation!=null){
			networkGraphRepresentation.clearParameters();
			networkGraphRepresentation.addParameters(getParameters());
			networkGraphRepresentation.refreshData();
		}
		if(networkGraphAlarmedNodes!=null){
			networkGraphAlarmedNodes.clearParameters();
			networkGraphAlarmedNodes.addParameters(getParameters());
			networkGraphAlarmedNodes.refreshData();
		}
	}
	
	@Override
	public void loadData() {
		setTitle();
		if(networkGraphRepresentation!=null){
			networkGraphRepresentation.clearParameters();
			networkGraphRepresentation.addParameters(getParameters());
			networkGraphRepresentation.loadData();
		}
		if(networkGraphAlarmedNodes!=null){
			networkGraphAlarmedNodes.clearParameters();
			networkGraphAlarmedNodes.addParameters(getParameters());
			networkGraphAlarmedNodes.loadData();
		}
	}
	
	private void setTitle() {
		String title = null;
		if(getParameters()!=null){
			if(getParameters().containsKey("title")){ //$NON-NLS-1$
				title = ((JSONString)getParameters().get("title")).stringValue(); //$NON-NLS-1$
			}
		}
		if(title==null){
			BasePanel parent = getPanelParent();
			if(parent!=null){
				title = parent.getTitle();
			}
		}
		if(title!=null){
			setTitle(title);
		}
	}

}
