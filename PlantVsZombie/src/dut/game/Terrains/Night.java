package dut.game.Terrains;

import java.util.ArrayList;

import dut.game.zombie.Zombie;

public class Night implements Terrain {

	public int hauteur = 5;
	public int largeur = 8;
	public int sunSpawnRate = 10000;
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public int getLargeur() {
		return largeur;
	}
	
	 @Override
	public int getSunSpawnRate() {
		return sunSpawnRate;
	}
	 
	
	 @Override
		public ArrayList<Zombie> getZombies() {
			return zombies;
		}

	@Override
	public boolean mushrooms() {
		return true;
	}


}
