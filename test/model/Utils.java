/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;


/**
 * Classe d'utilitaires pour les tests 
 * @author Amélie Cordier
 */
public class Utils {
    
    /**
     * Méthode qui joue quelques coups sur le plateau
     * Utile pour les tests ou l'on a besoin d'un plateau non vide
     */
    static void simulateAGame(Game aGame){
        for(int i=0; i<10; i++){
            aGame.playMove(i);
        }
        for(int i=0; i<7; i=i+2){
            aGame.playMove(i);
        }
        
        //System.out.println(aGame.getBoard().toStringSymbols());
        /*
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        x-o-x-o-x-
        xoxoxoxoxo
        */

    }
    
    /**
     * Méthode qui place quelques coups sur le plateau de sorte 
     * à ce que le nombre de jeton ne soit pas égal entre joueur 1 et joueur 2
     * @param aGame 
     */
    static void simulateAnOddGame(Game aGame){
        
        int height = aGame.getBoard().getHeight();
        
        aGame.getBoard().getTileIJ(height-1, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 1).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 2).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 3).setStatus(1);
        aGame.getBoard().getTileIJ(height-2, 0).setStatus(2);
        aGame.getBoard().getTileIJ(height-2, 1).setStatus(2);
        
        /*
        System.out.println(aGame.getBoard().toStringSymbols());
        
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        oo--------
        xxxx------
        */
        
    }
    
    /**
     * Méthode qui place quelques coups sur le plateau de sorte 
     * à ce que P1 gagne en colone
     * @param aGame 
     */
    static void simulateGameP1WinsColumn(Game aGame){
        int height = aGame.getBoard().getHeight(); 
        
        aGame.getBoard().getTileIJ(height-1, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-2, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-3, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-4, 0).setStatus(1);
        
        /*
        System.out.println(aGame.getBoard().toStringSymbols());
        
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        x---------
        x---------
        x---------
        x---------
        */
    }
    
    /**
     * Méthode qui place quelques coups sur le plateau de sorte 
     * à ce que P2 gagne en colone vers la gauche 
     * @param aGame 
     */
    static void simulateGameP2WinsLeftDiag(Game aGame){
        int height = aGame.getBoard().getHeight(); 
        
        aGame.getBoard().getTileIJ(height-1, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-2, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-3, 0).setStatus(1);
        aGame.getBoard().getTileIJ(height-4, 0).setStatus(2);
        aGame.getBoard().getTileIJ(height-1, 1).setStatus(1);
        aGame.getBoard().getTileIJ(height-2, 1).setStatus(1);
        aGame.getBoard().getTileIJ(height-3, 1).setStatus(2);
        aGame.getBoard().getTileIJ(height-1, 2).setStatus(1);
        aGame.getBoard().getTileIJ(height-2, 2).setStatus(2);
        aGame.getBoard().getTileIJ(height-1, 3).setStatus(2);
        
        /*
        System.out.println(aGame.getBoard().toStringSymbols());
        
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        o---------
        xo--------
        xxo-------
        xxxo------
        */
    }
    
    /**
     * Méthode qui place quelques coups sur le plateau de sorte 
     * à ce que P1 gagne en colone vers la droite 
     * @param aGame 
     */
    static void simulateGameP1WinsRightDiag(Game aGame){
        int height = aGame.getBoard().getHeight(); 
        
        aGame.getBoard().getTileIJ(height-1, 3).setStatus(2);
        aGame.getBoard().getTileIJ(height-2, 3).setStatus(2);
        aGame.getBoard().getTileIJ(height-3, 3).setStatus(2);
        aGame.getBoard().getTileIJ(height-4, 3).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 2).setStatus(2);
        aGame.getBoard().getTileIJ(height-2, 2).setStatus(2);
        aGame.getBoard().getTileIJ(height-3, 2).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 1).setStatus(2);
        aGame.getBoard().getTileIJ(height-2, 1).setStatus(1);
        aGame.getBoard().getTileIJ(height-1, 0).setStatus(1);
        
        /*
        System.out.println(aGame.getBoard().toStringSymbols());
        
        ----------
        ----------
        ----------
        ----------
        ----------
        ----------
        ---x------
        --xo------
        -xxo------
        xxxo------
        */
    }
}
