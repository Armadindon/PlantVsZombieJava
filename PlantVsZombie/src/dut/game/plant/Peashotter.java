package dut.game.plant;

import java.awt.Color;

public class Peashotter extends PlantImplementation {
	
	public Peashotter(int x,int y) {
		super(x,y,40,40,10,1500,Color.BLUE,100,100);
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Peashotter(x, y);
	}
}
