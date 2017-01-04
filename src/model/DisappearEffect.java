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
     * This effect erases the tile that has just been played
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
    	int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
    	tile_id=-1;
    	game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    }

}
