package dut.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import java.util.Arrays;

import dut.game.plant.Plant;
import dut.game.plant.Day.CherryBomb;
import dut.game.plant.Day.Chomper;
import dut.game.plant.Day.Peashotter;
import dut.game.plant.Day.PotatoMine;
import dut.game.plant.Day.Repeatter;
import dut.game.plant.Day.SnowPea;
import dut.game.plant.Day.SunFlower;
import dut.game.plant.Day.Wallnut;
import dut.game.plant.Night.DoomShroom;
import dut.game.plant.Night.FumeShroom;
import dut.game.plant.Night.GraveBuster;
import dut.game.plant.Night.HypnoShroom;
import dut.game.plant.Night.IceShroom;
import dut.game.plant.Night.PuffShroom;
import dut.game.plant.Night.ScaredyShroom;
import dut.game.plant.Night.SunShroom;
import dut.game.plant.pool.Jalapeno;
import dut.game.plant.pool.LilyPad;
import dut.game.plant.pool.SpikeWeed;
import dut.game.plant.pool.Squash;
import dut.game.plant.pool.TallNut;
import dut.game.plant.pool.TangleKelp;
import dut.game.plant.pool.Threepeater;
import dut.game.plant.pool.TorchWood;
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
		available.add(new HypnoShroom(0, 0));
		available.add(new ScaredyShroom(0, 0));
		available.add(new IceShroom(0, 0));
		available.add(new DoomShroom(0, 0));
		available.add(new LilyPad(0, 0));
		available.add(new Threepeater(0, 0));
		available.add(new TangleKelp(0, 0));
		available.add(new TallNut(0, 0));
		available.add(new Squash(0, 0));
		available.add(new Jalapeno(0, 0));
		available.add(new SpikeWeed(0, 0));
		available.add(new TorchWood(0, 0));

		
		while(true) {
			context.renderFrame(graphics->drawSelector(graphics, screen,(int) (screen.getWidth()/4), (int) (screen.getHeight()/4), (int) screen.getHeight()/2));
			
			event = context.pollOrWaitEvent(50); // modifier pour avoir un affichage fluide

			if (event == null) { // no event
				continue;
			}

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				if(event.getKey()==KeyboardKey.SPACE && selected.size()==8) {
					break;
				}
			}

			if (action == Action.POINTER_DOWN) {
				if(event.getLocation()!=null) {
					int i =indexFromReaCoord(event.getLocation().x, (int) (screen.getWidth()/4), (int) ((screen.getHeight()/2)*1.0)/5);
					int j =indexFromReaCoord(event.getLocation().y, (int) (screen.getHeight()/4), (int) ((screen.getHeight()/2)*1.0)/5);
					int selectedPlant = (j*8+i);
					if(j==6 && i>=0 && i< selected.size()) {
						available.add(selected.get(i));
						selected.remove(selected.get(i));
					}else if(selectedPlant<available.size() && selectedPlant>=0 && selected.size()<8) {
						selected.add(available.get(selectedPlant));
						available.remove(available.get(selectedPlant));
					}
				}
				event = null;
			}
			
		}
		
		return selected;//jamais atteint, pr�sent pour faire plaisir a eclipse
	}

	private static void drawSelector(Graphics2D g,ScreenInfo sc, int xOrigin, int yOrigin, int length) {
		int squareSize = (int) (length * 1.0 / 5);
		
		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Float(0, 0, sc.getWidth(), sc.getHeight()));//on réaffiche le fond
		g.setFont(new Font("regular",0,40));
		
		g.setColor(Color.green);
		g.fill(new Rectangle2D.Float(xOrigin,yOrigin,squareSize*8,length));
		g.fill(new Rectangle2D.Float(xOrigin, yOrigin+squareSize*6, squareSize*8,squareSize));
		
		g.setColor(Color.black);
		
		//on affiche le cadre principal
		for (int i = 0; i <= 5+2; i++) {
			g.draw(new Line2D.Float(xOrigin, yOrigin + i * squareSize, xOrigin + squareSize*8, yOrigin + i * squareSize));
		}
		for (int i = 0; i <= 8; i++) {
			g.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin, xOrigin + i * squareSize, yOrigin + length));
			g.draw(new Line2D.Float(xOrigin + i * squareSize, yOrigin+squareSize*(5+1), xOrigin + i * squareSize, yOrigin+squareSize*(5+2)));
		}
		
		//on affiche les plantes disponibles
		for(int i=0;i<available.size();i++) {
			g.setColor(available.get(i).getColor());
			g.fill(available.get(i).instantiateFlower(midCell(xOrigin, i%8, available.get(i).getSizeX(), squareSize), midCell(yOrigin, (int)(i/8), available.get(i).getSizeY(), squareSize)).draw());
		}
		
		//on affiche les plantes disponibles
		for(int i=0;i<selected.size();i++) {
			g.setColor(selected.get(i).getColor());
			g.fill(selected.get(i).instantiateFlower(midCell(xOrigin, i, selected.get(i).getSizeX(), squareSize), midCell(yOrigin, 6, selected.get(i).getSizeY(), squareSize)).draw());
		}
		
	}
	
	private static int midCell(int origin , int index,int taille,int squareSize) {
		return (int) (origin+(index*squareSize)+((squareSize-taille)/2));
	}
	
	private static int indexFromReaCoord(float coord, int origin,int squareSize) { // attention, il manque des test de validité des coordonnées!
		return (int) ((coord - origin) / squareSize);
	}
}
