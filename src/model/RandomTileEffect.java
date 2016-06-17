package model;

import java.util.Random;

/**
 *
 * @author p1506068
 */
public class RandomTileEffect extends Effect{
        
    

    @Override
    public void playEffect(int line, int column, Game game) {
        
        int min = 0;
        int max = game.getBoard().getWidth()-1;
        Random rand = new Random();
        int random = rand.nextInt(max - min + 1) + min;
        game.playMove(random);
    }
    
}
