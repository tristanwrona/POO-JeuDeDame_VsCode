package model;

import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

public class PawnModel implements PieceModel {

	private Coord coord;
	private PieceSquareColor pieceColor;

	private int direction;

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super();

		this.coord = coord;
		this.pieceColor = pieceColor;
		this.direction = PieceSquareColor.BLACK.equals(this.getPieceColor()) ? -1 : 1;

	}

	@Override
	public char getColonne() {

		return coord.getColonne();

	}

	@Override
	public int getLigne() {

		return coord.getLigne();

	}

	@Override
	public boolean hasThisCoord(Coord coord) {

		return this.coord.equals(coord);
	}

	@Override
	public PieceSquareColor getPieceColor() {

		return pieceColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String st = null;

		st = "PawnModel [coord=" + coord + ", pieceColor=" + pieceColor + "]";

		return st;
	}

	@Override
	public void move(Coord coord) {
		this.coord = coord;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;

		int colDistance = targetCoord.getColonne() - this.getColonne();
		int ligDistance = targetCoord.getLigne() - this.getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		
		// Cas d'un d�placement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){
			
			// sans prise
			if (!isPieceToCapture) {
				if (deltaLig == this.direction && Math.abs(colDistance) == 1) {
					ret = true;
				}
			}
			// avec prise
			else {
				if (Math.abs(colDistance) == 2) {
					ret = true;
				}
			}
		}
		return ret;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>();

		// TODO Atelier 2

		return coordsOnItinery;
	}

}
