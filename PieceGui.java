package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nutsAndBolts.PieceSquareColor;


/**
 * @author francoise.perrin
 * 
 * Cette classe permet de donner une image aux pi�ces
 *
 */

public class PieceGui extends ImageView implements CheckersPieceGui {
	
	private PieceSquareColor color;

	public PieceGui(Image image, PieceSquareColor color) {
		this.color = color;
		this.setImage(image);
	}

	@Override
	public void promote(Image image) {
		this.setImage(image);
	}

	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {
		return this.color.equals(gamerColor);
	}

}