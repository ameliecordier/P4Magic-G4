package model;

/**
 *
 * @author gabba
 */
public class LineFallEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        
        int a = super.incremente( line, column, game);
        
        if (a == 0){
            return;
        }
        
        int idTile = game.getBoard().getTileIJ(line, column).getStatus();
        
        Player bob = null;
        
         if (game.getCurrentPlayer() == game.getPlayer1()){
             
                bob = (Player) game.getPlayer1();
                
            }else{
             
                bob = (Player) game.getPlayer2();
                
            }
         
        for (int i = 0 ; i < game.getBoard().getWidth() ; i ++){
            
            if(i != column){
                
                game.playMoveNoEffectNoChangeP(i); 
                
                    }
            
               }
        
          }
    
    }
    

