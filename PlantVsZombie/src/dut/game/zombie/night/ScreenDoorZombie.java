package dut.game.zombie.night;

import java.awt.Color;

import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;
import dut.game.zombie.day.BucketheadZombie;

public class ScreenDoorZombie extends ZombieImplementation {
	public ScreenDoorZombie(int x,int y) {
		super(x, y, 50, -0.8, 1300, Color.gray);
	}
	
	@Override
	public boolean isHittable() {
		return (getHealth()<200)?true:false;
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new ScreenDoorZombie(x, y);
	}

}
