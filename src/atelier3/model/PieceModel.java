package atelier3.model;


import java.util.List;

import atelier3.nutsAndBolts.PieceSquareColor;

public interface PieceModel extends Comparable<PieceModel>{
	
	
	/**
	 * @return the coord
	 */
	public char getColonne() ;
	public int getLigne() ;
	
	/**
	 * @param coord
	 * @return true si la pièce est aux coordonnées passées en paramètre
	 */
	public boolean hasThisCoord(Coord coord);
	
	/**
	 * @param coord the coord to set
	 * le déplacement d'une pièce change ses coordonnées
	 */
	public void move(Coord coord);


	/**
	 * @return the pieceColor
	 */
	public PieceSquareColor getPieceColor() ;
	
	
	/**
	 * @param targetCoord
	 * @param isPieceToCapture
	 * @return true si le déplacement est légal
	 */
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture);

	/**
	 * @param targetCoord
	 * @return liste des coordonnées des cases traversées par itinéraire de déplacement
	 */
	public List<Coord> getCoordsOnItinerary(Coord targetCoord);

	/**
	 * @return la liste des nouvelles destinations possibles de la pièce en cas de rafle
	 * i.e les cases en diagonale avec une distance d'au moins 2 cases
	 */
	public List<Coord> getTargetCoordsInMultiJumpCase();
	

}

