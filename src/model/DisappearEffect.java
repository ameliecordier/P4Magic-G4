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
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        int a = super.incremente( line, column, game);
        this.SetNbIterations(5);
        
        if (a == 0){
            return;
        }
            game.getBoard().getTileIJ(line, column).setStatus(-1);
        
    }
}
