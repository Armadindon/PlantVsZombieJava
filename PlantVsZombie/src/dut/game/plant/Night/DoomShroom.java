package dut.game.plant.Night;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dut.game.Crater;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.Graves;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class DoomShroom extends PlantImplementation {
	public DoomShroom(int x,int y) {
		super(x, y, 50, 50, 10, 1000, Color.black, 125, 50000);
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
				for(Zombie z: collidingExplosion(data.getLstZ(), v)) {
					z.addToHealth(-1800);
				}
				addToHealth(-100);
				data.addCrater(new Crater(getX(), getY()));
		}
		return false;
	}
	
	private Rectangle2D drawImpact(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX())-3);
		int y =  (int) v.yFromJ(v.lineFromY(getY())-2);
		int x2 = (int) v.getSquareSize()*7;
		int y2 = (int) v.getSquareSize()*5;
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	private boolean collisionExplosion(Zombie g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
	
	public ArrayList<Zombie> collidingExplosion(List<Zombie> lstG,GameView v){
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie z: lstG) {
			if(collisionExplosion(z, v)) {
				System.out.println("Collision Explosion Doom Shroom !");
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
		return new DoomShroom(x, y);
	}
}
