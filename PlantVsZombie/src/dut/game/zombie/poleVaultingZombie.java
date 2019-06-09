package dut.game.zombie;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import dut.game.GameView;
import dut.game.plant.Plant;

public class poleVaultingZombie extends ZombieImplementation {
	private boolean jumped =false;
	
	public poleVaultingZombie(int x, int y) {
		super(x, y, 40, -1.2, 4, Color.red);
	}
	

	@Override
	public void special(LinkedList<Plant> lstP,GameView v) {
		if(!(jumped)) {
			boolean jumping = false;
			for(Plant p:lstP) {
				if(p.collision(draw())) {
					System.out.println("Jumping");
					jumping = true;
					break;
				}
			}
			if(jumping) {
				jump(v);
				jumped = true;
			}
		}else if(colliding(lstP).size()==0) {
			setSpeed(-0.8);
		}
	}

}