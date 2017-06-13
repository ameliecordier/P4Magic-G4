/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package puissance4;

import controller.GameController;
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
        Game game = new Game();
        GameSettings settings = new GameSettings();
        Boolean f;
        do{
            f=settings.getIsFinish();
            Thread.sleep(500);
        }while(f==false);
        
        int width =settings.getTheWidth();
        System.out.println(width);
        int height =settings.getTheHeight();
        System.out.println(height);
        
        GameView board = new GameView();
        GameController controller = new GameController(board, game,width,height);
        game.addObserver(board);

    }
}
