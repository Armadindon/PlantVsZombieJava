package dut.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

import dut.game.Terrains.Day;
import dut.game.Terrains.Night;
import dut.game.Terrains.Pool;
import dut.game.Terrains.Terrain;
import dut.game.plant.Plant;
import dut.game.plant.Day.CherryBomb;
import dut.game.plant.Day.Chomper;
import dut.game.plant.Day.Peashotter;
import dut.game.plant.Day.PotatoMine;
import dut.game.plant.Day.Repeatter;
import dut.game.plant.Day.SnowPea;
import dut.game.plant.Day.SunFlower;
import dut.game.plant.Day.Wallnut;
import dut.game.plant.Night.FumeShroom;
import dut.game.plant.Night.GraveBuster;
import dut.game.plant.Night.PuffShroom;
import dut.game.plant.Night.SunShroom;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class PlantSelector {
	private static ArrayList<Plant> selected = new ArrayList<Plant>();
	private static ArrayList<Plant> available = new ArrayList<Plant>();

	public static ArrayList<Plant> selector(ApplicationContext context,ScreenInfo screen) {
		Event event;
		
		//On ajoute les plantes
		available.add(new CherryBomb(0, 0));
		available.add(new Chomper(0, 0));
		available.add(new Peashotter(0, 0));
		available.add(new PotatoMine(0, 0));
		available.add(new Repeatter(0, 0));
		available.add(new SnowPea(0, 0));
		available.add(new SunFlower(0, 0));
		available.add(new Wallnut(0, 0));
		available.add(new FumeShroom(0, 0));
		available.add(new GraveBuster(0, 0));
		available.add(new PuffShroom(0, 0));
		available.add(new SunShroom(0, 0));
		
		while(true) {
			context.renderFrame(graphics->drawSelector(graphics, screen,(int) (screen.getWidth()/4), (int) (screen.getHeight()/4), (int) screen.getHeight()/2));
			
			event = context.pollOrWaitEvent(50); // modifier pour avoir un affichage fluide

			if (event == null) { // no event
				continue;
			}

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				break;
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}
			
		}
		
		System.exit(0);
		return null;//jamais atteint, présent pour faire plaisir a eclipse
	}

	private static void drawSelector(Graphics2D g,ScreenInfo sc, int xOrigin, int yOrigin, int length) {
		int squareSize = (int) (length * 1.0 / 5);
		
		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Float(0, 0, sc.getWidth(), sc.getHeight()));//on rÃ©affiche le fond
		g.setFont(new Font("regular",0,40));
		
		g.setColor(Color.green);
		g.fill(new Rectangle2D.Float(xOrigin,yOrigin,squareSize*8,length));
		g.fill(new Rectangle2D.Float(xOrigin, yOrigin+squareSize*6, squareSize*8,squareSize));
		
		g.setColor(Color.black);
		
		for (int i = 0; i <= 5+2; i++) {
			g.draw(new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + squareSize*8, yOrigin + i * squareSize));
		}
		for (int i = 0; i <= 8; i++) {
			g.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
			g.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin+squareSize*(5+1), xOrigin + i * squareSize, yOrigin+squareSize*(5+2)));
		}
		
		
	}
}
