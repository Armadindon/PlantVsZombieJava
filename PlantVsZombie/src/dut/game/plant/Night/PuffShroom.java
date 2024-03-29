package dut.game.plant.Night;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class PuffShroom extends PlantImplementation {
	
	public PuffShroom(int x,int y) {
		super(x, y, 30, 30, 10, 1500, new Color(102, 0, 102), 0, 7500);
	}
	
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
				for(Zombie z: data.getLstZ()) {
					if(z.getX()<=getX()+(v.getSquareSize()*3) && v.lineFromY(getY()) == v.lineFromY(z.getY())) {
						return true;
					}
				}	
		}
		return false;
	}
	
	@Override
	public boolean isMushroom() {
		return true;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new PuffShroom(x, y);
	}

}
