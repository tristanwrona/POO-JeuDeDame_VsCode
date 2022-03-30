package model;



import nutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 *
 * Programme de test des classes du model
 * Utile, tant que M V C non connectés (atelier 2)
 */
public class TestModel {


	public static void main(String[] args) {

		//////////////////////////////////
		// Test classe Coord
		//////////////////////////////////

		System.out.println("Test classe Coord");
		Coord c1 = new Coord('a', 7);
		Coord c2 = new Coord('b', 3);
		System.out.println("MAX = " + Coord.MAX); // MAX = 10
		System.out.println("c1 = " + c1);	// c1 = [7,a]
		System.out.println("c2 = " + c2);	// c2 = [3,b]

//		System.out.println("coord valides ('a',7) ? "+ Coord.coordonnees_valides(c1));		//true
//		System.out.println("coord valides ('w',9) ? "+ Coord.coordonnees_valides(new Coord('w',9)));	//false
//		System.out.println("coord valides ('b',11) ? "+ Coord.coordonnees_valides(new Coord('b',11) ));	//false
//		System.out.println("c1.equals(c2) ? "+ c1.equals(c2));	// false
//		System.out.println("c1.equals(new Coord('a', 7)) ? "+ c1.equals(new Coord('a', 7)));	// true
//		System.out.println("c1.equals(new String(\"Erreur\")) ? "+ c1.equals(new String("Erreur")));	// false
//		System.out.println("c1.compareTo(c2) ? "+ c1.compareTo(c2) );	// nb négatif car c1 < c2
//		System.out.println("c1.compareTo(new Coord('a', 7)) ? "+ c1.compareTo(new Coord('a', 7)) );	// 0 car coords égales
//
//
//		//////////////////////////////////
//		// Test classe PawnModel
//		//////////////////////////////////
//
//		System.out.println("\nTest classe PawnModel");
//		PieceModel pieceModel1 = new PawnModel(new Coord('a', 7), PieceSquareColor.BLACK);
//		PieceModel pieceModel2 = new PawnModel(new Coord('b', 4), PieceSquareColor.WHITE);
//		PieceModel pieceModel3 = new PawnModel(new Coord('e', 7), PieceSquareColor.BLACK);
//		System.out.println("pieceModel1 = " + pieceModel1);	// [B[7,a]]
//							pieceModel1.move(new Coord('b', 6));
//		System.out.println("pieceModel1 = " + pieceModel1);	// [B[6,b]]
//		System.out.println("isMoveOk ('b',4) --> ('c',5) = " 
//							+ pieceModel2.isMoveOk(new Coord('c',5),false)); // true : depl. de 1 case sans prise
//		System.out.println("isMoveOk ('e',7) --> ('d',6) = " 
//							+ pieceModel3.isMoveOk(new Coord('d',6),false)); // true : depl. de 1 case sans prise
//							pieceModel2.move(new Coord('c', 5));
//							pieceModel3.move(new Coord('d', 6));
//		System.out.println("isMoveOk ('c',5) --> ('e',7) = " 
//							+ pieceModel2.isMoveOk(new Coord('e',7),true)); // true : depl. de 2 cases avec prise
//		System.out.println("isMoveOk ('c',5) --> ('d',6) = " 
//							+ pieceModel2.isMoveOk(new Coord('d',6),true)); // false : depl. de 1 case avec prise
//		System.out.println("isMoveOk ('c',5) --> ('b',6) = " 
//							+ pieceModel2.isMoveOk(new Coord('b',6),true)); // false : depl. de 1 case avec prise
//		System.out.println("isMoveOk ('c',5) --> ('e',7) = " 
//							+ pieceModel2.isMoveOk(new Coord('e',7),false)); // false : depl. de 2 cases sans prise
//		System.out.println("hasThisCoord ('c',5)  = " 
//							+ pieceModel2.hasThisCoord(new Coord('c',5))); 	 // true : ('c',5) = Coord de l'objet référencé par pieceModel2
//
//
//		//////////////////////////////////
//		// Test classe ModelImplementor
//		//////////////////////////////////
//
//		System.out.println("\nTest classe ModelImplementor");
//		ModelImplementor modelImpl = new ModelImplementor();
//		System.out.println("findPieceModel ('b',4) = " + modelImpl.findPiece(new Coord('b',4)));	// [W[4,b]]
//		System.out.println("findPieceModel ('b',6) = " + modelImpl.findPiece(new Coord('b',6)));	// null
//		System.out.println("getPieceColor('b',4) = " + modelImpl.getPieceColor(new Coord('b',4)));	// WHITE
//		System.out.println("getPieceColor('b',6) = " + modelImpl.getPieceColor(new Coord('b',6)));	// null
//		System.out.println("isPiecehere('b',4) = " + modelImpl.isPiecehere(new Coord('b',4)));	// true 
//		System.out.println("isPiecehere('b',6) = " + modelImpl.isPiecehere(new Coord('b',6)));	// false 
//		System.out.println("isMovePieceOk ('b',4) -> ('c',5) = " + 
//							modelImpl.isMovePieceOk(new Coord('b',4), new Coord('c',5),false));	// true
//		System.out.println("movePiece ('b',4) -> ('c',5) = " + 
//							modelImpl.movePiece(new Coord('b',4), new Coord('c',5)));	// true : move OK
//
//
//		//////////////////////////////////
//		// Test classe Model
//		//////////////////////////////////
//
//		System.out.println("\nTest classe Model");
//		Model model = new Model();	// constructeur crée model et l'affiche
//		System.out.println("isPieceMoveable ('b',4) -> ('c',5)  = " 
//							+ model.isPieceMoveable(new Coord('b',4), new Coord('c',5)));	// true
//		System.out.println("isPieceMoveable ('c',7) -> ('d',6) = " 
//							+ model.isPieceMoveable(new Coord('c',7),  new Coord('d',6)));	// false, tour des blancs
//		System.out.println("isPieceMoveable ('c',3) -> ('d',4)  = " 
//							+ model.isPieceMoveable(new Coord('c',3),  new Coord('d',4)));	// false, case occupée
//		System.out.println("isPieceMoveable ('b',4) -> ('w',12) = " 
//							+ model.isPieceMoveable(new Coord('b',4),  new Coord('w',12)));	// false, hors damier
//
//		System.out.println("isMovePiecePossible ('b',4) -> ('c',5) = " + 
//							model.isMovePiecePossible(new Coord('b',4), new Coord('c',5), false));	// true
//		System.out.println("movePiece ('b',4) -> ('c',5) = move OK - Cf. damier ci-dessous" );
//							model.movePiece(new Coord('b',4), new Coord('c',5));	// move OK visible sur affichage 
//		System.out.println(model);
//	
//		model.switchGamer(); // Changement joueur - c'est au tour des noirs
//		System.out.println("isPieceMoveable ('c',7) -> ('d',6) = " + 
//							model.isPieceMoveable(new Coord('c',7), new Coord('d',6)));	// true c'est bien au joueur noir de jouer
//		System.out.println("isMovePiecePossible ('c',7) -> ('c',6) = " + 
//							model.isMovePiecePossible(new Coord('c',7), new Coord('c',6), false));	// false pas déplacement en diagonale
//	
//		
//		// on recrée un objet Model sur lequel aucun mouvement n'a été effectué 
//		// pour tester méthode publique moveCapturePromote()
//		// normalement elle doit être fonctionnelle sans modification si vous avez bien testé tout ce qui précède
//		System.out.println("\n\nTest méthode publique moveCapturePromote() du Model");
//		model = new Model();	
//		System.out.println("moveCapturePromote ('b',4) -> ('c',5)  = move OK - Cf. damier ci-dessous ");
//							model.moveCapturePromote(new Coord('b',4), new Coord('c',5)); 
//		System.out.println("moveCapturePromote ('e',7) -> ('d',6)  = move OK - Cf. damier ci-dessous ");
//				model.moveCapturePromote(new Coord('e',7), new Coord('d',6)); 
//		System.out.println("moveCapturePromote ('c',5) -> ('e',7)  = move KO on ne gère pas encore les prises - Cf. damier ci-dessous ");
//				model.moveCapturePromote(new Coord('c',5), new Coord('e',7)); 
//		System.out.println("moveCapturePromote ('h',4) -> ('h',5)  = move KO déplacement vertical - Cf. damier ci-dessous ");
//				model.moveCapturePromote(new Coord('h',4), new Coord('h',5)); 
	
	}


}
