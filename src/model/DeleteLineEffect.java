package model;

/**
 *
 * @author mathi
 */
public class DeleteLineEffect extends Effect{
    
    @Override
    public void playEffect(int line, int column, Game game) {

       int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
        if (tile_id == player1_id || tile_id == player2_id) {
            for (int i = 0 ; i < game.getBoard().getWidth() ; i++) {
                int ancienneValeur = game.getBoard().getTileIJ(line, i).getStatus()-1;
                game.getBoard().getTileIJ(line, i).setStatus(ancienneValeur-1);
            }
        } 
        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
        
    }
}
