package fr.vallfeur.averagecalc;

import fr.vallfeur.averagecalc.css.Css;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Area {

	private static TextArea backgroundArea = new TextArea();
	private static TextField writeArea = new TextField();
	
	public static Node backgroundSetup(){
		backgroundArea.getStylesheets().add(Css.getBackgroundArea());
		backgroundArea.setLayoutX(50);
		backgroundArea.setLayoutY(55);
		backgroundArea.setPrefHeight(55);
		backgroundArea.setEditable(false);
		
		return backgroundArea;
	}
	
	public static Node writeSetup(){
		writeArea.getStylesheets().add(Css.getWriteArea());
		writeArea.setLayoutX(50);
		writeArea.setLayoutY(55);
		writeArea.setPrefHeight(55);
		writeArea.setPrefWidth(640);
		writeArea.setOnKeyPressed(event ->{
			if(event.getCode() == KeyCode.ENTER){
				Calculator.run();
			}
		});
		
		return writeArea;
	}

	
	public static Node backgroundGet(){
		return backgroundArea;
	}
	
	public static TextField writeGet(){
		return writeArea;
	}
}
