package model;

/**
 *
 * @author Simon
 */
public class ChangeNeighborsColorEffect extends Effect {

    /**
     * This effect toggles the color of the 7 neighbors of the tile that has just been played and
     * thus the owner of the tiles
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        int l = line;
        int c = column;
        int[][] positionsToCheck = new int[][] {
            {l-1,c-1}, {l-1, c+1},
            {l, c-1}, {l, c+1},
            {l+1, c-1}, {l+1, c}, {l+1, c+1} 
        };
        int height = game.getBoard().getHeight();
        int width = game.getBoard().getWidth();
         
        for (int i = 0; i < positionsToCheck.length; i++) {
            if (positionsToCheck[i][0] >= 0 && positionsToCheck[i][0] <= height - 1 &&
                positionsToCheck[i][1] >= 0 && positionsToCheck[i][1] <= width - 1 &&
                game.getBoard().getTileIJ(positionsToCheck[i][0], positionsToCheck[i][1]).getStatus() != -1) {
                toggleTileColor(positionsToCheck[i][0], positionsToCheck[i][1], game);
            }
            
        }
    }
    
    public void toggleTileColor(int line, int column, Game game) {
        
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();

        if (tile_id == player1_id) {
            tile_id = player2_id;
        } else {
            tile_id = player1_id;
        }
        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    }

}
