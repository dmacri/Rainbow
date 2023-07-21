package it.icarcnr.rainbow.client.checkboxtree;

import it.icarcnr.rainbow.client.util.UrlParamsUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.checkboxtree.IServiceCheckBoxTree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
import com.gwtext.client.data.Node;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.TreeLoader;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.AsyncTreeNodeListenerAdapter;
import com.gwtext.client.widgets.tree.event.TreeLoaderListenerAdapter;

public class ServiceCheckBoxTree extends BasePanel {

	private AsyncTreeNode root;
	private XMLTreeLoader loader;
	private TreePanel treePanel;
	private ToolbarButton cb ;
	
	static IServiceCheckBoxTree iServiceCheckBoxTree = (IServiceCheckBoxTree)GWT.create(IServiceCheckBoxTree.class);
	
	@Override
	public void init() {
		Toolbar toolbar = new Toolbar();  
		setLayout(new FitLayout());
		setTitle(iServiceCheckBoxTree.ServiceCheckBoxTree_Elements_of_network()); //$NON-NLS-1$
		setFrame(false);
		setHeight(160);
		setCollapsible(true);
		setBorder(true); 
		setMargins(3, 0, 0, 0);
		boolean checked = false;
		cb = new ToolbarButton(iServiceCheckBoxTree.ServiceCheckBoxTree_Select_all(),new ButtonListenerAdapter() {   //$NON-NLS-1$
			public void onClick(Button button, EventObject e) {  
				if(cb.isPressed()){
					checkAllNode();
				}else{
					uncheckAllNode();
				}
			}  

			public void onToggle(Button button, boolean pressed) {
				if (pressed)
					button.setIcon("js/ext/resources/images/default/menu/checked.gif"); //$NON-NLS-1$
				else
					button.setIcon("js/ext/resources/images/default/menu/unchecked.gif"); //$NON-NLS-1$
			}
		});
		
		cb.setEnableToggle(true);
		toolbar.addFill();
		toolbar.addButton(cb);
		
		cb.setPressed(checked);
		if (checked)
			cb.setIcon("js/ext/resources/images/default/menu/checked.gif"); //$NON-NLS-1$
		else
			cb.setIcon("js/ext/resources/images/default/menu/unchecked.gif"); //$NON-NLS-1$
		
		
		

		setBottomToolbar(toolbar);
		
		//		panel.setPaddings(15);  
		treePanel = makeTreePanel();
		add(treePanel);  

	}

	public void resetCheckButton(){
		cb.setPressed(false);
		cb.setIcon("js/ext/resources/images/default/menu/unchecked.gif");
		
	}

	/**
	 * 
	 */
	private TreePanel makeTreePanel() {
		treePanel = new TreePanel();  
//		treePanel.setTitle(iMServiceCheckBoxTree.ServiceCheckBoxTree_Nodes());
		treePanel.setId("node-icon"); //$NON-NLS-1$
		//		treePanel.setIconCls("world-icon");  
		//		treePanel.setHeight(220);  
		//		treePanel.setWidth(100);  
		//		treePanel.setCollapsible(true);
		treePanel.setAnimate(true);
		treePanel.setEnableDD(false);
		treePanel.setAutoScroll(true);
		treePanel.setContainerScroll(true);
		treePanel.setRootVisible(false);
		treePanel.setBorder(true); 
		treePanel.setFrame(true);



		loader = makeTreeLoader();

		root = new AsyncTreeNode(iServiceCheckBoxTree.ServiceCheckBoxTree_Services(), loader);   //$NON-NLS-1$
		treePanel.setRootNode(root);  

		root.addListener(new AsyncTreeNodeListenerAdapter(){
			@Override
			public void onLoad(AsyncTreeNode node) {
				treePanel.getEl().unmask();  
				//				root.expand(true, true);  
			}
		});

		//		root.expand();  

		treePanel.addTool(new Tool(Tool.REFRESH, new Function() {  
			public void execute() {  
				refreshData();  
			}  
		}, iServiceCheckBoxTree.ServiceCheckBoxTree_Refresh()));   //$NON-NLS-1$

//		treePanel.expandAll();  

		//		Button button = new Button("Show Checked", new ButtonListenerAdapter() {  
		//			public void onClick(Button button, EventObject e) {  
		//				TreeNode[] checkedNodes = treePanel.getChecked();  
		//				String checkedNodesString = "";  
		//				for (int i = 0; i < checkedNodes.length; i++) {  
		//					TreeNode checkedNode = checkedNodes[i];  
		//					checkedNodesString += "<br>" + checkedNode.getText();  
		//				}  
		//				System.out.println("Checked Nodes :" + checkedNodesString);  
		//			}  
		//		});  
		//
		//		Button toggle = new Button("Toggle Team A", new ButtonListenerAdapter() {  
		//			public void onClick(Button button, EventObject e) {  
		//				treePanel.getNodeById("team-a").getUI().toggleCheck();  
		//			}  
		//		});  

		//		treePanel.addButton(button);  
		//		treePanel.addButton(toggle);  
		return treePanel;
	}

	/**
	 * 
	 */
	private XMLTreeLoader makeTreeLoader() {
		XMLTreeLoader loader = new XMLTreeLoader();  
		String urlString = UrlParamsUtil.toUrlString(getUrlParams());
		String actionPath = IActionPathConstants.SERVICE_CHECK_BOX_TREE_ACTION_PATH;
		if(urlString!=null && !urlString.isEmpty()){
			actionPath= actionPath+"?"+urlString; //$NON-NLS-1$
		}
		loader.setDataUrl(actionPath);  
		loader.setMethod(Connection.GET);  
		loader.setRootTag("servicesTree");   //$NON-NLS-1$
		loader.setFolderIdMapping("@id");   //$NON-NLS-1$
		loader.setLeafIdMapping("@id");   //$NON-NLS-1$
		loader.setFolderTitleMapping("@title"); //$NON-NLS-1$
		loader.setFolderTag("services");   //$NON-NLS-1$
		loader.setLeafTitleMapping("@title");   //$NON-NLS-1$
		loader.setLeafTag("service");   //$NON-NLS-1$
		loader.setQtipMapping("@qtip");   //$NON-NLS-1$
		loader.setDisabledMapping("@disabled");   //$NON-NLS-1$
		loader.setCheckedMapping("@checked");   //$NON-NLS-1$
		loader.setIconClsMapping("@iconCls"); //$NON-NLS-1$
		loader.setExpandedMapping("@expanded"); //$NON-NLS-1$
		loader.setPreloadChildren(false);
		loader.addListener(new TreeLoaderListenerAdapter(){
			/* (non-Javadoc)
			 * @see com.gwtext.client.widgets.tree.event.TreeLoaderListenerAdapter#onLoadException(com.gwtext.client.widgets.tree.TreeLoader, com.gwtext.client.widgets.tree.TreeNode, java.lang.String)
			 */
			@Override
			public void onLoadException(TreeLoader self, TreeNode node,
					String response) {
			}
		});

		String[] atts = new String[1];
		atts[0] = "@serviceId"; //$NON-NLS-1$

		loader.setAttributeMappings(atts);

		return loader;
	}  

	@Override
	public void refreshData() {
		treePanel.getEl().mask(iServiceCheckBoxTree.ServiceCheckBoxTree_Loading(), "x-mask-loading");   //$NON-NLS-1$ //$NON-NLS-2$
		loader = makeTreeLoader();
		root.setLoader(loader);
		root.reload();
		//		root.collapse(true, false);
	}

	public void reset(){
		if(root!=null){
			Node[] nodes = root.getChildNodes();
			if(nodes!=null){
				Node[] children = nodes[0].getChildNodes();
				if(children!=null){
					for (Node child : children) {
						child.remove();
					}
				}
			}
		}

	}

	public List<Integer> getChecked(){
		List<Integer> serviceIdList = new ArrayList<Integer>();
		TreeNode[] checkedNodes = treePanel.getChecked();  
		for (int i = 0; i < checkedNodes.length; i++) {  
			TreeNode checkedNode = checkedNodes[i];  
			serviceIdList.add(Integer.valueOf(checkedNode.getAttribute("serviceId"))); //$NON-NLS-1$
		} 
		return serviceIdList;

	}
	public void checkAllNode(){
		Node[] rootNode =  treePanel.getRootNode().getChildNodes();
		if(rootNode!=null && rootNode.length > 0){
			Node rn = rootNode[0];
			if(rn!=null){
				Node[] rnList = rn.getChildNodes();
				for (int i = 0; i < rnList.length; i++) {
					TreeNode child = (TreeNode) rnList[i];
					child.getUI().toggleCheck(Boolean.TRUE);
				}
			}
		}
	}
	public void uncheckAllNode(){
		Node[] rootNode =  treePanel.getRootNode().getChildNodes();
		if(rootNode!=null && rootNode.length > 0){
			Node rn = rootNode[0];
			if(rn!=null){
				Node[] rnList = rn.getChildNodes();
				for (int i = 0; i < rnList.length; i++) {
					TreeNode child = (TreeNode) rnList[i];
					if(!child.getUI().isChecked()&& child.getUI().isChecked()){
						child.getUI().toggleCheck(Boolean.TRUE);
					}else{
						child.getUI().toggleCheck(Boolean.FALSE);
					}

				}
			}
		}

	}
//	public void invertNodeSelected(){
//		Node[] rootNode =  treePanel.getRootNode().getChildNodes();
//		if(rootNode!=null && rootNode.length > 0){
//			Node rn = rootNode[0];
//			if(rn!=null){
//				Node[] rnList = rn.getChildNodes();
//				for (int i = 0; i < rnList.length; i++) {
//					TreeNode child = (TreeNode) rnList[i];
//					if(!child.getUI().isChecked()){
//						child.getUI().toggleCheck(Boolean.TRUE);
//					}else{
//						child.getUI().toggleCheck(Boolean.FALSE);
//					}
//				}
//			}
//		}
//	}
}
