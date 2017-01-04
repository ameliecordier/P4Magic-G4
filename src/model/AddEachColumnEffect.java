package model;

/**
 *
 * @author Alejandro
 */
public class AddEachColumnEffect extends Effect{

    /**
     * Cet effet ajoute un jeton de la couleur du joueur actuel dans chaque colonne.
     * @param line indique le nombre de ligne
     * @param column indique le nombre de colonne
     * @param game indique la partie en cours
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        //on initialise un compteur pour appliquer l'effet sur chaque colonne.       
        for(int j=0; j<10; ++j){
            //appel de la fonction qui ajoute une case pour une colonne ciblée
            AddTile(j,game);
        }
    }
    
    /**
     * Ajoute un jeton de la couleur du joueur actuel dans la colonne entrée en paramètre/
     * @param column indique la colonne ciblée
     * @param game indique la partie concernée
     */
    public void AddTile(int column, Game game){
        int i;
        if(game._board.getTileIJ(0,column).getStatus() == -1){
            for(i = 0; i < game._board.getHeight(); ++i){
                if(game._board.getTileIJ(i,column).getStatus() != -1){
                    break;
                }
            }
            if(i > 0){
                i--;
                game._board.getTileIJ(i, column).setStatus(game._currentPlayer.getId());
            }
            if(game._board.getTileIJ(i,column).getEffect() != null){
                game._board.getTileIJ(i,column).getEffect().playEffect(i, column, game);
            }
        }
    }
    
}
