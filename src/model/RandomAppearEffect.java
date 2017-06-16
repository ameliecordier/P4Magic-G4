/**
 * MagicP4
 * IUT Lyon 1 - 2017
 */
package model;

import java.util.Random;

/**
 *
 * @author Valentin Gilles - Loïc Caille - IUT Lyon 1 - 2017
 */
public class RandomAppearEffect extends Effect {
    
    /**
     * This effect adds another tile of the color of the player
     * at a random position
     * 
     * @param line
     * @param column
     * @param game 
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        if (play()){
            int i = game.getBoard().getTotalTilesCount();
            Random rand = new Random();
            //Tire un nombre aléatoire entre min et max compris
            do{
                int random = rand.nextInt(game.getBoard().getWidth() + 1);
                game.playMoveNoChange(random);
            } while (game.getBoard().getTotalTilesCount()!=i+1);
        }
        
    }
    
}
