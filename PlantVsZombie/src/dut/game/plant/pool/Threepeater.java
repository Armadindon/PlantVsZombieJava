package dut.game.plant.pool;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.plant.Day.Repeatter;
import dut.game.zombie.Zombie;

public class Threepeater extends PlantImplementation {
	
	public Threepeater(int x,int y) {
		super(x,y,50,50,1,1500,Color.pink,325,7500);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[],ArrayList<Graves> lstG,ArrayList<Crater> lstC) {//condition un peu longue
		if(System.currentTimeMillis()>getLastFired()+getFrequence() && 
		(zombieNumber[v.lineFromY(getY())]!=0 || 
		(v.lineFromY(getY())-1>=0)?(zombieNumber[v.lineFromY(getY())-1]!=0):false || 
		(v.lineFromY(getY())+1<=zombieNumber.length)?zombieNumber[v.lineFromY(getY())+1]!=0:false)){
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet(getX()+getSizeX()+10,getY()+getSizeY()/2,20,false));
		bullets.add(new Bullet(getX()+getSizeX()+10,getY()+getSizeY()/2-100,20,false));
		bullets.add(new Bullet(getX()+getSizeX()+10,getY()+getSizeY()/2+100,20,false));
		setLastFired(System.currentTimeMillis());
		return bullets;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Threepeater(x, y);
	}
}
