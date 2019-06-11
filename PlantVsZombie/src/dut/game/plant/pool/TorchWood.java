package dut.game.plant.pool;

import java.awt.Color;
import java.util.ArrayList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;

public class TorchWood extends PlantImplementation {
	
	public TorchWood(int x,int y) {
		super(x, y, 50,50, 10, 0, new Color(153, 102, 51), 175,7500);
	}
	
	@Override
	public boolean isFire(GameView v, GameData data) {
		for(Bullet b: data.getLstB()) {
			if(collision(b.draw())) {
				b.setOnFire();
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new TorchWood(x, y);
	}

}
