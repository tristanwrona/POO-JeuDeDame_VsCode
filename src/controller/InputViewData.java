package controller;

import java.io.Serializable;

import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 * Objet à destination de la View
 * créé par le Controller
 * à partir des données retournées par le Model
 * 
 */
public class InputViewData<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public T toMovePieceIndex = null;
	public T targetSquareIndex = null;
	public T capturedPieceIndex = null;
	public T promotedPieceIndex = null;
	public PieceSquareColor promotedPieceColor = null;
	
	
	public InputViewData(
			T toMovePieceIndex, 
			T targetSquareIndex, 
			T capturedPieceIndex,
			T promotedPieceIndex,
			PieceSquareColor promotedPieceColor) {
		super();
		this.toMovePieceIndex = toMovePieceIndex;
		this.targetSquareIndex = targetSquareIndex;
		this.capturedPieceIndex = capturedPieceIndex;
		this.promotedPieceIndex = promotedPieceIndex;
		this.promotedPieceColor = promotedPieceColor;
	}


	@Override
	public String toString() {
		return "DataAfterMove [toMovePieceIndex=" + toMovePieceIndex
				+ ", targetSquareIndex=" + targetSquareIndex + ", capturedPieceIndex=" + capturedPieceIndex
				+ ", promotedPieceIndex=" + promotedPieceIndex + ", promotedPieceColor=" + promotedPieceColor + "]";
	}


	

	
}
