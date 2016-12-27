/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 */
public class CondemnedLineEffect extends Effect {

    /**
     * This effect condamn all the line of the tile that has just been played.
     * All the line is settled in black and the board's part under this line
     * become unavailable. If some move have been made above this line, they are
     * still available.
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {

        int neutral_id = game.getPlayerNeutral().getId();

        for (int i = 0; i < game.getBoard().getWidth(); i++) {
            game.getBoard().getTileIJ(line, i).setStatus(neutral_id);
        }
    }

}
