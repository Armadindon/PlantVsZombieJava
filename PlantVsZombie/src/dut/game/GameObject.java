package dut.game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface GameObject {
	public void move();

	public Rectangle2D.Float draw();
	
	public GameObject colliding(ArrayList<GameObject> lst);
	public boolean collision(Rectangle2D r);
}
