package atelier3.model;


import java.util.LinkedList;
import java.util.List;

import atelier3.nutsAndBolts.PieceSquareColor;

public class PawnModel extends AbstractPieceModel implements PieceModel, Promotable{

	
	private int direction;
	
	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
		this.direction = PieceSquareColor.BLACK.equals(this.getPieceColor()) ? -1 : 1;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;

		int colDistance = targetCoord.getColonne() - this.getColonne();
		int ligDistance = targetCoord.getLigne() - this.getLigne();
		int deltaLig = (int) Math.signum(ligDistance);
		
		// Cas d'un déplacement en diagonale
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
	public boolean isPromotable() {
		boolean ret = false;

		if (direction == 1 && this.getLigne() == ModelConfig.LENGTH ||
				direction == -1 && this.getLigne() == 1) {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Dans notre contexte les PieceModel sont soit des PawnModel soit des QueenModel
	 * donc la promotion d'un PawnModel suppose sa destruction et la création d'une QueenModel
	 * Dans d'autre contexte, on aurait pu imaginer lui changer sa stratégie de déplacement 
	 */
	@Override
	public void promote() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Coord> getTargetCoordsInMultiJumpCase(){
		List<Coord> targetCoords = new LinkedList<Coord>();
		Coord coord = null;
		int col = this.getColonne();
		int lig = this.getLigne();

		coord = new Coord((char) (col+2), lig-2);  // NE
		if (Coord.coordonnees_valides(coord))
			targetCoords.add(coord);	
		
		coord = new Coord((char) (col-2), lig-2);	// NW
		if (Coord.coordonnees_valides(coord))
			targetCoords.add(coord);	
		
		coord = new Coord((char) (col+2), lig+2);	// SE
		if (Coord.coordonnees_valides(coord))
			targetCoords.add(coord);	
		
		coord = new Coord((char) (col-2), lig+2);	// SW
		if (Coord.coordonnees_valides(coord))
			targetCoords.add(coord);	
		
		return targetCoords;
	}

	

}

