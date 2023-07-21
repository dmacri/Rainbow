package it.icarcnr.rainbow.client.dashboard;

import it.icarcnr.rainbow.client.renderer.NetworkServiceStatusSummaryRenderer;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IParameterHttpServletRequestContants;
import it.icarcnr.rainbow.client.util.constants.IServiceConstants;
import it.icarcnr.rainbow.client.util.i18n.dashboard.IPortal;
import it.icarcnr.rainbow.client.util.pagebus.GraphAreaMessage;
import it.icarcnr.rainbow.client.util.pagebus.ISubjectToPublishTheMessageTo;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.pagebus.PageBus;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GridView;
import com.gwtext.client.widgets.grid.Renderer;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.portal.PortalColumn;
import com.gwtext.client.widgets.portal.Portlet;

public class NetworkServicesStatus extends BasePanel {

	private Store summaryServiceStatusStore;

	private Integer networkId;
	private String title;
	private GridPanel grid;

	private static IPortal iPortal = (IPortal)GWT.create(IPortal.class);
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	public PortalColumn getGridPortlet(String title, Integer idNetwork){

		summaryServiceStatusStore = getSummaryServiceStatusStore();
		networkId=idNetwork;
		PortalColumn serviceStatus = new PortalColumn();
		//		serviceStatus.setPaddings(10, 10, 0, 10);

		Portlet gridPortlet = new Portlet();
		gridPortlet.setFrame(true);
		this.title = title;
		gridPortlet.setTitle(this.title);
		gridPortlet.setLayout(new RowLayout());
		gridPortlet.setAutoHeight(true);

		grid= new GridPanel(); 
		GridView view = new GridView();

		view.setAutoFill(true);
		view.setForceFit(true);

		grid.setView(view);
		grid.setStore(summaryServiceStatusStore);

		ColumnModel columnModel = makeColumnModel();

		grid.setColumnModel(columnModel);
		grid.setStripeRows(true);  
		grid.setAutoHeight(true);
		grid.setFrame(true);
		grid.addListener(new PanelListenerAdapter() {
			public void onRender(Component component) {				
				grid.getStore().load(getParams());	
				super.onRender(component);
			}
		});

		grid.addGridCellListener(new GridCellListenerAdapter(){
			@Override
			public void onCellClick(GridPanel grid, int rowIndex, int colIndex,
					EventObject e) {
				if(grid.getStore().getRecordAt(rowIndex).getAsBoolean("viewGraphPermission")){
					sendOpenGraphMessage();
				}
			}
		});

		gridPortlet.add(grid);  
		serviceStatus.add(gridPortlet);  

		return serviceStatus;
	}
	
	private void sendOpenGraphMessage() {
		GraphAreaMessage graphAreaMessage = new GraphAreaMessage();
		graphAreaMessage.setNetworkId(networkId);
		graphAreaMessage.setNodeUniqueId(null);
		PageBus.publish(ISubjectToPublishTheMessageTo.GRAPH_AREA, graphAreaMessage);
	}



	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[1];
		params [0] = new UrlParam(IParameterHttpServletRequestContants.NODE_SENSOR_ID, networkId);
		return params;
	}

	private ColumnModel makeColumnModel(){  

		Renderer renderer = new NetworkServiceStatusSummaryRenderer();

		ColumnConfig suspended = new ColumnConfig("<center>"+iPortal.SummaryServiceStatus_Suspended()+"</center>", IServiceConstants.SUSPENDED_STATUS, 70, false); //$NON-NLS-1$ //$NON-NLS-2$
		suspended.setRenderer(renderer);

		ColumnConfig normal = new ColumnConfig("<center>"+iPortal.SummaryServiceStatus_Normal()+"</center>", IServiceConstants.NORMAL_STATUS, 70, false); //$NON-NLS-1$ //$NON-NLS-2$
		normal.setRenderer(renderer);

		ColumnConfig critical = new ColumnConfig("<center>"+iPortal.SummaryServiceStatus_Critical()+"</center>", IServiceConstants.CRITICAL_STATUS,70); //$NON-NLS-1$ //$NON-NLS-2$
		critical.setRenderer(renderer);


		ColumnConfig major = new ColumnConfig("<center>"+iPortal.SummaryServiceStatus_Major()+"</center>", IServiceConstants.MAJOR_STATUS,70); //$NON-NLS-1$ //$NON-NLS-2$
		major.setRenderer(renderer);


		ColumnModel columnModel  = new ColumnModel(new ColumnConfig[]{
				critical,
				major,
				normal,
				suspended
		});
		return columnModel;
	}	  


	private RecordDef getSummaryServiceStatusRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new IntegerFieldDef(IServiceConstants.SUSPENDED_STATUS), 
						new IntegerFieldDef(IServiceConstants.NORMAL_STATUS), 
						new IntegerFieldDef(IServiceConstants.MAJOR_STATUS), 
						new IntegerFieldDef(IServiceConstants.CRITICAL_STATUS), 
						new BooleanFieldDef("viewGraphPermission") 
				}  
		); 
		return recordDef;
	}

	private Store getSummaryServiceStatusStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.PORTAL_SUMMARY_SERVICE_STATUS_ACTION_PATH);       
		RecordDef dataRecordDef = getSummaryServiceStatusRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); 
		readerLoad.setTotalProperty("totalCount"); 
		readerLoad.setId("threadid"); 
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);

		return st;
	}
	@Override
	public void refreshData() {
		summaryServiceStatusStore.load(getParams());
	}


}
