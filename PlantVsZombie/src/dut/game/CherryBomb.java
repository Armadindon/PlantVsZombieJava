package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class CherryBomb extends Plant{
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
	public boolean isFire() {
		compteur++;
		if(compteur>75) {
			return true;
		}
		return false;
	}
	
	private boolean collisionExplosion(GameObject g,GameView v) {
		return drawImpact(v).getBounds().intersects(g.draw());
	}
	
	public ArrayList<GameObject> collidingExplosion(LinkedList<GameObject> lstG,GameView v){
		ArrayList<GameObject> lstCol = new ArrayList<GameObject>();
		for(GameObject g: lstG) {
			if(collisionExplosion(g, v) && !(equals(g)) && g instanceof Zombie) {
				System.out.println("Collision Explosion Cherry Bomb !");
				lstCol.add(g);
			}
		}
		return lstCol;
	}
	
	
}
