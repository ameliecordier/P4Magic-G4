package model;

/**
 *
 * @author p1517348
 */
public class DisappearColumnEffect extends Effect {
    
    /**
     * This effect delete all the tiles of the column where the tile has just been played
     * @param line
     * @param column
     * @param game 
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int height = game.getBoard().getHeight();
        for(int i=0; i<height-line; i++){
            game.getBoard().getTileIJ(line+i, column).setStatus(-1);
        }
    }
}
