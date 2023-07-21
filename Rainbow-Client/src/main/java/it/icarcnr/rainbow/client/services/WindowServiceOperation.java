/**
 * 
 */
package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.constants.IOperationConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Hidden;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.event.CheckboxListenerAdapter;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * @author Faber
 *
 */
public class WindowServiceOperation extends BasePanel{

	private String serviceId;
	private String description;
	private String nodeFrom;
	private String sources;


	FieldSet fieldSuspendedThreshold = new FieldSet();

	private FormPanel formPanel;

	private Hidden serviceIdH;
	private Button saveButton;
	private Button cancelButton;
	static IServices iServices = (IServices)GWT.create(IServices.class);


	public WindowServiceOperation(String serviceId, String description,String nodeFrom, String sources) {
		this.serviceId = serviceId;
		this.description = description;
		this.nodeFrom = nodeFrom;
		this.sources = sources;
		
	}

	@Override
    public void init() {


		setLayout(new FitLayout());
		setFrame(true);

		formPanel = new FormPanel();
		formPanel.setPaddings(15);
		formPanel.setBorder(false);
		formPanel.setLabelWidth(75);


		Label serviceLabel = new Label();
		serviceLabel.setHtml("<span style='text-decoration: none;font-family: tahoma, Geneva, Arial, Helvetica, sans-serif;color: #0a4a6e;font-size: 11px;'><b>"+description +" - "+ nodeFrom+" - "+sources+"</b></span><br/><br/>"); //$NON-NLS-1$ 
		formPanel.add(serviceLabel);

		final Store store = getValueStore();
		//		store.setBaseParams(getDisableThresholdURLParams(serviceId));
		store.load(getDisableThresholdURLParams(serviceId));

		serviceIdH = new Hidden("serviceId",serviceId); //$NON-NLS-1$
		formPanel.add(serviceIdH);

		saveButton = new Button( iServices.TakeCarePanel_Save() );
		saveButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {

				formPanel.getForm().submit(IActionPathConstants.DISABLE_THRESHOLD_ACTION, null, Connection.POST, iServices.TakeCarePanel_Save_(), false); //$NON-NLS-1$

			}
		});
		saveButton.disable();

		cancelButton = new Button(iServices.TakeCarePanel_Cancel());
		cancelButton.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {
				closeWindow();

			}

		});

		formPanel.addFormListener(new FormListenerAdapter(){
			@Override
			public void onActionComplete(Form form, int httpStatus,
					String responseText) {
				String title=iServices.TakeCarePanel_Suspended_Threshold();
				String msg=iServices.TakeCarePanel_The_Operation_Execute_Will_Be_Active_At_The_Next_Sampling();
				MessageBox.alert(title, msg,  
						new MessageBox.AlertCallback() { 
					@Override
					public void execute() {
						closeWindow();
					}
				}); 
				closeWindow();
			}
			@Override
			public void onActionFailed(Form form, int httpStatus,
					String responseText) {

				String title = iServices.TakeCarePanel_Warning(); //$NON-NLS-1$
				String msg = iServices.TakeCarePanel_Threshold_Disabled_Can_Not_Suspend();//$NON-NLS-1$
				if(httpStatus== Response.SC_UNAUTHORIZED){
					title = iServices.TakeCarePanel_Error(); //$NON-NLS-1$
					msg = iServices.TakeCarePanel_HTTP_Status_401(); //$NON-NLS-1$
				}

				MessageBox.alert(title, msg,  
						new MessageBox.AlertCallback() { 
					@Override
					public void execute() {
						closeWindow();
					}
				}); 

			}
		});
		formPanel.addButton( saveButton );
		formPanel.addButton(cancelButton);
		add(formPanel);

	}

	protected void closeWindow() {
		Window window = getWindowParent();
		window.close();
	}

	private RecordDef getValuetRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{
						new StringFieldDef("idServiceOperation"),						
						new BooleanFieldDef("criteriaSuspended"),
						new BooleanFieldDef("criteriaSuspendedBySystem")
				}  
		); 
		return recordDef;
	}

	private Store getValueStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.SERVICE_OPERATION_LIST_ACTION);       
		RecordDef dataRecordDef = getValuetRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length>0){
					fieldSuspendedThreshold.setCheckboxToggle(false);
					fieldSuspendedThreshold.setAutoScroll(true);
					fieldSuspendedThreshold.setFrame(false);  
					fieldSuspendedThreshold.setTitle(iServices.TakeCarePanel_List_Suspended_Threshold());  
					//					fieldSuspendedThreshold.setCollapsed(false);

					for(int i=0;i<records.length;i++){
						Record record = records[i];
						Boolean criteriaSuspended = record.getAsBoolean("criteriaSuspended");
						String idOperation = record.getAsString("idServiceOperation");
						Boolean criteriaSuspendedBySystem = record.getAsBoolean("criteriaSuspendedBySystem");
						Checkbox cbSuspendedThreshold = null;
						String message="";

						if(criteriaSuspendedBySystem){
							message = iServices.TakeCarePanel_Service_Suspended_By_System();

						}
						if(idOperation.equals(IOperationConstants.ON_TIME.toString())){
							String labelCb=iServices.ServiceStatus_Value();
							cbSuspendedThreshold = new Checkbox(labelCb + " " +message,IOperationConstants.ON_TIME_NAME);
							cbSuspendedThreshold.setChecked(criteriaSuspended);
							cbSuspendedThreshold.addListener(new CheckboxListenerAdapter() {
								public void onCheck(Checkbox field, boolean checked) {
									saveButton.enable();
								};
							});
							fieldSuspendedThreshold.add(cbSuspendedThreshold);
						}else if(idOperation.equals(IOperationConstants.STEP_BY_STEP.toString())){
							String labelCb=iServices.ServiceStatus_Delta_Value();
							cbSuspendedThreshold = new Checkbox(labelCb,IOperationConstants.STEP_BY_STEP_NAME);
							cbSuspendedThreshold.setChecked(criteriaSuspended);
							cbSuspendedThreshold.addListener(new CheckboxListenerAdapter() {
								public void onCheck(Checkbox field, boolean checked) {
									saveButton.enable();
								};
							});
							fieldSuspendedThreshold.add(cbSuspendedThreshold);
						}
					}
					formPanel.add(fieldSuspendedThreshold);
					doLayout(true);
				}

			}
		});
		return st;
	}
	private UrlParam[] getDisableThresholdURLParams(String serviceId) {
		UrlParam[] urlParams = new UrlParam[1];
		urlParams[0] = new UrlParam("serviceId", serviceId);
		return urlParams;

	}

}
