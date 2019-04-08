package dut.game;

import java.awt.Color;
import java.util.LinkedList;

public class Wallnut extends PlantImplementation {
	
	public Wallnut(int x, int y) {
		super(x,y,50,20,50,Color.YELLOW);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		return false;// ne tire jamais
	}

}
