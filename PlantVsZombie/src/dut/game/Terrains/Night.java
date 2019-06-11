package dut.game.Terrains;

import java.awt.Color;
import java.util.ArrayList;

import dut.game.zombie.BasicZombie;
import dut.game.zombie.Zombie;
import dut.game.zombie.night.FootballZombie;
import dut.game.zombie.night.NewspaperZombie;
import dut.game.zombie.night.ScreenDoorZombie;

public class Night implements Terrain {

	public int hauteur = 5;
	public int largeur = 8;
	public int sunSpawnRate = Integer.MAX_VALUE;//les soleils ne tombent pas du ciel
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	public Night() {
		zombies.add(new NewspaperZombie(0, 0));
		zombies.add(new ScreenDoorZombie(0, 0));
		zombies.add(new FootballZombie(0, 0));
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
		return true;
	}

	@Override
	public boolean haveGraves() {
		return true;
	}
	
	@Override
	public Color getBackgroundColor() {
		return new Color(51, 51, 0);
	}


	@Override
	public boolean haveWater() {
		return false;
	}


}
