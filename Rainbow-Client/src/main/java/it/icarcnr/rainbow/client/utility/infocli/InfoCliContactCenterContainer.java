package it.icarcnr.rainbow.client.utility.infocli;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.utility.execution.MyCronjob;

import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.RowLayout;

public class InfoCliContactCenterContainer extends ScreenPanel{
	InfoCliContactCenter infoCliContactCenter;
	MyCronjob myCronjob;
	
	@Override
    public void init() {

		setLayout(new BorderLayout());
		setFrame(true);

		Panel rowPanel = new Panel();
		rowPanel.setLayout(new RowLayout());
		infoCliContactCenter = new InfoCliContactCenter();
		infoCliContactCenter.setPanelParent(this);
		infoCliContactCenter.init();
		myCronjob = new MyCronjob();
		myCronjob.setPanelParent(this);
		myCronjob.addParameters(parameters);
		myCronjob.init();

		BorderLayoutData centerLayoutData = new BorderLayoutData(RegionPosition.CENTER);
		centerLayoutData.setMargins(new Margins(5, 0, 5, 5));

		//setup the west regions layout properties
		BorderLayoutData eastLayoutData = new BorderLayoutData(RegionPosition.EAST);
		eastLayoutData.setMargins(new Margins(5, 0, 0, 5));
		eastLayoutData.setCMargins(new Margins(5, 0, 5, 5));
		eastLayoutData.setMinSize(160);
		eastLayoutData.setMaxSize(450);
		eastLayoutData.setSplit(true);

		add(infoCliContactCenter,centerLayoutData);
		add(myCronjob,eastLayoutData);	
		
		doLayout(Boolean.TRUE);

	}
	
	
	@Override
	public void refreshData() {
		if(infoCliContactCenter!=null){
			infoCliContactCenter.refreshData();
		}
		if(myCronjob!=null){
			myCronjob.refreshData();
		}
	}

	@Override
	public void loadData() {
		if(infoCliContactCenter!=null){
			infoCliContactCenter.loadData();
		}
		if(myCronjob!=null){
			myCronjob.loadData();
		}
	}
}
