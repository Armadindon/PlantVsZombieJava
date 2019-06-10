package dut.game.zombie;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import dut.game.GameView;
import dut.game.plant.Plant;

public class ZombieImplementation implements Zombie{
	private double x;
	private int y;
	private int taille;
	private double speed;
	private double health;
	private final Color color;
	private final double initialSpeed;
	private boolean frozen = false;
	private long lastFrozen;
	private boolean hypnose;
	private long lastStun;

	public ZombieImplementation(int x, int y, int taille,double speed,double health,Color color) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.speed = speed;
		this.health = health;
		this.color = color;
		this.initialSpeed = speed;
	}

	@Override
	public void move() {
		if(hypnose) {
			x -= (frozen)?(speed/2):speed;
		}else {
			x += (frozen)?(speed/2):speed;
		}
	}
	
	@Override
	public Rectangle2D.Float draw(){
		return new Rectangle2D.Float((float) x, y, taille,taille);
	}


	@Override
	public boolean collision(Rectangle2D r) {
		return r.getBounds2D().intersects(this.draw());
	}

	@Override
	public boolean isAlive() {
		return health>0;
	}
	
	@Override
	public Color getColor() {
		return (frozen)?Color.cyan:color;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ZombieImplementation)) {
			return false;
		}
		ZombieImplementation z = (ZombieImplementation) o;
		return z.x==x && z.y == y && z.taille == taille && z.speed == speed && z.health == health && color.equals(z.color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,taille,speed,health,color);
	}
	
	public boolean matrixOut(GameView v) {
		return v.isOutZombie((int) x);
	}
	
	@Override
	public String toString() {
		return "Zombie en"+x+" "+y+" de couleur "+color;
	}
	
	@Override
	public void addToHealth(double i) {
		health+= i;
	}

	@Override
	public double getDamage() {
		return (frozen)?0.025:0.05;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public void setSpeed(double d) {
		this.speed = d;
	}
	
	@Override
	public void setToInitialSpeed() {
		speed= initialSpeed;
	}
	
	@Override
	public void freeze() {
		frozen = true;
		lastFrozen = System.currentTimeMillis();
	}
	
	@Override
	public void unFreeze() {
		if(lastFrozen+5000 < System.currentTimeMillis()) {
			System.out.println("Defreez ! ");
			frozen=false;
		}
	}
	
	@Override
	public boolean isFrozen() {
		return frozen;
	}
	
	@Override
	public void special(LinkedList<Plant> lstP,GameView v) {
		
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	public void jump(GameView v) {
		x-= v.getSquareSize();
	}
	
	public ArrayList<Plant> collidingP(LinkedList<Plant> lstP){
		ArrayList<Plant> col = new ArrayList<Plant>();
		for(Plant p: lstP) {
			if(p.collision(draw())) {
				col.add(p);
			}
		}
		return col;
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new ZombieImplementation(x, y, taille, speed, health, color);
	}
	
	public double getHealth() {
		return health;
	}
	
	@Override
	public boolean isMushroom() {
		return false;
	}

	@Override
	public void hypnose() {
		hypnose =true;
	}
	
	@Override
	public boolean isHypnose() {
		return hypnose;
	}

	@Override
	public ArrayList<Zombie> colliding(LinkedList<Zombie> lstZ) {
		ArrayList<Zombie> col = new ArrayList<Zombie>();
		for(Zombie z: lstZ) {
			if(z.collision(draw()) && !equals(z)) {
				col.add(z);
			}
		}
		return col;
	}
	
	@Override
	public void stun() {
		lastStun =  System.currentTimeMillis();
	}
	
	@Override
	public boolean isStun() {
		return System.currentTimeMillis()<lastStun+3250;
	}
	
	@Override
	public boolean isHittable() {
		return true;
	}

}
