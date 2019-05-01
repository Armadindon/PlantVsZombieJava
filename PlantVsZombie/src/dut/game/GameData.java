package dut.game;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;

public class GameData{
	private final Cell[][] matrix;
	private Coordinates selected;
	private final LinkedList<Zombie> lstZ;
	private final LinkedList<Plant> lstP;
	private final LinkedList<Bullet> lstB;
	private final LinkedList<Sun> lstS;
	private final int sunNumber = 0;
	private int zombieNumber[];
	private int nbZombies= 20;
	private int playerHealth = 3;
	private int alive = 0;
	private int choixPlante = -1;

	public GameData(int nbLines, int nbColumns) {
		matrix = new Cell[nbLines][nbColumns];
		lstZ = new LinkedList<>(); 
		lstP = new LinkedList<>(); 
		lstB = new LinkedList<>(); 
		lstS = new LinkedList<>(); 
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
		if (i >= 0 && i < matrix.length  || i == matrix.length+1 ) {
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

	public void updatePlant(GameView v) {
		ArrayList<Plant> deleted = new ArrayList<>();
		for(Plant p:lstP) {
			//on tire
			if(p.isFire(lstZ, v,zombieNumber)) {
				System.out.println("La plante "+p+" Tire / Explose !");
				if(p.bullet()!=null) {
					lstB.add(p.bullet());
				}
			}
			for(Zombie z:p.colliding(lstZ)) {
				p.addToHealth(-z.getDamage());
				z.setSpeed(0);
			}
			if(!(p.isAlive())) {
				deleted.add(p);
			}
		}

		lstP.removeAll(deleted);
	}

	public void updateZombie(GameView v,int width,int height) {
		ArrayList<Zombie> deleted = new ArrayList<>();
		for(Zombie z:lstZ) {
			z.move();
			z.setToInitialSpeed();
			if(z.matrixOut(v)) {
				deleted.add(z);
				System.out.println("Le zombie "+z+" est sorti de la matrice! On le suprimme");
				nbZombies--;
				alive--;
				playerHealth--;
				zombieNumber[v.lineFromY(z.getY())]--;
			}else {
				if(!(z.isAlive())) {
					deleted.add(z);
					System.out.println("Mort du zombie : "+z);
					zombieNumber[v.lineFromY(z.getY())]--;
					nbZombies--;
					alive--;	
				}
			}
		}
		if((int)(Math.random()*100)==5 && nbZombies-alive!=0) {
			int ligne =(int) (Math.random()*getNbLines());
			zombieNumber[ligne]+=1;
			int typeZombie = (int)(Math.random()*2);
			System.out.println(typeZombie);
			if (nbZombies-alive != 1) {
				switch (typeZombie) {
				case 0:
					lstZ.add(new BasicZombie(v.midCell((int) (width/4), 8,40),v.midCell((int) (height/4), ligne,40), 40));
					break;

				case 1:
					lstZ.add(new ConeheadZombie(v.midCell((int) (width/4), 8,40),v.midCell((int) (height/4), ligne,40), 40));
					break;
				}
			}else {
				lstZ.add(new FlagZombie(v.midCell((int) (width/4), 8,40),v.midCell((int) (height/4), ligne,40), 40));

			}

			System.out.println("Nouveau zombie ligne "+ligne);
			alive++;
		}
		lstZ.removeAll(deleted);
	}

	public void updateBullet(GameView v) {
		ArrayList<Bullet> deleted = new ArrayList<>();
		for(Bullet b:lstB) {
			b.move();
			if(b.matrixOut(v)) {
				deleted.add(b);
			}else {
				for(Zombie z: b.colliding(lstZ)) {
					z.addToHealth(-b.getDamage());
					z.setSpeed(0);
					deleted.add(b);
				}
			}
		}
		lstB.removeAll(deleted);
	}
	
	public boolean canPlant(int i,int j,GameView v) {
		for(Plant p: lstP) {
			if(p.collision(new Rectangle2D.Float(v.xFromI(i),v.yFromJ(j),v.getSquareSize(),v.getSquareSize()))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Updates the data contained in the GameData.
	 * Order :
	 * -Vérifie si c'est en dehors de la Matrice , si oui , on supprime et on le dit a l'utilisateur
	 * -On vérifie si une plante tire , si oui , on ajoute une Balle a lstB (Seulement si il y a un zombie sur la même ligne , voir le tableau zombieNumber[])
	 * -On vérifie si il y a des collisions des zombies , si oui , on enlève de la vie et on réduit la vitesse (sinon on remet a la vitesse initiale)
	 * -On ajoute un Zombie sur une ligne aléatoire et de manière aléatoire
	 * 
	 * On traite Dans l'ordre :
	 * -Plantes
	 * -Bullet
	 * -Zombies
	 */
	public void updateData(GameView v,int width , int height,Event event,boolean debug) {
		Point2D.Float location;
		updateZombie(v,width,height);
		updatePlant(v);
		updateBullet(v);
		
		if(playerHealth == 0) {
			System.out.println("Vous Avez Perdu !");
			System.exit(0);
		}else if(nbZombies == 0){
			System.out.println("Vous Avez Gagn� !");
			System.exit(0);
		}

		if ((event == null || event.getAction() != Action.POINTER_DOWN)&& !(debug) ) {
			return;
		}
		
		if(!(debug)) {
			location = event.getLocation();
			selectCell(v.lineFromY(location.y), v.columnFromX(location.x));
			//pour s�lectionner la plante
			if(v.lineFromY(location.y) == getNbLines()+1) {
				choixPlante = v.columnFromX(location.x);
			}

			if (v.lineFromY(location.y) >=0 && v.lineFromY(location.y) <getNbLines() && (v.columnFromX(location.x) >=0 && v.columnFromX(location.x) < getNbColumns())) {
				if(canPlant(v.columnFromX(location.x),v.lineFromY(location.y),v)) {

				switch (choixPlante) {
				case 0:
					addPlant(new Peashotter(v.midCell((int) (width/4), v.columnFromX(location.x),40), v.midCell((int) (height/4),v.lineFromY(location.y),40)));
					break;

				case 1:
					addPlant( new Wallnut(v.midCell((int) (width/4), v.columnFromX(location.x),50), v.midCell((int) (height/4),v.lineFromY(location.y),50)));
					break;

				case 2:
					addPlant( new CherryBomb(v.midCell((int) (width/4), v.columnFromX(location.x),50), v.midCell((int) (height/4),v.lineFromY(location.y),50)));
					break;	
				}
				choixPlante=-1;
				}
			}
		} else {
			if((int)(Math.random()*100)==5) {
				choixPlante = (int) (Math.random()*3);
				System.out.println(choixPlante);
				switch (choixPlante) {
				case 0:
					addPlant(new Peashotter(v.midCell((int) (width/4), (int)(Math.random()*getNbColumns()),40), v.midCell((int) (height/4),(int)(Math.random()*getNbLines()),40)));
					System.out.println("Ajout Plante");
					break;

				case 1:
					addPlant(new Wallnut(v.midCell((int) (width/4),(int)(Math.random()*getNbColumns()),50), v.midCell((int) (height/4),(int)(Math.random()*getNbLines()),50)));
					System.out.println("Ajout Plante");
					break;

				case 2:
					addPlant(new CherryBomb(v.midCell((int) (width/4), (int)(Math.random()*getNbColumns()),50), v.midCell((int) (height/4),(int)(Math.random()*getNbLines()),50)));
					System.out.println("Ajout Plante");
					break;	
				}
			}
			
		}
		

		

	}

	public void addPlant(Plant p) {
		lstP.add(p);
	}



	public void updateAll() {

	}

	public LinkedList<Zombie> getLstZ() {
		return lstZ;
	}

	public LinkedList<Bullet> getLstB() {
		return lstB;
	}

	public LinkedList<Plant> getLstP() {
		return lstP;
	}

	public int getChoixPlante() {
		return choixPlante;
	}
}
