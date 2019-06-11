package dut.game.zombie.pool;

import java.awt.Color;

import dut.game.zombie.BasicZombie;
import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;

public class DuckyTubeZombie extends ZombieImplementation {
	
	public DuckyTubeZombie(int x,int y) {
		super(x, y, 40, -0.8, 200, Color.yellow);
	}
	
	@Override
	public boolean canFloat() {
		return true;
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new DuckyTubeZombie(x, y);
	}
	
	@Override
	public String toString() {
		return "DuckyTube";
	}
	
}
