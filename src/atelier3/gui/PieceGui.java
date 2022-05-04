package atelier3.gui;

import atelier3.nutsAndBolts.PieceSquareColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @author francoise.perrin
 * 
 * Cette classe permet de donner une image aux pièces
 *
 */

public class PieceGui extends ImageView implements CheckersPieceGui {
	
	private PieceSquareColor color;
	
	public PieceGui(Image image, PieceSquareColor color) {
	
		this.color = color;
		this.setImage(image);
	}
	
	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {
		
		return this.color.equals(gamerColor);
	}
	
	@Override
	public void promote(Image image) {
	
		this.setImage(image);
	}


	
}