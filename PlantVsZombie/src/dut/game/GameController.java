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
		GameView view = GameView.initGameGraphics(0, 0, (int) height, data);
		view.draw(context, data);

		Point2D.Float location;
		
		
		while (true) {
			view.moveAllAndDraw(context, data);
			Event event = context.pollOrWaitEvent(30); // modifier pour avoir un affichage fluide
			view.draw(context, data);
			if ((int)(Math.random()*100)==5) {
				int ligne =(int) (Math.random()*8);
				System.out.println("Nouveau zombie ligne "+ligne);
				data.addGameObject(new Zombie((int) ((height/5)*7+(height/20)),(int) ((height/5)*ligne+(height/20)),(int) (height/10),-5));
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
