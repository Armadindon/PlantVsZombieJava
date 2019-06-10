package dut.game.zombie.night;

import java.awt.Color;

import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;
import dut.game.zombie.day.BucketheadZombie;

public class FootballZombie extends ZombieImplementation {
	
	 public FootballZombie(int x,int y) {
		super(x,y,40,-1.2,1600,Color.red);
	}
	 
	 @Override
		public Zombie instantiateZombie(int x,int y) {
			return new FootballZombie(x, y);
		}
}
