package dut.game.plant.Night;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class FumeShroom extends PlantImplementation {
	public FumeShroom(int x,int y) {
		super(x, y, 45, 45, 10, 1500, new Color(102, 0, 102), 75, 7500);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v, int zombieNumber[]) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
			ArrayList<Zombie> col;
				if((col = collidingSpore(lstZ, v)).size()!=0){
					for(Zombie z: col) {
						z.addToHealth(-20);
					}
				}
		}
		return false;
	}
	
	private Rectangle2D drawImpact(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX()));
		int y =  (int) v.yFromJ(v.lineFromY(getY()));
		int x2 = (int) v.getSquareSize()*4;
		int y2 = (int) v.getSquareSize();
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	private boolean collisionSpore(Zombie g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
	
	public ArrayList<Zombie> collidingSpore(LinkedList<Zombie> lstG,GameView v){
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie z: lstG) {
			if(collisionSpore(z, v)) {
				lstCol.add(z);
			}
		}
		return lstCol;
	}
	
	@Override
	public Bullet bullet(GameData data) {
		return null;
	}
	
	@Override
	public boolean isMushroom() {
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x, int y) {
		return new FumeShroom(x, y);
	}

}
