package fr.vallfeur.averagecalc.menu;

import fr.vallfeur.averagecalc.Main;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Settings {

	public static void run(){
		System.out.println("?");
		//from https://stackoverflow.com/questions/22166610/how-to-create-a-popup-windows-in-javafx
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(Main.getStage().getOwner());
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
		
	}
	
}
