package dut.game.zombie.pool;

import java.awt.Color;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.zombie.Zombie;
import dut.game.zombie.ZombieImplementation;
import dut.game.zombie.day.poleVaultingZombie;

public class DolphinRiderZombie extends ZombieImplementation {

	private boolean jumped =false;
	
	public DolphinRiderZombie(int x, int y) {
		super(x, y, 40, -1.2, 340, Color.gray);
	}
	

	@Override
	public void special(LinkedList<Plant> lstP,GameView v) {
		if(!(jumped)) {
			boolean jumping = false;
			for(Plant p:lstP) {
				if(p.collision(draw()) && !p.isTall() && p.isHitable()) {
					System.out.println("Jumping");
					jumping = true;
					break;
				}
			}
			if(jumping) {
				jump(v);
				jumped = true;
			}
		}else if(collidingP(lstP).size()==0) {
			setSpeed(-0.8);
		}
	}
	
	@Override
	public Zombie instantiateZombie(int x,int y) {
		return new DolphinRiderZombie(x, y);
	}
	
	@Override
	public boolean canFloat() {
		return true;
	}
}
