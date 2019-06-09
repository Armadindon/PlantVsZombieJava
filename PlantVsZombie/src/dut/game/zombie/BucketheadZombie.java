package dut.game.zombie;

import java.awt.Color;

public class BucketheadZombie extends ZombieImplementation {
	
	public BucketheadZombie(int x,int y) {
		super(x,y,40,-0.8,1300,Color.darkGray);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new BucketheadZombie(x, y);
	}
}
