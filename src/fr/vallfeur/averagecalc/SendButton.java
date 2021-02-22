package fr.vallfeur.averagecalc;

import fr.vallfeur.averagecalc.css.Css;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SendButton {

	private static Button button = new Button("=");
	
	public static Node setup(){
		button.getStylesheets().add(Css.getSendButton());
		button.setLayoutX(Area.backgroundGet().getLayoutX()+645);
		button.setLayoutY(Area.backgroundGet().getLayoutY());
		
		button.setOnMouseClicked(event -> {
			Calculator.run();
		});
		return button;
	}
	
}
