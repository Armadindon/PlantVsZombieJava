package dut.game.plant.pool;

import java.awt.Color;
import java.util.ArrayList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class Jalapeno extends PlantImplementation {
	
	public Jalapeno(int x,int y) {
		super(x,y,25,50, 10, 3000, Color.red, 125, 50000);
	}
	
	@Override
	public boolean isFire(GameView v, GameData data) {
		if(super.isFire(v, data)) {
			for(Zombie z: data.getLstZ()) {
				if(v.lineFromY(z.getY())==v.lineFromY(getY())) {
					z.addToHealth(-1800);
				}
			}
			addToHealth(-1800);
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}
	
	@Override
	public Plant instantiateFlower(int x,int y) {
		return new Jalapeno(x, y);
	}

}
