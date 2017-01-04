package model;

/**
 * MagicP4 IUT Lyon 1 - 2016
 */
public class DisappearLineEffect extends Effect {

    /**
     * Élimine chaque tuile sur la ligne de la case affectée
     * @author Alexi 
     * @param line représente la ligne concernée
     * @param column représente la colomne concernée
     * @param game représente la partie en cours
     */
    @Override
    public void playEffect(int line, int column, Game game) {

        int tile_id;
        int player1_id = game.getPlayer1().getId();
        int player2_id = game.getPlayer2().getId();

        //on fait disparaitre la ligne 
        for (int i = 0; i < 10; i++) {
            game._board.getTileIJ(line, i).setStatus(-1);
        }
        //on remet les pièces en place si nécessaire
        for (int i = 0; i < 10; i++) {
            for (int j = line; j >= 0; j--) {
                tile_id = game._board.getTileIJ(j, i).getStatus();

                if (tile_id != -1) {
                    game._board.getTileIJ(j, i).setStatus(-1);
                    game._board.getTileIJ(j + 1, i).setStatus(tile_id);
                }
            }
        }
    }

}