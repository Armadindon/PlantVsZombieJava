package dut.game.plant;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.zombie.Zombie;

public class Chomper extends PlantImplementation {
	private boolean notChomp = true;

	public Chomper(int x, int y) {
		super(x, y, 50, 50, 10, 42000, new Color(204, 0, 255), 150, 7500);
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Chomper(x, y);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v, int zombieNumber[]) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis() || notChomp) {
			ArrayList<Zombie> colliding;
			if((colliding = collidingChomp(lstZ, v)).size()>0) {
				colliding.get(0).addToHealth(-Integer.MAX_VALUE);
				notChomp=false;
				return true;
		}
		}
		return false;
	}
	
	@Override
	public Bullet bullet(GameData data) {
		return null;
	}
	
	private Rectangle2D drawImpact(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX()));
		int y =  (int) v.yFromJ(v.lineFromY(getY()));
		int x2 = (int) v.getSquareSize()*2;
		int y2 = (int) v.getSquareSize()*1;
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	public ArrayList<Zombie> collidingChomp(LinkedList<Zombie> lstG,GameView v){
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie z: lstG) {
			if(collisionChomp(z, v)) {
				lstCol.add(z);
			}
		}
		return lstCol;
	}
	
	private boolean collisionChomp(Zombie g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
}
