package it.icarcnr.rainbow.client.utility.execution;

import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IUtilityConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.DateFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.ObjectFieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.WindowListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.ColumnNodeUI;
import com.gwtext.client.widgets.tree.ColumnTree;
import com.gwtext.client.widgets.tree.ColumnTreeListenerAdapter;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.event.AsyncTreeNodeListenerAdapter;

public class MyCronjob extends BasePanel {
		
	static IUtility iUtility = (IUtility)GWT.create(IUtility.class);
	private AsyncTreeNode root;
	private TreeLoader loader;
	private Store store;
	private String currentUtilityStatusToView = IUtilityConstants.ALL_STATUS; //$NON-NLS-1$
	
	@Override
    public void init() {

		setLayout(new FitLayout());
		setCollapsible(true);
		setAutoScroll(true);
		setWidth(340);
		setBorder(true);
		setFrame(true);
		setTitle(iUtility.MyCronjob_Utility_Status()); //$NON-NLS-1$
		
		store = getStore();
		
		ColumnTree coltree = new ColumnTree();  
		coltree.setFrame(true);
		coltree.setAutoHeight(true);
		coltree.setAutoWidth(true);
		coltree.setRootVisible(false);  
		coltree.setAutoScroll(true);
		coltree.setStyle("background-color:#FFFFFF; td"); //$NON-NLS-1$
		
		final ColumnTree.Column cols[] = new ColumnTree.Column[2];
		cols[0] = coltree.new Column(iUtility.MyCronjob_Task(), 215, "task"); //$NON-NLS-1$ //$NON-NLS-2$
		cols[1] = coltree.new Column(iUtility.MyCronjob_Date(), 100, "datestart"); //$NON-NLS-1$ //$NON-NLS-2$
		coltree.setColumns(cols);
		
		Toolbar topToolbar = makeTopToolbar();
		setTopToolbar(topToolbar);
		
		coltree.addListener(new ColumnTreeListenerAdapter() {
			
			@Override
			public void onDblClick(TreeNode node, EventObject e) {
				// TODO Auto-generated method stub 
				if (node.getAttribute("status").equals(IUtilityConstants.SCHEDULED_STATUS)) { //$NON-NLS-1$ //$NON-NLS-2$
					MessageBox.alert(iUtility.MyCronjob_This_job_have_no_logs_because_is_scheduled_at()+node.getAttribute("datestart")); //$NON-NLS-1$ //$NON-NLS-2$
				}
				else if (node.getAttribute("status").equals(IUtilityConstants.RUNNING_STATUS) && !(Boolean)node.getAttributeAsObject("timerexecution")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					MessageBox.alert(iUtility.MyCronjob_You_dont_have_permission_to_see_log_pending_the_job_is_running()); //$NON-NLS-1$
				}
				else {
					UrlParam[] params = new UrlParam[1];
					params[0] = new UrlParam("idCronjob", node.getAttribute("idjob")); //$NON-NLS-1$ //$NON-NLS-2$
					store.load(params);
				}
				
				//super.onDblClick(node, e);
			}
			
		});
		
		loader = new TreeLoader();
		loader.setPreloadChildren(true);
		loader.setDataUrl(IActionPathConstants.MYCRONJOB_ACTION_PATH);
		addParameter("view", new JSONString(currentUtilityStatusToView)); //$NON-NLS-1$
		loader.setBaseParams(getUrlParams());
		loader.setMethod(Connection.GET);
		loader.setUiProviders(ColumnNodeUI.getUiProvider()); 
		
		root = new AsyncTreeNode(iUtility.MyCronjob_Tasks(), loader); //$NON-NLS-1$
		root.addListener(new AsyncTreeNodeListenerAdapter(){
			@Override
			public void onLoad(AsyncTreeNode node) {
				// TODO Auto-generated method stub
				getEl().unmask();
				super.onLoad(node);
			}
		});
		coltree.setRootNode(root);
		add(coltree);

	}
	
	private Toolbar makeTopToolbar() {
		Toolbar toolbar = new Toolbar();
		
		ToolbarButton refreshButton = new ToolbarButton();
		refreshButton.setTooltip(iUtility.MyCronjob_Refresh_Data()); //$NON-NLS-1$
		refreshButton.setText(iUtility.MyCronjob_Refresh()); //$NON-NLS-1$
		refreshButton.setIconCls("refresh-icon"); //$NON-NLS-1$
		refreshButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				refreshData(currentUtilityStatusToView);
			}
		});

		toolbar.addButton(refreshButton);
		toolbar.addFill();
		ToolbarButton allButton = new ToolbarButton();
		allButton.setToggleGroup("view"); //$NON-NLS-1$
		allButton.setTooltip(iUtility.MyCronjob_All_Jobs()); //$NON-NLS-1$
		allButton.setText(iUtility.MyCronjob_All()); //$NON-NLS-1$
		allButton.toggle(true);
		allButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				button.toggle(true);
				currentUtilityStatusToView=IUtilityConstants.ALL_STATUS; //$NON-NLS-1$
				refreshData(currentUtilityStatusToView);
			}
		});
		ToolbarButton scheduledButton = new ToolbarButton();
		scheduledButton.setToggleGroup("view"); //$NON-NLS-1$
		scheduledButton.setTooltip(iUtility.MyCronjob_Job_Scheduled()); //$NON-NLS-1$
		scheduledButton.setText(iUtility.MyCronjob_Scheduled()); //$NON-NLS-1$
		scheduledButton.setIconCls("mycronjob-grey"); //$NON-NLS-1$
		scheduledButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				button.toggle(true);
				currentUtilityStatusToView=IUtilityConstants.SCHEDULED_STATUS; //$NON-NLS-1$
				refreshData(currentUtilityStatusToView);
				
			}
		});
		ToolbarButton runningButton = new ToolbarButton();
		runningButton.setToggleGroup("view"); //$NON-NLS-1$
		runningButton.setTooltip(iUtility.MyCronjob_Job_Running()); //$NON-NLS-1$
		runningButton.setText(iUtility.MyCronjob_Running()); //$NON-NLS-1$
		runningButton.setIconCls("mycronjob-yellow"); //$NON-NLS-1$
		runningButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				button.toggle(true);
				currentUtilityStatusToView=IUtilityConstants.RUNNING_STATUS; //$NON-NLS-1$
				refreshData(currentUtilityStatusToView);
			}
		});
		ToolbarButton executedButton = new ToolbarButton();
		executedButton.setToggleGroup("view"); //$NON-NLS-1$
		executedButton.setTooltip(iUtility.MyCronjob_Job_Executed()); //$NON-NLS-1$
		executedButton.setText(iUtility.MyCronjob_Executed()); //$NON-NLS-1$
		executedButton.setIconCls("mycronjob-green"); //$NON-NLS-1$
		executedButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				button.toggle(true);
				currentUtilityStatusToView=IUtilityConstants.EXECUTED_STATUS; //$NON-NLS-1$
				refreshData(currentUtilityStatusToView);
			}
		});
		toolbar.addButton(allButton);
		toolbar.addButton(scheduledButton);
		toolbar.addButton(runningButton);
		toolbar.addButton(executedButton);
		
		

		return toolbar;
	}
	
	@Override
	public void refreshData() {
		refreshData(currentUtilityStatusToView);
	};
	
	@Override
	public void loadData() {
		refreshData(currentUtilityStatusToView);
	}
	
	private void refreshData(String view) {
		getEl().mask(iUtility.MyCronjob_Loading(),"x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
		addParameter("view", new JSONString(view)); //$NON-NLS-1$
		loader.setBaseParams(getUrlParams());
		root.reload();
	}
	
	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.MYCRONJOB_VIEWLOG_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);;
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoadException(Throwable error) {
				MessageBox.alert(iUtility.Error(),iUtility.System_busy_Please_try_later());
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				// TODO Auto-generated method stub
				Record record = records[0];
				
				final Window window = new Window();  
				window.setClosable(true);  
				window.setWidth(600);  
				window.setHeight(400);
				window.setPlain(true);  
				window.setLayout(new FitLayout());
				window.setCloseAction(Window.CLOSE);
				window.setTitle(iUtility.MyCronjob_Log_Execution_Utility()); //$NON-NLS-1$
				
				final MyCronjobViewLogPanel myPanel = new MyCronjobViewLogPanel();
				myPanel.setRecord(record);
				myPanel.init();
				
				window.add(myPanel);
				window.addListener(new WindowListenerAdapter() {
					@Override
					public void onClose(Panel panel) {
						myPanel.unscheduleRefreshData();
						super.onClose(panel);
					}
				});
				window.show();
			}
		});
		return st;
	}


	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{
						new StringFieldDef("utilityDescription"),
						new StringFieldDef("status"),
						new ObjectFieldDef("outputExecution"),
						new StringFieldDef("outputFormat"),
						new DateFieldDef("startExecution",IDateFormatUtil.dateTimeFormat),
						new DateFieldDef("endExecution",IDateFormatUtil.dateTimeFormat)
				}  
		); 
		return recordDef;
	}
	
	
}
