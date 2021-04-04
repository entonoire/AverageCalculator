package fr.vallfeur.averagecalc.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import fr.vallfeur.averagecalc.Area;
import fr.vallfeur.averagecalc.Calculator;
import fr.vallfeur.averagecalc.Main;
import fr.vallfeur.averagecalc.file.Manager;
import fr.vallfeur.averagecalc.file.Settings;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;



public class Bar {
	//real help https://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
	private static CheckMenuItem resultSave = new CheckMenuItem("save result");
	private static CheckMenuItem lastresultSave = new CheckMenuItem("save last result");
	
	public static Node setup(){
		MenuBar menu = new MenuBar();
		Menu files = new Menu("Files");
		MenuItem load = new MenuItem("Load");
		MenuItem purge = new MenuItem("Purge");
		Menu settings = new Menu("Settings");

		try {
			for(String str : Files.readAllLines(Paths.get(Settings.get().toURI()))){
				switch (str) {
				case "resultsave: true;":
					resultSave.setSelected(true);
					break;
				case "resultsave: false;":
					resultSave.setSelected(false);
					break;
				case "lastresultsave: true;":
					lastresultSave.setSelected(true);
					break;
				case "lastresultsave: false;":
					lastresultSave.setSelected(false);
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			Calculator.errorPopup("FileError", "I can't read the file in "+Settings.get().getPath()+", please contact the developper for help.");
			e.printStackTrace();
		}

		resultSave.setOnAction(event -> {
			try {
				Files.write(Paths.get(Settings.get().toURI()), ("resultsave: "+resultSave.isSelected()+";").getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				Calculator.errorPopup("FileError", "I can't write the file in "+Settings.get().getPath()+", please contact the developper for help.");
				e.printStackTrace();
			}

		});
		lastresultSave.setOnAction(event -> {
			try {
				Files.write(Paths.get(Settings.get().toURI()), ("\nlastresultsave: "+lastresultSave.isSelected()+";").getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				Calculator.errorPopup("FileError", "I can't write the file in "+Settings.get().getPath()+", please contact the developper for help.");
				e.printStackTrace();
			}
		});
		
		load.setOnAction(event -> {
			onLoadClick();
		});
		purge.setOnAction(event -> {
			onPurgeClick();
		});
		settings.getItems().addAll(resultSave, lastresultSave);
		files.getItems().addAll(load, purge);
		menu.getMenus().addAll(files, settings);
		
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
	
	
	public static boolean savingResult(){
		return resultSave.isSelected();
	}
	
	public static boolean savingLastResult(){
		return lastresultSave.isSelected();
	}
}
