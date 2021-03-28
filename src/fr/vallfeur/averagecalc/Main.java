package fr.vallfeur.averagecalc;

import fr.vallfeur.averagecalc.file.Manager;
import fr.vallfeur.averagecalc.menu.Bar;
import fr.vallfeur.averagecalc.resources.Colors;
import fr.vallfeur.averagecalc.version.VersionMain;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 800, 220);
		
		VersionMain.setup();
		Manager.createBase();
		pane.setStyle("-fx-background-color: "+Colors.toHex(Colors.darkgray)+";");
		pane.getChildren().add(Area.backgroundSetup());
		pane.getChildren().add(Area.writeSetup());
		pane.getChildren().add(SendButton.setup());
		pane.getChildren().add(OutPut.setupArea());
		pane.getChildren().add(OutPut.setupText());
		pane.getChildren().add(Bar.setup());
//		pane.getChildren().add(versionText()); wait i need to do the code for the version with github api -_-
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();
		stage.getIcons().add(new Image(Colors.class.getResourceAsStream("icon.png")));
		stage.setTitle("Average Calculator");
		stage.setResizable(false);
		
		Main.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage(){
		return stage;
	}

	public static Node versionText(){
		Text text = new Text("1.0");
		
		return text;
	}
	
}
