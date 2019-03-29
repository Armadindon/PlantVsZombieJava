package dut.game;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

public class Plant implements GameObject {
	private int x;
	private int y;
	private final int taille;
	private int health;
	
	public Plant(int x, int y,int taille,int health) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.health = health;
	}
	
	@Override
	public void move() {
		//ne fait rien
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
