package launcher;


import controller.Mediator;
import controller.localController.Controller;
import gui.GuiConfig;
import gui.View;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BoardGame;
import model.Coord;
import model.Model;


public class Launcher extends Application {

	private BoardGame<Coord> model;
	private EventHandler<MouseEvent> controller;
	private View view;
	
	public static void main (String[] args) {

		Launcher.launch();
	}

	@Override
	public void init () throws Exception {
		super.init();
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui g�re les aspects m�tier du jeu de dame :
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.model = new Model();

		
		///////////////////////////////////////////////////////////////////////////////////////
		// Objet qui contr�le les actions de la vue et les transmet au model
		// il renvoie les r�ponses du model � la vue
		///////////////////////////////////////////////////////////////////////////////////////
		
		this.controller = new Controller();
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Fen�tre dans laquelle se dessine le damier est �cout�e par controller
		///////////////////////////////////////////////////////////////////////////////////////
				
		this.view = new View(controller);
		
		// Controller doit pouvoir invoquer les m�thodes du model
		// il enverra ensuite des instructions � view qui relaiera � son objet Board
		// En mode Client/Server 
		// Les actions devront �tre propag�es sur les vues de chaque client et non pas seulement 
		// sur celle qui a initi� l'action 
		 ((Mediator) controller).setView(view);
		 ((Mediator) controller).setModel(model);
	}


	@Override
	public void start (Stage primaryStage) {

		primaryStage.setScene(new Scene(this.view, GuiConfig.HEIGHT, GuiConfig.HEIGHT));
		primaryStage.setTitle("Jeu de dames - Version de d�part");
		primaryStage.show();
	
	}

	
}

