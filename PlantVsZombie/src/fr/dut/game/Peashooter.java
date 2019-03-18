package fr.dut.game;

public class Peashooter extends Plant {
	
	public Peashooter(double x , double y) {
		super(x,y,250,new Projectile(x,y,0.05));
	}
}
