package dut.game.plant;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.zombie.Zombie;

public class Wallnut extends PlantImplementation {
	
	public Wallnut(int x, int y) {
		super(x,y,50,20,50,Color.YELLOW,1,300);
	}
	
	@Override
	public boolean isFire(LinkedList<Zombie> lstZ , GameView v,int zombieNumber[]) {
		return false;// ne tire jamais
	}

}
