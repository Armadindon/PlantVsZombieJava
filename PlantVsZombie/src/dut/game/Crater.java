package dut.game;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.plant.Plant;

public class Crater {
	private int x;
	private int y;
	private long creationTime = System.currentTimeMillis();
	
	public Crater(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ellipse2D draw() {
		return new Ellipse2D.Float(x,y,50,50);
	}
	
	public boolean collision(Shape r) {
		if(r.getBounds2D().intersects(this.draw().getBounds2D())) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Graves> colliding(ArrayList<Graves> lst) {
		ArrayList<Graves> lstCol = new ArrayList<Graves>();
		for(Graves g: lst) {
			if(collision(g.draw())) {
				System.out.println("Collision Tombe");
				lstCol.add(g);
			}
		}
		return lstCol;
	}
	
	public ArrayList<Plant> colliding(LinkedList<Plant> lst) {
		ArrayList<Plant> lstCol = new ArrayList<Plant>();
		for(Plant p: lst) {
			if(p.collision(draw().getBounds2D())) {
				System.out.println("Collision Tombe");
				lstCol.add(p);
			}
		}
		return lstCol;
	}
	
	public boolean isAlive() {
		return creationTime+180000>System.currentTimeMillis();
	}

}
