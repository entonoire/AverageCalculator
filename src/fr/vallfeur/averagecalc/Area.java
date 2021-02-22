package fr.vallfeur.averagecalc;

import fr.vallfeur.averagecalc.css.Css;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class Area {

	private static TextArea backgroundArea = new TextArea();
	private static TextArea writeArea = new TextArea();
	
	public static Node backgroundSetup(){
		backgroundArea.getStylesheets().add(Css.getBackgroundArea());
		backgroundArea.setLayoutX((1280/3)-50);
		backgroundArea.setLayoutY(40);
		backgroundArea.setPrefHeight(55);
		backgroundArea.setEditable(false);
		
		return backgroundArea;
	}
	
	public static Node writeSetup(){
		writeArea.getStylesheets().add(Css.getWriteArea());
		writeArea.setLayoutX((1280/3)-50);
		writeArea.setLayoutY(40);
		writeArea.setPrefHeight(55);
		writeArea.setPrefWidth(640);
		
		return writeArea;
	}

	
	public static Node backgroundGet(){
		return backgroundArea;
	}
	
	public static TextArea writeGet(){
		return writeArea;
	}
}
