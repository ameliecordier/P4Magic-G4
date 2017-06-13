/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import java.util.Random;

/**
 *
 * @author Thiago - IUT Lyon 1 - 2017
 */
public class AddRandomPawnEffect extends Effect {

    /**
     * This effect makes a pawn pop randomly
     *
     * @param line
     * @param column
     * @param game
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        boolean colFull = true;

        int min = 0;
        int nbCol = game.getBoard().getHeight() - 1;

        Random rand = new Random();
        int randomCol = 0;

        while (colFull) {
            randomCol = rand.nextInt(nbCol - min + 1) + min;
            colFull = (game.getBoard().getTileIJ(0, randomCol).getStatus() != -1);
        }
        //game.playMove(randomCol);
        int i = 0;
        while (i < game.getBoard().getHeight()) {
            if (game.getBoard().getTileIJ(i, randomCol).getStatus() != -1) {
                break;
            }
            ++i;
        }
        i--;
        game.getBoard().getTileIJ(i, randomCol).setStatus(rand.nextInt(2) + 1);

        if (game.getBoard().getTileIJ(i, randomCol).getEffect() != null) {
            game.getBoard().getTileIJ(i, randomCol).getEffect().playEffect(0, randomCol, game);
        }
    }

}
