package dut.game.plant.Night;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class GraveBuster extends PlantImplementation {

	public GraveBuster(int x, int y) {
		super(x, y, 40, 40, 10, 5000, Color.darkGray, 75, 7500);
	}
	
	@Override
	public boolean canPlant(GameData data,GameView v) {
		for(Graves g: data.getLstG()) {
			if(collision(g.draw())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
			Graves deleted=null;
			for(Graves g: data.getLstG()) {
				if(collision(g.draw())) {deleted=g;break;}
			}
			data.getLstG().remove(deleted);
			addToHealth(-150);
		}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new GraveBuster(x, y);
	}

}
