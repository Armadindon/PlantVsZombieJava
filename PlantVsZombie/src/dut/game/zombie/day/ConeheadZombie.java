package dut.game.zombie.day;

import java.awt.Color;
import dut.game.zombie.Zombie;

import dut.game.zombie.ZombieImplementation;

public class ConeheadZombie extends ZombieImplementation {
	
	public ConeheadZombie(int x, int y) {
		super(x,y,40,-0.8,560,Color.ORANGE);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new ConeheadZombie(x, y);
	}

}
