package atelier3.controller;

import atelier3.gui.View;
import atelier3.model.BoardGame;
import atelier3.model.Coord;

/**
 * @author francoise.perrin
 * Le Controller fait le lien entre laView et le Model 
 * qui ne se connaissent pas
 * 
 */
public interface Mediator {
	
	public void setView(View view) ;
	public void setModel(BoardGame<Coord> model) ;
}
