package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public interface Zombie {
	public boolean matrixOut(GameView v);
	public void move();
	public Rectangle2D.Float draw();
	public boolean collision(Rectangle2D r);
	public boolean isAlive();
	public Color getColor();
	public void addToHealth(double i);
	public double getDamage();
	public void setToInitialSpeed();
	public void setSpeed(int speed);
	public int getY();
}
