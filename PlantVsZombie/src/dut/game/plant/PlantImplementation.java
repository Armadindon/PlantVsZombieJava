package dut.game.plant;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import dut.game.Bullet;
import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.zombie.Zombie;

public class PlantImplementation implements Plant  {
	private int x;
	private int y;
	private final int tailleX;
	private final int tailleY;
	private double health;
	private long frequence;
	private long lastFired = System.currentTimeMillis();
	private Color color;
	private int cost;
	private int respawnTime;
	private boolean isFire=false;

	
	public PlantImplementation(int x, int y,int tailleX,int tailleY,double health,long frequence,Color color,int cost,int respawnTime) {
		this.x = x;
		this.y = y;
		this.tailleX = tailleX;
		this.tailleY= tailleY;
		this.health = health;
		this.frequence=frequence;
		this.color = color;
		this.cost = cost;
		this.respawnTime = respawnTime;
	}

	@Override
	public Shape draw() {
		return new Rectangle2D.Float(x, y, tailleX,tailleY);
	}

	@Override
	public ArrayList<Zombie> colliding(List<Zombie> lst) {
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
	public boolean collision(Shape r) {
		return r.getBounds2D().intersects(this.draw().getBounds2D());
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(System.currentTimeMillis()>lastFired+frequence) {
			isFire=true;
		}
		if(isFire && data.getZombieNumber()[v.lineFromY(y)]!=0) {isFire=false;return true;}
		return false;
	}
	
	public ArrayList<Bullet> bullet(GameData data) {
		lastFired=System.currentTimeMillis();
		ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
		Bullets.add(new Bullet(x+tailleX+10,y+tailleY/2,20,false));
		return Bullets;
	}

	@Override
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PlantImplementation)) {return false;}
		PlantImplementation p = (PlantImplementation) o;
		return x==p.x &&  y==p.y && tailleX == p.tailleX && tailleY == p.tailleY && health==p.health && frequence == p.frequence && color.equals(p.color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,tailleX,tailleY,health,frequence);
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

	
	public int getY() {
		return y;
	}
	
	@Override
	public Integer getCost() {
		return cost;
	}
	
	@Override
	public int getRespawnTime() {
		return respawnTime;
	}

	public long getFrequence() {
		return frequence;
	}

	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new PlantImplementation(x, y, tailleX,tailleY, health, frequence, color, cost, respawnTime);
	}
	
	@Override
	public int getSizeX() {
		return tailleX;
	}
	@Override
	public int getSizeY() {
		return tailleY;
	}
	
	public long getLastFired() {
		return lastFired;
	}
	
	public void setLastFired(long lastFired) {
		this.lastFired = lastFired;
	}
	
	public boolean isMushroom() {
		return false;
	}

	@Override
	public boolean canPlant(GameData data,GameView v) {
		for(Graves g: data.getLstG()) {
			if(collision(g.draw())) {
				return false;
			}
		}
		for(Crater c: data.getLstC()){
			if(collision(c.draw())) {return false;}
		}
		return true;
	}

	@Override
	public void die(Zombie z) {	
	}

	@Override
	public boolean flotte() {
		return false;
	}

	@Override
	public boolean support() {
		return false;
	}
	
	@Override
	public boolean isTall() {
		return false;
	}
	
	public void jump(int i,GameView v) {
		x = x+(i*v.getSquareSize());
	}

	@Override
	public boolean isHitable() {
		return true;
	}
	
	
}
