package model;

/**
 *
 * @author Kostia & Simon
 */
public class ChangeNeighborsColorEffect extends Effect {

    /**
     * This effect toggles the color of the 7 neighbors of the tile that has
     * just been played and thus the owner of the tiles
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {

        int l = line;
        int c = column;
        // positionsToCheck contains the coordinates of the 7 neighbors tiles that can be affected
        // by the ChangeNeighborsColorEffect
        int[][] positionsToCheck = new int[][]{
            {l - 1, c - 1}, {l - 1, c + 1},
            {l, c - 1}, {l, c + 1},
            {l + 1, c - 1}, {l + 1, c}, {l + 1, c + 1}
        };

        // for each tile to check, if the tile is inside the board, we toogle its color
        for (int i = 0; i < positionsToCheck.length; i++) {
            if (checkTileOnBoard(positionsToCheck[i][0], positionsToCheck[i][1], game)
                    && game.getBoard().getTileIJ(positionsToCheck[i][0], positionsToCheck[i][1]).getStatus() != -1) {
                toggleTileColor(positionsToCheck[i][0], positionsToCheck[i][1], game);
            }

        }
    }

    /**
     * This method return true if the tested tile is inside the board and false
     * if it's outside
     *
     * @param line
     * @param column
     * @param game
     * @return true if the tile is inside the board, false if not
     */
    public boolean checkTileOnBoard(int line, int column, Game game) {
        int height = game.getBoard().getHeight();
        int width = game.getBoard().getWidth();
        if (line >= 0 && line <= height - 1 && column >= 0 && column <= width - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void toggleTileColor(int line, int column, Game game) {

        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();

        if (tile_id == player1_id) {
            tile_id = player2_id;
        } else if (tile_id == player2_id) {
            tile_id = player1_id;
        }
        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    }

}
