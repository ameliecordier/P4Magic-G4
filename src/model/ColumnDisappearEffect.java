package model;

/**
 *
 * @author p1502007
 */
public class ColumnDisappearEffect extends Effect{

    @Override
    public void playEffect(int line, int column, Game game) {
        int height = game.getBoard().getHeight();
        
        for(int i = 0; i < height; i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
    
}
