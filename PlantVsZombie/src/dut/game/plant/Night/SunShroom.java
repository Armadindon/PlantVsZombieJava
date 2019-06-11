package dut.game.plant.Night;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.Sun;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.plant.Day.SunFlower;
import dut.game.zombie.Zombie;

public class SunShroom extends PlantImplementation {
	private long instantiateTime;

	public SunShroom(int x, int y) {
		super(x, y, 30,30, 1, 24000, new Color(254,255,151), 25, 7500);
		instantiateTime = System.currentTimeMillis();
		setLastFired(System.currentTimeMillis()-17000);
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		setLastFired(System.currentTimeMillis());
		data.addSun(new Sun(getX()+5, getY()+5,(System.currentTimeMillis()>instantiateTime+120000)?25:15));
		return null;
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(System.currentTimeMillis()>super.getFrequence()+super.getLastFired()) {return true;}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new SunShroom(x, y);
	}
	
	@Override
	public boolean isMushroom() {
		return true;
	}

}
