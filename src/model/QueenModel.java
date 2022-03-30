package model;


import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;
/**
 * @author francoiseperrin
 *
 *le mode de déplacement et de prise de la reine est différent de celui du pion
 */
public class QueenModel implements PieceModel {

	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
		super();
	}

	private Coord coord;
	private PieceSquareColor pieceColor;


	@Override
	public char getColonne() {
		char col = 'z';
		
		// TODO atelier 3
		
		return col;
	}
	
	@Override
	public int getLigne() {
		int ligne = -1;
		
		// TODO atelier 3
		
		return ligne;
	}
	
	@Override
	public boolean hasThisCoord(Coord coord) {
		boolean hasThisCoord = false;
		
		// TODO atelier 3
		
		return hasThisCoord;
	}

	@Override
	public void move(Coord coord) {
		
		// TODO atelier 3
		
	}

	@Override
	public PieceSquareColor getPieceColor() {
		PieceSquareColor pieceSquareColor = null;
		
		// TODO atelier 3
		
		return pieceSquareColor;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>(); 
		
		// TODO atelier 3
		
		return coordsOnItinery;
	}


	
	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;
		
		// TODO atelier 3
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " ["+pieceColor.toString().charAt(0) + coord + "]";
	}

}

