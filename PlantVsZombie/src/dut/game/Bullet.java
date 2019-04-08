package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Bullet {
	
	private int x;
	private int y;
	private int speed = 10;
	private double damage = 1;
	private int taille =5;
	private final Color color= Color.BLACK; 
	
	public Bullet(int x,int y) {
		this.x = x;
		this.y = y;
	}

	
	public void move() {
		x+=speed;
	}

	
	public Float draw() {
		return new Rectangle2D.Float(x, y, taille,taille);
	}

	public ArrayList<Zombie> colliding(LinkedList<Zombie> lst) {
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie g: lst) {
			if(collision(g.draw()) && !(equals(g)) && g instanceof ZombieImplementation) {
				System.out.println("COllision plante");
				lstCol.add(g);
			}
		}
		return lstCol;
	}

	public boolean collision(Rectangle2D r) {
		if(r.getBounds2D().intersects(this.draw())) {
			return true;
		}
		return false;
	}

	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Bullet)) {return false;}
		Bullet b = (Bullet) o;
		return x==b.x &&  y==b.y && taille == b.taille && speed==b.speed && damage == b.damage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,speed,damage,taille);
	}

	public Color getColor() {
		return color;
	}

	public boolean matrixOut(GameView v) {
		return v.isOut(x);
	}
	
	public String toString() {
		return "Un tir en position " + x + " "+ y + " a une vitesse de " + speed;
	}
	

	public double getDamage() {
		return damage;
	}
	
	public int getY() {
		return y;
	}
}
