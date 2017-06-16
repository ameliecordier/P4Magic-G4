package model;

import java.util.Random;

/**
 *
 * @author p1607831
 */
public class AppearPionEffect extends Effect{

    @Override
    public void playEffect(int line, int column, Game game) {
        game.getBoard().getTileIJ(line,column).setStatus(-1); //permet de ne pas afficher le pion sur la grille car on doit seulement appliquer l'effet
        int ligne = game.getBoard().getWidth() - 1;   // égal à 9, la ligne qui se situe tout en bas
        int min = 0;
        int max = 9;
        Random rand = new Random();
        
        int min2 = 1;
        int max2 = 2;
        
        //Tire un nombre aléatoire entre min et max compris
        int random = rand.nextInt(max - min + 1) + min;
        int random2 = rand.nextInt(max2 - min2 + 1) + min;
        
        if (game.getBoard().getTileIJ(ligne,random).getStatus() == -1) {
            game.getBoard().getTileIJ(ligne,random).setStatus(random2); // rempli une case de couleur aléatoire si elle est vide
        }
        
        else {
            while (game.getBoard().getTileIJ(ligne,random).getStatus() != -1) {
                ligne--;                                                        //sinon on monte d'un cran, jusqu'à que la case soit vide
            }
            game.getBoard().getTileIJ(ligne,random).setStatus(random2);         // et on la rempli
        }
        
    }
    
}
