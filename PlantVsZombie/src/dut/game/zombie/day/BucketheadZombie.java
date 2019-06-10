package dut.game.zombie.day;

import java.awt.Color;

import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;

public class BucketheadZombie extends ZombieImplementation {
	
	public BucketheadZombie(int x,int y) {
		super(x,y,40,-0.8,1300,Color.darkGray);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new BucketheadZombie(x, y);
	}
}
