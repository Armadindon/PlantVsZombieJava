package dut.game.zombie;

import java.awt.Color;

public class ConeheadZombie extends ZombieImplementation {
	
	public ConeheadZombie(int x, int y) {
		super(x,y,40,-0.8,7,Color.ORANGE);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new ConeheadZombie(x, y);
	}

}
