package dut.game;

import java.awt.Graphics2D;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.ScreenInfo;

/**
 * The GameView class is in charge of the graphical view of a clicky game.
 */
public interface GameDrawer {
	/**
	 * Transforms a real y-coordinate into the index of the corresponding line.
	 * 
	 * @param y a float y-coordinate
	 * @return the index of the corresponding line.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int lineFromY(float y);

	/**
	 * Transforms a real x-coordinate into the index of the corresponding column.
	 * 
	 * @param x a float x-coordinate
	 * @return the index of the corresponding column.
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int columnFromX(float x);

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
	public void draw(Graphics2D graphics, GameData data,ScreenInfo screen,int choixPlante);

	/**
	 * Draws the game board from its data, using an existing
	 * {@code ApplicationContext}.
	 * 
	 * @param context the {@code ApplicationContext} of the game
	 * @param data    the GameData containing the game data.
	 */
	public default void draw(ApplicationContext context, GameData data,ScreenInfo screen,int choixPlante) {
		context.renderFrame(graphics -> draw(graphics, data,screen,choixPlante));
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
	public void drawOnlyOneCell(Graphics2D graphics, GameData data, int x, int y);

	/**
	 * Draws only the cell specified by the given coordinates in the game board from
	 * its data, using an existing {@code ApplicationContext}.
	 * 
	 * @param context the {@code ApplicationContext} of the game
	 * @param data    the GameData containing the game data.
	 * @param x       the float x-coordinate of the cell.
	 * @param y       the float y-coordinate of the cell.
	 */
	public default void drawOnlyOneCell(ApplicationContext context, GameData data, int x, int y) {
		context.renderFrame(graphics -> drawOnlyOneCell(graphics, data, x, y));
	}
	
	public void moveAllAndDraw(Graphics2D graphics,GameData data);
	public default void moveAllAndDraw(ApplicationContext context, GameData data) {
		context.renderFrame(graphics -> moveAllAndDraw(graphics, data));
	}

}
