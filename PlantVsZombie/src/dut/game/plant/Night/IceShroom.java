package dut.game.plant.Night;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Crater;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class IceShroom extends PlantImplementation {
	
	public IceShroom(int x,int y) {
		super(x, y, 40, 40, 10, 1000, new Color(51, 102, 204), 75, 50000);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v, int zombieNumber[],ArrayList<Graves> lstG,ArrayList<Crater> lstC) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
				for(Zombie z: lstZ) {
					z.stun();
					z.addToHealth(-20);
					addToHealth(-500);
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
		return new IceShroom(x, y);
	}
}
