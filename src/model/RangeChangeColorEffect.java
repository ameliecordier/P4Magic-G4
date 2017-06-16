/**
 * MagicP4
 * IUT Lyon 1 - 2017
 */
package model;

/**
 *
 * @author Gilles Valentin - Caille Loïc - IUT Lyon 1 - 2017
 */
public class RangeChangeColorEffect extends Effect{
    /**
     * This effect changes the color of the tile that has just been played and
     * the ones arround
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        if (play()){
            int player1_id = game.getPlayer1().getId();
            int player2_id = game.getPlayer2().getId();

            int width = game.getBoard().getWidth();
            int height = game.getBoard().getHeight();

            for (int i = line -1 ; i<line+2 ; i++){
                for (int j = column-1 ; j<column+2 ; j++){
                    if ((i>=0 && i<height) && (j>=0 && j<width) && game.getBoard().getTileIJ(i,j).getStatus() != -1 ){
                        int tile_id = game.getBoard().getTileIJ(i,j).getStatus();


                        if (tile_id == player1_id) {
                            tile_id = player2_id;
                        } else {
                            tile_id = player1_id;
                        }
                        game.getBoard().getTileIJ(i, j).setStatus(tile_id);
                    }

                }
            }
        }
    }
}
