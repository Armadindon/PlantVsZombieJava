package dut.game.plant.pool;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class TangleKelp extends PlantImplementation {
	public TangleKelp(int x, int y) {
		super(x, y, 40,40, 10, 0, new Color(102, 51, 0), 25,30000);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(super.getX(), super.getY(), 40, 40);
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(System.currentTimeMillis()>getLastFired()+getFrequence()) {
			ArrayList<Zombie> colliding;
			if((colliding = colliding(data.getLstZ())).size()!=0) {
				for(Zombie z: colliding) {
					z.addToHealth(-Integer.MAX_VALUE);
					this.addToHealth(-1000000);
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new TangleKelp(x, y);
	}
	
	@Override
	public boolean flotte() {
		return true;
	}
}
