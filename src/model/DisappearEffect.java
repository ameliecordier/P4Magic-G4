/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi / acordier - IUT Lyon 1 - 2016
 */
public class DisappearEffect extends Effect {

    /**
     * 
     * @param line la ligne
     * @param column la colonne
     * @param game la partie
     */
    @Override
    public void playEffect(int line, int column, Game game) {
       int tile_id = -1;
       game.getBoard().getTileIJ(line, column).setStatus(tile_id);
       int player1_id = game.getPlayer1().getId();
       
       if (tile_id == player1_id) {
           tile_id = -1;
       } else {
           tile_id = player1_id;
       }
       game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    }

}
