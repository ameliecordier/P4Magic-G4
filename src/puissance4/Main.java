/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package puissance4;

import controller.GameController;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import model.Game;
import view.GameView;

/**
 * main
 *
 * @author hakkahi IUT Lyon 1 - 2016
 */
public class Main {

    public static void main(String args[]) throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String couleur1, couleur2;
        System.out.println("Saisir les couleurs des joueurs, en majuscules et en anglais.");
        couleur1 = bf.readLine();
        couleur2 = bf.readLine();
        
        Color color1, color2;
        Field field = Color.class.getField(couleur1);
        color1 = (Color)field.get(null);
        field = Color.class.getField(couleur2);
        color2 = (Color)field.get(null);

        
        System.out.println(Color.getColor(couleur1));
        Game game = new Game(color1, color2);
        GameView board = new GameView();
        GameController controller = new GameController(board, game);
        game.addObserver(board);

    }
}
