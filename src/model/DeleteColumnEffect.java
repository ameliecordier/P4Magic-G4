package model;

/**
 * @version 1.0
 * @author Sinhnyphoy Sisouphanthong et Lucas Marrel
 */
public class DeleteColumnEffect extends Effect {
    
    /**
     * 
     * @param line le numero de la ligne est passé en paramètre
     * @param column le numero de la colonne est passé en paramètre
     * @param game la session du jeu est passée en paramètre
     */
    @Override
    public void playEffect(int line, int column, Game game) {
    
        
            //On parcourt toutes les cases de la colonne est on les désactive
            for (int i = 0; i < game.getBoard().getHeight(); ++i) {

                

                    
                    game.getBoard().getTileIJ(i, column).setStatus(-1);

                

            }

        
    }
    
}
