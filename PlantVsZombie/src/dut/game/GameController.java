package dut.game;

import java.awt.Color;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class GameController {

	static void simpleGame(ApplicationContext context) {
		boolean debug = false;
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
		System.out.println("size of the screen (" + width + " x " + height + ")");

		GameData data = new GameData(5, 8);
		data.setRandomMatrix();
		GameView view = GameView.initGameGraphics((int) (width/4), (int) (height/4), (int) height/2, data);
		view.draw(context, data,screenInfo,data.getChoixPlante());
		
		
		
		Event event = null;
		
		
		while (true) {
			view.draw(context, data,screenInfo,data.getChoixPlante());
			

			data.updateData(view,(int) width,(int) height,event);
			
			view.moveAllAndDraw(context, data);
			event = context.pollOrWaitEvent(40); // modifier pour avoir un affichage fluide
			
			if (event == null) { // no event
				continue;
			}
			
			Action action = event.getAction();
			if (action == Action.KEY_PRESSED) {
				if(event.getKey()!=KeyboardKey.D) {
					System.out.println("Aaaaahh!");
					context.exit(0);
					return;
				}else {
					debug = !(debug);
				}
			}

			if (action != Action.POINTER_DOWN) {
				continue;
			}
				
				
			}

		}
	public static void main(String[] args) {
		// pour changer de jeu, remplacer stupidGame par le nom de la méthode de jeu
		Application.run(Color.LIGHT_GRAY, GameController::simpleGame); // attention, utilisation d'une lambda.
	}
}
