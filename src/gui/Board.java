package gui;

import controller.InputViewData;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author francoiseperrin
 * <p>
 * Cette classe représente le damier de la vue
 * <p>
 * Elle tire les valeurs d'affichage d'une fabrique de constante (GuiConfig)
 * 		public final static int size = 10;
 * 		public final static double height = 600.0;
 * 
 * Elle délègue à une fabrique le soin de créer et positionner les cases noires et blanches
 * et de créer et positionner les pièces à leur position initiale
 * 
 * Lorsque le model est MAJ, la méthode moveCapturePromotion() est invoquée pour 
 * déplacer effectivement la pièce sur le damier et éventuellement prendre et/ou promouvoir une PieceGui
 * (invocation à partir du controller en passant par classe View)
 * 
 */
class Board extends GridPane {

	public Board (EventHandler<MouseEvent> clicListener) {
		super();

		int nbCol, nbLig;
		nbCol = nbLig = GuiConfig.SIZE;

		BorderPane square = null;
		ImageView piece = null;

		for (int ligne = 0; ligne < nbLig; ligne++){
			for (int col = 0; col < nbCol; col++) {

				///// Création d'une case /////

				// création d'un BorderPane
				square = GuiFactory.createSquare(col, ligne);

				// ajout d'un écouteur sur le carré
				square.setOnMouseClicked(clicListener);

				// taille des carrés = taille de la fenetre / nombre de carrés par lignes
				square.prefWidthProperty().bind(this.prefWidthProperty().divide(nbCol));
				square.prefHeightProperty().bind(this.prefHeightProperty().divide(nbLig));

				// ajout du carre sur le damier
				this.add(square, col, ligne);


				///// Si une pièce doit se trouver sur cette case /////

				// création de la pièce uniquement si doit être sur cette case
				piece = GuiFactory.createPiece(col, ligne);

				if (piece != null) {

					// ajout d'un écouteur de souris
					// si la pièce est sélectionnée, elle sera supprimé de son emplacement actuel
					// et repositionnée sur une autre case
					piece.setOnMouseClicked(clicListener);

					// gestion de la taille et position de la pièce (au centre du carré)
					piece.fitWidthProperty().bind(square.widthProperty().divide(1.5));
					piece.fitHeightProperty().bind(square.heightProperty().divide(1.5));
					piece.xProperty().bind((square.widthProperty().subtract(piece.fitWidthProperty())).divide(2));
					piece.yProperty().bind(square.heightProperty().subtract(piece.fitHeightProperty()).divide(2));

					// Ajout de la pièce sur le carré noir
					square.getChildren().add(piece);
				}
			}
		}
	}

	/////////////////////////////////////////////////////////////
	// Actions sur la view
	// initiées par le controller en passant par la classe View
	/////////////////////////////////////////////////////////////


	/**
	 * @param dataToRefreshView
	 * Cette méthode est appelée par le controller en passant par la classe View
	 * afin de rafraichir la view lorsque le model a été mis à jour
	 */
	public void actionOnGui(InputViewData<Integer> dataToRefreshView) {
		
		if (dataToRefreshView != null) {
			
			////////////////////////////////////////////////////
			// la PieceGui de la vue est effectivement déplacée
			////////////////////////////////////////////////////
			if (dataToRefreshView.toMovePieceIndex != -1 && dataToRefreshView.targetSquareIndex != -1) {
				ImageView toMovePiece = null;
				BorderPane toMovePieceSquare = (BorderPane) this.getChildren().get(dataToRefreshView.toMovePieceIndex);
				BorderPane targetSquare = (BorderPane) this.getChildren().get(dataToRefreshView.targetSquareIndex);

				// Ajout sur la case de destination
				if (!toMovePieceSquare.getChildren().isEmpty())
					toMovePiece = (ImageView) toMovePieceSquare.getChildren().get(0);

				// clear la case d'origine de la pièce déplacée
				if (toMovePiece != null) {
					targetSquare.getChildren().add(toMovePiece);
					toMovePieceSquare.getChildren().removeAll();
				}
			}

			////////////////////////////////////////////////////
			// la PieceGui de la vue est éventuellement promue
			////////////////////////////////////////////////////
			if (dataToRefreshView.promotedPieceIndex != -1) {
				BorderPane targetSquare = (BorderPane) this.getChildren().get(dataToRefreshView.promotedPieceIndex);
				ImageView piece = (ImageView) targetSquare.getChildren().get(0);
				// délégation à la fabrique qui sait comment fabriquer des images
				GuiFactory.PromotePiece(piece, dataToRefreshView.promotedPieceColor);
			}

			////////////////////////////////////////////////////
			// l'éventuelle pièce intermédiaire est supprimée 
			////////////////////////////////////////////////////
			if (dataToRefreshView.capturedPieceIndex != -1) {
				// clear la case d'origine de la pièce supprimée
				BorderPane capturedPieceSquare = (BorderPane) this.getChildren().get(dataToRefreshView.capturedPieceIndex);
				capturedPieceSquare.getChildren().clear();
			}
		}

	}

}



