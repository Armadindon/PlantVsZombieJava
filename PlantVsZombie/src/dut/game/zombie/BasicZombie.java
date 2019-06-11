package dut.game.zombie;

import java.awt.Color;

public class BasicZombie extends ZombieImplementation{
	
	public BasicZombie(int x,int y) {
		super(x,y,40,-0.8,200,Color.gray);
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new BasicZombie(x, y);
	}
	
	@Override
	public String toString() {
		return "BasicZombie";
	}
}
