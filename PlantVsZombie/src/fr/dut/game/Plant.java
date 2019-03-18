package fr.dut.game;

public abstract class Plant implements GameObjects {
	private double x;
	private double y;
	private int freq;
	private Projectile ammo;
	
	public Plant(double x , double y , int freq,Projectile ammo) {
		this.x =x;
		this.y = y;
		this.freq = freq;
		this.ammo = ammo;
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
