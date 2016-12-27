package model;

import java.util.Random;

/**
 *
 * @author Leen
 */
public class RandomTileEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int width = game.getBoard().getWidth();
        int height = game.getBoard().getHeight();
        
        Random rand = new Random();
        int random = rand.nextInt(width-1);
        
        for (int i=height-1; i>0; i--) {
            if (game.getBoard().getTileIJ(i,random).getStatus() == -1) {
                game.getBoard().getTileIJ(i,random).setStatus(tile_id);
                break;
               }
            }
    }
    
}
