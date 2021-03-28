package fr.vallfeur.averagecalc.version;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class VersionMain {

	static Double version;
	static Double content;
	static int i = 0;
	
	public static void setup(){
		try{
			
			//from https://github.com/goxr3plus/Java-GitHub-API/blob/master/URLReader.java
			//Create HttpURLConnection 
			HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/entonoire/AverageCalculator/releases").openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			
			//Read line by line
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
			
			FileReader reader = new FileReader("README.md");
			BufferedReader bufferedReader = new BufferedReader(reader);
			try{
				version = Double.parseDouble(bufferedReader.readLine());		
				bufferedReader.close();
			}catch(IOException e){
				e.printStackTrace();
				return;			
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
			return;
		}catch(IOException e){
			e.printStackTrace();
		}
		if(version < content){
			System.out.println("a new version is here! (actual version: "+version+" new version: "+content+")");
		}
	}

}
