package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.renderer.JobActionsRenderer;
import it.icarcnr.rainbow.client.renderer.JobCursorRenderer;
import it.icarcnr.rainbow.client.renderer.JobRowRenderer;
import it.icarcnr.rainbow.client.renderer.JobServiceStatusRenderer;
import it.icarcnr.rainbow.client.renderer.JobToRenderer;
import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BaseMenu;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.event.WindowListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.grid.event.GridMouseListener;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.menu.BaseItem;
import com.gwtext.client.widgets.menu.MenuItem;
import com.gwtext.client.widgets.menu.event.BaseItemListenerAdapter;

public class JobStatus extends BasePanel{

	private GridPanel gridJob;
	Store checkPermissionStore = null;
	protected boolean acknowledgementPermission;
	protected boolean wayViewPermission;
	boolean userDetected;
	boolean controlThreshold;
	private Store gridStore;
	static IServices iServices = (IServices)GWT.create(IServices.class);
	static Ii18nGlobalConstants i18nContants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	private int xPos =0;
	private int yPos =0;

	private class JobActionsMenu extends BaseMenu{

		@Override
		public void init() {
			// TODO Auto-generated method stub

		}

	}


	@Override
	public void init() {

		setTitle(iServices.JobStatus_Taking_Charge());
		setCollapsible(Boolean.TRUE);
		setLayout(new FitLayout());

		acknowledgementPermission = false;
		wayViewPermission = false;
		checkPermissionStore = getCheckPermissionStore();
		gridStore = getJobStatusStore();
		gridStore.setDefaultSort("status", SortDir.DESC);

		loadOnlyDataGrid();
	}


	/**
	 * 
	 */
	private void makeGridJob() {
		ColumnModel columnModel = makeColumnModel();
		columnModel.setDefaultSortable(true);

		gridJob = new GridPanel();
		gridJob.setStore(gridStore);
		gridJob.setColumnModel(columnModel);
		gridJob.setTrackMouseOver(true);
		gridJob.setLoadMask(true);
		gridJob.setSelectionModel(new RowSelectionModel());
		gridJob.setFrame(true);
		gridJob.setStripeRows(true);
		gridJob.setIconCls("grid-icon"); //$NON-NLS-1$


		GridView view = new GridView();
		view.setAutoFill(true);
		view.setForceFit(true);
		gridJob.setView(view);


		gridJob.addListener(new PanelListenerAdapter() {
			public void onRender(Component component) {
				getEl().mask(i18nContants.ScreenManager_Loading_data(), "x-mask-loading");
				gridStore.load(getPanelParent().getUrlParams());
			}
		});
		gridJob.addGridMouseListener(new GridMouseListener(){

			public void onMouseDown(EventObject e) {}

			public void onMouseOut(EventObject e) {
				xPos = 0;
				yPos = 0;
			}

			public void onMouseOver(EventObject e) {
				xPos = e.getPageX();
				yPos = e.getPageY();
			}

			public void onMouseUp(EventObject e) {}

		});


		gridJob.addGridCellListener(new GridCellListenerAdapter() {
			public void onCellClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {
				final Record record = gridStore.getAt(rowIndex);
				if (grid.getColumnModel().getDataIndex(colIndex).equals("acknowledgement") && record.getAsBoolean("userDetected") ) {   //$NON-NLS-1$
					if(record.getAsBoolean("acknowledgementPermission")){ 
						final BaseMenu menu = makeCellMenu(record);
						menu.setRecord(record);
						gridJob.add(menu);						
						menu.showAt(xPos, yPos);
					}
				}

			};

			@Override
			public void onCellDblClick(GridPanel grid, int rowIndex, int colIndex, EventObject e) {  
				final Record record = gridStore.getAt(rowIndex);
				if (grid.getColumnModel().getDataIndex(colIndex).equals("comment") ) {   //$NON-NLS-1$
					final String comment = record.getAsString("comment"); //$NON-NLS-1$
					MessageBox.show(new MessageBoxConfig() {  
						{  
							setTitle(iServices.JobStatus_Comment());   //$NON-NLS-1$
							setMsg(comment);  
							setMinWidth(300);
							setButtons(MessageBox.OK);   
						}  
					});  
				}  
			} 
		}
		);
		clear();
		add(gridJob);
	}
	/**
	 * @return
	 */
	private ColumnModel makeColumnModel() {

		/**  SETTO LE COLONNE **/
		JobRowRenderer jobRowRenderer = new JobRowRenderer();
		JobActionsRenderer jobAknowledgmentRenderer = new JobActionsRenderer();
		JobServiceStatusRenderer jobServiceStatusRenderer = new JobServiceStatusRenderer();
		JobCursorRenderer cursorRenderer = new JobCursorRenderer();
		JobToRenderer jobToRenderer = new JobToRenderer();
		

		List<ColumnConfig> columnConfigList = new ArrayList<ColumnConfig>();

		ColumnConfig jobIdColumn = new ColumnConfig("jobId", "jobId", 1, true); //$NON-NLS-1$ //$NON-NLS-2$
		jobIdColumn.setHidden(true);
		columnConfigList.add(jobIdColumn);

		if(acknowledgementPermission){
			ColumnConfig actionsColumn = new ColumnConfig(iServices.JobStatus_Actions(), "acknowledgement", 6, true); //$NON-NLS-1$ //$NON-NLS-2$
			actionsColumn.setRenderer(jobAknowledgmentRenderer);
			columnConfigList.add(actionsColumn);
		}
		ColumnConfig startDateColumn = new ColumnConfig(iServices.JobStatus_Start_date(), "startDate", 17, true); //$NON-NLS-1$ //$NON-NLS-2$
		startDateColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(startDateColumn);


		ColumnConfig userColumn = new ColumnConfig(iServices.JobStatus_User(), "user", 20, true); //$NON-NLS-1$ //$NON-NLS-2$
		userColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(userColumn);

		ColumnConfig commentColumn = new ColumnConfig(iServices.JobStatus_Comment(), "comment", 30, true); //$NON-NLS-1$ //$NON-NLS-2$
		commentColumn.setRenderer(cursorRenderer);
		columnConfigList.add(commentColumn);

		ColumnConfig statusColumn = new ColumnConfig(iServices.JobStatus_Status(), "status", 12, true); //$NON-NLS-1$ //$NON-NLS-2$
		statusColumn.setRenderer(jobServiceStatusRenderer);
		columnConfigList.add(statusColumn);


		ColumnConfig lastCheckColumn = new ColumnConfig(iServices.JobStatus_Date(), "lastCheck", 17, true); //$NON-NLS-1$ //$NON-NLS-2$
		lastCheckColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(lastCheckColumn);


		ColumnConfig networkColumn = new ColumnConfig(iServices.ServiceStatus_Network(), "networkName", 17, true); //$NON-NLS-1$ //$NON-NLS-2$
		networkColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(networkColumn);

		ColumnConfig functionColumn = new ColumnConfig(iServices.ServiceStatus_Function(), "functionName", 20, true); //$NON-NLS-1$ //$NON-NLS-2$
		functionColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(functionColumn);

		ColumnConfig sourcesColumn = new ColumnConfig(iServices.JobStatus_Source(), "sources",18, true); //$NON-NLS-1$ //$NON-NLS-2$
		sourcesColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(sourcesColumn);
		
		ColumnConfig referenceColumn = new ColumnConfig(iServices.JobStatus_Reference(), "reference",18, true); //$NON-NLS-1$ //$NON-NLS-2$
		referenceColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(referenceColumn);

		
		if(wayViewPermission){
			ColumnConfig nodeFromColumn = new ColumnConfig(iServices.JobStatus_NodeFrom(), "nodeFrom",18, true); //$NON-NLS-1$ //$NON-NLS-2$
			nodeFromColumn.setRenderer(jobRowRenderer);
			columnConfigList.add(nodeFromColumn);
			
			ColumnConfig nodeToColumn = new ColumnConfig(iServices.JobStatus_NodeTo(),"nodeTo",18,true);
			nodeToColumn.setRenderer(jobToRenderer);
			columnConfigList.add(nodeToColumn);
			
		}
		
		

		ColumnConfig statusInformationColumn = new ColumnConfig(iServices.JobStatus_Service(), "serviceDescription", 30, true); //$NON-NLS-1$ //$NON-NLS-2$
		statusInformationColumn.setRenderer(jobRowRenderer);
		columnConfigList.add(statusInformationColumn);


		ColumnConfig[] columnConfigs = new ColumnConfig[columnConfigList.size()];
		ColumnModel columnModel = new ColumnModel(columnConfigList.toArray(columnConfigs));
		return columnModel;
	}

	private Store getJobStatusStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.JOB_STATUS_ACTION_PATH);       
		RecordDef dataRecordDef = getJobStatusRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
			}

			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
			}
		});
		return st;
	}


	private RecordDef getJobStatusRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{
						new BooleanFieldDef("userDetected"),
						new StringFieldDef("jobId"), //$NON-NLS-1$
						new StringFieldDef("sources"), //$NON-NLS-1$
						new StringFieldDef("status"), //$NON-NLS-1$
						new DateFieldDef("lastCheck",IDateFormatUtil.dateTimeFormat), //$NON-NLS-1$
						new StringFieldDef("description"), //$NON-NLS-1$
						new StringFieldDef("reference"),
						new StringFieldDef("nodeFrom"), //$NON-NLS-1$
						new StringFieldDef("nodeTo"),
						new StringFieldDef("user"), //$NON-NLS-1$
						new StringFieldDef("comment"), //$NON-NLS-1$
						new DateFieldDef("startDate",IDateFormatUtil.dateTimeFormat), //$NON-NLS-1$
						new BooleanFieldDef("acknowledgementPermission"),
						new StringFieldDef("networkName"),
						new StringFieldDef("functionName"), //$NON-NLS-1$
						new IntegerFieldDef("serviceId"),
						new StringFieldDef("serviceDescription"),
                        new BooleanFieldDef("controlThreshold")
				}  
		); 
		return recordDef;

	}

	private Store getAcknoledgmentStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.ACKNOLEDGMENT_ACTION_PATH);       
		RecordDef dataRecordDef = getAcknoledgmentRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.setBaseParams(getUrlParams());
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record = records[0];
					boolean success = record.getAsBoolean("success"); //$NON-NLS-1$
					if(success){
						//						MessageBox.alert("Info", "Acknoledgment executed succesfully!");
						refreshView();
					}else{
						MessageBox.alert(iServices.JobStatus_Warning(), iServices.JobStatus_Acknoledgment_forbidden()); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			}
			@Override
			public void onLoadException(Throwable error) {
				MessageBox.alert(iServices.JobStatus_Error(), iServices.JobStatus_Acknoledgment_failed()); //$NON-NLS-1$ //$NON-NLS-2$
				refreshView();
			}
		});
		return st;
	}

	private RecordDef getAcknoledgmentRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("success") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}

	private void refreshView() {
		getPanelParent().refreshData();
	}

	/**
	 * 
	 */
	private void loadPage() {
		gridStore.removeAll();
		makeGridJob();
		doLayout(true);
	}

	private Store getCheckPermissionStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECK_JOB_STATUS_PERMISSION_ACTION);       
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
					acknowledgementPermission = record.getAsBoolean("acknowledgementPermission"); //$NON-NLS-1$
					wayViewPermission = record.getAsBoolean("wayViewPermission");
					loadPage();
				}
			}
		});
		return st;
	}


	private RecordDef getCheckPermissionRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new BooleanFieldDef("acknowledgementPermission"),
						new BooleanFieldDef("wayViewPermission")
				}  
		); 
		return recordDef;
	}

	@Override
	public void refreshData() {
		gridJob.getStore().load(getPanelParent().getUrlParams());
	}

	@Override
	public void loadData() {
		getEl().mask(i18nContants.ScreenManager_Loading_data(), "x-mask-loading");
		loadOnlyDataGrid();
	}

	private void loadOnlyDataGrid(){
		checkPermissionStore.load(getPanelParent().getUrlParams());
	}

	private BaseMenu makeCellMenu(final Record record) {
		JobActionsMenu cmenu = new JobActionsMenu();

		String itemName = iServices.JobStatus_Suspend_Threshold();
		String iconCls = ("disable-threshold-icon");

		String parameters = ("");
		final MenuItem disableThreshold = getMenuItem(record, itemName, iconCls, parameters);
		disableThreshold.addListener(new BaseItemListenerAdapter(){	
			public void onClick(BaseItem item, EventObject e) {
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

				String serviceId = record.getAsString("serviceId");
				String nodeFrom = record.getAsString("nodeFrom");
				String description = record.getAsString("description");
				String sources = record.getAsString("sources");
				

				WindowServiceOperation windowServiceOperation = new  WindowServiceOperation(serviceId, description, nodeFrom,sources);
				windowServiceOperation.addParameters(getParameters());
				windowServiceOperation.setWindowParent(window);
				windowServiceOperation.init();

				window.addListener(new WindowListenerAdapter(){
					@Override
					public void onClose(Panel panel) {
						refreshView();
					}
				});

				window.add(windowServiceOperation);
				window.show();
			}							
		});

		itemName = iServices.JobStatus_Change_Comment();
		iconCls = ("comment-icon");

		parameters = ("");
		final MenuItem changeComment = getMenuItem(record, itemName, iconCls, parameters);
		changeComment.addListener(new BaseItemListenerAdapter(){	
			public void onClick(BaseItem item, EventObject e) {
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
				String jobId = record.getAsString("jobId"); //$NON-NLS-1$
				String serviceDescription = record.getAsString("description"); //$NON-NLS-1$
				String nodeFrom = record.getAsString("nodeFrom"); //$NON-NLS-1$
				String comment = record.getAsString("comment");
				WindowChangeComment windowChangeComment = new WindowChangeComment(jobId, serviceDescription, nodeFrom,comment);
				windowChangeComment.addParameters(getParameters());
				windowChangeComment.setWindowParent(window);
				windowChangeComment.init();
				window.add(windowChangeComment);
				window.addListener(new WindowListenerAdapter(){
					@Override
					public void onClose(Panel panel) {
						refreshData();
					}
				});
				window.show();
			}							
		});

		itemName = iServices.JobStatus_Close_Job();
		iconCls = ("close-icon");
		final MenuItem closeJob = getMenuItem(record, itemName, iconCls, parameters);
		closeJob.addListener(new BaseItemListenerAdapter(){	
			public void onClick(BaseItem item, EventObject e) {
				if (record.getAsBoolean("controlThreshold")){
					MessageBox.alert(iServices.JobStatus_It_Is_Not_Possible_To_Close());
				}
				else {
					if(record.getAsBoolean("acknowledgementPermission") && record.getAsBoolean("userDetected")){
						//$NON-NLS-1$
						MessageBox.confirm(iServices.JobStatus_Close_Job(), //$NON-NLS-1$
								iServices.JobStatus_Are_you_sure_you_want_to_do_that(),   //$NON-NLS-1$
								new MessageBox.ConfirmCallback() {  
							public void execute(String btnID) {
								if(btnID.equals("yes")){ //$NON-NLS-1$
									acknowledgement();
								}
							} 
							private void acknowledgement() {
								Integer jobId = record.getAsInteger("jobId"); //$NON-NLS-1$

								UrlParam[] urlParams = getAcknoledgmentURLParams(jobId);
								Store st = getAcknoledgmentStore();
								st.setBaseParams(getPanelParent().getUrlParams());
								st.load(urlParams);
							}
						});
					}
				}

			}							
		});
		cmenu.addItem(disableThreshold);
		cmenu.addItem(changeComment);
		cmenu.addItem(closeJob);
		return cmenu;
	}

	private MenuItem getMenuItem(final Record record,
			final String itemName, final String iconCls,
			final String parameters) {
		final MenuItem menI = new MenuItem();

		menI.setText(itemName);
		menI.setIconCls(iconCls);
		return menI;
	}

	private UrlParam[] getAcknoledgmentURLParams(Integer jobId) {
		UrlParam[] urlParams = new UrlParam[1];
		urlParams[0] = new UrlParam("jobId", jobId); //$NON-NLS-1$
		return urlParams;
	}
	
	

}
