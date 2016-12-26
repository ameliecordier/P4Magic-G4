
package model;

/**
 *
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

        /* prendre tout les i> line
        met la case de la colonne à -1 => vide*/
      
       
        for (int i=0;i<game.getBoard().getHeight();i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
}

