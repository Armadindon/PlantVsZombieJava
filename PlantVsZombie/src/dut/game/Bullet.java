package dut.game;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

public class Bullet implements GameObject {
	
	private int x;
	private int y;
	private int speed = 10;
	private int damage = 1;
	private int taille =5;
	
	public Bullet(int x,int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void move() {
		x+=speed;
	}

	@Override
	public Float draw() {
		return new Rectangle2D.Float(x, y, taille,taille);
	}

	@Override
	public GameObject colliding(ArrayList<GameObject> lst) {
		for(GameObject g: lst) {
			if(collision(g.draw())) {
				return g;
			}
		}
		return null;
	}

	@Override
	public boolean collision(Rectangle2D r) {
		return r.getMinX()<=x+taille;//on vérifie que la variable x car on se déplace seulement sur cet axe
	}

}
