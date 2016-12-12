package model;

/**
 *
 * @author mathi
 */
public class DeleteColumnEffect extends Effect{

    
    
    @Override
    public void playEffect(int line, int column, Game game) {

       int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();
        if (tile_id == player1_id || tile_id == player2_id) {
            for (int i = 0 ; i < game.getBoard().getHeight() ; i++) {
                int ancienneValeur = game.getBoard().getTileIJ(i, column).getStatus()-1;
                game.getBoard().getTileIJ(i, column).setStatus(ancienneValeur-1);
            }
        } 
        game.getBoard().getTileIJ(line, column).setStatus(tile_id);
        
        // Eventuellement faire en sorte de passer tour si joue sur colonne bloqué / Supprimer Pion jouer
    }

}
