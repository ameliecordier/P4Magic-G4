package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1506068
 */
public class RandomTileEffectTest {

    static Game aGame;

    public RandomTileEffectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of playEffect method, of class RandomTileEffect.
     */
    /*@Test
    public void testPlayEffect() {
        System.out.println("playEffect");
        int line = 0;
        int column = 0;
        Game game = null;
        RandomTileEffect instance = new RandomTileEffect();
        instance.playEffect(line, column, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
    @Test
    public void testRandomTileEffect() {
        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RandomTileEffect());
        
        int nbTile = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - deux pions on bien été genéré (le pion joué et le pion random)
        assertEquals( nbTile+2, aGame.getBoard().getTotalTilesCount());
    }
}
