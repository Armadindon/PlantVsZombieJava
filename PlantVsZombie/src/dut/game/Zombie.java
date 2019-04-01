package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Objects;

public class Zombie implements GameObject{
	private int x;
	private int y;
	private int taille;
	private final int speed;
	private int health;
	private final Color color;

	public Zombie(int x, int y, int taille,int speed,int health,Color color) {
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.speed = speed;
		this.health = health;
		this.color = color;
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
		if(!(o instanceof Zombie)) {
			return false;
		}
		Zombie z = (Zombie) o;
		return z.x==x && z.y == y && z.taille == taille && z.speed == speed && z.health == health && color.equals(z.color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x,y,taille,speed,health,color);
	}
	
	public boolean matrixOut(GameView v) {
		return v.isOut(x);
	}
	
	@Override
	public String toString() {
		return "Zombie en"+x+" "+y+" de couleur "+color;
	}
}
