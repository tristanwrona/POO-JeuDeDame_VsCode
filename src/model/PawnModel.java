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
		this.direction = (pieceColor == PieceSquareColor.BLACK) ? 1 : -1;

	}

	@Override
	public char getColonne() {
		char colonne = ' ';

		colonne = coord.getColonne();

		return colonne;
	}

	@Override
	public int getLigne() {
		int ligne = -1;

		ligne = coord.getLigne();

		return ligne;
	}

	@Override
	public boolean hasThisCoord(Coord coord) {
		boolean hasThisCoord = false;

		if (this.coord.equals(coord)) {
			hasThisCoord = true;
		}

		return hasThisCoord;
	}

	@Override
	public PieceSquareColor getPieceColor() {
		PieceSquareColor color = null;

		color = this.pieceColor;

		return color;
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

		int colDistance = Math.abs(targetCoord.getColonne() - this.coord.getColonne());
		int ligDistance = Math.abs(targetCoord.getLigne() - this.coord.getLigne());
		int deltalig = (int) Math.signum(ligDistance);

		if (Math.abs(colDistance) == Math.abs(ligDistance)) {

			if (!isPieceToCapture) {
				if (deltalig == this.direction && Math.abs(colDistance) == 1) {
					ret = true;
				}
			}

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
