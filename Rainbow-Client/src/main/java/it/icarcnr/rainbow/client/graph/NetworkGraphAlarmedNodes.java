package it.icarcnr.rainbow.client.graph;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IParameterHttpServletRequestContants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.i18n.graph.INetworkGraph;
import it.icarcnr.rainbow.client.util.pagebus.GraphAreaMessage;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.AsyncTreeNodeListenerAdapter;
import com.gwtext.client.widgets.tree.event.TreeLoaderListenerAdapter;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;

public class NetworkGraphAlarmedNodes extends BasePanel {
	
	static Ii18nGlobalConstants i18nContants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);
	static INetworkGraph iNetworkGraph = (INetworkGraph)GWT.create(INetworkGraph.class);
	
	private Integer networkId;
	
	private TreePanel treePanel;
	private AsyncTreeNode treeNode;
	
	private ToolbarTextItem selectedItemMessage;

	
	@Override
    public void init() {
		setTitle(iNetworkGraph.RoleGraphAlarmed_AlarmedRole()); //$NON-NLS-1$
		setFrame(true);
		setLayout(new FitLayout());
		setWidth(270);
		setCollapsible(true);
		
		if(getParameters()!=null){
			if(getParameters().containsKey(IParameterHttpServletRequestContants.NODE_SENSOR_ID)){
				networkId = Double.valueOf(getParameters().get(IParameterHttpServletRequestContants.NODE_SENSOR_ID).isNumber().doubleValue()).intValue();
			}
		}
		
		add(getTreePanel());

	}

	private TreePanel getTreePanel() {
		treePanel = new TreePanel();
//		treePanel.setTitle("");
//		treePanel.setWidth(180);
		treePanel.setCollapsible(false);
		treePanel.setAnimate(true);
		treePanel.setEnableDD(false);
		treePanel.setAutoScroll(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setBorder(false);

		treePanel.setBottomToolbar(makeToolbar());


		TreeLoader loader = makeTreeLoader();


		treeNode = new AsyncTreeNode("Service Explorer", loader);
		treePanel.setRootNode(treeNode);

		treePanel.addListener(new TreePanelListenerAdapter() {
			public void onClick(TreeNode node, EventObject e) {
				menuTreeNodeClickAcion(node,null);
			}
		});

		treeNode.addListener(new AsyncTreeNodeListenerAdapter(){
			@Override
			public void onLoad(AsyncTreeNode node) {
				treePanel.getEl().unmask();  
			}
		});

		return treePanel;
	}

	private TreeLoader makeTreeLoader() {
		TreeLoader loader = new TreeLoader();
		loader.setDataUrl(IActionPathConstants.ALARMED_NODE_ACTION_PATH);
		loader.setBaseParams(getUrlParams());
		loader.setMethod(Connection.GET);
		loader.setPreloadChildren(false);
		
		loader.addListener(new TreeLoaderListenerAdapter(){

			@Override
			public boolean doBeforeLoad(TreeLoader self, TreeNode node) {
				selectedItemMessage.setText(i18nContants.ScreenManager_Loading_data());
				getEl().mask(i18nContants.ScreenManager_Loading_data());
				return super.doBeforeLoad(self, node);
			}
			@Override
			public void onLoad(TreeLoader self, TreeNode node, String response) {
				if(node!=null  ){
					if (node.getChildNodes().length > 0){
						selectedItemMessage.setText("");
						getEl().unmask();
					}else{
						selectedItemMessage.setText(iNetworkGraph.NetworkGraphAlarmedNode_NoAlarmedNode()); //$NON-NLS-1$
						getEl().unmask();
					}

				}
				super.onLoad(self, node, response);
			}
			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
				selectedItemMessage.setText(i18nContants.ScreenManager_Error());
			}
		});

		return loader;
	}
	
	protected void menuTreeNodeClickAcion(TreeNode node, Object object) {
		GraphAreaMessage graphAreaMessage = new GraphAreaMessage();
		graphAreaMessage.setNetworkId(networkId);
		graphAreaMessage.setNodeUniqueId(node.getAttribute(IParameterHttpServletRequestContants.NODE_UNIQUE_ID));
		PageBus.publish(ISubjectToPublishTheMessageTo.NETWORK_GRAPH, graphAreaMessage);
		
	}

	private Toolbar makeToolbar() {
		final Toolbar toolbar = new Toolbar();
		
		selectedItemMessage = new ToolbarTextItem(""); //$NON-NLS-1$
		toolbar.addItem(selectedItemMessage);

//		toolbar.addFill();
//
//		ToolbarButton collapseButton = new ToolbarButton();
//		collapseButton.setCls("x-btn-icon collapse-all-btn"); //$NON-NLS-1$
//		collapseButton.setTooltip(i18nContants.ScreenManager_Collapse_All()); //$NON-NLS-1$
//		collapseButton.addListener(new ButtonListenerAdapter() {
//			public void onClick(Button button, EventObject e) {
//				treePanel.collapseAll();
//			}
//		});
//
//		toolbar.addButton(collapseButton);
		return toolbar;
	}
	
	@Override
	public void refreshData() {
		treeNode.reload();
	}
	
	@Override
	public void loadData() {
		treeNode.setLoader(makeTreeLoader());
		treeNode.reload();
	}

}
