package dut.game.plant;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.zombie.Zombie;

public class PlantImplementation implements Plant  {
	private int x;
	private int y;
	private final int taille;
	private double health;
	private int frequence;
	private int compteur;
	private Color color;
	private int cost;
	private int respawnTime;
	
	public PlantImplementation(int x, int y,int taille,int health,int frequence,Color color,int cost,int respawnTime) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.health = health;
		this.frequence=frequence;
		this.color = color;
		this.cost = cost;
		this.respawnTime = respawnTime;
	}

	@Override
	public Float draw() {
		return new Rectangle2D.Float(x, y, taille,taille);
	}

	@Override
	public ArrayList<Zombie> colliding(LinkedList<Zombie> lst) {
		ArrayList<Zombie> lstCol = new ArrayList<>();
		for(Zombie g: lst) {
			if(collision(g.draw())) {
				System.out.println("Collision plante");
				lstCol.add(g);
			}
		}
		return lstCol;
	}

	@Override
	public boolean collision(Rectangle2D r) {
		return r.getBounds2D().intersects(this.draw());
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		compteur++;
		if(compteur%frequence==0 && zombieNumber[v.lineFromY(y)]!=0) {return true;}
		return false;
	}
	
	public Bullet bullet(GameData data) {
		return new Bullet(x+taille+10,y+taille/2);
	}

	@Override
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PlantImplementation)) {return false;}
		PlantImplementation p = (PlantImplementation) o;
		return x==p.x &&  y==p.y && taille == p.taille && health==p.health && frequence == p.frequence;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,taille,health,frequence);
	}
	
	public Color getColor() {
		return color;
	}

	public String toString() {
		return "Plante en position "+x+" "+y+" ayant "+ health + " point de vie";
	}

	@Override
	public void addToHealth(double i) {
		health+= i;
	}

	
	public int getX() {
		return x;
	}
	
	public void decrementCompteur(){
		compteur--;
	}
	
	public void incrementCompteur() {
		compteur++;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public int getCost() {
		return cost;
	}
	
	@Override
	public int getRespawnTime() {
		return respawnTime;
	}
	
	public int getCompteur() {
		return compteur;
	}
	public int getFrequence() {
		return frequence;
	}
}
