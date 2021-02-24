package fr.vallfeur.averagecalc.file;

import java.io.File;

public class Manager {

	private static String Appdata = System.getenv("APPDATA");
	private static File AppFile = new File(Appdata, ".AverageCalculator");
	
	public static void createBase(){
		if(!AppFile.exists()){
			if(!AppFile.mkdir()){
				AppFile.mkdir();
			}
		}
		Settings.setup();
	}
	
	public static File getAppFile(){
		return AppFile;
	}
	
}
