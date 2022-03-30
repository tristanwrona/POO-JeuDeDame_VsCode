package model;


import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cete classe stocke toutes les constantes utiles au model
 */
public class ModelConfig {

	// les coordonnées des pièces nécessaires à  la création des pièces
	public final  static Coord[] BLACK_PIECE_COORDS = new Coord[] {
			new Coord('b',10), new Coord('d',10), new Coord('f',10), new Coord('h',10), new Coord('j',10),
			new Coord('a',9), new Coord('c',9), new Coord('e',9), new Coord('g',9), new Coord('i',9),
			new Coord('b',8), new Coord('d',8), new Coord('f',8), new Coord('h',8), new Coord('j',8),
			new Coord('a',7), new Coord('c',7), new Coord('e',7), new Coord('g',7), new Coord('i',7),};
	public final static Coord[] WHITE_PIECE_COORDS  = new Coord[] {
			new Coord('b',4), new Coord('d',4), new Coord('f',4), new Coord('h',4), new Coord('j',4),
			new Coord('a',3), new Coord('c',3), new Coord('e',3), new Coord('g',3), new Coord('i',3),
			new Coord('b',2), new Coord('d',2), new Coord('f',2), new Coord('h',2), new Coord('j',2),
			new Coord('a',1), new Coord('c',1), new Coord('e',1), new Coord('g',1), new Coord('i',1),};

	public final static int LENGTH = 10;
	
	public final static PieceSquareColor BEGIN_COLOR = PieceSquareColor.WHITE;

}
