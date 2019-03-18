package fr.dut.game;

public class Projectile implements GameObjects {
	private double x;
	private double y;
	private double speed;
	
	public Projectile(double x, double y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean collision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
