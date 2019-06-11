package dut.game.plant.Night;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class HypnoShroom extends PlantImplementation {
	
	public HypnoShroom(int x,int y) {
		super(x, y, 30, 30, 10, 0, new Color(204, 153, 255), 75, 7500);
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}
	
	@Override
	public boolean isMushroom() {
		return true;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new HypnoShroom(x, y);
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		return false;
	}
	
	@Override
	public void die(Zombie z) {
		z.hypnose();
	}

}
