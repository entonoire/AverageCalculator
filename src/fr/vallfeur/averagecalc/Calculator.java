package fr.vallfeur.averagecalc;

import fr.vallfeur.averagecalc.file.LastFile;
import fr.vallfeur.averagecalc.file.Register;
import fr.vallfeur.averagecalc.menu.Bar;
import fr.vallfeur.averagecalc.resources.Colors;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calculator {

	private static double result;
	private static double num;
	
	public static void run(){
		
		if(Area.writeGet().getText().isEmpty()){
			errorPopup("EmptyText", "How can I calculate with zero number?");
			return;
		}
		
		if(!Area.writeGet().getText().contains(" ")){
			errorPopup("NumberExecption", "How can I calculate with one number?");
			return;
		}
		
		String[] numbers = Area.writeGet().getText().replace(",", ".").split(" ");

		for(String number : numbers){
			if(isParssable(number)){
				num = Double.parseDouble(number);
				
			}
			else{
				errorPopup("NumberFormatExecption", "How can I calculate with letters?");
				return;
			}
			
			result = result+num;
		}
		result = result/numbers.length;
		OutPut.setText(String.valueOf(result));
		if(Bar.savingLastResult()){
			LastFile.register(result, numbers);
		}
		if(Bar.savingResult()){
			Register.create(result, numbers);
		}
		result = 0;
	}
	
	
	public static boolean isParssable(String str){
		try{
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static void errorPopup(String title, String text){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(text);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Colors.class.getResourceAsStream("icon.png")));
		alert.showAndWait();
	}
}
