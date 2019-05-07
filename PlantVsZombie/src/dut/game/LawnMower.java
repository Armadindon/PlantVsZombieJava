package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import dut.game.zombie.Zombie;

public class LawnMower {
	private int x;
	private int y;
	private int taille = 50;
	private int speed = 0;
	private Color color = Color.DARK_GRAY;
	private boolean running = false;
	
	public LawnMower(int x,int y) {
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
			if(collision(g.draw())) {
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
	
	public boolean matrixOut(GameView v) {
		return v.isOut2(x);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void run() {
		speed = 7;
		running = true;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof LawnMower)) {return false;}
		LawnMower l = (LawnMower) o;
		return l.running == running && l.x==x && l.y==y && speed == l.speed && color.equals(l.color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,taille,speed,running,color);
	}
	
	public int getY() {
		return y;
	}
}
