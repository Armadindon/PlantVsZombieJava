 package dut.game.plant.pool;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class SpikeWeed extends PlantImplementation {
	public SpikeWeed(int x,int y) {
		super(x,y,60,60,10,1000,new Color(153, 102, 0), 100,7500);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(getX(),getY(),getSizeX(),getSizeY());
	}
	
	@Override
	public boolean isFire(GameView v, GameData data) {
		if(super.isFire(v, data)) {
			for(Zombie z: colliding(data.getLstZ())) {
				z.addToHealth(-20);
			}
			setLastFired(System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new SpikeWeed(x, y);
	}
	
	@Override
	public boolean isHitable() {
		return false;
	}
}
