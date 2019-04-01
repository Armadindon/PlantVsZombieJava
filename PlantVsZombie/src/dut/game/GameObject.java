package dut.game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface GameObject {
	public void move();

	public Rectangle2D.Float draw();
	
	public GameObject colliding(ArrayList<GameObject> lst);
	public boolean collision(Rectangle2D r);
	public boolean isAlive();
	public Color getColor();
	public boolean matrixOut(GameView v);
}
