package fr.vallfeur.averagecalc.resources;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Resources {

	public static ImageView getMenuIcon(String icon){
		//icons from https://material.io/resources/icons/?search=trash&icon=delete&style=baseline and https://fontawesome.com/icons?d=gallery&p=2
		ImageView content = null;
		switch (icon) {
		case "purge":
			content = new ImageView(new Image(Resources.class.getResourceAsStream("menu"+File.separator+"purge.png")));
			break;
		case "load":
			content = new ImageView(new Image(Resources.class.getResourceAsStream("menu"+File.separator+"load.png")));
			break;
		case "settings":
			content = new ImageView(new Image(Resources.class.getResourceAsStream("menu"+File.separator+"settings.png")));
			break;
		case "files":
			content = new ImageView(new Image(Resources.class.getResourceAsStream("menu"+File.separator+"files.png")));
			break;
		default:
			break;
		}
		return content;
	}
	
}
