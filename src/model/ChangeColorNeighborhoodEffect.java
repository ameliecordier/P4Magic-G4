/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author thiago
 */
public class ChangeColorNeighborhoodEffect extends Effect {
    
    /**
     * This effect changes the color of the tile that has just been played and
     * its neighborhood
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
        
        if (line > 0 && column > 0) {
            changeColour(game, player1_id, player2_id, line-1, column-1);
        }
        if (line > 0 && column < (game.getBoard().getWidth() - 1)) {
            changeColour(game, player1_id, player2_id, line-1, column+1);
        }
        if (column > 0) {
            changeColour(game, player1_id, player2_id, line, column-1);
        }
        if (column < (game.getBoard().getWidth() - 1)) {
            changeColour(game, player1_id, player2_id, line, column+1);
        }
        if (line < (game.getBoard().getHeight() - 1) && column > 0) {
            changeColour(game, player1_id, player2_id, line+1, column-1);
        }
        if (line < (game.getBoard().getHeight() - 1)) {
            changeColour(game, player1_id, player2_id, line+1, column);
        }
        if ((line < (game.getBoard().getHeight() - 1)) && (column < (game.getBoard().getWidth() - 1))) {
            changeColour(game, player1_id, player2_id, line+1, column+1);
        }
        changeColour(game, player1_id, player2_id, line, column);

    }

    private void changeColour(Game game, int player1_id, int player2_id, int line, int col) {
        int tile_id = game.getBoard().getTileIJ(line, col).getStatus();

        if (tile_id == player1_id) {
            tile_id = player2_id;
        } else if (tile_id == player2_id) {
            tile_id = player1_id;
        }
        game.getBoard().getTileIJ(line, col).setStatus(tile_id);
    }
}
