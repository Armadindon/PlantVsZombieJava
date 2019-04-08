package dut.game;

import java.awt.Color;
import java.awt.geom.Point2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class GameController {

	static void simpleGame(ApplicationContext context) {
		// get the size of the screen
		int ChoixPlante = -1;
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (" + width + " x " + height + ")");

		GameData data = new GameData(5, 8);
		data.setRandomMatrix();
		GameView view = GameView.initGameGraphics((int) (width/4), (int) (height/4), (int) height/2, data);
		view.draw(context, data,screenInfo);
		Point2D.Float location;
		
		
		while (true) {
			view.draw(context, data,screenInfo);
			data.updateData(view,(int) width,(int) height);
			view.moveAllAndDraw(context, data);
			Event event = context.pollOrWaitEvent(40); // modifier pour avoir un affichage fluide
			
			if (event == null) { // no event
				continue;
			}
			

			Action action = event.getAction();
			if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
				System.out.println("Aaaaahh!");
				context.exit(0);
				return;
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}

			if (!data.hasASelectedCell()) { // no cell is selected
				location = event.getLocation(); // !!! attention aucune vérfifcation des coordonnées!!!
				data.selectCell(view.lineFromY(location.y), view.columnFromX(location.x));
				if(view.lineFromY(location.y) == data.getNbLines()+1) {
					ChoixPlante = view.columnFromX(location.x);
				}
				if (view.lineFromY(location.y) >=0 && view.lineFromY(location.y) <data.getNbLines() && (view.columnFromX(location.x) >=0 && view.columnFromX(location.x) < data.getNbColumns())) {
					switch (ChoixPlante) {
					case 0:
						data.addGameObject( new Peashotter(view.midCell((int) (width/4), view.columnFromX(location.x),40), view.midCell((int) (height/4),view.lineFromY(location.y),40)));
						break;
					
					case 1:
						data.addGameObject( new Wallnut(view.midCell((int) (width/4), view.columnFromX(location.x),50), view.midCell((int) (height/4),view.lineFromY(location.y),50)));

						break;
					
					case 2:
						data.addGameObject( new CherryBomb(view.midCell((int) (width/4), view.columnFromX(location.x),50), view.midCell((int) (height/4),view.lineFromY(location.y),50)));
						break;	
					}
					
				}
			} else {
				data.unselect();
			}

		}
	}

	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la méthode de jeu
		Application.run(Color.LIGHT_GRAY, GameController::simpleGame); // attention, utilisation d'une lambda.
	}
}
