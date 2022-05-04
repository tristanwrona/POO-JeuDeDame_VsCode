package atelier3.model;


import java.util.List;

import atelier3.controller.OutputModelData;
import atelier3.nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 * Cette classe gère les aspects métiers du jeu de dame
 * indépendamment de toute vue
 * 
 * Elle délègue à son objet ModelImplementor 
 * le stockage des PieceModel dans une collection
 * 
 * Les pièces sont capables de se déplacer d'une case en diagonale 
 * si la case de destination est vide
 * ou de 2 cases en diagonale s'il existe une pièce
 * du jeu opposé à  prendre sur le trajet
 * Ces tests sont délégués aux PieceModel
 * 
 * Les pièces sont des pions ou des dames
 * les rafles ne sont pas gérées
 * 
 * N'est pas géré le fait que lorsqu'une prise est possible
 * une autre pièce ne doit pas être jouée
 * 
 */
public class Model implements BoardGame<Coord> {

	private PieceSquareColor currentGamerColor;	// couleur du joueur courant
 
	private ModelImplementor implementor;		// Cet objet sait communiquer avec les PieceModel

	public Model() {
		super();
		this.implementor = new ModelImplementor();
		this.currentGamerColor = ModelConfig.BEGIN_COLOR;

		System.out.println(this);
	}

	@Override
	public String toString() {
		return implementor.toString();
	}



	/**
	 * Actions potentielles sur le model : move, capture, promotion pion, rafles
	 */
	@Override
	public OutputModelData<Coord> moveCapturePromote(Coord toMovePieceCoord, Coord targetSquareCoord) {

		OutputModelData<Coord> outputModelData = null;
		boolean isMoveDone = false;
		Coord toCapturePieceCoord = null;
		Coord toPromotePieceCoord = null;
		PieceSquareColor toPromotePieceColor = null;

		// Si la pièce est déplaçable (couleur du joueur courant et case arrivée disponible)
		if (this.isPieceMoveable(toMovePieceCoord, targetSquareCoord)) {

			// S'il n'existe pas plusieurs pièces sur le chemin
			if (this.isThereMaxOnePieceOnItinerary(toMovePieceCoord, targetSquareCoord)) {

				//Recherche coord de l'éventuelle pièce à prendre
				toCapturePieceCoord = this.getToCapturePieceCoord(toMovePieceCoord, targetSquareCoord);

				// si le déplacement est légal (en diagonale selon algo pion ou dame)
				boolean isPieceToCapture = toCapturePieceCoord != null;
				if (this.isMovePiecePossible(toMovePieceCoord, targetSquareCoord, isPieceToCapture)) {

					// déplacement effectif de la pièce
					this.movePiece(toMovePieceCoord, targetSquareCoord);
					isMoveDone = true;

					// suppression effective de la pièce prise 
					this.remove(toCapturePieceCoord);

					// promotion éventuelle de la pièce après déplacement 
					if (this.isPiecePromotable(targetSquareCoord)) {
						this.promotePiece(targetSquareCoord);
						toPromotePieceCoord = targetSquareCoord;
						toPromotePieceColor = this.currentGamerColor;
					}
					
					// S'il n'y a pas eu de prise
					// ou si une rafle n'est pas possible alors changement de joueur 
					if (true) {	// TODO : Test à changer atelier 4
						this.switchGamer();
					}

				}
			}
		}
		System.out.println(this);

		// Constitution objet de données avec toutes les infos nécessaires à la view
		outputModelData = new OutputModelData<Coord>(
				isMoveDone, 
				toCapturePieceCoord, 
				toPromotePieceCoord, 
				toPromotePieceColor);

		return outputModelData;

	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true si la PieceModel à déplacer est de la couleur du joueur courant 
	 * et que les coordonnées d'arrivées soient dans les limites du tableau
	 * et qu'il n'y ait pas de pièce sur la case d'arrivée
	 */
	private boolean isPieceMoveable(Coord toMovePieceCoord, Coord targetSquareCoord) {
		boolean bool = false;
		

		bool = 	this.implementor.isPiecehere(toMovePieceCoord) 
				&& this.implementor.getPieceColor(toMovePieceCoord) == this.currentGamerColor 
				&& Coord.coordonnees_valides(targetSquareCoord) 
				&& !this.implementor.isPiecehere(targetSquareCoord) ;

		return bool ;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true s'il n'existe qu'1 seule pièce à prendre d'une autre couleur sur la trajectoire
	 * ou pas de pièce à prendre
	 */
	private boolean isThereMaxOnePieceOnItinerary(Coord toMovePieceCoord, Coord targetSquareCoord) {
		boolean isThereMaxOnePieceOnItinerary = false;

		List<Coord> coordsOnItinerary = this.implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord);

		if (coordsOnItinerary != null) { 

			int count = 0;
			Coord potentialToCapturePieceCoord = null;
			for (Coord coordOnItinerary : coordsOnItinerary) {
				if (this.implementor.isPiecehere(coordOnItinerary)) {
					count++;
					potentialToCapturePieceCoord = coordOnItinerary;
				}
			}
			// Il n'existe qu'1 seule pièce à prendre d'une autre couleur sur la trajectoire
			if (count == 0 
					|| (count == 1 && this.currentGamerColor != 
					this.implementor.getPieceColor(potentialToCapturePieceCoord))) {
				isThereMaxOnePieceOnItinerary = true;
			}
		}

		return isThereMaxOnePieceOnItinerary;


	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return les coord de la pièce à prendre, null sinon
	 */
	private Coord getToCapturePieceCoord(Coord toMovePieceCoord, Coord targetSquareCoord) {
		Coord toCapturePieceCoord = null;
		List<Coord> coordsOnItinerary = this.implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord);

		if (coordsOnItinerary != null) { 

			int count = 0;
			Coord potentialToCapturePieceCoord = null;
			for (Coord coordOnItinerary : coordsOnItinerary) {
				if (this.implementor.isPiecehere(coordOnItinerary)) {
					count++;
					potentialToCapturePieceCoord = coordOnItinerary;
				}
			}
			// Il n'existe qu'1 seule pièce à prendre d'une autre couleur sur la trajectoire
			if (count == 0 
					|| (count == 1 && this.currentGamerColor != 
					this.implementor.getPieceColor(potentialToCapturePieceCoord))) {
				toCapturePieceCoord = potentialToCapturePieceCoord;
			}
		}

		return toCapturePieceCoord;
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @param isPieceToCapture
	 * @return true si le déplacement est légal
	 * (s'effectue en diagonale, avec ou sans prise)
	 * La PieceModel qui se trouve aux coordonnées passées en paramètre 
	 * est capable de répondre à cette question (par l'intermédiare du ModelImplementor)
	 */
	private boolean isMovePiecePossible(Coord toMovePieceCoord, Coord targetSquareCoord, boolean isPieceToCapture) {
		return this.implementor.isMovePieceOk(toMovePieceCoord, targetSquareCoord, isPieceToCapture ) ;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * Déplacement effectif de la PieceModel
	 */
	private void movePiece(Coord toMovePieceCoord, Coord targetSquareCoord) {
		this.implementor.movePiece(toMovePieceCoord, targetSquareCoord);
	}

	/**
	 * @param toCapturePieceCoord
	 * Suppression effective de la pièce capturée
	 */
	private void remove(Coord toCapturePieceCoord) {
		this.implementor.removePiece(toCapturePieceCoord);
	}

	/**
	 * @param targetSquareCoord
	 * @return true si le pion après déplacement peut être promue en dame
	 */
	private boolean isPiecePromotable(Coord targetSquareCoord) {
		return this.implementor.isPiecePromotable(targetSquareCoord);
	}

	/**
	 * @param targetSquareCoord
	 * promotion effective du pion en dame 
	 */
	private void promotePiece(Coord targetSquareCoord) {
		this.implementor.promotePiece(targetSquareCoord);
	}

	private void switchGamer() {
		this.currentGamerColor = (PieceSquareColor.WHITE).equals(this.currentGamerColor) ?
				PieceSquareColor.BLACK : PieceSquareColor.WHITE;
		
	}


}