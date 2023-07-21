/*
 * GWT-Ext Widget Library
 * Copyright 2007 - 2008, GWT-Ext LLC., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.combobox.ServiceStatusChangeComboBox;
import it.icarcnr.rainbow.client.util.UrlParamsUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IServiceConstants;
import it.icarcnr.rainbow.client.util.i18n.combobox.IComboBox;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.event.CheckboxListenerAdapter;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.ColumnNodeUI;
import com.gwtext.client.widgets.tree.ColumnTree;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.event.TreeLoaderListenerAdapter;

public class StatusChangeHistory extends BasePanel {

	static IComboBox iComboBox = (IComboBox)GWT.create(IComboBox.class);

	private ToolbarTextItem selectedItemMessage;
	//	private TimeField startTimeField=null;
	//	private TimeField endTimeField= null;
	private AsyncTreeNode root;
	private final int DEFAULT_PERIOD = 1;
	private int selectedPeriod = DEFAULT_PERIOD;
	private Integer serviceRequestId=null;
	static IServices iServices = (IServices)GWT.create(IServices.class);

	boolean enablePeriodsCB;
	private ComboBox periodsCB;
	private ColumnTree columnTree;

	private ServiceStatusChangeComboBox serviceStatusChangeCB;
	private Checkbox criticalChangeStatus;

	@Override
	public void init() {

		//		startTimeField = new TimeField("Ora","startTimeField");
		//		endTimeField = new TimeField("Ora fine", "endTimeField");

		enablePeriodsCB = true;
		//		timeField.addListener(new FieldListenerAdapter(){
		//			public void onFocus(Field field) {
		//
		//			}
		//
		//		}
		//		);

		if(getParameters().containsKey("enablePeriodsCB")){ //$NON-NLS-1$
			JSONBoolean enablePeriodsCBJSON = getParameters().get("enablePeriodsCB").isBoolean(); //$NON-NLS-1$
			if(enablePeriodsCBJSON!=null){
				enablePeriodsCB = enablePeriodsCBJSON.booleanValue();
			}
		}

		columnTree = new ColumnTree();   
		columnTree.setRootVisible(false);  
		columnTree.setAutoScroll(true);


		ColumnTree.Column cols[] = new ColumnTree.Column[11];  
		cols[0] = columnTree.new Column(iServices.ServiceStatus_Service(), 290, "serviceDescription");
		cols[1] = columnTree.new Column(iServices.StatusChangeHistory_Date(), 120, "date");
		cols[2] = columnTree.new Column(iServices.StatusChangeHistory_From_Satus(), 70, "fromStatus");
		cols[3] = columnTree.new Column(iServices.StatusChangeHistory_To_Status(), 70, "toStatus");
		cols[4] = columnTree.new Column(iServices.StatusChangeHistory_Value(), 50, "value");
		cols[5] = columnTree.new Column(iServices.ServiceStatus_Network(),50,"network");
		cols[6] = columnTree.new Column(iServices.ServiceStatus_Function(),100,"serviceType");
		cols[7] = columnTree.new Column(iServices.ServiceStatus_Source(),130 , "source");
		cols[8] = columnTree.new Column(iServices.ServiceStatus_Reference(),130,"reference");
		cols[9] = columnTree.new Column(iServices.ServiceStatus_NodeFrom(),130,"nodeFrom");
		cols[10] = columnTree.new Column(iServices.ServiceStatus_NodeTo(),130,"nodeTo");

		columnTree.setColumns(cols);

		TreeLoader loader = makeTreeLoader();

		root = new AsyncTreeNode(iServices.StatusChangeHistory_Service_Status_Change_History(), loader);   //$NON-NLS-1$
		columnTree.setRootNode(root);

		//		panel.setTitle("Status Change History");
		setLayout(new FitLayout());
		setFrame(true);
		//		panel.setAutoScroll(true);

		Toolbar topToolbar = makeTopToolbar();
		setTopToolbar(topToolbar);
		setTitle();

		add(columnTree);  

	}

	/**
	 * @return
	 */
	private TreeLoader makeTreeLoader() {
		TreeLoader loader = new TreeLoader();
		String actionPath = IActionPathConstants.SERVICE_STATUS_CHANGE_HISTORY_ACTION_PATH;
		String urlString = UrlParamsUtil.toUrlString(getParams());
		if(urlString!=null && !urlString.isEmpty()){
			actionPath= actionPath+"?"+urlString; //$NON-NLS-1$
		}

		loader.setDataUrl(actionPath);  
		loader.setPreloadChildren(true);
		loader.setMethod(Connection.GET);
		loader.setUiProviders(ColumnNodeUI.getUiProvider());
		loader.addListener(new TreeLoaderListenerAdapter(){

			@Override
			public boolean doBeforeLoad(TreeLoader self, TreeNode node) {
				selectedItemMessage.setText(iServices.StatusChangeHistory_Loading()); //$NON-NLS-1$
				//				columnTree.getEl().mask(iServices.StatusChangeHistory_Loading()); //$NON-NLS-1$
				getEl().mask(iServices.StatusChangeHistory_Loading()); //$NON-NLS-1$

				return super.doBeforeLoad(self, node);
			}
			@Override
			public void onLoad(TreeLoader self, TreeNode node, String response) {
				if(node!=null  ){
					if (node.getChildNodes().length > 0){

						selectedItemMessage.setText(""); //$NON-NLS-1$

						//					columnTree.getEl().unmask();
						getEl().unmask();
					}else{
						selectedItemMessage.setText(iServices.StatusChangeHistory_Noitemstoview()); //$NON-NLS-1$
						//						columnTree.getEl().unmask();
						getEl().unmask();
					}

				}
				super.onLoad(self, node, response);
			}
			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
				selectedItemMessage.setText(iServices.StatusChangeHistory_An_error_was_encountered_while_loading()); //$NON-NLS-1$
				getEl().unmask();

			}
		});
		return loader;
	}

	protected UrlParam[] getParams() {
		List<String> serviceIdList = getExspandedNode();
		JSONArray jsonArray = new JSONArray();
		int i = 0;
		for (String serviceId : serviceIdList) {
			jsonArray.set(i, new JSONString(serviceId));
			i++;
		}

		addParameter("servciceIdList", jsonArray); //$NON-NLS-1$

		addParameter("period", new JSONNumber(selectedPeriod));
		if(serviceRequestId!=null ){
			addParameter("serviceRequestId", new JSONNumber(serviceRequestId));
		}

		//		if(startTimeField.getValue()==null){
		//			timeField.setMinValue("00:00");
		//			addParameter("timeField",new JSONString("00:00"));
		//		}else{
		//			addParameter("startTimeField",new JSONString(startTimeField.getValue()));
		//		}
		//		if(endTimeField.getValue()==null){
		//			addParameter("endTimeField", new JSONString("23:59"));
		//			
		//		}else{
		//			addParameter("endTimeField", new JSONString(endTimeField.getValue()));
		//			
		//		}
		return getUrlParams();
	}

	/**
	 * 
	 */
	private List<String> getExspandedNode() {
		List<String> serviceIdList = new ArrayList<String>();
		List<TreeNode> treeNodes = new ArrayList<TreeNode>(); 
		if(root!=null){
			treeNodes.add((TreeNode)root);
			while (!treeNodes.isEmpty()){
				TreeNode treeNode = treeNodes.remove(0);
				Node[] nodes = treeNode.getChildNodes();
				for (Node node : nodes) {
					if (((TreeNode)node).isExpanded()){
						serviceIdList.add(((TreeNode)node).getAttribute("serviceId")); //$NON-NLS-1$
					}
					treeNodes.add((TreeNode)node);
				}
			}			
		}
		return serviceIdList;
	}


	private Toolbar makeTopToolbar() {
		Toolbar toolbar = new Toolbar();
		if(enablePeriodsCB){
			final Store periodsStore = new SimpleStore(new String[]{"label", "value"}, getPeriods());   //$NON-NLS-1$ //$NON-NLS-2$
			periodsStore.load();

			periodsCB = new ComboBox();
			periodsCB.setValue(String.valueOf(DEFAULT_PERIOD));
			periodsCB.setAllowBlank(false);
			periodsCB.setForceSelection(true);   
			periodsCB.setStore(periodsStore);  
			periodsCB.setDisplayField("label");   //$NON-NLS-1$
			periodsCB.setMode(ComboBox.LOCAL); 
			periodsCB.setWidth(50);
			periodsCB.addListener(new ComboBoxListenerAdapter(){
				@Override
				public void onSelect(ComboBox comboBox, Record record, int index) {
					selectedPeriod = record.getAsInteger("value");
					addParameter("serviceRequestId", null);
					serviceStatusChangeCB.reset();
					refreshServiceStatusChangeComboBox();
					serviceStatusChangeCB.setEmptyText(iComboBox.ServiceTypeComboBox_Enter_service_type());
					refreshData();
				}
			});

			ToolbarTextItem viewLastText = new ToolbarTextItem(iServices.StatusChangeHistory_View_last()); //$NON-NLS-1$
			ToolbarTextItem daysText = new ToolbarTextItem(iServices.StatusChangeHistory_day_days()); //$NON-NLS-1$
			ToolbarTextItem criticalStatus = new ToolbarTextItem("Stati Critical");

			//			ToolbarTextItem startTimeText = new ToolbarTextItem("Ora inizio");
			//			ToolbarTextItem endTimeText = new ToolbarTextItem("Ora fine");

			//			startTimeField.setWidth(65);
			//			startTimeField.setFormat("H:i");
			//			startTimeField.setIncrement(60);
			//			endTimeField.setWidth(65);
			//			endTimeField.setFormat("H:i");
			//			endTimeField.setIncrement(60);
			criticalChangeStatus = new Checkbox();  
			criticalChangeStatus.addListener(new CheckboxListenerAdapter(){
				@Override
				public void onCheck(Checkbox field, boolean checked) {

					addParameter("criticalChangeStatus", JSONBoolean.getInstance(criticalChangeStatus.getValue()));
					addParameter("criticalStatus", new JSONString(IServiceConstants.CRITICAL_STATUS));



					refreshData();
				}

			})  ;




			toolbar.addSpacer();
			toolbar.addItem(viewLastText);
			toolbar.addSpacer();
			toolbar.addField(periodsCB);
			toolbar.addSpacer();
			toolbar.addItem(daysText);
			toolbar.addSpacer();
			toolbar.addSeparator();
			//			toolbar.addItem(startTimeText);
			//			toolbar.addField(startTimeField);
			//			toolbar.addSeparator();
			//			toolbar.addSpacer();
			//			toolbar.addItem(endTimeText);
			//			toolbar.addField(endTimeField);



			toolbar.addSpacer();



			/** Combo Filter Service **/

			serviceStatusChangeCB = new ServiceStatusChangeComboBox(getUrlParams(), ServiceStatusChangeComboBox.LoadOption.ALL_SELECT,Boolean.TRUE);

			serviceStatusChangeCB.addListener(new ComboBoxListenerAdapter() {  

				public void onSelect(ComboBox comboBox, Record record, int index) { 
					serviceRequestId=record.getAsInteger("id");
					refreshData();
					comboBox.collapse();
				}
			});

			toolbar.addText(iServices.ServiceStatus_Service());
			toolbar.addField(serviceStatusChangeCB);
			toolbar.addSeparator();
			toolbar.addSpacer();
			toolbar.addSpacer();
			toolbar.addItem(criticalStatus);
			toolbar.addSpacer();
			toolbar.addField(criticalChangeStatus);



		}else{
			ToolbarTextItem title = new ToolbarTextItem(iServices.StatusChangeHistory_Real_Time_Detected()); //$NON-NLS-1$
			toolbar.addItem(title);
		}


		toolbar.addFill();
		selectedItemMessage = new ToolbarTextItem(""); //$NON-NLS-1$
		toolbar.addItem(selectedItemMessage);

		//create a Store using local array data  


		ToolbarButton collapseButton = new ToolbarButton();
		collapseButton.setCls("x-btn-icon collapse-all-btn"); //$NON-NLS-1$
		collapseButton.setTooltip(iServices.StatusChangeHistory_Collapse_All()); //$NON-NLS-1$
		collapseButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				columnTree.collapseAll();
			}
		});

		toolbar.addButton(collapseButton);


		return toolbar;
	}


	protected void refreshServiceStatusChangeComboBox() {
		serviceRequestId = null;
		serviceStatusChangeCB.refreshData(getParams());
	}

	private static String[][] getPeriods() {  
		return new String[][]{
				new String[]{"1","1"},   //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"2", "2"},     //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"3", "3"},   //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"4", "4"},  //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"5", "5"}, //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"6", "6"},  //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"7", "7"},  //$NON-NLS-1$ //$NON-NLS-2$

		};  
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


	@Override
	public void refreshData() {
		TreeLoader loader = makeTreeLoader();
		root.setLoader(loader);
		root.reload();

	}

	@Override
	public void loadData() {
		setTitle();
		selectedPeriod=DEFAULT_PERIOD;
		serviceRequestId=null;
		refreshServiceStatusChangeComboBox();
		periodsCB.reset();
		criticalChangeStatus.reset();
		serviceStatusChangeCB.reset();
		

		refreshData();
	}



}
