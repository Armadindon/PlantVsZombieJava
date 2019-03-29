package dut.game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Zombie implements GameObject{
	private int x;
	private int y;
	private int taille;
	private final int speed;
	private int health;

	public Zombie(int x, int y, int taille,int speed,int health) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.speed = speed;
		this.health = health;
	}

	@Override
	public void move() {
		x += speed;
	}
	
	@Override
	public Rectangle2D.Float draw(){
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
		return r.getMaxX()<=x;//on vérifie que la variable x car on se déplace seulement sur cet axe
	}
	
	
}
