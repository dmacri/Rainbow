package it.icarcnr.rainbow.client.utility.cnc;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.UrlParam;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.HttpProxy;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.JsonReader;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.data.event.StoreListenerAdapter;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Hidden;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtextux.client.widgets.layout.RowLayout;


@SuppressWarnings("unchecked")
public class CheckCnc extends BasePanel{

	private FormPanel formPanel;

	static IUtility iUtility = (IUtility)GWT.create(IUtility.class);


	private TextField telephoneNumberTF;
	Button executeButton;
	private Store st = getStore();

	@Override
	public void init() {

		setLayout(new FitLayout());
		setFrame(true);

		setTitle();

		formPanel = makeFomPanel();
		add(formPanel);

	}


	private void setTitle() {
		String title = getPanelParent().getTitle();
		if(getPanelParent().getParameters()!=null){
			if(getPanelParent().getParameters().containsKey("title")){ //$NON-NLS-1$
				title = ((JSONString)getPanelParent().getParameters().get("title")).stringValue(); //$NON-NLS-1$
			}
		}
		if(title!=null){
			setTitle(title);
		}
	}

	private FormPanel makeFomPanel() {
		formPanel = new FormPanel();
		formPanel.setAutoScroll(true);
		formPanel.setLabelWidth(120);


		telephoneNumberTF = new TextField(iUtility.CheckCnc_Telephone_Number(), "telNumber", 100); //$NON-NLS-1$ //$NON-NLS-2$
		telephoneNumberTF.setAllowBlank(false);
		telephoneNumberTF.setMaxLength(10);
		telephoneNumberTF.setRegex("0[0-9]{5,10}"); //$NON-NLS-1$

		executeButton = new Button(iUtility.Start()); //$NON-NLS-1$
		executeButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				if(validateFields()){
					getEl().mask(iUtility.Loading(), "x-mask-loading"); //$NON-NLS-1$ //$NON-NLS-2$
					st.load(getParams());
				}

			}
		});

		MultiFieldPanel multiFieldPanel = new MultiFieldPanel();  
		multiFieldPanel.addToRow(telephoneNumberTF, 270);  
		multiFieldPanel.addToRow(executeButton, 50);

		final FieldSet networkFieldSet = new FieldSet(); //$NON-NLS-1$
		networkFieldSet.add(multiFieldPanel);
		formPanel.add(networkFieldSet, new AnchorLayoutData("100%"));
		doLayout(true);

		return formPanel;
	}

	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[1];
		params [0] = new UrlParam("telNumber", telephoneNumberTF.getValueAsString()); //$NON-NLS-1$
		return params;
	}


	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.CHECKCNC_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				// TODO Auto-generated method stub
				getEl().unmask();
				MessageBox.alert(iUtility.CheckCnc_Error(),iUtility.CheckCnc_System_busy_Please_try_later()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					final String telNumber = record.getAsString("telNumber"); //$NON-NLS-1$
					String namejob = record.getAsString("namejob"); //$NON-NLS-1$
					Integer processes = record.getAsInteger("processes"); //$NON-NLS-1$
					String file = record.getAsString("file"); //$NON-NLS-1$
					Integer cnc = record.getAsInteger("cnc");
					Integer upper_Bound = record.getAsInteger("upper_bound");
					String db  = record.getAsString("db");
					String error  = record.getAsString("error");
					Boolean hasError = record.getAsBoolean("hasError"); //$NON-NLS-1$
					Boolean reachedMaxNumberOfProcess = record.getAsBoolean("reachedMaxNumberOfProcess"); //$NON-NLS-1$
					final String outputExecution = record.getAsString("outputExecution"); //$NON-NLS-1$
					if (reachedMaxNumberOfProcess) {
						MessageBox.alert(iUtility.CheckCnc_Warning(),iUtility.CheckCnc_Maximum_number_of_processes_reached()+processes+iUtility.CheckCnc_Please_try_again_later()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					else if(outputExecution == null || outputExecution.equals("")){ //$NON-NLS-1$
						getPanelParent().refreshData();
						MessageBox.alert(iUtility.CheckCnc_Warning(),iUtility.Process_is_queued_but_not_have_output()); //$NON-NLS-1$ //$NON-NLS-2$
					}
					else{
						getPanelParent().refreshData();
						if(outputExecution.contains((iUtility.CheckCnc_No_Results()))){ //$NON-NLS-1$
							MessageBox.alert(iUtility.CheckCnc_Warning(),iUtility.CheckCnc_Telephone_number_not_Found()); //$NON-NLS-1$ //$NON-NLS-2$
						}else{

							final Window window = new Window();  
							window.setClosable(true);  
							window.setLayout(new FitLayout());
							window.setWidth(500);  
							window.setHeight(300);
							window.setPlain(true);  
							window.setMaximizable(true);  
							window.setResizable(true);  
							window.setIconCls("automatism-icon"); //(\public\images.css) //$NON-NLS-1$

							window.setModal(false);  
							window.setLayout(new FitLayout());
							window.setCloseAction(Window.CLOSE);
							window.setTitle(iUtility.CheckCnc_Execution_Job()); //$NON-NLS-1$


							/*		CheckCncPanel checkCncPanel = new CheckCncPanel();
							checkCncPanel.addParameter("outputExecution", new JSONString(outputExecution));

							checkCncPanel.setWindowParent(window);
							checkCncPanel.setPanelParent(CheckCnc.this);
							checkCncPanel.init();
							window.add(checkCncPanel);

							 */	


							Panel rowpanel = new Panel();
							rowpanel.setLayout(new RowLayout());
							Panel panel1 = new Panel();
							panel1.setLayout(new FitLayout());
							panel1.setHeight(15);
							panel1.setHtml(iUtility.CheckCnc_Output_execution()); //$NON-NLS-1$
							rowpanel.add(panel1);

							Panel panel2=new Panel();
							panel2.setFrame(true);
							panel2.setLayout(new FitLayout());
							panel2.setAutoScroll(true);
							if(!hasError){
								panel2.setHtml(iUtility.CheckCnc_Numbering_Info()+" "+telNumber+"<br>"+"<b>"+iUtility.CheckCnc_cnc()+"</b> : "+cnc+"<br><b>"+iUtility.CheckCnc_db()+"</b> : "+db);
							}else{
								panel2.setHtml(iUtility.CheckCnc_Numbering_Info()+" "+telNumber+"<br>"+"<b>"+iUtility.CheckCnc_Error()+"</b> : "+error);
							}

							rowpanel.add(panel2);

							final FormPanel fPanel = new FormPanel();
							fPanel.setFrame(true);

							fPanel.setLayout(new FormLayout());

							fPanel.setPaddings(15);
							fPanel.setBorder(false);
							fPanel.setHeight(100);
							//							fPanel.setLabelWidth(75);

							Hidden namejobHidden;
							namejobHidden = new Hidden("namejob",namejob); //$NON-NLS-1$
							Hidden telNumberHidden;
							telNumberHidden = new Hidden("telNumber",telNumber); //$NON-NLS-1$
							fPanel.add(namejobHidden);
							fPanel.add(telNumberHidden);
							
							if((!hasError) && (cnc > upper_Bound)){
								fPanel.setFrame(true);
								Label question = new Label();
								question.setHtml("<center><b>"+iUtility.CheckCnc_Do_you_want_to_delete_record_on_UDB_Sip_Server()+"</center></b><br>"); //$NON-NLS-1$
								fPanel.add(question);


								fPanel.setButtonAlign(Position.CENTER);
								Button yesButton = new Button (iUtility.CheckCnc_Yes()); //$NON-NLS-1$
								fPanel.addButton(yesButton);

								yesButton.addListener(new ButtonListenerAdapter() {
									public void onClick(final Button button, EventObject e) {
										fPanel.getForm().submit(IActionPathConstants.CHECKCNC_EXECUTION_ACTION_PATH, null, Connection.POST, null, false);
									}  

								});

								Button noButton = new Button (iUtility.CheckCnc_No()); //$NON-NLS-1$
								fPanel.addButton(noButton);

								noButton.addListener(new ButtonListenerAdapter() {
									public void onClick(Button button, EventObject e) {
										window.close();
									};
								});

							}
							else {
								fPanel.setFrame(true);
								fPanel.setButtonAlign(Position.CENTER);
								Button okButton = new Button (iUtility.CheckCnc_OK());
								fPanel.addButton(okButton);

								okButton.addListener(new ButtonListenerAdapter() {
									public void onClick(Button button, EventObject e) {
										window.close();
									};
								});
							}


							fPanel.addFormListener(new FormListenerAdapter() {

								public void onActionComplete(com.gwtext.client.widgets.form.Form form, int httpStatus, String responseText) {
									Store store = getSubmitResponseStore();
									store.loadJsonData(responseText, true);
									Record [] records = store.getRecords();
									if(records!=null && records.length>0){
										Record record = records[0];
										String id = record.getAsString("id"); //$NON-NLS-1$
										if (id.equals("ok")) { //$NON-NLS-1$
											getPanelParent().refreshData();
											MessageBox.alert(iUtility.CheckCnc_Confirm(),iUtility.CheckCnc_Changes_saved_successfully()); //$NON-NLS-1$ //$NON-NLS-2$
										}
										window.close();
									}
								};
							});

							rowpanel.add(fPanel);

							window.add(rowpanel);
							window.show();
						}
					}


				}

			}

		});

		return st;
	}

	private RecordDef getRecorDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new IntegerFieldDef("processes"), //$NON-NLS-1$
						new StringFieldDef("file"), //$NON-NLS-1$
						new StringFieldDef("outputExecution"), //$NON-NLS-1$
						new StringFieldDef("namejob"), //$NON-NLS-1$
						new StringFieldDef("telNumber"), //$NON-NLS-1$
						new BooleanFieldDef("reachedMaxNumberOfProcess"), //$NON-NLS-1$
						new IntegerFieldDef("upper_bound"), //$NON-NLS-1$
						new IntegerFieldDef("cnc"),
						new StringFieldDef("db"),
						new StringFieldDef("error"),
						new BooleanFieldDef("hasError")
				}  
		); 
		return recordDef;
	}

	private Store getSubmitResponseStore() {
		RecordDef errorRecordDef = new RecordDef(new FieldDef[]{  
				new StringFieldDef("id"),   //$NON-NLS-1$
				new StringFieldDef("msg")   //$NON-NLS-1$
		});
		JsonReader errorReader = new JsonReader("errors",errorRecordDef); //$NON-NLS-1$
		errorReader.setSuccessProperty("success"); //$NON-NLS-1$
		Store store = new Store(errorReader);
		return store;
	}

	private boolean validateFields() {
		boolean isValid = telephoneNumberTF.isValid();
		if(!isValid){
			MessageBox.alert(iUtility.CheckCnc_Warning(),iUtility.CheckCnc_Insert_valid_number()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return isValid;
	}


}
