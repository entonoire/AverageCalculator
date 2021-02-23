package fr.vallfeur.averagecalc;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Optional;

import fr.vallfeur.averagecalc.css.Css;
import fr.vallfeur.averagecalc.resources.Colors;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OutPut {

	private static TextArea area = new TextArea();
	private static Text text = new Text("result: ");
	
	public static Node setupArea(){
		area.getStylesheets().add(Css.getOutPut());
		area.setEditable(false);
		area.setLayoutX(Area.backgroundGet().getLayoutX());
		area.setLayoutY(145);
		area.setPrefHeight(0);
		area.setPrefWidth(Area.writeGet().getPrefWidth());
		
		return area;
	}
	
	public static Node setupText(){
		text.setLayoutX(area.getLayoutX() + 10);
		text.setLayoutY(176);
		text.setStyle("-fx-font-weight: 900; -fx-font-size: 25;");
		text.setFill(Colors.toPaint(Colors.blueviolet));
		
		text.setOnMouseClicked(event -> {
			if(!text.getText().equalsIgnoreCase("result: ")){
				popup();
			}
		});
		
		return text;
	}
	
	
	public static void setText(String text){
		OutPut.text.setText("result: "+text);
	}

	
	public static void popup(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Did you wan't to copy the result?");
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Colors.class.getResourceAsStream("icon.png")));
		
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get().equals(ButtonType.OK)){	
			
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			String[] content = text.getText().split("result: ");
			StringSelection selection = new StringSelection(content[1]);
			
			clipboard.setContents(selection, selection);
		}
		
	}
}
