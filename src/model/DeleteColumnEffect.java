package model;

/**
 *
 * @author p1511158
 */
public class DeleteColumnEffect extends Effect{
    /**
     * Fait disparaitre tous les pions a la colonne: column
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        for(int i=0;i<game.getBoard().getHeight();i++)
        {
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
}
