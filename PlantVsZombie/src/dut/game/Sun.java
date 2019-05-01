package dut.game;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;

public class Sun {
	private int x;
	private int y;
	private final int taille = 30;
	
	public Sun(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Float draw() {
		return new Ellipse2D.Float(x,y,taille,taille);
	}
	
	
	
}
