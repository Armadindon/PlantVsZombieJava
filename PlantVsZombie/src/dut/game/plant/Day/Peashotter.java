package dut.game.plant.Day;

import java.awt.Color;

import dut.game.plant.PlantImplementation;
import dut.game.plant.Plant;

public class Peashotter extends PlantImplementation {
	
	public Peashotter(int x,int y) {
		super(x,y,40,40,10,1500,Color.BLUE,100,7500);
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Peashotter(x, y);
	}
}
