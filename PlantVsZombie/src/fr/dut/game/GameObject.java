package fr.dut.game;

public abstract class GameObject {
	int x;
	int y;
	
	public void draw() {
		
	}
	
	public boolean colliding(GameObject g) {
		return x==g.x && g.y == y; 
	}
	
	public void update() {
		
	}

}
