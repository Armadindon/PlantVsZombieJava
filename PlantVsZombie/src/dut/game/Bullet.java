package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;

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

	/**
	 * Move the Bullet using his speed
	 */
	public void move() {
		x+=speed;
	}

	/**
	 * Draw the current state of the Bullet
	 * @return Rectangle who represent the bullet at this time
	 */
	public Float draw() {
		return new Rectangle2D.Float(x, y, taille,taille);
	}
	
	/**
	 * Check Collision with Each Zombie From the List
	 * @param lst List of Zombies
	 * @return lLst LinkedList who contains every zombie touching the Bullet
	 */
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
	
	/**
	 * Check the collision with the rectangle
	 * @param r Rectangle of the test
	 * @return Boolean
	 */
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
	
	/**
	 * Return the color of the item
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Tell if the item is out of the Matrix
	 * @param v View Used for checking if the item is out
	 * @return boolean
	 */
	public boolean matrixOut(GameView v) {
		return v.isOut(x);
	}
	
	public String toString() {
		return "Un tir en position " + x + " "+ y + " a une vitesse de " + speed;
	}

	/**
	 * Return the damagegiven by the bullet
	 * @return Damage int
	 */
	public double getDamage() {
		return damage;
	}
	
	/**
	 * Return the Y pos of the item
	 * @return y (int)
	 */
	public int getY() {
		return y;
	}
}
