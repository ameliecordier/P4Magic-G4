/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 */
public class ChangeNeighborhoodColorEffect extends Effect {

    /**
     * Cet effet change la couleur de tout le voisinnage 
     * du pion joué et de lui-même.
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        for(int j=line-1;j<=line+1;j++){
            for(int i=column-1;i<=column+1;i++){
                if(j!=0 && i!=1) changeColor(j, i, game);
            }
        }
    }
    
    private void changeColor(int line, int column, Game game){
        try{
            if(testPresenceTuile(line, column, game)){
                int tile_id = game.getBoard().getTileIJ(line, column).getStatus();
                int player1_id = game.getPlayer1().getId();
                int player2_id = game.getPlayer2().getId();

                if (tile_id == player1_id) {
                    tile_id = player2_id;
                } else {
                    tile_id = player1_id;
                }
                game.getBoard().getTileIJ(line, column).setStatus(tile_id);
            }
        }catch(Exception e){
            
        }
    }
    
    private boolean testPresenceTuile(int line, int column, Game game){
        return game.getBoard().getTileIJ(line, column).getStatus()!=-1 ;
    }
    
}
