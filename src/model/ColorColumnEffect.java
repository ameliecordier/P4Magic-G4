package model;

/**
 *
 * @author p1519286
 */
public class ColorColumnEffect extends Effect {

    /**
     * This effect adds a tile of the current player's color in each column.
     * 
     * @param line
     * @param column
     * @param game 
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        
        game.getBoard().getTileIJ(line,column).setStatus(-1);
        
        for (int i=0; i<game.getBoard().getWidth(); i++) {
            for (int j=game.getBoard().getHeight()-1; j>0; j--) {
                
               if (game.getBoard().getTileIJ(j,i).getStatus() == -1) {
                   game.getBoard().getTileIJ(j,i).setStatus(tile_id);
                   break;
               }
               
            }
                              
        }
        
    }
}
