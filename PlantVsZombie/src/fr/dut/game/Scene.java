package fr.dut.game;

public class Scene {
	private int hour;

	public Scene(){
		hour=12;
	}
	public void Update() {
		if(hour==24) {
			hour = 0;
		}
		
	}

}
