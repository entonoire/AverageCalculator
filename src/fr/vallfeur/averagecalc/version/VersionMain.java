package fr.vallfeur.averagecalc.version;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

import fr.vallfeur.averagecalc.resources.Colors;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VersionMain {

	static Double version = 0.1;
	static Double content;
	static int i = 0;
	static String versionName;
	
	public static void setup(){
		try{
			
			//from https://github.com/goxr3plus/Java-GitHub-API/blob/master/URLReader.java
			HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/entonoire/AverageCalculator/releases").openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			
			StringBuilder responseSB = new StringBuilder();
			String line;
			while ( ( line = in.readLine() ) != null) {
				responseSB.append("\n" + line);
			}
			in.close();
			Arrays.stream(responseSB.toString().split("\"tag_name\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> {
				if(i == 0){
					content = Double.parseDouble(l.replace('"', ' '));
				}
				i++;
			});
			i=0;
			Arrays.stream(responseSB.toString().split("\"name\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> {
				if(i == 0){
					versionName = l.replace('"', " ".charAt(0)).trim();
				}
				i++;
			});
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return;
		}catch(IOException e){
			e.printStackTrace();
		}
		if(version < content){			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Update: "+versionName);
			alert.setHeaderText("A new version is here, did you wan't to download it?\nYour version: "+version+"\nLast version: "+content);
			((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Colors.class.getResourceAsStream("icon.png")));
			
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent() && result.get().equals(ButtonType.OK)){	
				
				//from https://stackoverflow.com/a/16394462/10523888
				
				 try {
					String url_open ="https://github.com/entonoire/AverageCalculator/releases/download/"+content+"/AverageCalc.jar";
					Desktop.getDesktop().browse(URI.create(url_open));
					System.exit(0);
			        }catch (IOException e) {
			            e.printStackTrace();
			        }
			}
			
		}
	}
	
	public static Double getVersion(){
		return version;
	}

}
