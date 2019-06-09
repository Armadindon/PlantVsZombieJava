package dut.game.zombie;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;

public class NewspaperZombie extends ZombieImplementation {
	public NewspaperZombie(int x,int y) {
		super(x,y,40,-0.8,340,Color.white);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new NewspaperZombie(x, y);
	}
	
	@Override
	public void special(LinkedList<Plant> lstP, GameView v){
		if(getHealth()<200 && colliding(lstP).size()==0) {
			setSpeed(-1.6);
		}
	}
}
