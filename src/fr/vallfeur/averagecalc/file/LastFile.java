package fr.vallfeur.averagecalc.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.vallfeur.averagecalc.Calculator;

public class LastFile {

	public static void register(double result, String[] numbers){	
		File file = new File(Manager.getAppFile(), "LastResult.csv");
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			return;
		}
		
		
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			StringBuilder builder = new StringBuilder();
			builder.append("Numbers: ");
			for(String num : numbers){
				builder.append(num+" ");
			}
			
			bufferedWriter.write(builder.toString());
			bufferedWriter.newLine();
			bufferedWriter.write("Result: "+result);
			bufferedWriter.close();
			writer.close();
			
		} catch (IOException e) {
			file.delete();
			Calculator.errorPopup("FileError", "I can't write the last result file, please contact the developper for help.");
			e.printStackTrace();
			return;
		}
		
	}
	
}
