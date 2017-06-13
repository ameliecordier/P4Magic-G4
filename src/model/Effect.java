/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 */
public abstract class Effect {

    int iterationsMax;
    int iterations = 0;
    
    //line and column are in the coordinates of the token that has just been played

    /**
     *
     * @param line
     * @param column
     * @param game
     */
    public abstract void playEffect(int line, int column, Game game);

    /**
     *
     * @param nb
     */
    public void SetNbIterations(int nb){
        iterationsMax =  nb +1;
    }

    /**
     *
     * @param line
     * @param column
     * @param game
     * @return
     */
    public int incremente(int line, int column, Game game) {
        iterations++;
        if(iterations == iterationsMax){
            game.getBoard().getTileIJ(line, column).setEffect(null);
            return 0;
        }else{
            return 1;
        }
    }
}
