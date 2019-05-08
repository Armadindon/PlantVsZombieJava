package dut.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.List;

import dut.game.plant.CherryBomb;
import dut.game.plant.Peashotter;
import dut.game.plant.Plant;
import dut.game.plant.SunFlower;
import dut.game.plant.Wallnut;
import dut.game.zombie.Zombie;
import fr.umlv.zen5.ScreenInfo;


public class GameView implements GameDrawer {
	private final int xOrigin;
	private final int yOrigin;
	private final int width;
	private final int length;
	private final int squareSize;

	private GameView(int xOrigin, int yOrigin, int length, int width, int squareSize) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.length = length;
		this.width = width;
		this.squareSize = squareSize;
	}
	

	public static GameView initGameGraphics(int xOrigin, int yOrigin, int length, GameData data) {
		int squareSize = (int) (length * 1.0 / data.getNbLines());
		return new GameView(xOrigin, yOrigin, length, data.getNbColumns() * squareSize, squareSize);
	}

	private int indexFromReaCoord(float coord, int origin) { // attention, il manque des test de validit√© des coordonn√©es!
		return (int) ((coord - origin) / squareSize);
	}

	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int lineFromY(float y) {
		return indexFromReaCoord(y, yOrigin);
	}

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int columnFromX(float x) {
		return indexFromReaCoord(x, xOrigin);
	}

	private float realCoordFromIndex(int index, int origin) {
		return origin + index * squareSize;
	}
	
	/**
	 * Return the column corresponding to the x coordinates given
	 * @param x
	 * @return column (int)
	 */
	public float xFromI(int i) {
		return realCoordFromIndex(i, xOrigin);
	}
	
	/**
	 * Return the line corresponding to the y coordinates given
	 * @param y
	 * @return column (int)
	 */
	public float yFromJ(int j) {
		return realCoordFromIndex(j, yOrigin);
	}

	private RectangularShape drawCell(int i, int j) {
		return new Rectangle2D.Float(xFromI(j) + 2, yFromJ(i) + 2, squareSize - 4, squareSize - 4);
	}

	private RectangularShape drawSelectedCell(int i, int j) {
		return new Rectangle2D.Float(xFromI(j), yFromJ(i), squareSize, squareSize);
	}

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
	@Override
	public void draw(Graphics2D graphics, GameData data,ScreenInfo screen,int choixPlante) {
		
		
		graphics.setColor(Color.WHITE);
		graphics.fill(new Rectangle2D.Float(0, 0, screen.getWidth(), screen.getHeight()));//on r√©affiche le fond
		graphics.setColor(Color.GREEN);
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));
		graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin+squareSize*(data.getNbLines()+1), width,squareSize));
		
		//on affiche la cellule selectionnÈe
		Coordinates c = data.getSelected();
		if (c != null) {
			graphics.setColor(Color.black);
			graphics.fill(drawSelectedCell(c.getI(), c.getJ()));
			graphics.setColor(Color.green);
			graphics.fill(drawCell(c.getI(),  c.getJ()));
		}
		
		//on affiche le selecteur
		graphics.setFont(new Font("Regular",0, 20));
		
		List<Plant> selectedPlant = data.getSelectedPlant();
		for(int i=0;i<data.getSelectedPlant().size();i++) {
			if(data.canPlant(100, 100, this, i)) {
				graphics.setColor(selectedPlant.get(i).getColor());
			}else {
				graphics.setColor(Color.GRAY);
			}
			int size = selectedPlant.get(i).getSize();
			graphics.fill(selectedPlant.get(i).instantiateFlower(midCell(xOrigin,i,size), midCell(yOrigin,data.getNbLines()+1,size)).draw());
			graphics.setColor(Color.black);
			graphics.drawString(selectedPlant.get(i).getCost().toString(), xOrigin+(squareSize*i)+5, yOrigin+(squareSize*(data.getNbLines()+2))-5);
		}
		
		
		
		
		//on affiche la plante selectionnÈe
		if(choixPlante!=-1) {
			graphics.setColor(selectedPlant.get(choixPlante).getColor());
			graphics.fill(selectedPlant.get(choixPlante).instantiateFlower(midCell(xOrigin,0,40), midCell(yOrigin,-1,40)).draw());
		}
		
		//on affiche le nombre de soleils
		graphics.setColor(Color.YELLOW);
		graphics.fill(new Sun(xOrigin+squareSize*(data.getNbColumns()-1)+squareSize/3,yOrigin-35,0).draw());
		graphics.setColor(Color.BLACK);
		graphics.drawString(data.getSunNumber().toString(), xOrigin+squareSize*data.getNbColumns()-30, yOrigin-10);
		
		
		graphics.setColor(Color.WHITE);
		for (int i = 0; i <= data.getNbLines()+2; i++) {
			graphics.draw(
					new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + width, yOrigin + i * squareSize));
		}

		for (int i = 0; i <= data.getNbColumns(); i++) {
			graphics.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
			graphics.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin+squareSize*(data.getNbLines()+1), xOrigin + i * squareSize, yOrigin+squareSize*(data.getNbLines()+2)));
		}

		
		for (int i = 0; i < data.getNbLines(); i++) {
			for (int j = 0; j < data.getNbColumns(); j++) {
				graphics.setColor(Color.GREEN);
				graphics.fill(drawCell(i, j));
				graphics.setColor(data.getCellColor(i, j));
			}
		}
		

	}

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param x        the float x-coordinate of the cell.
	 * @param y        the float y-coordinate of the cell.
	 */
	@Override
	public void drawOnlyOneCell(Graphics2D graphics, GameData data, int x, int y) {
		graphics.setColor(Color.BLACK);
		graphics.fill(new Rectangle2D.Float(x, y, 10, 10));
	}

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 * @param moving   the moving element.
	 */

	
	public void moveAllAndDraw(Graphics2D graphics,GameData data) {
		for(Zombie g :data.getLstZ()){
			if(g.matrixOut(this)) {
				graphics.setColor(graphics.getBackground());
			}else {
				graphics.setColor(Color.GREEN);
			}
			graphics.setColor(g.getColor());
			graphics.fill(g.draw());
		}
		for(Plant g :data.getLstP()){
			graphics.fill(g.draw());
			graphics.setColor(g.getColor());
			graphics.fill(g.draw());
		}
		for(Bullet g :data.getLstB()){
			g.move();
			graphics.setColor(g.getColor());
			graphics.fill(g.draw());
		}
		graphics.setColor(Color.YELLOW);
		for (Sun s:data.getLstS()) {
			graphics.fill(s.draw());
		}
		
		for(LawnMower l :data.getLstL()) {
			if (l!=null) {
				graphics.setColor(l.getColor());
				graphics.fill(l.draw());
			}
		}
		
	}
	
	
	/**
	 * Give the coordinate for placing a new object of size taille to the given index
	 * @param origin
	 * @param index
	 * @param taille
	 * @return
	 */
	public int midCell(int origin , int index,int taille) {
		return (int) (realCoordFromIndex(index, origin)+(squareSize-taille)/2);
	}
	
	/**
	 * Check if 
	 * @param x
	 * @return
	 */
	public boolean isOut(int x) {
		return x<xOrigin || x>width+xOrigin;
	}
	
	public boolean isOut2(int x) {
		return x>width+xOrigin;
	}
	public boolean isOutZombie(int x) {
		return x<xOrigin;
	}
	
	public int getSquareSize() {
		return squareSize;
	}
	
}
