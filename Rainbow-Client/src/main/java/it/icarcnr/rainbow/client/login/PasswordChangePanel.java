package it.icarcnr.rainbow.client.login;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.i18n.login.ILogin;

import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.Form;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FormListenerAdapter;
import com.gwtext.client.widgets.form.event.TextFieldListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class PasswordChangePanel extends BasePanel {
	
	private TextField usernameTF;
	private TextField nameTF;
	private TextField surnameTF;
	private TextField oldPasswordTF;
	private TextField newPasswordTF;
	private TextField confirmNewPasswordTF;
	
	static ILogin iLogin = (ILogin)GWT.create(ILogin.class);
	
	private Button okButton;
	
	private FormPanel formPanel;
	
	private Store userInfoStore;
	
	private static final String LOAD_USER_INFO_ACTION_PATH = "../login/userLoginInfo.do"; //$NON-NLS-1$
	private static final String CHANGE_PASSWORD_ACTION_PATH = "../login/changePassword.do"; //$NON-NLS-1$

	
	@Override
    public void init() {

		setLayout(new FitLayout());
		setFrame(Boolean.TRUE);
		setPaddings(20);
		formPanel = new FormPanel();
		
		formPanel.setLabelWidth(160);


		nameTF = new TextField( iLogin.PasswordChangePanel_Name(), "name"); //$NON-NLS-1$ //$NON-NLS-2$
		nameTF.setReadOnly(true);
		nameTF.setDisabled(true);
		
		surnameTF = new TextField( iLogin.PasswordChangePanel_Surname(), "surname"); //$NON-NLS-1$ //$NON-NLS-2$
		surnameTF.setReadOnly(true);
		surnameTF.setDisabled(true);
		
		usernameTF = new TextField( iLogin.PasswordChangePanel_Username(), "username"); //$NON-NLS-1$ //$NON-NLS-2$
		usernameTF.setReadOnly(true);
		usernameTF.setDisabled(true);

		
		oldPasswordTF = new TextField( iLogin.PasswordChangePanel_Old_Password(), "oldPassword"); //$NON-NLS-1$ //$NON-NLS-2$
		oldPasswordTF.setInputType( "password" ); //$NON-NLS-1$
		oldPasswordTF.setAllowBlank( false );
		oldPasswordTF.focus();
		
		oldPasswordTF.addListener(new TextFieldListenerAdapter(){
			@Override
			public void onSpecialKey(Field field, EventObject e) {
				if(e.getKey()==EventObject.ENTER){
					submit();		
				}
			}
		});
		
		newPasswordTF = new TextField( iLogin.PasswordChangePanel_New_Password(), "newPassword"); //$NON-NLS-1$ //$NON-NLS-2$
		newPasswordTF.setInputType( "password" ); //$NON-NLS-1$
		newPasswordTF.setAllowBlank( false );
		
		newPasswordTF.addListener(new TextFieldListenerAdapter(){
			@Override
			public void onSpecialKey(Field field, EventObject e) {
				if(e.getKey()==EventObject.ENTER){
					submit();		
				}
			}
		});
		
		confirmNewPasswordTF = new TextField( iLogin.PasswordChangePanel_Confirm_New_Password(), "confirmNewPassword"); //$NON-NLS-1$ //$NON-NLS-2$
		confirmNewPasswordTF.setInputType( "password" ); //$NON-NLS-1$
		confirmNewPasswordTF.setAllowBlank( false );
		
		confirmNewPasswordTF.addListener(new TextFieldListenerAdapter(){
			@Override
			public void onSpecialKey(Field field, EventObject e) {
				if(e.getKey()==EventObject.ENTER){
					submit();		
				}
			}
		});
		
		okButton = new Button( iLogin.PasswordChangePanel_Save() ); //$NON-NLS-1$
		
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				submit();
			}
		});
		
		formPanel.add( nameTF, new AnchorLayoutData("95%"));  //$NON-NLS-1$
		formPanel.add( surnameTF, new AnchorLayoutData("95%"));  //$NON-NLS-1$
		formPanel.add( usernameTF, new AnchorLayoutData("95%"));  //$NON-NLS-1$
		formPanel.add( oldPasswordTF, new AnchorLayoutData("95%"));  //$NON-NLS-1$
		formPanel.add( newPasswordTF, new AnchorLayoutData("95%"));  //$NON-NLS-1$
		formPanel.add( confirmNewPasswordTF, new AnchorLayoutData("95%")); //$NON-NLS-1$
		formPanel.addButton( okButton );
		
		formPanel.addFormListener(new FormListenerAdapter(){

			public void onActionComplete(Form form, int httpStatus,
					String responseText) {
				MessageBox.alert(iLogin.PasswordChangePanel_Info(),iLogin.PasswordChangePanel_Password_changed_successfully()); //$NON-NLS-1$ //$NON-NLS-2$
				closeWindow();				
			}

			public void onActionFailed(Form form, int httpStatus,
					String responseText) {				
				RecordDef errorRecordDef = new RecordDef(new FieldDef[]{  
						new StringFieldDef("id"),   //$NON-NLS-1$
						new StringFieldDef("msg")   //$NON-NLS-1$
				});
				JsonReader errorReader = new JsonReader("errors",errorRecordDef); //$NON-NLS-1$
				errorReader.setSuccessProperty("success"); //$NON-NLS-1$
				Store store = new Store(errorReader);
				store.loadJsonData(responseText, true);
				Record [] records = store.getRecords();
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < records.length; i++) {
					Record r = records[i];
					if(i>0){
						message.append(r.getAsString("\n")); //$NON-NLS-1$
					}
					message.append(r.getAsString("msg")); //$NON-NLS-1$
				}
				MessageBox.alert(iLogin.PasswordChangePanel_Warning(),message.toString()); //$NON-NLS-1$
			}
		});
		
		loadUserInfo();
		
		add(formPanel);
	}

	
	protected void submit() {
		if(validateFields()){
			formPanel.getForm().submit(CHANGE_PASSWORD_ACTION_PATH, null, Connection.POST, iLogin.PasswordChangePanel_Change_password(), false);	 //$NON-NLS-1$
		}		
	}

	private void loadUserInfo() {
		userInfoStore = getUserInfoStore();
		userInfoStore.load();
	}
	
	private Store getUserInfoStore() {
		HttpProxy dataProxyLoad = new HttpProxy(LOAD_USER_INFO_ACTION_PATH);       
		RecordDef dataRecordDef = getUserInfoRecordDef();		  
		JsonReader readerLoad = new JsonReader(dataRecordDef);  
		readerLoad.setRoot("root"); //$NON-NLS-1$
		readerLoad.setTotalProperty("totalCount"); //$NON-NLS-1$
		readerLoad.setId("threadid"); //$NON-NLS-1$
		Store st = new Store(dataProxyLoad,readerLoad);
		st.setRemoteSort(false);
		st.addStoreListener(new StoreListenerAdapter(){
			@Override
			public void onLoad(Store store, Record[] records) {
				if(records!=null && records.length >0){
					Record record = records[0];
					String name = record.getAsString("name"); //$NON-NLS-1$
					String surname = record.getAsString("surname"); //$NON-NLS-1$
					String username = record.getAsString("username"); //$NON-NLS-1$
					nameTF.setValue(name);
					surnameTF.setValue(surname);
					usernameTF.setValue(username);
				}
			}
		});
		return st;
	}

	private RecordDef getUserInfoRecordDef(){	
		RecordDef recordDef = new RecordDef(  
				new FieldDef[]{  
						new StringFieldDef("name"), //$NON-NLS-1$
						new StringFieldDef("surname"), //$NON-NLS-1$
						new StringFieldDef("username") //$NON-NLS-1$
				}  
		); 
		return recordDef;
	}
	
	private boolean validateFields() {
		boolean isValid = oldPasswordTF.isValid()&& newPasswordTF.isValid() && confirmNewPasswordTF.isValid();
		return isValid;
	}
	
	protected void closeWindow() {
		Window window = getWindowParent();
		window.close();
	}

}
