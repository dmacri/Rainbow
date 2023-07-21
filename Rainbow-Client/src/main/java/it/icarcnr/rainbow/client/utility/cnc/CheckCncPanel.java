package it.icarcnr.rainbow.client.utility.cnc;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.RowLayout;


@SuppressWarnings("unchecked")
public class CheckCncPanel extends BasePanel{

	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	private String outputExecution;
	
	@Override
	public void init() {
		outputExecution=getParameters().get("outputExecution").isString().stringValue();
		
		Panel rowpanel = new Panel();
		rowpanel.setLayout(new RowLayout());
		
		Panel panel1 = new Panel();
		panel1.setLayout(new FitLayout());
		panel1.setHeight(15);
		panel1.setHtml(iUtility.CheckCnc_Output_execution()); //$NON-NLS-1$
		rowpanel.add(panel1);
		
		Panel panel2=new Panel();
		panel2.setFrame(true);
		panel2.setLayout(new FitLayout());
		panel2.setAutoScroll(true);
		panel2.setHtml(outputExecution);
		rowpanel.add(panel2);

	}

}
