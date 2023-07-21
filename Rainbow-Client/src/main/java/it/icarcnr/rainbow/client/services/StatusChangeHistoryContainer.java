package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;

import com.gwtext.client.widgets.layout.FitLayout;

public class StatusChangeHistoryContainer extends ScreenPanel {

	private StatusChangeHistory statusHistory;
	
	@Override
    public void init() {

		setLayout(new FitLayout());
		setFrame(true);
		
		statusHistory = new StatusChangeHistory();	
		statusHistory.addParameters(getParameters());
		statusHistory.setPanelParent(this);
		statusHistory.init();
		
		add(statusHistory);

	}
	

	@Override
	public void refreshData() {
		if(statusHistory!=null){
//			statusHistory.addParameters(getParameters());
			statusHistory.refreshData();
		}
	}
	
	@Override
	public void loadData() {
		if(statusHistory!=null){
			statusHistory.clearParameters();
			statusHistory.addParameters(getParameters());
			statusHistory.loadData();
			
		}
	}
}