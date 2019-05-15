package dut.game.plant;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.zombie.Zombie;

public class CherryBomb extends PlantImplementation{
	
	public CherryBomb(int x, int y) {
		super(x,y,50,1,3000,Color.RED,150,200);
	}
	
	private Rectangle2D drawImpact(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX())-1);
		int y =  (int) v.yFromJ(v.lineFromY(getY())-1);
		int x2 = (int) v.getSquareSize()*3;
		int y2 = (int) v.getSquareSize()*3;
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v, int zombieNumber[]) {
		if(getLastFired()+getFrequence()<System.currentTimeMillis()) {
				for(Zombie z: collidingExplosion(lstZ, v)) {
					z.addToHealth(-10);
					this.addToHealth(-100);
				}
				return true;
				
		}
		return false;
	}
	
	@Override
	public Bullet bullet(GameData data) {
		return null;
	}
	
	private boolean collisionExplosion(Zombie g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
	
	public ArrayList<Zombie> collidingExplosion(LinkedList<Zombie> lstG,GameView v){
		ArrayList<Zombie> lstCol = new ArrayList<Zombie>();
		for(Zombie z: lstG) {
			if(collisionExplosion(z, v)) {
				System.out.println("Collision Explosion Cherry Bomb !");
				lstCol.add(z);
			}
		}
		return lstCol;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new CherryBomb(x, y);
	}
	
	
}
