package dut.game.plant.pool;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.plant.Day.Wallnut;

public class TallNut extends PlantImplementation {
	public TallNut(int x, int y) {
		super(x,y,40,80,40,50,new Color(204,153,0),125,30000);
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		return false;// ne tire jamais
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new TallNut(x, y);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(super.getX(), super.getY(), getSizeX(), getSizeY());
	}
	
	@Override
	public boolean isTall() {
		return true;
	}

}
