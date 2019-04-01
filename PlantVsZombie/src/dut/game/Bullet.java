package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.Objects;

public class Bullet implements GameObject {
	
	private int x;
	private int y;
	private int speed = 10;
	private int damage = 1;
	private int taille =5;
	private boolean alive = true;
	private final Color color= Color.BLACK; 
	
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
		if(r.getMinX()<=x+taille) {
			alive = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Bullet)) {return false;}
		Bullet b = (Bullet) o;
		return x==b.x &&  y==b.y && taille == b.taille && speed==b.speed && damage == b.damage && alive == b.alive;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,speed,damage,taille,alive);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean matrixOut(GameView v) {
		return v.isOut(x);
	}

}
