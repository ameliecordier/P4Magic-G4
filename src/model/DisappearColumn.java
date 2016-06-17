package model;

/**
 *
 * @author p1500486
 */
public class DisappearColumn extends Effect{
    /**
     * Une colonne disparait lorsqu'elle est rempli
     */
    
    public void playEffect(int line, int column, Game game){
        for (int i=0; i<game.getBoard().getHeight();i++){
           game.getBoard().getTileIJ(i, column).setStatus(-1); 
        }
    }
}
