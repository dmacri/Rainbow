package it.icarcnr.rainbow.client.utility.infocli;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONString;
import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtextux.client.widgets.layout.RowLayout;


public class InfoCliContactCenter extends BasePanel{
	private FormPanel formPanel;

	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);

	private TextField telephoneNumberTF;
	private Button executeButton;
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
			if(getPanelParent().getParameters().containsKey("title")){
				title = ((JSONString)getPanelParent().getParameters().get("title")).stringValue();
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


		telephoneNumberTF = new TextField(iUtility.Telephone_Number(), "telNumber", 100);
		telephoneNumberTF.setAllowBlank(false);
		telephoneNumberTF.setMaxLength(10);
		telephoneNumberTF.setRegex("0[0-9]{5,10}");

		executeButton = new Button(iUtility.CheckIpLock_Start());
		executeButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				if(validateFields()){
					getEl().mask(iUtility.Loading(), "x-mask-loading");
					st.load(getParams());
				}

			}
		});

		MultiFieldPanel multiFieldPanel = new MultiFieldPanel();  
		multiFieldPanel.addToRow(telephoneNumberTF, 270);  
		multiFieldPanel.addToRow(executeButton, 50);

		final FieldSet networkFieldSet = new FieldSet();

		networkFieldSet.add(multiFieldPanel);

		formPanel.add(networkFieldSet, new AnchorLayoutData("100%"));

		return formPanel;
	}

	protected UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[1];
		params [0] = new UrlParam("telNumber", telephoneNumberTF.getValueAsString());

		return params;
	}


	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.INFOCLI_CONTACT_CENTER_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root");
		readerLoad.setId("threadid");
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoadException(Throwable error) {
				// TODO Auto-generated method stub
				getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					final String telNumber = record.getAsString("telNumber");
					String namejob = record.getAsString("namejob");
					Integer processes = record.getAsInteger("processes");
					String file = record.getAsString("file");
					Boolean reachedMaxNumberOfProcess = record.getAsBoolean("reachedMaxNumberOfProcess");
					final String waitExecution = record.getAsString("waitExecution");
					if (reachedMaxNumberOfProcess) {
						MessageBox.alert("Warning",iUtility.Maximum_number_of_processes_reached()+": "+processes+". "+iUtility.Please_try_again_later()+".");
					}
					else if(waitExecution == null || waitExecution.equals("")){
						getPanelParent().refreshData();
						MessageBox.alert("Warning",iUtility.Process_is_queued_but_not_have_output());
					}
					else{
						getPanelParent().refreshData();
						if(waitExecution.contains(("No Results!"))){
							MessageBox.alert("Warning",iUtility.Telephone_number_not_Found());
						}else{

							final Window window = new Window();  
							window.setClosable(true);  
							window.setLayout(new FitLayout());
							window.setWidth(500);  
							window.setHeight(300);
							window.setPlain(true);  
							window.setMaximizable(true);  
							window.setResizable(true);  
							window.setIconCls("automatism-icon"); //(\public\images.css)

							window.setModal(false);  
							window.setLayout(new FitLayout());
							window.setCloseAction(Window.CLOSE);
							window.setTitle(iUtility.Execution_Job());

							Panel rowpanel = new Panel();
							rowpanel.setLayout(new RowLayout());
							Panel panel1 = new Panel();
							panel1.setLayout(new FitLayout());
							panel1.setHeight(15);
							panel1.setHtml("<b>"+iUtility.Output_execution()+":</b>");
							rowpanel.add(panel1);


							Panel panel2=new Panel();
							panel2.setFrame(true);
							panel2.setLayout(new FitLayout());
							panel2.setAutoScroll(true);
							panel2.setHtml(waitExecution);
							rowpanel.add(panel2);

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
						new IntegerFieldDef("processes"),
						new StringFieldDef("file"),
						new StringFieldDef("waitExecution"),
						new StringFieldDef("namejob"),
						new StringFieldDef("telNumber"),
						new BooleanFieldDef("reachedMaxNumberOfProcess")
				}  
		); 
		return recordDef;
	}

	private boolean validateFields() {
		boolean isValid = telephoneNumberTF.isValid();
		if(!isValid){
			MessageBox.alert("Warning",iUtility.Insert_valid_number()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return isValid;
	}
}
