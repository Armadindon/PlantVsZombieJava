package dut.game.plant;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.zombie.Zombie;

public interface Plant {
	public void addToHealth(double i);
	public Shape draw();
	public ArrayList<Zombie> colliding(LinkedList<Zombie> lst);
	public boolean collision(Rectangle2D r);
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]);
	public Bullet bullet(GameData data);
	public boolean isAlive();
	public Color getColor();
	public Integer getCost();
	public int getRespawnTime();
	public Plant instantiateFlower(int x,int y);
	public int getSizeX();
	public int getSizeY();
}