/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author Alice
 */
public class DisappearColumnEffect extends Effect{
    /**
     * This effect deletes the column in which the tile has been played.
     * 
     * @param line
     * @param column
     * @param game 
     */
    @Override
    public void playEffect(int line, int column, Game game) {
       
        for (int i=0;i<game.getBoard().getHeight();i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
}
