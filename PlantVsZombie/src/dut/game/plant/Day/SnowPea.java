package dut.game.plant.Day;

import java.awt.Color;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;

public class SnowPea extends PlantImplementation {
	
	public SnowPea(int x, int y) {
		super(x,y,40,40,1,1500,Color.CYAN,175,7500);;
	}
	
	@Override
	public Bullet bullet(GameData data) {
		setLastFired(System.currentTimeMillis());
		return new Bullet(getX()+getSizeX()+10,getY()+getSizeY()/2,0.5,true);
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new SnowPea(x, y);
	}
	
}
