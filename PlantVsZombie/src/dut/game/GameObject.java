package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public interface GameObject {
	public void move();

	public Rectangle2D.Float draw();
	
	public ArrayList<GameObject> colliding(LinkedList<GameObject> lst);
	public boolean collision(Rectangle2D r);
	public boolean isAlive();
	public Color getColor();
	public boolean matrixOut(GameView v);
	public void addToHealth(double i);
	public double getDamage();
	public int getY();
}
