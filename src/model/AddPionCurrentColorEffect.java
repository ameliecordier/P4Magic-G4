package model;

/**
 *
 * @author p1607831
 */
public class AddPionCurrentColorEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        //System.out.println(line);
        game.getBoard().getTileIJ(line,column).setStatus(-1);   //permet de ne pas afficher le pion sur la grille car on doit seulement appliquer l'effet
        int currentPlayer_id = game.getCurrentPlayer().getId();
        int ligne = game.getBoard().getWidth() - 1;              // égal à 9, la ligne qui se situe tout en bas
        
        for (int i = 0; i < game.getBoard().getWidth(); i++) {              // on parcourt chaque colonne
            int temp = ligne;
            if (game.getBoard().getTileIJ(ligne, i).getStatus() == -1) {
                game.getBoard().getTileIJ(ligne,i).setStatus(currentPlayer_id);     //rempli la colonne par la couleur du joueur courant si la colonne est vide
            }
            else {
                while (game.getBoard().getTileIJ(ligne, i).getStatus() != -1) {
                   ligne--;                                                     //si la colonne est différente de -1, càd si elle est rempli, on monte d'un cran jusqu'à qu'elle soit vide
                }
                game.getBoard().getTileIJ(ligne,i).setStatus(currentPlayer_id);   //on rempli la colonne par la couleur du joueur courant ensuite
            }
            ligne = temp;                       // à la fin de chaque itération, on remet la valeur par défaut de ligne soit 9 pour vérifier chaque colonne en commençant par le bas
        }
     
    }  
    
}
