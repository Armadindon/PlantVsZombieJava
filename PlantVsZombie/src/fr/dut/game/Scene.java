package fr.dut.game;

import java.util.ArrayList;

public class Scene {
	private int hour;
	private final  ArrayList<GameObjects> objectsList;

	public Scene(){
		hour=12;
		objectsList = new ArrayList<>();
	}
	public void Update() {
		if(hour==24) {
			hour = 0;
		}
		
	}
	
	public void Affichage() {
		for (GameObjects g: objectsList) {
			
		}
	}

}
