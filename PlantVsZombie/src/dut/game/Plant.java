package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Plant implements GameObject {
	private int x;
	private int y;
	private final int taille;
	private double health;
	private int frequence;
	private int compteur;
	private Color color;
	
	public Plant(int x, int y,int taille,int health,int frequence,Color color) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.health = health;
		this.frequence=frequence;
		this.color = color;
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
	public ArrayList<GameObject> colliding(LinkedList<GameObject> lst) {
		ArrayList<GameObject> lstCol = new ArrayList();
		for(GameObject g: lst) {
			if(collision(g.draw()) && !(equals(g)) && g instanceof Zombie) {
				System.out.println("COllision plante");
				lstCol.add(g);
			}
		}
		return lstCol;
	}

	@Override
	public boolean collision(Rectangle2D r) {
		return r.getBounds2D().intersects(this.draw());
	}
	
	public boolean isFire() {
		compteur++;
		if(compteur%frequence==0) {return true;}
		return false;
	}
	
	public Bullet bullet() {
		return new Bullet(x+taille+10,y+taille/2);
	}

	@Override
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Plant)) {return false;}
		Plant p = (Plant) o;
		return x==p.x &&  y==p.y && taille == p.taille && health==p.health && frequence == p.frequence;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,taille,health,frequence);
	}
	
	public Color getColor() {
		return color;
	}
	@Override
	public boolean matrixOut(GameView v) {
		return false;//une plante ne peut pas être en dehors de la matrice puisqu'elle ne bouge pas
	}
	public String toString() {
		return "Plante en position "+x+" "+y+" ayant "+ health + " point de vie";
	}

	@Override
	public void addToHealth(double i) {
		health+= i;
	}

	@Override
	public double getDamage() {
		return 0;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void decrementCompteur(){
		compteur--;
	}

}
