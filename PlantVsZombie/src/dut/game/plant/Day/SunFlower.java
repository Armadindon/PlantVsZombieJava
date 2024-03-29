package dut.game.plant.Day;

import java.awt.Color;
import dut.game.plant.Plant;

import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.Sun;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class SunFlower extends PlantImplementation {

	public SunFlower(int x, int y) {
		super(x, y, 40,40, 1, 24000, new Color(254,255,151), 50, 7500);
		setLastFired(System.currentTimeMillis()-17000);
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		setLastFired(System.currentTimeMillis());
		data.addSun(new Sun(getX()+5, getY()+5,25));
		return null;
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(System.currentTimeMillis()>super.getFrequence()+super.getLastFired()) {return true;}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new SunFlower(x, y);
	}
}
