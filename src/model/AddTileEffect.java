package model;

import java.util.*;

/**
 * @version 1.0
 * @author Sinhnyphoy Sisouphanthong et Lucas Marrel
 */
public class AddTileEffect extends Effect {
    
    /**
     * 
     * @param line le numero de la ligne est passé en paramètre
     * @param column le numero de la colonne est passé en paramètre
     * @param game la session du jeu est passée en paramètre
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        //On parcourt le chaque ligne de chaque colonne tant que les cases sont actives
        for (int colonne = 0; colonne < game.getBoard().getWidth(); ++colonne) {
            if (colonne == column) {
                colonne++;
            }

            for (int ligne = game.getBoard().getHeight() - 1; ligne > 0; --ligne) {
                
                //On ajoute un jeton de la couleur du jour actuel dans la case
                if (game.getBoard().getTileIJ(ligne, colonne).getStatus() == -1) {
                    game.getBoard().getTileIJ(ligne, colonne).setStatus(game.getCurrentPlayer().getId());
                    break;

                }
            }

        }
    }

}
