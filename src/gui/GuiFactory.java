package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import nutsAndBolts.PieceSquareColor;


/**
 * @author francoise.perrin
 * 
 * Cette classe est responsable de :
 * 		cr�er les cases noires et blanches et les positionner au bon endroit sur le damier
 * 		cr�er les pions noirs et blancs en leur affectant une image et les positionner sur leur case initiale
 *		promouvoir les pions en dame en changeant leur image
 */
public class GuiFactory {

	
	/**
	 * @param col
	 * @param ligne
	 * @return Une case noire ou blanche en alternance
	 * la case en bas � gauche est noire
	 */
	public static BorderPane createSquare(int col, int ligne) {
		
		BorderPane square = null;
		PieceSquareColor squareColor;

		// s�lection de la couleur de la case
		if ((col % 2 == 0 && ligne % 2 == 0) || (col % 2 != 0 && ligne % 2 != 0)) {
			squareColor = PieceSquareColor.WHITE;
		} else {
			squareColor = PieceSquareColor.BLACK;
		}
		square = new BorderPane();
		
		// la couleur est d�finie par les valeurs par d�faut de configuration
		Color color = PieceSquareColor.BLACK.equals(squareColor) ? GuiConfig.CASEBLACK : GuiConfig.CASEWHITE;
		square.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		return square;
	}

	/**
	 * @param col
	 * @param ligne
	 * @return une PieceGui si col/ligne correspond � cases noires
	 * des 4 lignes du haut (piece noire) et du bas du damier (piece blanche)
	 */
	public static ImageView createPiece(int col, int ligne) {

		ImageView pieceGui = null;
		Image image = null;
		PieceSquareColor pieceColor = null;

		if  ( !((col % 2 == 0 && ligne % 2 == 0) || (col % 2 != 0 && ligne % 2 != 0)) ) {
			if (ligne < 4)
				pieceColor = PieceSquareColor.BLACK;
			if (ligne > 5)
				pieceColor = PieceSquareColor.WHITE;
		}
		if (pieceColor != null) {
			image = GuiFactory.createImage(pieceColor, true);
			pieceGui = new ImageView();
			pieceGui.setImage(image);
		}

		return pieceGui;
	}

	/**
	 * @param piece
	 * @param promotedPieceColor
	 * la promotion consiste � changer l'image de la PieceGui
	 */
	public static void PromotePiece(ImageView piece, PieceSquareColor promotedPieceColor) {

		// ToDo Atelier 3
		
	}
	
	/**
	 * @param pieceColor
	 * @param ispawn
	 * @return une image cr��e � partir d'un fichier png
	 */
	private static Image createImage(PieceSquareColor pieceColor, boolean ispawn) {

		Image image = null;
		String pieceImageFile = null, nomImageFile = null;
		File g = new File("");

		if (ispawn) {
			nomImageFile = pieceColor == PieceSquareColor.BLACK ? "PionNoir.png" : "PionBlanc.png";
		}
		else {	
			nomImageFile = pieceColor == PieceSquareColor.BLACK ? "DameNoire.png" : "DameBlanche.png";
		}

		pieceImageFile = g.getAbsolutePath()+"/images/" + nomImageFile;	// TODO - attention au chemin
		try {
			image = new Image(new FileInputStream(pieceImageFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}


}


