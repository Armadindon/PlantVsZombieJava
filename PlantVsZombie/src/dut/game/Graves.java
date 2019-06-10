package dut.game;


import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.zombie.Zombie;



public class Graves {
	int x;
	int y;
	
	public Graves(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle2D draw() {
		return new Rectangle2D.Float(x,y,50,50);
	}
	
	public boolean collision(Rectangle2D r) {
		if(r.getBounds2D().intersects(this.draw())) {
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
}
