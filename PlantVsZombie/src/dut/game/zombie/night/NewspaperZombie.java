package dut.game.zombie.night;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;
import dut.game.zombie.day.BucketheadZombie;

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
		if(getHealth()<200 && collidingP(lstP).size()==0) {
			setSpeed(-1.6);
		}
	}
	

}
