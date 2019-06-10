package dut.game.zombie;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;

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
	public void setSpeed(double speed);
	public int getY();
	public boolean isFrozen();
	public void freeze();
	public void unFreeze();
	public void special(LinkedList<Plant> lstP, GameView v);
	public double getX();
	public Zombie instantiateZombie(int x,int y);
	public boolean isMushroom();
}
