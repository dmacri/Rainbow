package it.icarcnr.rainbow.client.graph;

import it.icarcnr.rainbow.client.services.ServiceStatus;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IParameterHttpServletRequestContants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.i18n.graph.INetworkGraph;
import it.icarcnr.rainbow.client.util.jit.AfterComputeEvent;
import it.icarcnr.rainbow.client.util.jit.AfterComputeHandler;
import it.icarcnr.rainbow.client.util.jit.BeforeComputeEvent;
import it.icarcnr.rainbow.client.util.jit.BeforeComputeHandler;
import it.icarcnr.rainbow.client.util.jit.ClickLabelEvent;
import it.icarcnr.rainbow.client.util.jit.ClickLabelHandler;
import it.icarcnr.rainbow.client.util.jit.JitWidget;
import it.icarcnr.rainbow.client.util.jit.SpaceTree;
import it.icarcnr.rainbow.client.util.pagebus.GraphAreaMessage;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.gwtext.client.core.Ext;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.ObjectFieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.pagebus.SubscriptionCallback;
import com.gwtext.client.widgets.BoxComponent;
import com.gwtext.client.widgets.Container;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

public class NetworkGraphRepresentation extends BasePanel {

	private static final String LOAD_GRAPH_ACTION_PATH = "../graph/loadGraph.do"; //$NON-NLS-1$
	private Store graphStore;
	private final String jitName = "spacetree"; //$NON-NLS-1$
	private static final String defaultBackgroundColor = "#fff"; //$NON-NLS-1$

	private boolean firstTimeLoad = true;
	private boolean loading = false;

	private SpaceTree spaceTree;
	private Panel spaceTreePanel;

	private NetworkGraphManager networkGraphManager;

	private static INetworkGraph iNetworkGraph = (INetworkGraph)GWT.create(INetworkGraph.class);
	private static Ii18nGlobalConstants i18nConstants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);

	
	@Override
    public void init() {
		PageBus.subscribe(ISubjectToPublishTheMessageTo.NETWORK_GRAPH, new SubscriptionCallback() {		
			@Override
			public void execute(String subject, Object message) {
				nodeSelectMessage(message);
			}
		});
		
		setLayout(new FitLayout());
		setTitle(); //$NON-NLS-1$

		networkGraphManager = new NetworkGraphManager();

		graphStore = getGraphStore();

		spaceTreePanel = makeSpaceTreePanel();

		add(spaceTreePanel);
		doLayout(true);

	}
	

	protected void nodeSelectMessage(Object message) {
		if(message instanceof GraphAreaMessage){
			GraphAreaMessage graphAreaMessage = (GraphAreaMessage)message;
			selectNode(graphAreaMessage.getNodeUniqueId());
		}
		
	}


	/**
	 * @return
	 */
	private Panel makeSpaceTreePanel() {
		Panel spaceTreePanel = new Panel();

		spaceTreePanel.addListener(new PanelListenerAdapter(){
			public void onAfterLayout(Container self) {
				if(firstTimeLoad){
					loadGraphStore();
					firstTimeLoad = false;
				}

			}
			@Override
			public void onResize(BoxComponent component, int adjWidth,
					int adjHeight, int rawWidth, int rawHeight) {
				ResizeEvent.fire(spaceTree, adjWidth, adjHeight);
				super.onResize(component, adjWidth, adjHeight, rawWidth, rawHeight);
			}
		});

		setTitle();
		spaceTree = makeSpaceTree();
		spaceTreePanel.add(spaceTree);
		return spaceTreePanel;
	}

	private void loadGraphStore() {
		getEl().mask(iNetworkGraph.NetworkGraph_Loading(), "x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
		loading = true;
		graphStore.load(getUrlParams());
	}

	/**
	 * @return
	 */
	private SpaceTree makeSpaceTree() {
		
		int width = getPanelParent().getWidth();
		int height = getPanelParent().getHeight();

		String firstTooltipMessage = iNetworkGraph.NetworkGraph_firstTooltipMessage();
		String secondTooltipMessage = iNetworkGraph.NetworkGraph_secondTooltipMessage();


		spaceTree = new SpaceTree(jitName, networkGraphManager.getConfig(Ext.isIE(),jitName, defaultBackgroundColor, width, height, firstTooltipMessage, secondTooltipMessage));

		spaceTree.addClickLabelHandler(new ClickLabelHandler() {

			@Override
			public void onRightClickLabel(ClickLabelEvent event) {
				JitWidget jit = (JitWidget) event.getSource();
				rightClickLabelAction(jit.getClickedNode());
			}

			@Override
			public void onClickLabel(ClickLabelEvent event) {

			}
		});

		spaceTree.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				spaceTree.onResize(event);

			}
		});

		spaceTree.addBeforeComputeHandler(new BeforeComputeHandler() {

			@Override
			public void onBeforeCompute(BeforeComputeEvent event) {
				getEl().mask(iNetworkGraph.NetworkGraph_Drawing_nodes(), "x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		});

		spaceTree.addAfterComputeHandler(new AfterComputeHandler() {

			@Override
			public void onAfterCompute(AfterComputeEvent event) {
				if(!loading){
					getEl().unmask();
				}
			}
		});

		return spaceTree;
	}

	protected void rightClickLabelAction(JavaScriptObject clickedNode) {
		JSONObject node = new JSONObject(clickedNode);
		String status = getNodeStatus(node);
		if(status!=null){
			showServiceStatusWidow(node);
		}else{
			MessageBox.alert(i18nConstants.rainbowClient_Info(),iNetworkGraph.NetworkGraph_NoServiceForThisNetworkItem());
		}

	}


	private String getNodeStatus(JSONObject node) {
		String status = null;
		JSONValue jsonValueStatus = node.get("data").isObject().get("status");
		if(jsonValueStatus!=null){
			JSONString jsonStringStatus = jsonValueStatus.isString();
			if(jsonStringStatus!=null){
				status = jsonStringStatus.stringValue();
			}
		}
		return status;
	}


	private void showServiceStatusWidow(JSONObject node) {
		Window window = new Window();
		window.setClosable(true);
		window.setAnimCollapse(true);
		window.setMaximizable(true);  
		window.setResizable(true);  
		window.setIconCls("services-tree-status-icon");  //$NON-NLS-1$
		window.setWidth(800);  
		window.setHeight(400);  
		window.setPlain(true);  
		window.setLayout(new FitLayout());
		window.setCloseAction(Window.CLOSE);

		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.addParameters(getParameters());

		serviceStatus.addParameter(IParameterHttpServletRequestContants.NODE_SENSOR_ID,node.get("data").isObject().get(IParameterHttpServletRequestContants.NODE_SENSOR_ID)); //$NON-NLS-1$
		serviceStatus.addParameter(IParameterHttpServletRequestContants.SENSOR_ID,node.get("data").isObject().get(IParameterHttpServletRequestContants.SENSOR_ID)); //$NON-NLS-1$
		serviceStatus.addParameter(IParameterHttpServletRequestContants.NODE_ID,node.get("data").isObject().get(IParameterHttpServletRequestContants.NODE_ID)); //$NON-NLS-1$

		StringBuilder windowTitle = new StringBuilder(node.get("data").isObject().get("fullName").isString().stringValue());
		serviceStatus.addParameter("title",new JSONString(windowTitle.toString())); //$NON-NLS-1$
		serviceStatus.setWindowParent(window);
		serviceStatus.init();
		
		window.add(serviceStatus);
		window.setTitle(iNetworkGraph.NetworkGraph_Monitoring()); //$NON-NLS-1$
		window.show();
	}

	/**
	 * 
	 */
	private void loadGraph() {
		firstTimeLoad = true;
		clear();
		spaceTreePanel = makeSpaceTreePanel();
		add(spaceTreePanel);
		doLayout(true);
	}


	private Store getGraphStore() {
		HttpProxy dataProxyLoad = new HttpProxy(LOAD_GRAPH_ACTION_PATH);
		RecordDef dataRecordDef = getGraphRecordDef();
		JsonReader readerLoad = new JsonReader(dataRecordDef);
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad, readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
				loading = false;
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				loading = false;
				if (records != null && records.length > 0) {
					Record record = records[0];
					JavaScriptObject graph = (JavaScriptObject) record.getAsObject("graph"); //$NON-NLS-1$
					String nodeUniqueIdToExpand = null;
					if(getParameters().containsKey(IParameterHttpServletRequestContants.NODE_UNIQUE_ID)){
						JSONString jsonString = getParameters().get(IParameterHttpServletRequestContants.NODE_UNIQUE_ID).isString();
						if(jsonString!=null){
							nodeUniqueIdToExpand = jsonString.stringValue();
						}
					}
					networkGraphManager.loadAndDisplay(jitName, graph, nodeUniqueIdToExpand);
					doLayout(true);
				}
			}
		});
		return st;
	}

	private RecordDef getGraphRecordDef() {
		RecordDef recordDef = new RecordDef(
				new FieldDef[] { 
						new ObjectFieldDef("graph"), //$NON-NLS-1$
				});
		return recordDef;
	}

	@Override
	public void loadData() {
		setTitle(); //$NON-NLS-1$
		loadGraph();
	}

	@Override
	public void refreshData() {
		loadGraphStore();
	};


	private void setTitle() {
//		String title = null;
//		if(getParameters()!=null){
//			if(getParameters().containsKey("title")){ //$NON-NLS-1$
//				title = ((JSONString)getParameters().get("title")).stringValue(); //$NON-NLS-1$
//			}
//		}
//		if(title==null){
//			BasePanel parent = getPanelParent();
//			if(parent!=null){
//				title = parent.getTitle();
//			}
//		}
//		if(title!=null){
//			setTitle(title);
//		}
	}
	
	private void selectNode(String nodeIdToExpand ){
		networkGraphManager.selectNode(jitName, nodeIdToExpand);
	}
}

