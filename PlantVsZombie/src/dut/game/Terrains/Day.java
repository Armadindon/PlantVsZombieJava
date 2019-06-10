package dut.game.Terrains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import dut.game.zombie.BasicZombie;
import dut.game.zombie.BucketheadZombie;
import dut.game.zombie.ConeheadZombie;
import dut.game.zombie.NewspaperZombie;
import dut.game.zombie.Zombie;
import dut.game.zombie.poleVaultingZombie;

public class Day implements Terrain {
	private int hauteur = 5;
	private int largeur = 8;
	private int sunSpawnRate = 10000;
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	public Day() {
		zombies.add(new BasicZombie(0, 0));
		zombies.add(new ConeheadZombie(0, 0));
		zombies.add(new poleVaultingZombie(0, 0));
		zombies.add(new BucketheadZombie(0, 0));
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

}
