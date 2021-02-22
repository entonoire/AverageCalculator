package fr.vallfeur.averagecalc.css;

public class Css {

	public static String getBackgroundArea(){		
		return Css.class.getResource("backgroundarea.css").toExternalForm();
	}
	
	public static String getWriteArea(){		
		return Css.class.getResource("writearea.css").toExternalForm();
	}
	
	public static String getSendButton(){		
		return Css.class.getResource("sendbutton.css").toExternalForm();
	}

	public static String getOutPut(){
		return Css.class.getResource("output.css").toExternalForm();
	}
}
