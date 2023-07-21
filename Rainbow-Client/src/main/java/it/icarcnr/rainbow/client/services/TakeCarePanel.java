package it.icarcnr.rainbow.client.services;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.services.IServices;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Hidden;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class TakeCarePanel extends BasePanel {

	private String serviceId;
	private String serviceDescription;
	private String reference;
	private String nodeTo;
	private String source;

	private FormPanel formPanel;
	private Hidden serviceIdH;
	private TextArea commentTA;
	private Button saveButton;
	private Button cancelButton;
	static IServices iServices = (IServices)GWT.create(IServices.class);

	public TakeCarePanel(String serviceId, String serviceDescription, String source,String reference, String nodeTo) {
		this.serviceId = serviceId;
		this.serviceDescription = serviceDescription;
		this.reference = reference;
		this.nodeTo = nodeTo;
		this.source = source;
	}

	@Override
    public void init() {

		setLayout(new FitLayout());

		formPanel = new FormPanel();
		formPanel.setPaddings(15);
		formPanel.setBorder(false);
		formPanel.setLabelWidth(75);

		formPanel.setBaseParams(getUrlParams());



		Label serviceLabel = new Label();
		serviceLabel.setHtml("<span style='text-decoration: none;font-family: tahoma, Geneva, Arial, Helvetica, sans-serif;color: #0a4a6e;font-size: 11px;'><b>"+serviceDescription+" &nbsp " +source+" &nbsp "+ reference+"</b></span><br/><br/>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		formPanel.add(serviceLabel);

		serviceIdH = new Hidden("serviceId",serviceId); //$NON-NLS-1$
		formPanel.add(serviceIdH);

		commentTA = new TextArea(iServices.TakeCarePanel_Comment(), "comment"); //$NON-NLS-1$ //$NON-NLS-2$
		commentTA.setHeight(80);
		commentTA.setAllowBlank(false);
		//		commentTA.setValue("Servizio preso in carico");
		//		commentTA.setInvalidText("Insert comment");

		formPanel.add(commentTA, new AnchorLayoutData("95%")); //$NON-NLS-1$

		saveButton = new Button( iServices.TakeCarePanel_Save() ); //$NON-NLS-1$

		saveButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				if(commentTA.validate()){
					formPanel.getForm().submit(IActionPathConstants.TAKE_CARE_ACTION, null, Connection.POST, iServices.TakeCarePanel_Save_(), false); //$NON-NLS-1$
				}

			}
		});




		formPanel.addFormListener(new FormListenerAdapter(){
			@Override
			public void onActionComplete(Form form, int httpStatus,
					String responseText) {
				closeWindow();
			}
			@Override
			public void onActionFailed(Form form, int httpStatus,
					String responseText) {

				String title = iServices.TakeCarePanel_Warning(); //$NON-NLS-1$
				String msg = iServices.TakeCarePanel_Alarm_already_taken_in_care(); //$NON-NLS-1$
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

				//				RecordDef errorRecordDef = new RecordDef(new FieldDef[]{  
				//						new StringFieldDef("id"),  
				//						new StringFieldDef("msg")  
				//				});
				//				JsonReader errorReader = new JsonReader("errors",errorRecordDef);
				//				errorReader.setSuccessProperty("success");
				//				Store store = new Store(errorReader);
				//				store.loadJsonData(responseText, true);
				//				Record [] records = store.getRecords();
				//				for (int i = 0; i < records.length; i++) {
				//					Record r = records[i];
				//					String msg = r.getAsString("msg");
				//					//					MessageBox.alert(I18nUtil.getI18nMessage(msg));	
				//					MessageBox.alert("Warning", "Alarm already taken in care",  
				//							new MessageBox.AlertCallback() { 
				//						@Override
				//						public void execute() {
				//							closeWindow();
				//						}
				//					}); 
				//				}
			}
		});


		cancelButton = new Button(iServices.TakeCarePanel_Cancel());
		cancelButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				closeWindow();
			}
		});


		formPanel.addButton( saveButton );
		formPanel.addButton( cancelButton );

		add(formPanel);

	}

	protected void closeWindow() {
		Window window = getWindowParent();
		window.close();
	}

}
