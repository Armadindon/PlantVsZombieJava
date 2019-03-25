package fr.dut.game;

import java.util.ArrayList;

public class Cell {
	private ArrayList<GameObject> lstItems;
	
	public Cell() {
		lstItems = new ArrayList<>();
	}
	
	public void add(GameObject g) {
		lstItems.add(g);
	}
	
	public void draw() {
		for (GameObject gameObject : lstItems) {
			gameObject.draw();
		}
	}
	
	public void update() {
		for (GameObject gameObject : lstItems) {
			gameObject.update();
		}
	}
}
