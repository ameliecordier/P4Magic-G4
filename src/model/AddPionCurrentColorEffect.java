package model;

/**
 *
 * @author p1607831
 */
public class AddPionCurrentColorEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        //System.out.println(line);
        game.getBoard().getTileIJ(line,column).setStatus(-1);   // Le pion n'apparait pas sur la grille car l'effet va être appliqué
        int currentPlayer_id = game.getCurrentPlayer().getId();
        int ligne = game.getBoard().getWidth() - 1;              // égal à 9, la ligne qui se situe tout en bas
        
        for (int i = 0; i < game.getBoard().getWidth(); i++) {
            int temp = ligne;
            if (game.getBoard().getTileIJ(ligne, i).getStatus() == -1) {
                game.getBoard().getTileIJ(ligne,i).setStatus(currentPlayer_id);
            }
            else {
                while (game.getBoard().getTileIJ(ligne, i).getStatus() != -1) {
                   ligne--; 
                }
                game.getBoard().getTileIJ(ligne,i).setStatus(currentPlayer_id);   
            }
            ligne = temp;
        }  
    }  
}
