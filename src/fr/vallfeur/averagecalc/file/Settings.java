package fr.vallfeur.averagecalc.file;

import java.io.File;
import java.io.IOException;

public class Settings {

	public static File file = new File(Manager.getAppFile(), "Settings.txt");
	
	public static void setup(){
		
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static File get(){
		return file;
	}
	
}
