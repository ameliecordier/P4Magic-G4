package model;

/**
 *
 * @author p1519286
 */
public class ColorColumnEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
        
        game.getBoard().getTileIJ(line,column).setStatus(-1);
        
        for (int i=0; i<game.getBoard().getWidth(); i++) {
            for (int j=game.getBoard().getHeight()-1; j>0; j--) {
                
               if (game.getBoard().getTileIJ(j,i).getStatus() == -1) {
                   game.getBoard().getTileIJ(j,i).setStatus(tile_id);
                   break;
               }
               
            }
                              
        }
        
//        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
    }

    /**
     * Adds a tile of the color which has just been played in each column.
     */
}
