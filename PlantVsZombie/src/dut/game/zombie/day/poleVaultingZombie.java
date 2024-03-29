package dut.game.zombie.day;

import java.awt.Color;
import dut.game.zombie.Zombie;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;
import dut.game.zombie.ZombieImplementation;

public class poleVaultingZombie extends ZombieImplementation {
	private boolean jumped =false;
	
	public poleVaultingZombie(int x, int y) {
		super(x, y, 40, -1.2, 340, Color.red);
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
		return new poleVaultingZombie(x, y);
	}

}
