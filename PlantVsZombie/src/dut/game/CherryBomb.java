package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class CherryBomb extends PlantImplementation{
	private int compteur =0;
	
	public CherryBomb(int x, int y) {
		super(x,y,50,1,1,Color.RED);
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
		compteur++;
		if(compteur>75) {
			if(collidingExplosion(lstZ, v).size()!=0) {
				for(Zombie z: collidingExplosion(lstZ, v)) {
					z.addToHealth(-10);
					this.addToHealth(-100);
				}
				return true;
				
			}
		}
		return false;
	}
	
	@Override
	public Bullet bullet() {
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
	
	
}
