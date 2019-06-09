package dut.game.Terrains;

public class Pool implements Terrain {
	public int hauteur = 6;
	public int largeur = 8;
	public int sunSpawnRate = 10000;
	

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
}
