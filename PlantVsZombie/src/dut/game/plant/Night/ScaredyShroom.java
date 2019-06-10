package dut.game.plant.Night;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Crater;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class ScaredyShroom extends PlantImplementation {
	public ScaredyShroom(int x,int y) {
		super(x, y, 30, 50, 10, 1500, new Color(102, 0, 102), 25, 7500);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v, int zombieNumber[],ArrayList<Graves> lstG,ArrayList<Crater> lstC) {
		boolean isFire =false;
		if(System.currentTimeMillis()>getLastFired()+getFrequence() && Proximity(lstZ, v).size()==0) {
			isFire=true;
		}
		if(isFire && zombieNumber[v.lineFromY(getY())]!=0) {isFire=false;return true;}
		return false;
	}
	
	private Rectangle2D drawImpact(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX())-1);
		int y =  (int) v.yFromJ(v.lineFromY(getY())-1);
		int x2 = (int) v.getSquareSize()*3;
		int y2 = (int) v.getSquareSize()*3;
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	private boolean collision(Zombie g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
	
	public ArrayList<Zombie> Proximity(LinkedList<Zombie> lstG,GameView v){
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie z: lstG) {
			if(collision(z, v)) {
				System.out.println("Collision Explosion Cherry Bomb !");
				lstCol.add(z);
			}
		}
		return lstCol;
	}
	
	@Override
	public boolean isMushroom() {
		return true;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new ScaredyShroom(x, y);
	}
}
