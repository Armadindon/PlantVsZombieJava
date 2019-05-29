package dut.game.zombie;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import dut.game.GameView;

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
		x += speed;
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
		return color;
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
		return 0.05;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void setToInitialSpeed() {
		speed= initialSpeed;
	}
	
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
	public boolean isFrozen() {
		return frozen;
	}

}
