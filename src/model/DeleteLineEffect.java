package model;

/**
 * @version 1.0
 * @author Sinhnyphoy Sisouphanthong et Lucas Marrel
 */
public class DeleteLineEffect extends Effect {

    /**
     * 
     * @param line le numero de la ligne est passé en paramètre
     * @param column le numero de la colonne est passé en paramètre
     * @param game la session du jeu est passée en paramètre
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        
        /*On parcourt toutes les cases de la ligne jouée dans chaque colonne et
        on les désactive
        */
        for (int y = 0; y < game.getBoard().getWidth(); ++y) {

            game.getBoard().getTileIJ(line, y).setStatus(-1);

        }

        /*On parcourt toutes les cases de chaque colonne de toutes les lignes se
        trouvant au-dessus de la ligne désactivée
        */
        for (int i = line-1; i >=0; --i) {

            for (int j = 0; j < game.getBoard().getWidth(); ++j) {
                
                /*Si la case située au dessus de est active, on récupère sa couleur,
                on la désactive et on active la case avec la couleur récupérée
                */
                
                if(game.getBoard().getTileIJ(i, j).getStatus()!=-1){
                        System.out.println("test1");
                        int couleur =game.getBoard().getTileIJ(i, j).getStatus();
                        game.getBoard().getTileIJ(i+1, j).setStatus(couleur);
                        game.getBoard().getTileIJ(i, j).setStatus(-1);
                        
                    }

            }

        }
    }

}
