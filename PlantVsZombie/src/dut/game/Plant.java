package dut.game;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

public class Plant implements GameObject {
	private int x;
	private int y;
	private final int taille;
	private int health;
	private int frequence;
	private int compteur;
	
	public Plant(int x, int y,int taille,int health,int frequence) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.health = health;
		this.frequence=frequence;
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
	
	public boolean isFire() {
		compteur++;
		if(compteur%frequence==0) {return true;}
		return false;
	}
	
	public Bullet bullet() {
		return new Bullet(x+taille,y+taille/2);
	}

}
