package it.icarcnr.rainbow.client.utility.infocli;

import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.utility.execution.MyCronjob;

import com.gwtext.client.core.Margins;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.RowLayout;

public class InfoCliPosteContainer extends ScreenPanel{
	InfoCliPoste infoCliPoste;
	MyCronjob myCronjob;
	
	@Override
    public void init() {

		setLayout(new BorderLayout());
		setFrame(true);

		Panel rowPanel = new Panel();
		rowPanel.setLayout(new RowLayout());
		infoCliPoste = new InfoCliPoste();
		infoCliPoste.setPanelParent(this);
		infoCliPoste.init();
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

		add(infoCliPoste,centerLayoutData);
		add(myCronjob,eastLayoutData);	

		doLayout(Boolean.TRUE);
	}
	
	
	@Override
	public void refreshData() {
		if(infoCliPoste!=null){
			infoCliPoste.refreshData();
		}
		if(myCronjob!=null){
			myCronjob.refreshData();
		}
	}

	@Override
	public void loadData() {
		if(infoCliPoste!=null){
			infoCliPoste.loadData();
		}
		if(myCronjob!=null){
			myCronjob.loadData();
		}
	}
	
}
