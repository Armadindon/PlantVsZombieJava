package dut.game;

import java.awt.Color;

public class Wallnut extends Plant {
	
	public Wallnut(int x, int y) {
		super(x,y,50,20,50,Color.YELLOW);
	}
	
	@Override
	public boolean isFire() {
		return false;
	}

}
