package fr.vallfeur.averagecalc.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.vallfeur.averagecalc.Calculator;

public class Register {

	public static void create(double result, String[] numbers){
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("HH.mm.ss dd-MM-yyyy");	
		File file = new File(Manager.getAppFile(), "Result "+date.format(cal.getTime())+".csv");
		
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
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
			Calculator.errorPopup("FileError", "I can't write the result file, please contact the developper for help.");
			e.printStackTrace();
			return;
		}
		
	}
	
}
