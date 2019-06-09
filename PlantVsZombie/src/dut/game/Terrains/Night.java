package dut.game.Terrains;

public class Night implements Terrain {

	public int hauteur = 5;
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