package atelier3.gui;

import atelier3.nutsAndBolts.PieceSquareColor;
import javafx.scene.image.Image;

/**
 * @author francoise.perrin
 * Cette interface permet de vérifier qu'un Node
 * est fonctionnellement une pièce du jeu
 * 
 * Lorsque le pion du model est promu en dame
 * le visuel change
 * 
 * La méthode hasSameColorAsGamer sera utilise en mode Client/server
 * pour empêcher un joueur de jouer une pièce qui ne lui appartient pas
 */
public interface CheckersPieceGui {
	
	public void promote(Image image);

	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor);
}
