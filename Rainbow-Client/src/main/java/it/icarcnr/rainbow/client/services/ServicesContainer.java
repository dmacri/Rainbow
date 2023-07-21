package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

public class ServicesContainer extends ScreenPanel {

	private ServiceStatus serviceStatus;
	private JobStatus jobStatus;
	
	@Override
    public void init() {

		setLayout(new FitLayout());
		setFrame(true);

		Panel rowPanel = new Panel();
		rowPanel.setLayout(new RowLayout());
		serviceStatus = new ServiceStatus();
		serviceStatus.setPanelParent(this);
		serviceStatus.clearParameters();
		serviceStatus.addParameters(getParameters());
		serviceStatus.init();
		jobStatus = new JobStatus();
		jobStatus.setPanelParent(this);
		jobStatus.init();

		rowPanel.add(serviceStatus, new RowLayoutData("70%"));
		rowPanel.add(jobStatus, new RowLayoutData("30%"));

		add(rowPanel);

	}



	@Override
	public void refreshData() {
		if(serviceStatus!=null){
			serviceStatus.refreshData();
		}
		if(jobStatus!=null){
			jobStatus.refreshData();
		}
	}

	@Override
	public void loadData() {
		if(serviceStatus!=null){
			serviceStatus.clearParameters();
			serviceStatus.addParameters(getParameters());
			serviceStatus.loadData();
		}
		if(jobStatus!=null){
			jobStatus.loadData();
		}
	}

}
