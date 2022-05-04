package atelier3.model;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import atelier3.nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cete classe fabrique et stocke toutes les PieceModel du Model dans une collection 
 * elle est donc responsable de rechercher et mettre à jour les PieceModel (leur position)
 * 
 * En revanche, elle n'est pas responsable des algorithme métiers liés au déplacement des pièces
 * (responsabilité de la classe Model)
 */
public class ModelImplementor {

	// la collection de pièces en jeu - mélange noires et blanches
	private Collection<PieceModel> pieces ;	

	public ModelImplementor() {
		super();

		pieces = ModelFactory.createPieceModelCollection();
	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;
		PieceModel piece = this.findPiece(coord);

		if (piece != null) {
			color = piece.getPieceColor();
		}
		return color;
	}

	public boolean isPiecehere(Coord coord) {
		return this.findPiece(coord) != null;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {

		boolean isMovePieceOk = false;
		PieceModel initPiece = this.findPiece(initCoord);
		if (initPiece != null) {
			isMovePieceOk = initPiece.isMoveOk(targetCoord, isPieceToTake ) ;
		}
		return isMovePieceOk;
	}


	public boolean movePiece(Coord initCoord, Coord targetCoord) {

		boolean isMovePieceDone = false;
		PieceModel initPiece = this.findPiece(initCoord);
		if (initPiece != null) {

			// déplacement pièce
			initPiece.move(targetCoord) ;
			isMovePieceDone = true;
		}
		return isMovePieceDone;
	}

	public void removePiece(Coord pieceToTakeCoord) {

		PieceModel pieceToTake = this.findPiece(pieceToTakeCoord);
		if (pieceToTake != null) {
			this.pieces.remove(pieceToTake);
		}
	}

	public boolean isPiecePromotable(Coord coord) {
		boolean isPiecePromotable = false;

		PieceModel piece = this.findPiece(coord);
		if (piece != null && piece instanceof Promotable) {
			Promotable toPromotePiece = (Promotable) piece;
			isPiecePromotable = toPromotePiece.isPromotable(); 
		}
		return isPiecePromotable;
	}

	public void promotePiece(Coord coord) {

		PieceModel piece = this.findPiece(coord);
		if (piece != null) {
			PieceSquareColor color = piece.getPieceColor();
			this.pieces.remove(piece);
			this.pieces.add(new QueenModel(coord, color)); 
		}
	}

	public List<Coord> getCoordsOnItinerary(Coord initCoord, Coord targetCoord) {
		List<Coord> coordsOnItinerary = null;
		PieceModel initPiece = this.findPiece(initCoord);
		if (initPiece != null) {
			coordsOnItinerary = initPiece.getCoordsOnItinerary(targetCoord) ;
		}

		return coordsOnItinerary;
	}

	public List<Coord> getTargetCoordsInMultiJumpCase(Coord pieceToMoveAgainCoord) {
		List<Coord> listPossibleTargetWithTake = null;
		PieceModel pieceToMoveAgain = this.findPiece(pieceToMoveAgainCoord);
		if (pieceToMoveAgain != null) {
			listPossibleTargetWithTake = pieceToMoveAgain.getTargetCoordsInMultiJumpCase();
		}
		return listPossibleTargetWithTake;
	}

	/**
	 * @param coord
	 * @return la pièce qui se trouve aux coordonnées indiquées
	 */
	private PieceModel findPiece(Coord coord) {		
		PieceModel findPiece = null;

		for(PieceModel piece : this.pieces) {

			if(coord != null && piece.hasThisCoord(coord)) {
				findPiece = piece;
				break;
			}
		}
		return findPiece;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * La méthode toString() retourne une représentation 
	 * de la liste de pièces sous forme d'un tableau 2D
	 * 
	 */
	//	public String toString() {
	//
	//
	//		String st = "";
	//		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];
	//
	//		// création d'un tableau 2D avec les noms des pièces à partir de la liste de pièces
	//		for(PieceModel piece : this.pieces) {
	//
	//			PieceSquareColor color = piece.getPieceColor();
	//			String stColor = (PieceSquareColor.WHITE.equals(color) ? "--B--" : "--N--" );
	//
	//			int col = piece.getColonne() -'a';
	//			int lig = piece.getLigne() -1;
	//			damier[lig][col ] = stColor ;
	//		}
	//
	//		// Affichage du tableau formatté
	//		st = "     a      b      c      d      e      f      g      h      i      j\n";
	//		for ( int lig = 9; lig >=0 ; lig--) {
	//			st += (lig+1) + "  ";
	//			for ( int col = 0; col <= 9; col++) {					 
	//				String stColor = damier[lig][col];				
	//				if (stColor != null) {						
	//					st += stColor + "  ";	
	//				} 
	//				else {
	//					st += "-----  ";
	//				}
	//			}
	//			st +="\n";
	//		}
	//		return "Damier du model \n" + st;	
	//	}

	public String toString() {

//		Collections.sort((List<PieceModel>) pieces); // --> OK
//		Collections.sort((List<PieceModel>)pieces, new PieceComparator());  // --> OK
//		Collections.sort((Set<PieceModel>) pieces); // --> KO compilation : pas tri sur Set
//
//		Collections.sort((List<PieceModel>)pieces, new Comparator<PieceModel>()
//					{
//						public int compare(PieceModel o1, PieceModel o2) {
//			
//							int comp = 0;
//							int max = ModelConfig.LENGTH;
//			
//							// Colonne croissant, ligne croissant
//							comp =  (o1.getColonne()*max - o2.getColonne()*max) 
//									- (o2.getLigne() - o1.getLigne());
//			
//							return comp;
//						}
//					}
//				);  // --> OK

		String st = "";
		int i = 1;

		// Parcours collection en utilisant un itérateur

		//		for(ListIterator<PieceModel> it = ((List<PieceModel>)pieces).listIterator(); it.hasNext(); i++) {
		for(Iterator<PieceModel> it = pieces.iterator(); it.hasNext(); i++) {
			PieceModel piece = it.next();
			st += piece;
			if (i%5 == 0)
				st+= "\n";
		}
		return "Damier du model \n" + st;	
	}




}
