package model;

import java.util.Random;

/**
 *
 * @author Leen
 */
public class RandomTileEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        int width = game.getBoard().getWidth();
        Random rand = new Random();
        int random = rand.nextInt(width-1);
        game.playMove(random);
        Player currentPlayer = game.getCurrentPlayer();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        if (currentPlayer.getId() == player1.getId()) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }
    
}
