package fr.dut.game;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;

import java.awt.Color;


public class Main {
	
	static void game(ApplicationContext context) {
		//écrire le code ici
		int width = 9;
		int heighth = 5;
		GameData gd = new GameData(9,5);
		GameView gv = new GameView(width, heighth,gd);// on crée notre fenêtre
		
	}
	
	
	

	public static void main(String[] args) {
	
		
		Application.run(Color.LIGHT_GRAY, Main::game);//lambda /!\ ne pas toucher
		
		
		
		
	}
}
