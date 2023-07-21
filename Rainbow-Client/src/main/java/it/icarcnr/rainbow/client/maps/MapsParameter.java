package it.icarcnr.rainbow.client.maps;

import java.util.HashMap;
import java.util.List;



import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.MultiFieldPanel;
import com.gwtext.client.widgets.form.event.FormPanelListenerAdapter;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.Radio;
import com.gwtext.client.widgets.form.event.CheckboxListenerAdapter;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import it.icarcnr.rainbow.client.util.basecomponents.BasePanel;
import it.icarcnr.rainbow.client.util.i18n.maps.IMaps;

public class MapsParameter extends BasePanel{
	
	private FormPanel formPanel;
	private Radio choiceTemperatureMaps;
	private Radio choiceHumidityMaps;
	private Radio choiceBrightnessMaps;
	private Radio choiceNoiseMaps;
	private Radio choiceBatteryMaps;
	private Radio choicePressionAtmoshericMaps;
	private Radio choiceConcentrationCO;
	private Radio choiceConcentrationCOTwo;
	private Radio choiceConcentrationNOTwo;
	private Radio choiceConcentrationOThree;
	private Radio choiceIndexUmidity;
	private Radio choiceIndexSummer;
	private String choiseRadio;
	private String holdSchoice;
	private Label watch;
	 DateTimeFormat dateFormat = DateTimeFormat.getFormat(
		     DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
	
	static IMaps iMaps = (IMaps)GWT.create(IMaps.class);
	@Override
	public void init() {
		setWidth(300);
		setFrame(true);
		setTitle(iMaps.MapsParameter_Title()); //
		setCollapsible(true);
		setLayout(new FitLayout());
		formPanel = makeFomPanel();
		formPanel.setAutoScroll(true);
		formPanel.addListener(new FormPanelListenerAdapter(){
			@Override
			public boolean doBeforeShow(Component component) {
				formPanel.doLayout(true);
				return super.doBeforeShow(component);
			}

		});
		add(formPanel);
		doLayout(true);
		
	}
	private FormPanel makeFomPanel() {

		formPanel = new FormPanel();
		formPanel.setLabelWidth(75);
		watch=new Label();
	     watch.setText("Last Update: "+dateFormat.format(new Date()));
		
		Panel choiceTypeSensor = makeChoiceTypeSensorPanel();
		formPanel.add(watch);
		formPanel.add(choiceTypeSensor, new AnchorLayoutData("94%")); //$NON-NLS-1$
	
		return formPanel;

	}
	
	private MultiFieldPanel makeChoiceTypeSensorPanel(){;
		MultiFieldPanel choiceTypeSensorPanel = new MultiFieldPanel();
		choiceTypeSensorPanel.setPaddings(10, 0, 0, 0);
		Label labelChart =new Label();
		labelChart.setHtml("Seleziona per :");
		labelChart.setHtml("");
		labelChart.setHtml("");
		labelChart.setHtml("</br>");
		choiceTypeSensorPanel.setLayout(new com.gwtext.client.widgets.layout.VerticalLayout());
		choiceTypeSensorPanel.setHeader(true);
		
		choiceTypeSensorPanel.addToRow(labelChart, 90);

		/** RADIO SERVICE TEMPERATURE */
		choiceTemperatureMaps = new Radio(iMaps.MapsParameter_Temperatura());
		choiceTemperatureMaps.enable();
		choiceTemperatureMaps.setName("choiceTemperatura");
		choiceTemperatureMaps.setHideLabel(true);
		choiceTemperatureMaps.setChecked(false);
		choiceTemperatureMaps.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					
					drawMaps(iMaps.MapsParameter_Temperatura());
					setHoldSchoice(iMaps.MapsParameter_Temperatura());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Temperatura());
				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceTemperatureMaps, 100);
		/** RADIO NODE HUMIDITY*/
		choiceHumidityMaps = new Radio(iMaps.MapsParameter_Umidita());
		choiceHumidityMaps.enable();
		choiceHumidityMaps.setHideLabel(true);
		choiceHumidityMaps.setName("choiceHumidity");
	
		choiceHumidityMaps.setChecked(false);
		choiceHumidityMaps.addListener(new CheckboxListenerAdapter (){
	
			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Umidita());
					setHoldSchoice(iMaps.MapsParameter_Umidita());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Umidita());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceHumidityMaps, 	new ColumnLayoutData(1));

		/** RADIO NODE brightness */
		choiceBrightnessMaps = new Radio(iMaps.MapsParameter_Luminosita());
		choiceBrightnessMaps.enable();
		choiceBrightnessMaps.setHideLabel(true);
		choiceBrightnessMaps.setName("choiceBrightness");

		choiceBrightnessMaps.setChecked(false);
		choiceBrightnessMaps.addListener(new CheckboxListenerAdapter() {
			/** RADIO NODE BRIGHTNESS */
			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if (checked) {
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Luminosita());
					setHoldSchoice(iMaps.MapsParameter_Luminosita());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Luminosita());
				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceBrightnessMaps,new ColumnLayoutData(2));
		
		/** RADIO NODE BATTERY */
		choiceBatteryMaps = new Radio(iMaps.MapsParameter_Batteria());
		choiceBatteryMaps.enable();
		choiceBatteryMaps.setHideLabel(true);
		choiceBatteryMaps.setName("choiceBattery");
		choiceBatteryMaps.setChecked(false);
		choiceBatteryMaps.addListener(new CheckboxListenerAdapter() {

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if (checked) {
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Batteria());
					setHoldSchoice(iMaps.MapsParameter_Batteria());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Batteria());
					
				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceBatteryMaps,new ColumnLayoutData(3));
		
		/** RADIO NODE NOISE */
		choiceNoiseMaps = new Radio(iMaps.MapsParameter_Rumore());
		choiceNoiseMaps.enable();
		choiceNoiseMaps.setHideLabel(true);
		choiceNoiseMaps.setName("choiceNoise");

		choiceNoiseMaps.setChecked(false);
		choiceNoiseMaps.addListener(new CheckboxListenerAdapter() {

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if (checked) {
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Rumore());
					setHoldSchoice(iMaps.MapsParameter_Rumore());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Rumore());
				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceNoiseMaps,new ColumnLayoutData(4));
	
		/** RADIO NODE PRESSION ATMOSHERIC */
		choicePressionAtmoshericMaps = new Radio(iMaps.MapsParameter_Pressione_Atmosferica());
		choicePressionAtmoshericMaps.enable();
		choicePressionAtmoshericMaps.setName("choicePressionAtmospheric");
		choicePressionAtmoshericMaps.setHideLabel(true);
		choicePressionAtmoshericMaps.setChecked(false);
		choicePressionAtmoshericMaps.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Pressione_Atmosferica());
					setHoldSchoice(iMaps.MapsParameter_Pressione_Atmosferica());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Pressione_Atmosferica());
				}
			}

		});

		choiceTypeSensorPanel.addToRow(choicePressionAtmoshericMaps, new ColumnLayoutData(5));
		
		

		/** RADIO NODE CONCENTRATION CO */
		choiceConcentrationCO = new Radio(iMaps.MapsParameter_Concentrazione_CO());
		choiceConcentrationCO.enable();
		choiceConcentrationCO.setName("choiceCO");
		choiceConcentrationCO.setHideLabel(true);
		choiceConcentrationCO.setChecked(false);
		choiceConcentrationCO.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Concentrazione_CO());
					setHoldSchoice(iMaps.MapsParameter_Concentrazione_CO());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Concentrazione_CO());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceConcentrationCO, new ColumnLayoutData(6));
		
		/** RADIO NODE CONCENTRATION CO 2 */
		choiceConcentrationCOTwo = new Radio(iMaps.MapsParameter_Concentrazione_CO_Due());
		choiceConcentrationCOTwo.enable();
		choiceConcentrationCOTwo.setName("choiceCoTwo");
		choiceConcentrationCOTwo.setHideLabel(true);
		choiceConcentrationCOTwo.setChecked(false);
		choiceConcentrationCOTwo.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Concentrazione_CO_Due());
					setHoldSchoice(iMaps.MapsParameter_Concentrazione_CO_Due());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Concentrazione_CO_Due());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceConcentrationCOTwo, new ColumnLayoutData(6));
		
		
		/** RADIO NODE CONCENTRATION NO 2 */
		choiceConcentrationNOTwo = new Radio(iMaps.MapsParameter_Concentrazione_NO_Due());
		choiceConcentrationNOTwo.enable();
		choiceConcentrationNOTwo.setName("choiceTemperatura");
		choiceConcentrationNOTwo.setHideLabel(true);
		choiceConcentrationNOTwo.setChecked(false);
		choiceConcentrationNOTwo.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					drawMaps(iMaps.MapsParameter_Concentrazione_NO_Due());
					setHoldSchoice(iMaps.MapsParameter_Concentrazione_NO_Due());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Concentrazione_NO_Due());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceConcentrationNOTwo, new ColumnLayoutData(7));
		
		
		/** RADIO NODE CONCENTRATION O 3 */
		choiceConcentrationOThree = new Radio(iMaps.MapsParameter_Concentrazione_O_Tre());
		choiceConcentrationOThree.enable();
		choiceConcentrationOThree.setName("choiceO3");
		choiceConcentrationOThree.setHideLabel(true);
		choiceConcentrationOThree.setChecked(false);
		choiceConcentrationOThree.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Concentrazione_O_Tre());
					setHoldSchoice(iMaps.MapsParameter_Concentrazione_O_Tre());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Concentrazione_O_Tre());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceConcentrationOThree, new ColumnLayoutData(8));
			
		/** RADIO NODE HUMIDEX INDEX */
		choiceIndexUmidity = new Radio(iMaps.MapsParameter_Index_Humidity());
		choiceIndexUmidity.enable();
		choiceIndexUmidity.setName("choiceTemperatura");
		choiceIndexUmidity.setHideLabel(true);
		choiceIndexUmidity.setChecked(false);
		choiceIndexUmidity.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexSummer.setChecked(false);
					drawMaps(iMaps.MapsParameter_Index_Humidity());
					setHoldSchoice(iMaps.MapsParameter_Index_Humidity());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Index_Humidity());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceIndexUmidity, new ColumnLayoutData(9));
	
		/** RADIO NODE SSI INDEX */
		choiceIndexSummer = new Radio(iMaps.MapsParameter_Index_Summer());
		choiceIndexSummer.enable();
		choiceIndexSummer.setName("choiceSummer");
		choiceIndexSummer.setHideLabel(true);
		choiceIndexSummer.setChecked(false);
		choiceIndexSummer.addListener(new CheckboxListenerAdapter(){

			@Override
			public void onCheck(Checkbox field, boolean checked) {
				if(checked){
					choiceTemperatureMaps.setChecked(false);
					choiceHumidityMaps.setChecked(false);
					choiceBrightnessMaps.setChecked(false);
					choiceBatteryMaps.setChecked(false);
					choiceNoiseMaps.setChecked(false);
					choicePressionAtmoshericMaps.setChecked(false);
					choiceConcentrationCO.setChecked(false);
					choiceConcentrationCOTwo.setChecked(false);
					choiceConcentrationNOTwo.setChecked(false);
					choiceConcentrationOThree.setChecked(false);
					choiceIndexUmidity.setChecked(false);
					drawMaps(iMaps.MapsParameter_Index_Summer());
					setHoldSchoice(iMaps.MapsParameter_Index_Summer());
					setTitle(iMaps.MapsParameter_Title()+iMaps.MapsParameter_Index_Summer());

				}
			}

		});

		choiceTypeSensorPanel.addToRow(choiceIndexSummer, new ColumnLayoutData(10));
		
		
		
		
		
		return choiceTypeSensorPanel;
	}
	public String getChoiseRadio() {
		return choiseRadio;
	}
	public void setChoiseRadio(String choiseRadio) {
		this.choiseRadio = choiseRadio;
	}
	
	public void drawMaps(String choose) {
		MapsContainer panelParent = (MapsContainer)getPanelParent();
		panelParent.drawMaps(choose);
		}
	public String getHoldSchoice() {
		return holdSchoice;
	}
	public void setHoldSchoice(String holdSchoice) {
		this.holdSchoice = holdSchoice;
	}
	
	 public void refreshWatch(){
		
	     watch.setText("Last Update: "+dateFormat.format(new Date()));
}
			
	}
	
	



