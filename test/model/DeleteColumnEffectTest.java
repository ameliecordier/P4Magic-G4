package model;
/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests de l'effet Disappear 
 * Principe de l'effet : un pion joué sur
 * une case portant l'effet Disappear disparaît immédiatement. Conséquences :
 * l'état du jeu n'est pas modifié, le pion joué n'apparaît pas sur la grille,
 * et le tour de jeu change
 *
 * @author acordier
 */
public class DeleteColumnEffectTest {

    static Game aGame;

    public DeleteColumnEffectTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }

    @After
    public void tearDown() {
    }


    @Test
    public void testDeleteColumnEffectNormalGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteColumnEffect());


        int id_player = aGame.getCurrentPlayer().getId();


        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();


        aGame.playMove(1);


        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();


        assertTrue("Doit être d'effet deleteColumn", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    @Test
    public void testDeleteColumnEffectEmptyGame() {

        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);


        assertEquals(1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet deleteColumn", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }


    @Test
    public void testDeleteColumnEffectEmptyGameWithTilesNumber() {


        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());


        aGame.playMove(0);


        assertEquals(1, aGame.getBoard().getTotalTilesCount());

    }


    @Test
    public void testDeleteColumnEffectFilledGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteColumnEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);

        assertEquals(1, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

}