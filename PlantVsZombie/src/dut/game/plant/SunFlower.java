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
		super(x, y, 40,40, 1, 24000, new Color(254,255,151), 50, 100);
		setLastFired(System.currentTimeMillis()-17000);
	}
	
	@Override
	public Bullet bullet(GameData data) {
		setLastFired(System.currentTimeMillis());
		data.addSun(new Sun(getX()+5, getY()+5,25));
		return null;
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		if(System.currentTimeMillis()>super.getFrequence()+super.getLastFired()) {return true;}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new SunFlower(x, y);
	}
}
