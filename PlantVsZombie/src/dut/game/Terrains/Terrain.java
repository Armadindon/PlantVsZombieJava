package dut.game.Terrains;

import java.util.ArrayList;

import dut.game.zombie.Zombie;

public interface Terrain {
	public int getHauteur();
	public int getLargeur();
	public int getSunSpawnRate();
	public ArrayList<Zombie> getZombies();
}
