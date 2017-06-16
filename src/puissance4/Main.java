/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package puissance4;

import controller.GameController;
import java.awt.Color;
import model.AddRandomPawnEffect;
import model.ChangeColorEffect;
import model.ChangeColorNeighborhoodEffect;
import model.DisappearEffect;
import model.Effect;
import model.Game;
import view.GameView;

/**
 * main
 *
 * @author hakkahi IUT Lyon 1 - 2016
 */
public class Main {

    public static void main(String args[]) {
        
        //Taille de la grille
        int height = 15;
        int width = 10;
        
        //Couleur des joueurs
        Color colorP1 = java.awt.Color.getHSBColor(279.0f/360, 0.83f, 0.54f);
        Color colorP2 = java.awt.Color.getHSBColor(133.0f/360, 0.96f, 0.83f);
        
        //Effets à utiliser
        Effect[] usableEffects = {
            new ChangeColorEffect(),
            new DisappearEffect(),
            new AddRandomPawnEffect(),
            new ChangeColorNeighborhoodEffect()
        };
        
        //Mode debug
        boolean debug = true;
        
        Game game = new Game(colorP1, colorP2, debug);
        
        GameView board = new GameView();
        
        GameController controller = new GameController(board, game, height, width, usableEffects);
        game.addObserver(board);

    }
}
