package dut.game.plant;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.zombie.Zombie;

public class PotatoMine extends PlantImplementation {

	public PotatoMine(int x, int y) {
		super(x, y, 40,40, 10, 14000, Color.ORANGE, 25,300);
	}
	
	@Override
	public Shape draw() {
		return new Ellipse2D.Float(super.getX(), super.getY(), 40, 40);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		if(System.currentTimeMillis()>getLastFired()+getFrequence()) {
			ArrayList<Zombie> colliding;
			if((colliding = colliding(lstZ)).size()!=0) {
				for(Zombie z: colliding) {
					z.addToHealth(-1800);
					this.addToHealth(-1000000);
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new PotatoMine(x, y);
	}

}
