package fr.dut.game;

import java.util.ArrayList;

public class GameData {
	private Cell[][] matrice;
	
	
	public GameData(int x , int y) {
		matrice = new Cell[x][y];
	}
	
	public void update() {
		for (Cell[] case1 : matrice) {
			for (Cell case2 : case1) {
				case2.update();
				case2.draw();
			}
		}
	}
	
	public boolean add(GameObject g,int x, int y) {
		try {
			matrice[x][y].add(g);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
