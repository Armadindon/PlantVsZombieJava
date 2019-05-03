package dut.game;

import java.awt.Color;
import java.util.LinkedList;

public class SunFlower extends PlantImplementation {

	public SunFlower(int x, int y) {
		super(x, y, 40, 1, 50, new Color(255,255,102), 1, 100);
	}
	
	@Override
	public Bullet bullet(GameData data) {
		data.addSun(new Sun(getX(), getY()));
		return null;
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		incrementCompteur();
		if(super.getCompteur()%super.getFrequence()==0) {return true;}
		return false;
	}
}
