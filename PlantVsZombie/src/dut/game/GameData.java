package dut.game;

import java.awt.Color;
import java.util.ArrayList;

public class GameData{
	private final Cell[][] matrix;
	private Coordinates selected;
	private final ArrayList<GameObject> lstG;

	public GameData(int nbLines, int nbColumns) {
		matrix = new Cell[nbLines][nbColumns];
		lstG = new ArrayList<>(); 
	}

	/**
	 * The number of lines in the matrix contained in this GameData.
	 * @return the number of lines in the matrix.
	 */
	public int getNbLines() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix contained in this GameData.
	 * @return the number of columns in the matrix.
	 */
	public int getNbColumns() {
		return matrix[0].length;
	}
	
	/**
	 * The color of the cell specified by its coordinates.
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @return the color of the cell specified by its coordinates
	 */
	public Color getCellColor(int i, int j) {
		return matrix[i][j].getColor();
	}
	
	/**
	 * The value of the cell specified by its coordinates.
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @return the value of the cell specified by its coordinates
	 */
	public int getCellValue(int i, int j) {
		return matrix[i][j].getValue();
	}
	
	/**
	 * The coordinates of the cell selected, if a cell is selected.
	 * @return the coordinates of the selected cell; null otherwise.
	 */
	public Coordinates getSelected() {
		return selected;
	}

	/**
	 * Tests if at least one cell is selected.
	 * @return true if and only if at least one cell is selected; false otherwise.
	 */
	public boolean hasASelectedCell() {
		return selected != null;
	}

	/**
	 * Selects, as the first cell, the one identified by the specified coordinates.
	 * @param i the first coordinate of the cell.
	 * @param j the second coordinate of the cell.
	 * @throws IllegalStateException if a first cell is already selected.
	 */
	public void selectCell(int i, int j) {
		if (selected != null) {
			throw new IllegalStateException("First cell already selected");
		}
		if (i >= 0 && i < matrix.length ) {
			if (j >= 0 && j < matrix[0].length) {
				selected = new Coordinates(i, j);
			}
		}
		
	}

	/**
	 * Unselects both the first and the second cell (whether they were selected or not).
	 */
	public void unselect() {
		selected = null;
	}

	public void setRandomMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = Cell.randomGameCell();
			}
		}
	}
	
	/**
	 * Updates the data contained in the GameData.
	 */
	public void updateData(GameView v,int width , int height) {
		ArrayList<GameObject> deleted= new ArrayList<>();
		ArrayList<GameObject> added= new ArrayList<>();
		for(GameObject g : lstG) {

			if (g.matrixOut(v)) {
				deleted.add(g);
				System.out.println(g+" est supprimé , il est sorti de la matrice");
			}

			if(g instanceof Plant) {
				Plant p = (Plant) g;
				if(p.isFire()) {
					added.add(p.bullet());
					System.out.println(g+" Tire une balle !");
				}
			}
			
			
		}
		lstG.removeAll(deleted);
		lstG.addAll(added);
		if ((int)(Math.random()*100)==5) {
			int ligne =(int) (Math.random()*5);
			this.addGameObject(new BasicZombie(v.midCell((int) (width/4), 8,40),v.midCell((int) (height/4), ligne,40), 40));
			System.out.println("Nouveau zombie ligne "+ligne);
		}
	}
	
	public void addGameObject(GameObject g) {
		lstG.add(g);
	}
	
	public ArrayList<GameObject> getLstG(){
		return lstG;
	}
	
	public void updateAll() {
		
	}
}
