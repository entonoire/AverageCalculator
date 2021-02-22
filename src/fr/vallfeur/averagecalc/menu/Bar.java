package fr.vallfeur.averagecalc.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fr.vallfeur.averagecalc.Area;
import fr.vallfeur.averagecalc.Main;
import fr.vallfeur.averagecalc.file.Manager;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class Bar {
	
	public static Node setup(){
		MenuBar menu = new MenuBar();
		Menu files = new Menu("Files");
		MenuItem load = new MenuItem("Load");
		MenuItem purge = new MenuItem("Purge");
		
		load.setOnAction(event -> {
			onLoadClick();
		});
		purge.setOnAction(event -> {
			onPurgeClick();
		});
		files.getItems().addAll(load, purge);
		menu.getMenus().add(files);
		
		return menu;
	}
	
	
	public static void onLoadClick(){
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(Manager.getAppFile());
		
		chooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("CSV Files", "*.csv*"),
				new FileChooser.ExtensionFilter("TXT Files", "*.txt*")
		);
		File selectedFile = chooser.showOpenDialog(Main.getStage());

		if(selectedFile == null){
			return;
		}
		try{
			FileReader reader = new FileReader(selectedFile);
			BufferedReader bufferedReader = new BufferedReader(reader);
			try{
				String firstLine = bufferedReader.readLine();
				if(firstLine.startsWith("Numbers: ")){
					Area.writeGet().setText(firstLine.replace("Numbers: ", ""));
				}
				else{
					Area.writeGet().setText(firstLine);
				}
				bufferedReader.close();
			}catch(IOException e){
				e.printStackTrace();
				return;			
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
			return;
		}
	}
	
	public static void onPurgeClick(){
		for(File file : Manager.getAppFile().listFiles()){
			file.delete();
		}
	}
}
