package dut.game.plant.pool;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import dut.game.Bullet;
import dut.game.GameData;
import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.plant.PlantImplementation;
import dut.game.zombie.Zombie;

public class Squash extends PlantImplementation {
	private boolean jumped =false;
	private long seen= 0;
	private int direction = 0;
	
	public Squash(int x, int y) {
		super(x,y,50,50,10,0,new Color(0, 204, 0),50,30000);
	}
	
	private Rectangle2D drawImpactBehind(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX())-1);
		int y =  (int) v.yFromJ(v.lineFromY(getY()));
		int x2 = (int) v.getSquareSize();
		int y2 = (int) v.getSquareSize();
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	private Rectangle2D drawImpactMid(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX()));
		int y =  (int) v.yFromJ(v.lineFromY(getY()));
		int x2 = (int) v.getSquareSize();
		int y2 = (int) v.getSquareSize();
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	private Rectangle2D drawImpactAfter(GameView v) {
		int x =  (int) v.xFromI(v.columnFromX(getX())+1);
		int y =  (int) v.yFromJ(v.lineFromY(getY()));
		int x2 = (int) v.getSquareSize();
		int y2 = (int) v.getSquareSize();
		return new Rectangle2D.Float(x,y,x2,y2);
	}
	
	@Override
	public boolean isFire(GameView v,GameData data) {
		if (jumped && System.currentTimeMillis()> seen+500) {//juste pour qu'on la voit après son jump
			addToHealth(-1800);
		}
		if(getLastFired()+getFrequence()<System.currentTimeMillis() && seen == 0) {
			for(Zombie z : data.getLstZ()) {
				if(collisionBehind(z, v)) {
					seen = System.currentTimeMillis();
					direction = -1;
				}else if(collisionMid(z, v)) {
					seen = System.currentTimeMillis();
					direction = 0;
				}else if(collisionAfter(z, v)) {
					seen = System.currentTimeMillis();
					direction = 1;
				}
			}
		}else if(seen+2000<System.currentTimeMillis()) {
			jump(direction, v);
			System.out.println("Jump !");
			for(Zombie z: data.getLstZ()) {
				if(collisionMid(z, v)) {
					z.addToHealth(-1800);
				}
			}
			jumped = true;
			seen = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Bullet> bullet(GameData data) {
		return null;
	}
	
	private boolean collisionAfter(Zombie g,GameView v) {
		return drawImpactAfter(v).getBounds().intersects(g.draw());
	}
	
	private boolean collisionMid(Zombie g,GameView v) {
		return drawImpactMid(v).getBounds().intersects(g.draw());
	}
	
	private boolean collisionBehind(Zombie g,GameView v) {
		return drawImpactBehind(v).getBounds().intersects(g.draw());
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Squash(x, y);
	}
}
