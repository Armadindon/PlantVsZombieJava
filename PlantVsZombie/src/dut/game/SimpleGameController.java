package dut.game;

import java.awt.Color;
import java.awt.geom.Point2D;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class SimpleGameController {

	static void simpleGame(ApplicationContext context) {
		// get the size of the screen
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (" + width + " x " + height + ")");

		SimpleGameData data = new SimpleGameData(5, 8);
		data.setRandomMatrix();
		SimpleGameView view = SimpleGameView.initGameGraphics(200, 50, 600, data);
		view.draw(context, data);

		Point2D.Float location;

		HorizontallyMovingElement bullet = new HorizontallyMovingElement(200, 100, 3);

		while (true) {
			view.moveAndDrawElement(context, data, bullet);
			Event event = context.pollOrWaitEvent(20); // modifier pour avoir un affichage fluide
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
			view.draw(context, data);
		}
	}

	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la méthode de jeu
		// (elle doit avoir extaement la mieme en-tête).
		Application.run(Color.LIGHT_GRAY, SimpleGameController::simpleGame); // attention, utilisation d'une lambda.
	}
}
