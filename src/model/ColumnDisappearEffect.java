package model;

/**
 *
 * @author p1502007
 */
public class ColumnDisappearEffect extends Effect{

    /**
     * Vide la colonne de la case qui possède cet effet quand un pion y est joué
     * 
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        int height = game.getBoard().getHeight();
        
        for(int i = 0; i < height; i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
    
}
