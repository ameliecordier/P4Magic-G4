package model;

/**
 *
 * @author p1511158
 */
public class DeleteLineEffect extends Effect{
    /**
     * Fait disparaitre tous les pions a la ligne: line
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        for(int i=0;i<game.getBoard().getWidth();i++)
        {
            game.getBoard().getTileIJ(line, i).setStatus(-1);
        }
    }
}
