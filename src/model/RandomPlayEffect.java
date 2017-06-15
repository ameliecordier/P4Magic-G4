package model;

import java.util.Random;

/**
 *
 * @author gabba
 */
public class RandomPlayEffect extends Effect {
    
    /**
     *
     * @param line
     * @param column
     * @param game
     * 
     * Effect that plays a token randomly
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        int a = super.incremente( line, column, game); //itération Limit
        
            if (a == 0){
                return;
            }
            Random rand = new Random();
           int random = rand.nextInt(game.getBoard().getWidth() - 0 + 1) + 0;
           game.playMove(random);
    }     
}
