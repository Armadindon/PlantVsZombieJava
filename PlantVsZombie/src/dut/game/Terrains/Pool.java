package dut.game.Terrains;

import java.awt.Color;
import java.util.ArrayList;

import dut.game.zombie.BasicZombie;
import dut.game.zombie.Zombie;
import dut.game.zombie.pool.DolphinRiderZombie;
import dut.game.zombie.pool.DuckyTubeZombie;

public class Pool implements Terrain {
	public int hauteur = 6;
	public int largeur = 8;
	public int sunSpawnRate = 10000;
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	public Pool() {
		zombies.add(new DuckyTubeZombie(0, 0));
		zombies.add(new DolphinRiderZombie(0, 0));
	}
	

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
		return false;
	}

	@Override
	public boolean haveGraves() {
		return false;
	}
	
	@Override
	public Color getBackgroundColor() {
		return Color.white;
	}

	@Override
	public boolean haveWater() {
		return true;
	}
	
	
}
