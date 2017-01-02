
package model;

/**
 * effet attendu : vide la colonne où le pion doit être joué
 * @author Morgane
 */
public class ColumnDisappearEffect extends Effect {
    
    /**
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();

       
        for (int i=0;i<game.getBoard().getHeight();i++){
            // met lestatut à -1 pour que la case soit vide
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
}

