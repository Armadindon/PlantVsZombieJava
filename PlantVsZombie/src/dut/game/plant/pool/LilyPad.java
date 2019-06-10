package dut.game.plant.pool;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;

public class LilyPad extends PlantImplementation {
	
	public LilyPad(int x,int y) {
		super(x, y, 20, 20, 10, 0, Color.green, 25, 7500);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(getX(),getY(),getSizeX(),getSizeY());
	}
	
	@Override
	public boolean flotte() {
		return true;
	}
	
	@Override
	public boolean support() {
		return true;
	}
	
	@Override
	public Plant instantiateFlower(int x,int y) {
		return new LilyPad(x, y);
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}

}
