package fr.vallfeur.averagecalc.resources;

import java.awt.Color;

import javafx.scene.paint.Paint;

public class Colors {

	public static Color red = new Color(240,71,71);
	public static Color blueviolet = new Color(114,137,218);
	public static Color ultrawhite = new Color(255,255,255);
	public static Color grayviolet = new Color(153,170,181);
	public static Color gray = new Color(44,47,51);
	public static Color darkgray = new Color(35,39,42);
	public static Color black = new Color(0,0,0);
	
	
	public static String toHex(Color color){
		
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public static Paint toPaint(Color color){
		return javafx.scene.paint.Color.valueOf(Colors.toHex(color));
	}
}