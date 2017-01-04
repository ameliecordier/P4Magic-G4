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
     * Cet effet fait disparaitre la le jeton posé à cette case. 
     * Condamnant ainsi la colonne.
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        int tile_id = -1;
        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
            
    }

}
