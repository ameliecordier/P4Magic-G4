package model;

/**
 *
 * @author p1607831
 */
public class DeleteColumnEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        game.getBoard().getTileIJ(line,column).setStatus(-1); //permet de ne pas afficher le pion sur la grille car on doit seulement appliquer l'effet
        int ligne = game.getBoard().getWidth() - 1; // égal à 9, la ligne qui se situe tout en bas
        
        for (int i = 0; i < game.getBoard().getHeight(); i++) { 
            game.getBoard().getTileIJ(ligne,column).setStatus(-1);
            ligne--;                                                // On parcourt chaque case de la colonne courante en supprimant la couleur de la case
        }
    }
    
}
