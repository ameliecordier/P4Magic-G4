package model;

/**
 * MagicP4
 * IUT Lyon 1 - 2017
 * @author Antonin-Christian
 */
public class AddLineEffect extends Effect {

    /**
     * Cet effet ajoute un jeton de la couleur du joueur actuel dans chaque colonne.
     * @param line
     * @param Column
     * @param game 
     */
	
    @Override
    public void playEffect(int line, int Column, Game game) {

    	int player = game.getCurrentPlayer().getId();
    	
    	//on fait jouer un coup joueur courant dans toutes les colonnes
        for (int j = 0; j < 10; ++j) {
        	game.playMove(j);
        	game.getCurrentPlayer().setId(player);
        }

    }
}