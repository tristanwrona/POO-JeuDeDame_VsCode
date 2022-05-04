package atelier3.model;


import java.util.LinkedList;
import java.util.List;

import atelier3.nutsAndBolts.PieceSquareColor;
/**
 * @author francoiseperrin
 *
 *le mode de déplacement et de prise de la reine est différent de celui du pion
 */
public class QueenModel extends AbstractPieceModel implements PieceModel {

	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
		super(coord, pieceColor);
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;

		int colDistance = targetCoord.getColonne() - this.getColonne();
		int ligDistance = targetCoord.getLigne() - this.getLigne();

		// Cas d'un déplacement en diagonale
		if (Math.abs(colDistance) == Math.abs(ligDistance)){
			ret = true;
		}

		return ret;
	}

	
	@Override
	public List<Coord> getTargetCoordsInMultiJumpCase(){
		List<Coord> targetCoords = new LinkedList<Coord>();
		Coord coord = null;
		int col = this.getColonne();
		int lig = this.getLigne();

		for (int i = 2; i < ModelConfig.LENGTH; i++) {
			coord = new Coord((char) (col+i), lig-i); // NE
			if (Coord.coordonnees_valides(coord))
				targetCoords.add(coord);	

			coord = new Coord((char) (col-i), lig-i);	// NW
			if (Coord.coordonnees_valides(coord))
				targetCoords.add(coord);	

			coord = new Coord((char) (col+i), lig+i);	// SE
			if (Coord.coordonnees_valides(coord))
				targetCoords.add(coord);	

			coord = new Coord((char) (col-i), lig+i);	// SW
			if (Coord.coordonnees_valides(coord))
				targetCoords.add(coord);	
		}
		return targetCoords;
	}


}

