package dut.game.Terrains;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;

import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class LevelSelector {
	private static int selected = 0;

	public static Terrain selector(ApplicationContext context,ScreenInfo screen) {
		ArrayList<Rectangle2D> lstLevels = new ArrayList<Rectangle2D>();
		
		lstLevels.add(new Rectangle2D.Float(screen.getWidth()/4,screen.getHeight()/10,(int)(screen.getWidth()*0.5),(int)(screen.getHeight()*(0.2))));
		lstLevels.add(new Rectangle2D.Float((int)(screen.getWidth()/4),(int)(screen.getHeight()*0.4),(int)(screen.getWidth()*0.5),(int)(screen.getHeight()*0.2)));
		lstLevels.add(new Rectangle2D.Float((int)(screen.getWidth()/4),(int)(screen.getHeight()*0.7),(int)(screen.getWidth()*0.5),(int)(screen.getHeight()*0.2)));

		Event event = null;

		while(true) {
			context.renderFrame(graphics->drawSelector(graphics, screen,lstLevels,selected));
			event = context.pollOrWaitEvent(50); // modifier pour avoir un affichage fluide

			if (event == null) { // no event
				continue;
			}

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				if(event.getKey().equals(KeyboardKey.SPACE)) {
					break;
				}else if(event.getKey().equals(KeyboardKey.DOWN)) {
					selected = ((selected+1)>2)?0:selected+1;
				}else if(event.getKey().equals(KeyboardKey.UP)) {
					selected = ((selected-1)<0)?2:selected-1;
				}
				
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}
		}

		switch (selected) {
		case 0:
			return new Day();
		case 1:
			return new Night();
		case 2:
			return new Pool();
			
		}
		return null;//jamais atteint, prÈsent pour faire plaisir a eclipse
	}

	private static void drawSelector(Graphics2D g,ScreenInfo sc,ArrayList<Rectangle2D> r,int s) {
		g.setColor(Color.WHITE);
		g.fill(new Rectangle2D.Float(0, 0, sc.getWidth(), sc.getHeight()));//on r√©affiche le fond
		g.setFont(new Font("regular",0,40));

		g.setColor(Color.green);
		g.fill(r.get(0));
		g.setColor(Color.WHITE);
		g.drawString("Jour", (float)(sc.getWidth()*0.45), (float)(sc.getHeight()*0.2));

		g.setColor(Color.black);
		g.fill(r.get(1));
		g.setColor(Color.WHITE);
		g.drawString("Nuit", (float)(sc.getWidth()*0.45), (float)(sc.getHeight()*0.5));

		g.setColor(Color.CYAN);
		g.fill(r.get(2));
		g.setColor(Color.WHITE);
		g.drawString("Piscine", (float)(sc.getWidth()*0.45), (float)(sc.getHeight()*0.8));
		
		
		g.setColor(Color.yellow);
		g.fill(new BasicStroke(10).createStrokedShape(r.get(s)));
	}

}
