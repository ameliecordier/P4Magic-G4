/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi / acordier - IUT Lyon 1 - 2016
 */
public class DisappearEffect extends Effect {

    /**
     * TODO
     * @param line
     * @param column
     * @param game
     */
    
    @Override
    public void playEffect(int line, int column, Game game) {
        game.getBoard().getTileIJ(line,column).setStatus(-1);           //permet de ne pas afficher le pion sur la grille
        //game.getBoard().getTileIJ(line,column).setEffect(null);
    }
    
  

}
