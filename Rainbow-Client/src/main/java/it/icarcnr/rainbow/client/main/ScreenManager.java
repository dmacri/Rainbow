

package it.icarcnr.rainbow.client.main;

import it.icarcnr.rainbow.client.maps.MapsBean;
import it.icarcnr.rainbow.client.maps.MapsServiceNew;
import it.icarcnr.rainbow.client.util.basecomponents.ScreenPanel;
import it.icarcnr.rainbow.client.util.constants.IAreaConstants;
import it.icarcnr.rainbow.client.util.constants.IMenuTreeConstants;
import it.icarcnr.rainbow.client.util.constants.IParameterHttpServletRequestContants;
import it.icarcnr.rainbow.client.util.i18n.global.Ii18nGlobalConstants;
import it.icarcnr.rainbow.client.util.pagebus.GraphAreaMessage;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.pagebus.SubscriptionCallback;
import com.gwtext.client.util.JSON;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.NodeExpansionCallback;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.AsyncTreeNodeListenerAdapter;
import com.gwtext.client.widgets.tree.event.TreeLoaderListenerAdapter;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;


public class ScreenManager{
	static Ii18nGlobalConstants i18nContants = (Ii18nGlobalConstants)GWT.create(Ii18nGlobalConstants.class);
	private final static String XML_MENU_TREE_ACTION_PATH = "../menutree/xmlMenuTree.do"; //$NON-NLS-1$

	private TabPanel appTabPanel;
	private RainbowMain mainPanel;
	private TreePanel treePanel;
	private boolean startApplication;

	public ScreenManager(RainbowMain mainPanel, TabPanel tabPanel) {
		this.mainPanel = mainPanel;
		this.appTabPanel = tabPanel;
		startApplication = true;
		ScreenPanelRegistrator.loadScreenPanelRegistratorMap(appTabPanel,mainPanel,new MapsBean());

		PageBus.subscribe(ISubjectToPublishTheMessageTo.GRAPH_AREA, new SubscriptionCallback() {		
			@Override
			public void execute(String subject, Object message) {
				opnenGraphArea(message);
			}
		});
	}
	
	
	public  void showScreen(ScreenPanel panel, String title, String iconCls, String screenName) {
		String panelID = panel.getId();
		if(!appTabPanel.getActiveTab().getId().equals(panelID)){
			mainPanel.setRefreshOnTabChange(false);
		}
		panel.setIconCls(iconCls);
		if (appTabPanel.hasItem(panelID)) {
			appTabPanel.scrollToTab(panel, true);
			appTabPanel.activate(panelID);
			panel.loadData();
		} else {
			if (!panel.isRendered()) {
				panel.setTitle(title);
				if (iconCls == null) {
					iconCls = "plugins-nav-icon"; //$NON-NLS-1$
				}
				panel.setClosable(true);
				panel.init();
			}else{
				panel.loadData();
			}
			appTabPanel.add(panel);
			appTabPanel.activate(panel.getId()); 
		}
		mainPanel.refreshSummary();
	}

	public TreePanel getTreeNav() {
		treePanel = new TreePanel();

		treePanel.setTitle("");
		treePanel.setId("nav-tree"); //$NON-NLS-1$
		treePanel.setWidth(180);
		treePanel.setCollapsible(false);
		treePanel.setAnimate(true);
		treePanel.setEnableDD(false);
		treePanel.setAutoScroll(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setBorder(false);
		//		treePanel.setTopToolbar(getFilterToolbar());
		treePanel.setTopToolbar(getCollapseToolbar());


		XMLTreeLoader loader = new XMLTreeLoader();
		loader.setDataUrl(XML_MENU_TREE_ACTION_PATH);
		loader.setMethod(Connection.GET);
		loader.setRootTag("menuTree"); //$NON-NLS-1$
		loader.setFolderIdMapping("@id"); //$NON-NLS-1$
		loader.setLeafIdMapping("@id"); //$NON-NLS-1$
		loader.setFolderTitleMapping("@title"); //$NON-NLS-1$
		loader.setFolderTag("folder"); //$NON-NLS-1$
		loader.setLeafTitleMapping("@title"); //$NON-NLS-1$
		loader.setLeafTag("leaf"); //$NON-NLS-1$
		loader.setQtipMapping("@qtip"); //$NON-NLS-1$
		loader.setDisabledMapping("@disabled"); //$NON-NLS-1$
		loader.setCheckedMapping("@checked"); //$NON-NLS-1$
		loader.setIconMapping("@icon"); //$NON-NLS-1$
		loader.setIconClsMapping("@iconCls"); //$NON-NLS-1$
		loader.setExpandedMapping("@expanded"); //$NON-NLS-1$
		loader.setPreloadChildren(false);



		loader.addListener(new TreeLoaderListenerAdapter(){

			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
				treePanel.getEl().unmask();
				MessageBox.alert(i18nContants.ScreenManager_Error(),i18nContants.ScreenManager_System_busy_Please_try_later());
			}

		});

		String[] atts = new String[4];
		atts[0] = "@className"; //$NON-NLS-1$
		atts[1] = "@parameters"; //$NON-NLS-1$
		atts[2] = "@iconPanel"; //$NON-NLS-1$
		atts[3] = "@titlePanel"; //$NON-NLS-1$

		loader.setAttributeMappings(atts);


		final AsyncTreeNode root = new AsyncTreeNode("Service Explorer", loader);
		treePanel.setRootNode(root);

		//		treeFilter = new TreeFilter(treePanel);

		treePanel.addListener(new TreePanelListenerAdapter() {
			public void onClick(TreeNode node, EventObject e) {
				menuTreeNodeClickAcion(node,null);
			}
		});

		root.addListener(new AsyncTreeNodeListenerAdapter(){
			@Override
			public void onLoad(AsyncTreeNode node) {
				treePanel.getEl().unmask();  
			}
		});

		return treePanel;
	}


	private Toolbar getCollapseToolbar() {
		final Toolbar collapseToolbar = new Toolbar();

		collapseToolbar.addFill();

		ToolbarButton collapseButton = new ToolbarButton();
		collapseButton.setCls("x-btn-icon collapse-all-btn"); //$NON-NLS-1$
		collapseButton.setTooltip(i18nContants.ScreenManager_Collapse_All()); //$NON-NLS-1$
		collapseButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				treePanel.collapseAll();
			}
		});

		collapseToolbar.addButton(collapseButton);
		return collapseToolbar;
	}

	private void menuTreeNodeClickAcion(final TreeNode node, JSONObject jsonParameters) {
		if (!node.isDisabled()) {
			final String className = node.getAttribute("className"); //$NON-NLS-1$
			final String parameters = node.getAttribute("parameters"); //$NON-NLS-1$
			final String iconCls = node.getAttribute("iconPanel"); //$NON-NLS-1$
			final String title = node.getAttribute("titlePanel"); //$NON-NLS-1$
			if(className!=null){
				ScreenPanel panel = ScreenPanelRegistrator.getScreenPanelMap().get(className);
				if (panel != null) {
					panel.setParameters(new JSONObject(JSON.decode(parameters)));
					panel.addParameters(jsonParameters);
					
					showScreen(panel, title, iconCls, node.getId());
				}
			}
		};
	}

	public void loadMenu(){
		if (startApplication){
			startApplication = false;
			treePanel.getEl().mask(i18nContants.ScreenManager_Loading_data(), "x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private void opnenGraphArea(Object message) {
		if(message instanceof GraphAreaMessage){
			GraphAreaMessage graphAreaMessage = (GraphAreaMessage)message;
			String elementURI = IMenuTreeConstants.AREA+IAreaConstants.GRAPH_AREA+IMenuTreeConstants.NETWORK+graphAreaMessage.getNetworkId();
			TreeNode treeNode = treePanel.getNodeById(elementURI);
			if (treeNode!=null){
				treePanel.expandPath(treeNode.getParentNode().getPath(), new NodeExpansionCallback() {
					@Override
					public void onExpand(boolean success,
							TreeNode lastNode) {
						// TODO Auto-generated method stub
						
					}
				});
				
				treeNode.select();
				JSONObject jsonParameters = new JSONObject();
				String nodeUniqueId = graphAreaMessage.getNodeUniqueId();
				if(nodeUniqueId!=null){
					jsonParameters.put(IParameterHttpServletRequestContants.NODE_UNIQUE_ID, new JSONString(nodeUniqueId));
				}
				menuTreeNodeClickAcion(treeNode,jsonParameters);
			}
		}
	}
	public TabPanel getAppTabPanel() {
		return appTabPanel;
	}

}
