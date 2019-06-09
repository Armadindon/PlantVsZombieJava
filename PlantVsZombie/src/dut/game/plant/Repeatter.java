package dut.game.plant;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.zombie.Zombie;

public class Repeatter extends PlantImplementation {
	boolean nextFire = false;
	
	public Repeatter(int x,int y) {
		super(x,y,40,40,1,1500,Color.pink,200,7500);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		if(System.currentTimeMillis()>getLastFired()+getFrequence() && zombieNumber[v.lineFromY(getY())]!=0) {
			nextFire =true;
			return true;
		}else if(nextFire && System.currentTimeMillis()>getLastFired()+50) {//on décale deux balles de 50 ms
			nextFire= false;
			return true;
		}
		return false;
	}
	
	@Override
	public Plant instantiateFlower(int x ,int y) {
		return new Repeatter(x, y);
	}

}
