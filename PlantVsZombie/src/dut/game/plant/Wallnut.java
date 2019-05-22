package dut.game.plant;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.zombie.Zombie;

public class Wallnut extends PlantImplementation {
	
	public Wallnut(int x, int y) {
		super(x,y,40,60,20,50,new Color(204,153,0),50,300);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		return false;// ne tire jamais
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Wallnut(x, y);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(super.getX(), super.getY(), getSizeX(), getSizeY());
	}

}
