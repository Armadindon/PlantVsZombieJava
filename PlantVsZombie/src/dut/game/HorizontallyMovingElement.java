package dut.game;

import java.awt.geom.Rectangle2D;

public class HorizontallyMovingElement implements MovingElement{
	private int x;
	private int y;
	private final int speed;

	public HorizontallyMovingElement(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	@Override
	public void move() {
		x += speed;
	}
	
	@Override
	public Rectangle2D.Float draw(){
		return new Rectangle2D.Float(x, y, 10, 10);
	}
}
