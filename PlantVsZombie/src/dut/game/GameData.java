package dut.game;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dut.game.plant.CherryBomb;
import dut.game.plant.Peashotter;
import dut.game.plant.Plant;
import dut.game.plant.SunFlower;
import dut.game.plant.Wallnut;
import dut.game.zombie.BasicZombie;
import dut.game.zombie.ConeheadZombie;
import dut.game.zombie.FlagZombie;
import dut.game.zombie.Zombie;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;

public class GameData{
	private final Cell[][] matrix;
	private Coordinates selected;
	private final LinkedList<Zombie> lstZ;
	private final LinkedList<Plant> lstP;
	private final LinkedList<Bullet> lstB;
	private final LinkedList<Sun> lstS;
	private final ArrayList<LawnMower> lstL;
	private int LawnMowerNb[];
	private int sunNumber = 0;
	private int zombieNumber[];
	private int nbZombies= 20;
	private int alive = 0;
	private int choixPlante = -1;
	private int respawnTime[] = {-1,-1,-1,-1};
	private int compteur = 0;
	private final LinkedList<Plant> selectedPlant = new LinkedList<Plant>();

	public GameData(int nbLines, int nbColumns) {
		matrix = new Cell[nbLines][nbColumns];
		lstZ = new LinkedList<>(); 
		lstP = new LinkedList<>(); 
		lstB = new LinkedList<>(); 
		lstS = new LinkedList<>(); 
		lstL = new ArrayList<LawnMower>();
		
		zombieNumber = new int[nbLines];
		LawnMowerNb = new int[nbLines];
		for (int i=0;i<nbLines;i++) {
			LawnMowerNb[i]=1;
		}
		selectedPlant.add(new Peashotter(0, 0));
		selectedPlant.add(new CherryBomb(0, 0));
		selectedPlant.add(new Wallnut(0, 0));
		selectedPlant.add(new SunFlower(0, 0));

	}
	
	public void initLawnMower(GameView v,int width,int height) {
		for (int i=0;i<getNbLines();i++) {
			lstL.add(new LawnMower(v.midCell((int) (width/4), -1, 50),v.midCell((int) (height/4), i, 50)));
		}
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
				if(p.bullet(this)!=null) {
					lstB.add(p.bullet(this));
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
	
	public void updateLawnMower(GameView v) {
		ArrayList<Integer> deleted = new ArrayList<>();
		for (LawnMower l: lstL) {
			if(l!=null && l.isRunning()) {
				ArrayList<Zombie> colliding = l.colliding(lstZ);
				l.move();
				if(colliding.size()!=0) {
					lstZ.removeAll(colliding);
				}
				if(l.matrixOut(v)) {
					deleted.add(v.lineFromY(l.getY()));
				}
			}
		}
		for(int i:deleted) {
			lstL.set(i, null);
		}
	}
	
	public void planterPlante(int i,int j,GameView v,int choixPlante,double width , double height,boolean debug) {
		if(canPlant(i,j,v,choixPlante) || debug) {
			int size = selectedPlant.get(choixPlante).getSize();
			addPlant(selectedPlant.get(choixPlante).instantiateFlower(v.midCell((int) (width/4), i,size),  v.midCell((int) (height/4),j,size)));

			choixPlante=-1;
			}else {choixPlante = -1;}
		
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
				if(LawnMowerNb[v.lineFromY(z.getY())]==1) {
					System.out.println("Tondeuse lanc�e en "+v.lineFromY(z.getY()));
					lstL.get(v.lineFromY(z.getY())).run();
					LawnMowerNb[v.lineFromY(z.getY())]=0;
				}else {
					System.out.println("Perdu");
					System.exit(0);
				}
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
		if((int)(Math.random()*200)==5 && nbZombies-alive!=0) {
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
	
	public boolean canPlant(int i,int j,GameView v,int choixPlante) {
		if(choixPlante==-1) {
			return false;
		}
		int prix=selectedPlant.get(choixPlante).getCost();
		int respawn=selectedPlant.get(i).getRespawnTime();
		
		if(sunNumber < prix) {
			System.out.println("Pas assez de soleils : "+sunNumber+"<"+prix);
			return false;
		}
		if(respawnTime[choixPlante] != -1 && respawnTime[choixPlante]+respawn > compteur ) {
			System.out.println("Temps de recharge non termin�");
			System.out.println((respawnTime[choixPlante]+respawn)+" "+compteur);
			return false;
		}
		for(Plant p: lstP) {
			if(p.collision(new Rectangle2D.Float(v.xFromI(i),v.yFromJ(j),v.getSquareSize(),v.getSquareSize()))) {
				return false;
			}
		}
		respawnTime[choixPlante] = compteur;
		sunNumber -= prix;
		return true;
	}
	
	public void clickOnSun (int i,int j,GameView v) {
		LinkedList<Sun> deleted = new LinkedList<Sun>();
		for(Sun s: lstS) {
			if(s.draw().getBounds2D().intersects(new Rectangle2D.Float(v.xFromI(i),v.yFromJ(j),v.getSquareSize(),v.getSquareSize()))) {
				System.out.println("Ramasse un soleil");
				sunNumber++;
				deleted.add(s);
				choixPlante = -1;
			}
		}
		lstS.removeAll(deleted);
	}
	
	public void updateSun(GameView v,int width,int height) {
		
		if((int)(Math.random()*100) == 5) {
			int i = (int)(Math.random()*getNbColumns());
			int j = (int)(Math.random()*getNbLines());
			System.out.println("Soleil en "+i+","+j);
			lstS.add(new Sun(v.midCell((int) (width/4),i , 30),v.midCell((int) (height/4),j , 30)));
		}
		
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
		compteur++;
		Point2D.Float location;
		updateZombie(v,width,height);
		updatePlant(v);
		updateBullet(v);
		updateSun(v,width,height);
		updateLawnMower(v);
		

		if(nbZombies == 0){
			System.out.println("Vous Avez Gagn� !");
			System.exit(0);
		}

		if ((event == null || event.getAction() != Action.POINTER_DOWN)&& !(debug) ) {
			return;
		}
		
		if(!(debug)) {
			location = event.getLocation();
			clickOnSun(v.columnFromX(location.x), v.lineFromY(location.y), v);
			selectCell(v.lineFromY(location.y), v.columnFromX(location.x));
			//pour s�lectionner la plante
			if(v.lineFromY(location.y) == getNbLines()+1) {
				choixPlante = v.columnFromX(location.x);
			}

			if (v.lineFromY(location.y) >=0 && v.lineFromY(location.y) <getNbLines() && (v.columnFromX(location.x) >=0 && v.columnFromX(location.x) < getNbColumns())) {
				planterPlante(v.columnFromX(location.x), v.lineFromY(location.y), v, choixPlante, width, height,debug);
			}
		} else {
			if((int)(Math.random()*100)==5) {
				choixPlante = (int) (Math.random()*3);
				planterPlante((int)(Math.random()*getNbColumns()), (int)(Math.random()*getNbLines()), v, choixPlante, width, height,debug);
			}
			
		}
		
		

	}

	public void addPlant(Plant p) {
		lstP.add(p);
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
	
	public LinkedList<Sun> getLstS() {
		return lstS;
	}
	
	public ArrayList<LawnMower> getLstL() {
		return lstL;
	}
	
	public void addSun(Sun s) {
		lstS.add(s);
	}
	
	public List<Plant> getSelectedPlant() {
		return Collections.unmodifiableList(selectedPlant);
	}
	
}
