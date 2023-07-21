package it.icarcnr.rainbow.client.utility.geoarea;

import it.icarcnr.rainbow.client.combobox.FilesComboBox;
import it.icarcnr.rainbow.client.util.IDateFormatUtil;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.constants.IActionPathConstants;
import it.icarcnr.rainbow.client.util.i18n.utility.IUtility;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.gwtext.client.widgets.event.PanelListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.TimeField;
import com.gwtext.client.widgets.form.ValidationException;
import com.gwtext.client.widgets.form.Validator;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtextux.client.widgets.layout.RowLayout;


@SuppressWarnings("unchecked")
public class GeoAlignPK0Panel extends BasePanel{
	private FormPanel formPanel;
	private static IUtility iUtility = (IUtility)GWT.create(IUtility.class);

	private DateField startDate;
	private TimeField startTimeField;
	private Store store; 
	private Store storeOnSelect = getStoreOnSelectFile();
	private Store storeLoadResultUdb = getStoreLoadResultUdb();

	private FilesComboBox filesCB;
	private String filesId;
	private String filesDescription;

	private static final String NO_FILES = "-2";
	private static final String GEO_ALIGN = "align";

	
	@Override
	public void init() {
		store=getStore();
		setTitle(iUtility.GeoArea_Panel1Title(), "");
		setIconCls("settings-icon");  
		formPanel = makeFomPanel();
		add(formPanel); 
		doLayout(true);
		
		addListener(new PanelListenerAdapter(){
			@Override
			public void onExpand(Panel panel) {
				formPanel.doLayout(true);
			}
		});

	}

	private FormPanel makeFomPanel() {
		formPanel = new FormPanel();
		formPanel.setAutoScroll(false);
		formPanel.setLabelWidth(85); 
//		formPanel.setPaddings(15);

		MultiFieldPanel startDatePanel = new MultiFieldPanel();  

		startDate = makeStartDateField();
		startTimeField = makeStartTimeField();
		
		
		
		Button executeButton = new Button(iUtility.GeoArea_Start()); //$NON-NLS-1$
		executeButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				if(validateFields()){
					store.load(getParams());
				}
			}
		});
		setValidators();
//		startDatePanel.addToRow(startDate, new ColumnLayoutData(.50));
//		startDatePanel.addToRow(startTimeField, new ColumnLayoutData(.30));
//		startDatePanel.addToRow(executeButton, new ColumnLayoutData(0.20)); 
		
		startDatePanel.addToRow(startDate, 220);
		startDatePanel.addToRow(startTimeField, 100);
		startDatePanel.addToRow(executeButton, 50); 
		startDatePanel.doLayout(true);
		
		final FieldSet startDateFieldSet = new FieldSet(); //$NON-NLS-1$
    	startDateFieldSet.setLayout(new FormLayout());
		startDateFieldSet.add(startDatePanel, new AnchorLayoutData("100%"));
		startDateFieldSet.setTitle(iUtility.GeoArea_MultiField_Date_Title());

		filesCB = makeFilesComboBox();

		Button cliUdbButton = new Button(iUtility.GeoArea_Open_Cli_Udb());
		cliUdbButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				getEl().mask(iUtility.Loading(), "x-mask-loading");
				storeLoadResultUdb.load();					
			}
		});

//		MultiFieldPanel viewOutputFilesPanel = new MultiFieldPanel();  
//		viewOutputFilesPanel.addToRow(filesCB, 280);  
//		viewOutputFilesPanel.addToRow(cliUdbButton, new ColumnLayoutData(1)); 
		final FieldSet viewOutputFilesFieldSet = new FieldSet();
		viewOutputFilesFieldSet.setLayout(new FormLayout());
		viewOutputFilesFieldSet.setTitle(iUtility.GeoArea_Output());
		viewOutputFilesFieldSet.add(filesCB, new AnchorLayoutData("62%"));
		viewOutputFilesFieldSet.add(cliUdbButton);

    	formPanel.add(startDateFieldSet, new AnchorLayoutData("100%"));
		formPanel.add(viewOutputFilesFieldSet, new AnchorLayoutData("100%"));
		return formPanel;
	}


	private DateField makeStartDateField() {
		DateField startDate = new DateField(iUtility.GeoArea_Start_Alignment(),IDateFormatUtil.dateFormat);//$NON-NLS-1$
		startDate.setName("dateStart"); //$NON-NLS-1$
		startDate.setAllowBlank(false);
		startDate.setFormat(IDateFormatUtil.dateFormat); //$NON-NLS-1$
		return startDate;
	}


	private TimeField makeStartTimeField() {
		TimeField startTimeField = new TimeField(iUtility.GeoArea_Start_Time(), "timeStart");   //$NON-NLS-1$ //$NON-NLS-2$
		startTimeField.setHideLabel(true);
		startTimeField.setAllowBlank(false);
		startTimeField.setForceSelection(Boolean.TRUE);
		startTimeField.setEditable(Boolean.FALSE);
		startTimeField.setWidth(65);
		startTimeField.setFormat("H:i"); //$NON-NLS-1$
		startTimeField.setMinValue("00:00"); //$NON-NLS-1$
		startTimeField.setMaxValue("23:00"); //$NON-NLS-1$
		startTimeField.setIncrement(60);
		return startTimeField;
	}

	private FilesComboBox makeFilesComboBox() {
		final FilesComboBox filesCB = new FilesComboBox(IActionPathConstants.GEOALIGNLISTOUTPUT_ACTION_PATH, getParamsCB(), FilesComboBox.LoadOption.SINGLE_SELECT, Boolean.TRUE);
		filesCB.setFieldLabel(iUtility.GeoArea_Misallignment_SSW());
		filesCB.setAllowBlank(false);
		filesCB.setWidth(-1);
		filesCB.setDisabled(Boolean.FALSE);

		filesCB.addListener(new ComboBoxListenerAdapter(){
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {
				onSelectFile(record);
			}
			@Override
			public void onExpand(ComboBox comboBox) {
				filesCB.reset();
				filesCB.refreshData(getParamsCB());
			}
		});

		return filesCB;
	}


	private void setValidators() {
		startDate.setValidator(new Validator() {
			public boolean validate(String value) throws ValidationException {
				String currentDateString=DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(new Date());
				Date currentDate=DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).parse(currentDateString);
				if (startDate.getValue().before(currentDate)) {
					startDate.setInvalidText(iUtility.GeoArea_Incorrect_Day_for_GeoArea_Alignment()); //$NON-NLS-1$
					return false;
				}
				startTimeField.validate();
				return true;
			}
		});

		startTimeField.setValidator(new Validator() {
			public boolean validate(String value) throws ValidationException {
				if (startDate.getValue() == null) {
					startDate.validate();
					return true;
				}
				else {
					String stTimeField=startTimeField.getValue();
					String stDateString=DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(startDate.getValue());
					String completeDate=stDateString+" "+stTimeField;
					Date buildDate = DateTimeFormat.getFormat(IDateFormatUtil.i18nDateTimeFormat).parse(completeDate);
					Date now = new Date();
					if (buildDate.before(now)) {
						startTimeField.setInvalidText(iUtility.GeoArea_Incorrect_set_of_Date_Time_for_GeoArea_Alignment()); //$NON-NLS-1$
						return false;
					}
					return true;
				}
			}
		});
	}


	public boolean validateFields() {
		if (startDate.isValid() && startTimeField.isValid()) {
			return true;
		}
		else {
			MessageBox.alert(iUtility.GeoAlignment_Warning(),iUtility.GeoAlignment_You_must_set_Date_Time_correctly_for_Geo_Alignment()); //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
	}


	private Store getStore() {
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.GEOAREAALIGNMENTPK0_ACTION_PATH); 
		RecordDef dataRecordDef = getRecorDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoadException(Throwable error) {
				getEl().unmask();
				MessageBox.alert("Error",iUtility.System_busy_Please_try_later());
			}
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					Integer processes = record.getAsInteger("processes"); //$NON-NLS-1$
					Boolean reachedMaxNumberOfProcess = record.getAsBoolean("reachedMaxNumberOfProcess"); //$NON-NLS-1$
					if (reachedMaxNumberOfProcess) {
						MessageBox.alert(iUtility.GeoAlignment_Warning(),iUtility.GeoAlignment_Maximum_number_of_processes_reached()+processes+iUtility.GeoAlignment_Please_try_again_later()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					else {
						getPanelParent().refreshData();
						MessageBox.alert(iUtility.GeoAlignment_Confirm(),iUtility.GeoAlignment_The_Process_is_queued_successfully()); //$NON-NLS-1$ //$NON-NLS-2$
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
						new StringFieldDef("namejob"), //$NON-NLS-1$
						new BooleanFieldDef("reachedMaxNumberOfProcess") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}


	private Store getStoreOnSelectFile() {
		final boolean storeLoadResultUdb = false;
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.GEOAREAONSELECTOUTPUTFILE_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDefOutputFile();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					String outputFile = record.getAsString("outputFile"); //$NON-NLS-1$
					openWindowOutputFile(outputFile,storeLoadResultUdb);
				}
			}
		});
		return st;
	}


	private RecordDef getRecorDefOutputFile(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new StringFieldDef("outputFile"), //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}


	private Store getStoreLoadResultUdb() {
		final boolean storeLoadResultUdb = true;
		HttpProxy dataProxyLoad = new HttpProxy(IActionPathConstants.GEOAREARESULTUDB_ACTION_PATH);       
		RecordDef dataRecordDef = getRecorDefOutputFile();		 
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(true);
		st.addStoreListener(new StoreListenerAdapter() {
			@Override
			public void onLoad(Store store, Record[] records) {
				getEl().unmask();
				if(records!=null && records.length > 0) {
					Record record = records[0];
					String outputFile = record.getAsString("outputFile"); //$NON-NLS-1$
					openWindowOutputFile(outputFile,storeLoadResultUdb);
				}
			}
		});
		return st;
	}


	private UrlParam[] getParams() {
		UrlParam[] params = new UrlParam[2];
		params[0] = new UrlParam("dateStart",DateTimeFormat.getFormat(IDateFormatUtil.i18nDateFormat).format(startDate.getValue())+" "+startDate.getValue());
		params[1] = new UrlParam("timeStart", startTimeField.getValue()); //$NON-NLS-1$
		return params;
	}


	private UrlParam[] getParamsOutputFile() {
		UrlParam[] params = new UrlParam[3];
		params[0] = new UrlParam("filesId",filesId);
		params[1] = new UrlParam("filesDescription",filesDescription);
		params[2] = new UrlParam("geo",GEO_ALIGN);
		return params;
	}

	private UrlParam[] getParamsCB() {
		UrlParam[] params = new UrlParam[2];
		params[0] = new UrlParam("net","PK0");
		params[1] = new UrlParam("geo","align");
		return params;
	}


	/**
	 * @param record
	 */
	private void onSelectFile(Record record) {
		filesId = record.getAsString("id");
		filesDescription = record.getAsString("description");
		if(!filesId.equals(NO_FILES)){
			getEl().mask(iUtility.Loading(), "x-mask-loading");
			storeOnSelect.load(getParamsOutputFile());
		}
	}



	private void openWindowOutputFile(String outputFile,boolean storeLoadResultUdb){

		if (storeLoadResultUdb){
			filesDescription = iUtility.GeoArea_FileDescription_ResultCliUdbPubblic();
		}
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
		window.setTitle(iUtility.GeoArea_Popoup_Windows_Title());

		Panel rowpanel = new Panel();
		rowpanel.setLayout(new RowLayout());
		Panel panel1 = new Panel();
		panel1.setLayout(new FitLayout());
		panel1.setHeight(15);

		panel1.setHtml("<b>"+iUtility.GeoArea_File_Name()+" : "+filesDescription+"</b>");
		rowpanel.add(panel1);

		Panel panel2=new Panel();
		panel2.setFrame(true);
		panel2.setLayout(new FitLayout());
		panel2.setAutoScroll(true);
		panel2.setHtml(outputFile);
		rowpanel.add(panel2);

		window.add(rowpanel);
		window.show();
	}


}
