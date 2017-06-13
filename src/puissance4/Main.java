/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package puissance4;

import controller.GameController;
import java.awt.Color;
import model.Game;
import view.GameSettings;
import view.GameView;

/**
 * main
 *
 * @author hakkahi IUT Lyon 1 - 2016
 */
public class Main {

    public static void main(String args[]) throws InterruptedException {
        
        GameSettings settings = new GameSettings();
        Boolean f;
        do{
            f=settings.getIsFinish();
            Thread.sleep(500);
        }while(f==false);
        int width = settings.getTheWidth();
        int height = settings.getTheHeight();
        Color color1 = settings.getColor1();
        Color color2 = settings.getColor2();
        Game game = new Game(color1,color2);
        
        GameView board = new GameView();
        GameController controller = new GameController(board, game,width,height);
        game.addObserver(board);

    }
}
