package dut.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameData{
	private final Cell[][] matrix;
	private Coordinates selected;
	private final LinkedList<GameObject> lstG;
	private int zombieNumber[];

	public GameData(int nbLines, int nbColumns) {
		matrix = new Cell[nbLines][nbColumns];
		lstG = new LinkedList<>(); 
		zombieNumber = new int[nbLines];
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
		StringBuilder st = new StringBuilder();
		GameObject col;
		LinkedList<GameObject> deleted= new LinkedList<>();
		LinkedList<GameObject> added= new LinkedList<>();
		for(GameObject g : lstG) {

			if (g.matrixOut(v)) {
				deleted.add(g);
				st.append(g).append(" est supprimé , il est sorti de la matrice\n");
				if(g instanceof Zombie) {
					zombieNumber[v.lineFromY(g.getY())]--;
				}
			}

			if(g instanceof Plant) {
				Plant p = (Plant) g;
				if(p.isFire()) {
					if(zombieNumber[v.lineFromY(p.getY())] !=0) {
						added.add(p.bullet());
						st.append(g).append(" Tire une balle !\n");
					}else {
						p.decrementCompteur();
					}
					
				}
			}
			
			if(zombieNumber[v.lineFromY(g.getY())] !=0) {
				col = g.colliding(lstG);
				if(col!=null) {
					if (!(g instanceof Bullet && col instanceof Plant)) {
						col.addToHealth(-g.getDamage());
						if(g instanceof Bullet) {
							deleted.add(g);
						}
					}
					
				}
			}
			
			if(!(g.isAlive())) {
				deleted.add(g);
				if (g instanceof Zombie) {
					Zombie z = (Zombie) g;
					zombieNumber[v.lineFromY(z.getY())]-=1;
				}
			}
			
		}
		lstG.removeAll(deleted);
		lstG.addAll(added);
		if ((int)(Math.random()*50)==5) {
			int ligne =(int) (Math.random()*5);
			zombieNumber[ligne]+=1;
			this.addGameObject(new BasicZombie(v.midCell((int) (width/4), 8,40),v.midCell((int) (height/4), ligne,40), 40));
			st.append("Nouveau zombie ligne").append(ligne).append("\n");
		}
		System.out.print(st.toString());
	}
	
	public void addGameObject(GameObject g) {
		lstG.add(g);
	}
	
	public LinkedList<GameObject> getLstG(){
		return lstG;
	}
	
	public void updateAll() {
		
	}
}
