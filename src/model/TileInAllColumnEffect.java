package model;

/**
 *
 * @author p1517700
 */
public class TileInAllColumnEffect extends Effect {

    /**
     * This effect play a tile of current color in all columns
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int width = game.getBoard().getWidth();
        for (int i = 0; i < width; i++) {
            if (column != i) {
                int j = game.getBoard().getHeight() - 1;
                while (game.getBoard().getTileIJ(j, i).getStatus() != -1) {
                    j--;
                }
                game.getBoard().getTileIJ(j, i).setStatus(tile_id);
            }
        }
    }
}
