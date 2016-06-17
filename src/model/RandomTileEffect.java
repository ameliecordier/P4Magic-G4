package model;

import java.util.Random;

/**
 *
 * @author p1506068
 */
public class RandomTileEffect extends Effect{
        
    

    @Override
    public void playEffect(int line, int column, Game game) {
        
        //recuperation de la taille du board
        int min = 0;
        int max = game.getBoard().getWidth()-1;
        
        //Creation d'une variable random dans les limites du board
        Random rand = new Random();
        int random = rand.nextInt(max - min + 1) + min;
        
        
        //on joue a une position aleatoire du board
        game.playMove(random);
        System.out.println("tout");
    }
    
}
