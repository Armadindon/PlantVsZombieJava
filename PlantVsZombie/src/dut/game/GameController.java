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
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (" + width + " x " + height + ")");

		GameData data = new GameData(5, 8);
		data.setRandomMatrix();
		GameView view = GameView.initGameGraphics((int) (width/4), (int) (height/4), (int) height/2, data);
		data.addGameObject(new Plant(view.midCell((int) (width/4), 1,40), view.midCell((int) (height/4),1,40), 40, 1, 50, Color.BLUE));
		view.draw(context, data);
		Point2D.Float location;
		
		
		while (true) {
			view.draw(context, data);

			view.moveAllAndDraw(context, data);
			Event event = context.pollOrWaitEvent(100); // modifier pour avoir un affichage fluide
			if ((int)(Math.random()*100)==5) {
				int ligne =(int) (Math.random()*5);
				data.addGameObject(new BasicZombie(view.midCell((int) (width/4), 8,40),view.midCell((int) (height/4), ligne,40), 40));
				System.out.println("Nouveau zombie ligne "+ligne);
			}
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
