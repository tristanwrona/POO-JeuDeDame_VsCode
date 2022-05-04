package atelier3.model;


import java.util.LinkedList;
import java.util.List;

import atelier3.nutsAndBolts.PieceSquareColor; 

public abstract class AbstractPieceModel implements PieceModel, Comparable<PieceModel> {

	private Coord coord;
	private PieceSquareColor pieceColor;


	public AbstractPieceModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord = coord;
		this.pieceColor = pieceColor;
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
	public void move(Coord coord) {
		this.coord = coord; 
	}

	@Override
	public PieceSquareColor getPieceColor() {
		return pieceColor;
	}

	@Override
	public abstract boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture);

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>(); 
		int initCol = this.getColonne();
		int initLig = this.getLigne();
		int colDistance = targetCoord.getColonne() - this.getColonne();
		int ligDistance = targetCoord.getLigne() - this.getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		int deltaCol = (int) Math.signum(colDistance);

		// Vérif déplacement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){

			// recherche coordonnées des cases traversées
			for (int i = 1; i < Math.abs(colDistance); i++) {
				Coord coord = new Coord((char) (initCol + i*deltaCol), initLig + i*deltaLig);
				coordsOnItinery.add(coord);
			}
		}
		return coordsOnItinery;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " ["+pieceColor.toString().charAt(0) + coord + "]";
	}

	/**
	 * Compare les coordonnées des PieceModel
	 */
	@Override
	public int compareTo(PieceModel arg0) {
		AbstractPieceModel o = (AbstractPieceModel)arg0;
//		return pieceColor.compareTo(o.getPieceColor()) + coord.compareTo(o.coord);
		return coord.compareTo(o.coord);
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
//		result = prime * result + ((pieceColor == null) ? 0 : pieceColor.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		AbstractPieceModel other = (AbstractPieceModel) obj;
//		if (coord == null) {
//			if (other.coord != null)
//				return false;
//		} else if (!coord.equals(other.coord))
//			return false;
//		if (pieceColor != other.pieceColor)
//			return false;
//		return true;
//	}


}

