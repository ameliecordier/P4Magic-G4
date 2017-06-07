/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author p1517700
 */
public class DisappearColumnEffect extends Effect{

    /**
     * This effect erase the column where the last tile has been played
     * 
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int i = 0;
        while (i < game.getBoard().getHeight() && game.getBoard().getTileIJ(game.getBoard().getHeight()-i-1, column).getStatus() != -1) {
            game.getBoard().getTileIJ(game.getBoard().getHeight()-i-1, column).setStatus(-1);
            i++;
        }
    }
    
}
