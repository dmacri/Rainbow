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

import it.icarcnr.rainbow.client.renderer.LogServiceRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceDeltaStatusRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceExtendedDescriptionRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceStatusRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceStatusRowRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceToRenderer;
import it.icarcnr.rainbow.client.renderer.ServiceValueStatusRenderer;
import it.icarcnr.rainbow.client.renderer.TakeCareRenderer;
import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.event.WindowListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

public class ServiceStatus extends BasePanel {



	private GridPanel grid;

	private int pageSize = 30;

	private Store gridStore = null;
	private Store checkPermissionStore = null;

	private boolean tackingChargePermission;
	private boolean logViewPermission;
	private boolean wayViewPermission;
	
	private static IServices iServices = (IServices)GWT.create(IServices.class);
	private static Ii18nGlobalConstants i18nContants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	@Override
	public void init() {

		setTitle();
		setIconCls();
		setCollapsible(Boolean.TRUE);
		setLayout(new FitLayout());

		tackingChargePermission = false;
		logViewPermission = false;
		wayViewPermission= false;
		checkPermissionStore = getCheckPermissionStore();
		gridStore = getStore();		
		loadOnlyDataGrid();

	}


	/**
	 * @param store
	 */
	private void makeGrid(final Store store) {
		ColumnModel columnModel = makeColumnModel();

		grid = new GridPanel();
		//		grid.setTitle(" Stato Servizi ");//Service Status Details For All Host
		grid.setStore(store);
		grid.setColumnModel(columnModel);
		grid.setTrackMouseOver(true);
		grid.setLoadMask(true);
		grid.setSelectionModel(new RowSelectionModel());
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setIconCls("grid-icon"); //$NON-NLS-1$

		grid.addGridCellListener(new GridCellListenerAdapter() {  
			@Override
			public void onCellClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {
				Record record = store.getAt(rowIndex);
				if (grid.getColumnModel().getDataIndex(colIndex).equals("isTakingCharge") ) {   //$NON-NLS-1$

					if(record.getAsBoolean("isTakingCharge")){ //$NON-NLS-1$
						MessageBox.alert(iServices.warning(), iServices.ServiceStatus_Alarm_already_taken_in_care(),   //$NON-NLS-1$
								new MessageBox.AlertCallback() { 
							@Override
							public void execute() {
								refreshView();
							}
						}); 
					}else if(record.getAsBoolean("tackingChargePermission")){ //$NON-NLS-1$

						final Window window = new Window();  
						window.setTitle(iServices.JobStatus_Taking_Charge());   //$NON-NLS-1$
						window.setClosable(true);  
						window.setWidth(500);  
						window.setHeight(250);  
						window.setPlain(true); 
						window.setMaximizable(true);  
						window.setResizable(true); 
						window.setLayout(new FitLayout());
						window.setCloseAction(Window.CLOSE);
						String serviceId = record.getAsString("serviceId"); //$NON-NLS-1$
						String serviceDescription = record.getAsString("description"); //$NON-NLS-1$
						String reference = record.getAsString("reference");
						String source = record.getAsString("source");
						String nodeTo = record.getAsString("nodeTo");
						TakeCarePanel takeCareWindow = new TakeCarePanel(serviceId, serviceDescription,source, reference,nodeTo);
						takeCareWindow.addParameters(getParameters());
						takeCareWindow.setWindowParent(window);
						takeCareWindow.init();	
						window.add(takeCareWindow);

						window.addListener(new WindowListenerAdapter(){
							@Override
							public void onClose(Panel panel) {
								refreshView();
							}
						});
						window.show();
					} 

				}
			}
			@Override
			public void onCellDblClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {  
				Record record = store.getAt(rowIndex);
				if (grid.getColumnModel().getDataIndex(colIndex).equals("logPresent")&& record.getAsBoolean("logPresent") ) { //$NON-NLS-1$ //$NON-NLS-2$

					final Window window = new Window();  
					String title = record.getAsString("description"); //$NON-NLS-1$
					window.setTitle(iServices.ServiceStatus_Log_of() +title);   //$NON-NLS-1$
					window.setClosable(true);  
					window.setWidth(400);  
					window.setHeight(200);
					window.setMaximizable(true);
					window.setCollapsible(true);
					window.setPlain(true);  
					window.setLayout(new FitLayout());
					window.setCloseAction(Window.CLOSE);
					int serviceId = record.getAsInteger("serviceId"); //$NON-NLS-1$

					LogViewPanel logViewPanel = new LogViewPanel(serviceId,title);
					logViewPanel.addParameters(getParameters());
					logViewPanel.setWindowParent(window);
					logViewPanel.init();
					window.add(logViewPanel);
					window.show();



				}else if(grid.getColumnModel().getDataIndex(colIndex).equals("value")){ //$NON-NLS-1$

					final Window window = new Window();  
					String serviceDescription = record.getAsString("description"); //$NON-NLS-1$
					String reference = record.getAsString("reference");
//					String nodeFrom = record.getAsString("nodeFrom"); //$NON-NLS-1$
//					String nodeTo = record.getAsString("nodeTo");

					window.setTitle(iServices.ServiceStatus_Criteria_for() +reference+" - "+serviceDescription);   //$NON-NLS-1$ //$NON-NLS-2$
					window.setClosable(true);  
					window.setWidth(400);  
					window.setHeight(290);
					window.setMaximizable(true);
					window.setCollapsible(true);
					window.setPlain(true);  
					window.setLayout(new FitLayout());
					window.setFrame(true);


					window.setCloseAction(Window.CLOSE);
					int idCriteria = record.getAsInteger("idCriteriaValue"); //$NON-NLS-1$
					int idRequest = record.getAsInteger("requestId");
					int networkId = record.getAsInteger("networkId");
					String requestName = record.getAsString("requestName");
					String nodeName = record.getAsString("nodeName");
					boolean request403ForbiddenRegister = record.getAsBoolean("request403ForbiddenRegister");

					CriteriaServiceView criteriaServiceView = new CriteriaServiceView();
					criteriaServiceView.addParameters(getParameters());
					criteriaServiceView.addParameter("criteriaId", new JSONNumber(idCriteria));
					criteriaServiceView.addParameter("requestName", new JSONString(requestName));
					criteriaServiceView.addParameter("nodeName", new JSONString(nodeName));
					criteriaServiceView.addParameter("requestId", new JSONNumber(idRequest));
					criteriaServiceView.addParameter("networkId", new JSONNumber(networkId));
					criteriaServiceView.addParameter("request403ForbiddenRegister", JSONBoolean.getInstance(request403ForbiddenRegister));
					
					criteriaServiceView.setWindowParent(window);
					criteriaServiceView.init();
					window.add(criteriaServiceView);
					window.show();

				}else if(grid.getColumnModel().getDataIndex(colIndex).equals("deltaValue")&& !record.isEmpty("deltaValue")){

					final Window window = new Window();  
					window.setFrame(true);
					String serviceDescription = record.getAsString("description"); //$NON-NLS-1$
					String reference = record.getAsString("reference");

					
//					String nodeFrom = record.getAsString("nodeFrom"); //$NON-NLS-1$
					//					window.setBodyStyle("background-color-window");
					//					window.setCls("background-color-window");
					window.setTitle("&#916Value "+iServices.ServiceStatus_Criteria_for()+ reference+" - " +serviceDescription);   //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					window.setClosable(true);  
					window.setWidth(400);  
					window.setHeight(220);
					window.setMaximizable(true);
					window.setCollapsible(true);
					//					window.setPlain(true);  
					window.setLayout(new FitLayout());

					window.setCloseAction(Window.CLOSE);
					int idCriteria = record.getAsInteger("idCriteriaDeltaValue");//$NON-NLS-1$
					int requestId = record.getAsInteger("requestId");
					int networkId = record.getAsInteger("networkId");
					String requestName = record.getAsString("requestName");
					String nodeName = record.getAsString("nodeName");
					
					
					boolean request403ForbiddenRegister = record.getAsBoolean("request403ForbiddenRegister");
					CriteriaServiceView criteriaServiceView = new CriteriaServiceView();
					criteriaServiceView.addParameters(getParameters());
					criteriaServiceView.addParameter("criteriaId", new JSONNumber(idCriteria));
					criteriaServiceView.addParameter("requestId", new JSONNumber(requestId));
					criteriaServiceView.addParameter("requestName", new JSONString(requestName));
					criteriaServiceView.addParameter("nodeName", new JSONString(nodeName));
					criteriaServiceView.addParameter("networkId", new JSONNumber(networkId));
					criteriaServiceView.addParameter("request403ForbiddenRegister", JSONBoolean.getInstance(request403ForbiddenRegister));
					criteriaServiceView.setWindowParent(window);
					criteriaServiceView.init();
					window.add(criteriaServiceView);
					window.show();



				}
			} 
		}
		);

		GridView view = new GridView();
		view.setAutoFill(true);
		view.setForceFit(true);
		//		grid.getView().setEnableRowBody(true);
		grid.setView(view);


		final PagingToolbar pagingToolbar = new PagingToolbar(store);
		pagingToolbar.setDisplayInfo(true);
		pagingToolbar.setPageSize(pageSize);
		pagingToolbar.setDisplayMsg(iServices.ServiceStatus_Displaying_sevices_of()); //$NON-NLS-1$
		pagingToolbar.setEmptyMsg(iServices.ServiceStatus_No_services_to_display()); //$NON-NLS-1$

		addPaginationTool(pagingToolbar);

		grid.setBottomToolbar(pagingToolbar);

		grid.addListener(new PanelListenerAdapter() {
			public void onRender(Component component) {
				getEl().mask(i18nContants.ScreenManager_Loading_data(), "x-mask-loading");
				grid.getStore().setBaseParams(getUrlParams());
				grid.getStore().load(0,pageSize);	
				super.onRender(component);
			}
		});

		//		panel = new Panel();

		//		if(panel == null){
		//			panel = new Panel();
		//			panel.setCollapsible(Boolean.TRUE);
		//			panel.setLayout(new FitLayout());
		//		}else{
		//			panel.clear();
		//		}
		clear();
		//		setTitle();
		//		setIconCls();
		add(grid);
	}

	/**
	 * @return
	 */
	private ColumnModel makeColumnModel() {
		//		boolean logViewEnabled = getPanelParent().getActions().containsKey(IActionPathConstants.LOG_VIEW_ACTION);
		//		boolean takeCareEnabled = getPanelParent().getActions().containsKey(IActionPathConstants.TAKE_CARE_ACTION);

		/**  SETTO LE COLONNE **/
		ServiceStatusRenderer serviceStatusRenderer = new ServiceStatusRenderer();
		ServiceStatusRowRenderer serviceStatusRowRenderer = new ServiceStatusRowRenderer();
		ServiceDeltaStatusRenderer serviceDeltaStatusRenderer = new ServiceDeltaStatusRenderer();
		ServiceValueStatusRenderer serviceValueStatusRenderer = new ServiceValueStatusRenderer();
		ServiceToRenderer serviceFromRenderer = new ServiceToRenderer();
		ServiceExtendedDescriptionRenderer extendedDescriptionRenderer = new ServiceExtendedDescriptionRenderer();
		TakeCareRenderer takeCareRenderer = new TakeCareRenderer();
		LogServiceRenderer logServiceRenderer = new LogServiceRenderer();

		List<ColumnConfig> columnConfigList = new ArrayList<ColumnConfig>();

		ColumnConfig serviceIdColumn = new ColumnConfig("serviceId", "serviceId", 1, false); //$NON-NLS-1$ //$NON-NLS-2$
		serviceIdColumn.setHidden(true);
		columnConfigList.add(serviceIdColumn);

		if(tackingChargePermission){
			ColumnConfig takingChargeColumn = new ColumnConfig(iServices.takeCharge(), "isTakingCharge", 5, false); //$NON-NLS-1$
			takingChargeColumn.setRenderer(takeCareRenderer);
			columnConfigList.add(takingChargeColumn);
		}

		ColumnConfig statusColumn = new ColumnConfig(iServices.ServiceStatus_Status(), "status", 15, true); //$NON-NLS-1$ //$NON-NLS-2$
		statusColumn.setRenderer(serviceStatusRenderer);
		columnConfigList.add(statusColumn);


		ColumnConfig lastCheckColumn = new ColumnConfig(iServices.ServiceStatus_Date(), "lastCheck", 20, true); //$NON-NLS-1$ //$NON-NLS-2$
		lastCheckColumn.setRenderer(serviceStatusRowRenderer);
		columnConfigList.add(lastCheckColumn);

		ColumnConfig networkColumn = new ColumnConfig(iServices.ServiceStatus_Network(), "networkName", 17, false); //$NON-NLS-1$ //$NON-NLS-2$
		networkColumn.setRenderer(serviceStatusRowRenderer);
		columnConfigList.add(networkColumn);

		ColumnConfig functionColumn = new ColumnConfig(iServices.ServiceStatus_Function(), "functionName", 20, false); //$NON-NLS-1$ //$NON-NLS-2$
		functionColumn.setRenderer(serviceStatusRowRenderer);
		columnConfigList.add(functionColumn);

		ColumnConfig sourcesColumn = new ColumnConfig(iServices.ServiceStatus_Source(), "source", 30, false); //$NON-NLS-1$ //$NON-NLS-2$
		sourcesColumn.setRenderer(serviceStatusRowRenderer);
		columnConfigList.add(sourcesColumn);
		
		ColumnConfig referenceColumn = new ColumnConfig(iServices.ServiceStatus_Reference(),"reference",30,false);
		referenceColumn.setRenderer(serviceStatusRowRenderer);
		columnConfigList.add(referenceColumn); 
		
		if(wayViewPermission){
			ColumnConfig nodeFromColumn = new ColumnConfig(iServices.ServiceStatus_NodeFrom(), "nodeFrom", 30, false); //$NON-NLS-1$ //$NON-NLS-2$
			nodeFromColumn.setRenderer(serviceStatusRowRenderer);
			columnConfigList.add(nodeFromColumn);

			ColumnConfig nodeToColumn = new ColumnConfig(iServices.ServiceStatus_NodeTo(),"nodeTo",30,false);
			nodeToColumn.setRenderer(serviceFromRenderer);
			columnConfigList.add(nodeToColumn);
			}
		

		ColumnConfig serviceDescriptionColumn = new ColumnConfig(iServices.ServiceStatus_Service(), "description", 30, true); //$NON-NLS-1$ //$NON-NLS-2$
		serviceDescriptionColumn.setRenderer(extendedDescriptionRenderer);
		columnConfigList.add(serviceDescriptionColumn);


		ColumnConfig valueColumn = new ColumnConfig(iServices.ServiceStatus_Value(), "value",10, false); //$NON-NLS-1$ //$NON-NLS-2$
		valueColumn.setRenderer(serviceValueStatusRenderer);
		columnConfigList.add(valueColumn);

		ColumnConfig deltaValueColumn = new ColumnConfig("&#916"+ iServices.ServiceStatus_Value(), "deltaValue",15, false); //$NON-NLS-1$ //$NON-NLS-2$
		deltaValueColumn.setRenderer(serviceDeltaStatusRenderer);
		columnConfigList.add(deltaValueColumn);

		if(logViewPermission){
			ColumnConfig logColumn = new ColumnConfig(iServices.ServiceStatus_Log_View(), "logPresent", 5, false); //$NON-NLS-1$ //$NON-NLS-2$
			logColumn.setRenderer(logServiceRenderer);
			columnConfigList.add(logColumn);
		}		

		ColumnConfig[] columnConfigs = new ColumnConfig[columnConfigList.size()];

		ColumnModel columnModel = new ColumnModel(columnConfigList.toArray(columnConfigs));

		return columnModel;
	}

	/**
	 * @param pagingToolbar
	 */
	private void addPaginationTool(final PagingToolbar pagingToolbar) {

		final NumberField pageSizeField = new NumberField();  
		pageSizeField.setWidth(40);
		pageSizeField.setSelectOnFocus(true);  
		pageSizeField.setAllowDecimals(false);
		pageSizeField.setAllowNegative(false);
		pageSizeField.disable();
		pageSizeField.setValue(pageSize);
		pageSizeField.addListener(new FieldListenerAdapter() {  
			public void onSpecialKey(Field field, EventObject e) {  
				if (e.getKey() == EventObject.ENTER) {
					Number value = ((NumberField)field).getValue();
					if(value!=null){
						pageSize = ((NumberField)field).getValue().intValue();  
						pagingToolbar.setPageSize(pageSize);
					}else{
						((NumberField)field).setValue(pageSize);
					}
				}  
			}  
		});  

		ToolTip toolTip = new ToolTip(iServices.ServiceStatus_Enter_page_size());   //$NON-NLS-1$
		toolTip.applyTo(pageSizeField);


		//create a Store using local array data  
		final Store pageSizeStore = new SimpleStore(new String[]{"label", "value"}, getPageSize());   //$NON-NLS-1$ //$NON-NLS-2$
		pageSizeStore.load();

		final ComboBox pageSizeCB = new ComboBox();
		pageSizeCB.setValue(String.valueOf(pageSize));
		pageSizeCB.setAllowBlank(false);
		pageSizeCB.setForceSelection(true);   
		pageSizeCB.setStore(pageSizeStore);  
		pageSizeCB.setDisplayField("label");   //$NON-NLS-1$
		pageSizeCB.setMode(ComboBox.LOCAL); 
		pageSizeCB.setWidth(100);
		pageSizeCB.addListener(new ComboBoxListenerAdapter(){
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {
				int value = record.getAsInteger("value"); //$NON-NLS-1$
				if(value==-1){
					pageSizeField.disable();
					int totalCount = grid.getStore().getTotalCount();
					if(pageSize!=totalCount){
						pageSize = totalCount;  
						pagingToolbar.setPageSize(pageSize);
						pageSizeField.setValue(pageSize);
						grid.getStore().load(0,pageSize);
					}
				}else if (value==-2){
					pageSizeField.enable();
				}else {
					pageSizeField.disable();
					if(pageSize!=value){
						pageSize = value;  
						pagingToolbar.setPageSize(pageSize);
						pageSizeField.setValue(pageSize);
						//						grid.getStore().load(0,pageSize);
					}
				}        			
			}
		});


		ToolbarTextItem selectPageSizeText = new ToolbarTextItem(iServices.ServiceStatus_Select_page_size()); //$NON-NLS-1$
		ToolbarTextItem pageSizeText = new ToolbarTextItem(iServices.ServiceStatus_Page_size()); //$NON-NLS-1$

		pagingToolbar.addSeparator();
		pagingToolbar.addItem(selectPageSizeText);
		pagingToolbar.addField(pageSizeCB);
		pagingToolbar.addItem(pageSizeText);
		pagingToolbar.addField(pageSizeField);
	}

	protected int[] getStartAndLimit() {
		PagingToolbar pt = (PagingToolbar)grid.getBottomToolbar();
		int start = (pt.getCurrentPage() - 1 ) * pageSize;
		int limit = start + pageSize;	
		int[] startAndLimit = new int[2];
		startAndLimit[0] = start;
		startAndLimit[1] = limit;
		return startAndLimit;
	}

	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.SERVICE_STATUS_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.setDefaultSort("status", SortDir.DESC); //$NON-NLS-1$
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
			}
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
			}
		});
		return st;
	}


	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new StringFieldDef("serviceId"), //$NON-NLS-1$
						new StringFieldDef("requestId"),
						new StringFieldDef("networkId"),
						new StringFieldDef("requestName"),
						new StringFieldDef("nodeName"),
						new BooleanFieldDef("request403ForbiddenRegister"),
						new StringFieldDef("idCriteriaValue"), //$NON-NLS-1$
						new StringFieldDef("idCriteriaDeltaValue"),						 //$NON-NLS-1$
						new BooleanFieldDef("isTakingCharge"), //$NON-NLS-1$
						new StringFieldDef("status"), //$NON-NLS-1$
						new DateFieldDef("lastCheck",IDateFormatUtil.dateTimeFormat), //$NON-NLS-1$
						new StringFieldDef("source"), //$NON-NLS-1$
						new StringFieldDef("description"), //$NON-NLS-1$
						new IntegerFieldDef("samplingPeriod"), //$NON-NLS-1$
						new StringFieldDef("extendedDescription"), //$NON-NLS-1$
						new StringFieldDef("reference"),
						new StringFieldDef("nodeFrom"), //$NON-NLS-1$
						new StringFieldDef("nodeTo"),
						new FloatFieldDef("value"), //$NON-NLS-1$
						new FloatFieldDef("deltaValue"), //$NON-NLS-1$
						new StringFieldDef("valueStatus"), //$NON-NLS-1$
						new StringFieldDef("deltaValueStatus"), //$NON-NLS-1$
						new BooleanFieldDef("logPresent"), //$NON-NLS-1$
						new BooleanFieldDef("tackingChargePermission"), //$NON-NLS-1$

						new FloatFieldDef("majorValue"), //$NON-NLS-1$
						new FloatFieldDef("criticalValue"), //$NON-NLS-1$
						new FloatFieldDef("majorDeltaValue"), //$NON-NLS-1$
						new FloatFieldDef("criticalDeltaValue"), //$NON-NLS-1$

						new StringFieldDef("valueTypeCheck"), //$NON-NLS-1$
						new StringFieldDef("deltaValueTypeCheck"), //$NON-NLS-1$

						new FloatFieldDef("secondaryMajorValue"), //$NON-NLS-1$
						new FloatFieldDef("secondaryCriticalValue"), //$NON-NLS-1$
						new FloatFieldDef("secondaryMajorDeltaValue"), //$NON-NLS-1$
						new FloatFieldDef("secondaryCriticalDeltaValue"), //$NON-NLS-1$

						new StringFieldDef("networkName"),
						new StringFieldDef("functionName")
				}  
		); 
		return recordDef;
	}

	private void refreshView() {
		BasePanel parent = getPanelParent();
		if(parent!=null){
			parent.refreshData();
		}else{
			refreshData();
		}
	}

	@Override
	public void refreshData() {
		grid.getStore().setBaseParams(getUrlParams());
		int[] startAndLimit = getStartAndLimit();
		grid.getStore().load(startAndLimit[0],startAndLimit[1]);
	}

	@Override
	public void loadData() {
		setTitle();
		setIconCls();
		getEl().mask(i18nContants.ScreenManager_Loading_data(), "x-mask-loading");
		loadOnlyDataGrid();
	}

	private void loadOnlyDataGrid(){
		checkPermissionStore.load(getUrlParams());
	}



	/**
	 * 
	 */
	private void loadPage() {
		gridStore.removeAll();
		makeGrid(gridStore);
		doLayout(true);
	}

	private void setIconCls() {
		//		String iconCls = mainPanel.getIconCls();
		//		if(mainPanel.getParameters()!=null){
		//			if(mainPanel.getParameters().containsKey("iconCls")){
		//				iconCls = ((JSONString)mainPanel.getParameters().get("iconCls")).stringValue();
		//			}
		//		}
		//		if(iconCls!=null){
		//			panel.setIconCls(iconCls);
		//		}

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


	private static String[][] getPageSize() {  
		return new String[][]{
				new String[]{"30","30"},   //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"50", "50"},     //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{"100", "100"},   //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{iServices.ServiceStatus_All(), "-1"},  //$NON-NLS-1$ //$NON-NLS-2$
				new String[]{iServices.ServiceStatus_Custom(), "-2"},  //$NON-NLS-1$ //$NON-NLS-2$
		};  
	} 

	private Store getCheckPermissionStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECK_SERVICES_STATUS_PERMISSION_ACTION);       
		RecordDef dataRecordDef = getCheckPermissionRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length>0){
					Record record = records[0];
					tackingChargePermission = record.getAsBoolean("tackingChargePermission"); //$NON-NLS-1$
					logViewPermission = record.getAsBoolean("logViewPermission"); //$NON-NLS-1$
					wayViewPermission =record.getAsBoolean("wayViewPermission");
					loadPage();
				}
			}
		});
		return st;
	}


	private RecordDef getCheckPermissionRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("tackingChargePermission"), //$NON-NLS-1$
						new BooleanFieldDef("logViewPermission"), //$NON-NLS-1$
						new BooleanFieldDef("wayViewPermission")
				}  
		); 
		return recordDef;
	}
}