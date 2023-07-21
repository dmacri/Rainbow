package it.icarcnr.rainbow.client.utility.geoarea;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.FitLayout;


@SuppressWarnings("unchecked")
public class GeoAreaPK3 extends BasePanel{

	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);

	@Override
	public void init() {
		
		setLayout(new FitLayout());
		setFrame(true);
		setTitle();

		setLayout(new FitLayout());  

		Panel accordionPanel = createAccordionPanel();  
		accordionPanel.setTitle(iUtility.GeoArea_AccordingPanel_Title());  
		add(accordionPanel);  
		doLayout(true);

	}


	public Panel createAccordionPanel() {  
		BasePanel accordionPanel = new BasePanel(){@Override
		public void init() {
    		setLayout(new AccordionLayout());
	
		}};  
		
		accordionPanel.init();
		GeoAlignPK3Panel panelGeoAlignPK3 = new GeoAlignPK3Panel();
		panelGeoAlignPK3.setPanelParent(accordionPanel);
		panelGeoAlignPK3.init();
//    	panelGeoAlignPK3.setCollapsible(true);
    	panelGeoAlignPK3.setCollapsed(true);
		accordionPanel.add(panelGeoAlignPK3);

		GeoPrefixPK3Panel panelGeoPrefixPK3 = new GeoPrefixPK3Panel();
		panelGeoPrefixPK3.setPanelParent(accordionPanel);
		panelGeoPrefixPK3.init();
//		panelGeoPrefixPK3.setCollapsible(true);
		panelGeoPrefixPK3.setCollapsed(true);
		accordionPanel.add(panelGeoPrefixPK3);
	
		return accordionPanel;  
	}  



	private void setTitle() {
		String title = getPanelParent().getTitle();
		if(getPanelParent().getParameters()!=null){
			if(getPanelParent().getParameters().containsKey("title")){
				title = ((JSONString)getPanelParent().getParameters().get("title")).stringValue();
			}
		}
		if(title!=null){
			setTitle(title);
		}
	}


}
