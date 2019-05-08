package dut.game.plant;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Sun;
import dut.game.zombie.Zombie;

public class SunFlower extends PlantImplementation {

	public SunFlower(int x, int y) {
		super(x, y, 40, 1, 50, new Color(204,153,0), 1, 100);
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
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new SunFlower(x, y);
	}
}
