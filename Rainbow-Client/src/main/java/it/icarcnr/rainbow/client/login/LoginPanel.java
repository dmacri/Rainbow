package it.icarcnr.rainbow.client.login;

import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.Connection;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListener;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FormListener;
import com.gwtext.client.widgets.form.event.TextFieldListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.TableLayout;

public class LoginPanel extends BasePanel {

	//	private HTML appLogo;
	private HTML welcomeText;

	private Button loginButton;
	private FormPanel formPanel;
	private TextField userNameTF;
	private TextField passwordTF;


//	@Override
    public void init() {

		setBorder(false);
		setBaseCls("grey-panel-body-login");

		Panel tablePanel = new Panel();
		tablePanel.setBaseCls("grey-panel-body-login");
		tablePanel.setAutoHeight(true);
		tablePanel.setAutoWidth(true);
		tablePanel.setBorder(false);
		tablePanel.setLayout(new TableLayout(3));

		//		Panel columnPanel = new Panel();
		//		columnPanel.setBorder(false);
		//		columnPanel.setLayout(new ColumnLayout());
		//		ColumnLayoutData layoutDataBorder = new ColumnLayoutData(.1);
		//		ColumnLayoutData layoutDataBody = new ColumnLayoutData(.8);
		Panel borderSx = new Panel();
		borderSx.setLayout(new FitLayout());
		borderSx.setBorder(false);
		borderSx.setWidth(10);
		borderSx.setHeight(100);
		borderSx.setBaseCls("");
	 //   borderSx.setBaseCls("border-login-sx");
	//  columnPanel.add(borderSx,layoutDataBorder);
		tablePanel.add(borderSx);

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		vPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vPanel.setBorderWidth(0);
		vPanel.setSize("480px", "100px");
		//		vPanel.setBaseCls("grey-panel-body-login");
		//		vPanel.setWidth(480);
		//		vPanel.setBorder(false);
		//vPanel.setStyleName("grey-panel-body-login");
		//		vPanel.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
		//		vPanel.setVerticalAlignment( VerticalPanel.ALIGN_MIDDLE );
		//		vPanel.setBorderWidth(0);
		Panel container = new Panel();
     //container.setBaseCls("");
		container.setBaseCls("grey-panel-body-login-login");
		//		container.setLayoutData(new FitLayout());
		container.setBorder(false);
		Panel loginPanel = loginForm();
		container.add( loginPanel );
		vPanel.add(container);
		//		columnPanel.add(vPanel,layoutDataBody);

		tablePanel.add(vPanel);

		Panel borderDx = new Panel();
		borderDx.setLayout(new FitLayout());
		borderDx.setBorder(false);
		borderDx.setWidth(10);
		borderDx.setHeight(100);
	 borderDx.setBaseCls("");
	//	borderDx.setBaseCls("border-login-dx");
		
		//		columnPanel.add(borderDx,layoutDataBorder);
		tablePanel.add(borderDx);

		add(tablePanel);
		
	}



	public Panel loginForm (){

		formPanel = new FormPanel();
		formPanel.setLayout(new FormLayout());
		formPanel.setBorder(false);
		formPanel.setWidth(260);
		formPanel.setBaseCls("grey-panel-body-login");
		formPanel.setLabelWidth(45);


		userNameTF = new TextField("Username","userName",180);
		userNameTF.setLabelStyle("text-align:right; font-weight:bold; width:auto; color:#000000;");
		userNameTF.setStyle("border: 1px solid #000000");
		userNameTF.setAllowBlank(false);
		userNameTF.setBlankText("Username Obbligatorio!");
		userNameTF.focus();

		passwordTF = new TextField("Password","pswd",180);
		passwordTF.setInputType("password");
		passwordTF.setLabelStyle("text-align:right; font-weight:bold; width:auto; color:#000000;");
		passwordTF.setStyle("border: 1px solid #000000");
		passwordTF.addListener(new TextFieldListenerAdapter(){
			@Override
			public void onSpecialKey(Field field, EventObject e) {
				if(e.getKey()==EventObject.ENTER){
					submit();		
				}
			}
		});

		formPanel.add(userNameTF);
		formPanel.add(passwordTF);

		loginButton = new Button( "Login" );
		formPanel.addButton( loginButton );

		formPanel.setId( "form1" );
		return formPanel;

	}

	public void addButtonlistener(ButtonListener listener) {
		if (loginButton != null){
			loginButton.addListener(listener);
		}
	}

	public void addFormListener(FormListener formHandler) {
		if (formPanel != null) {
			formPanel.addFormListener(formHandler);
		}
	}

	public void submit() {
		if (formPanel != null) {
			formPanel.getForm().submit("../login/login.do", null, Connection.POST, "Login...", false);
		}
	}

	public void resetPassword(){
		passwordTF.reset();
	}

}
