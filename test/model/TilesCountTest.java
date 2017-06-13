package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gabba
 */
public class TilesCountTest {
    
     static Game aGame;

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {

    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {

        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }

    /**
     *
     */
    @After
    public void tearDown() {
    }
    
    /**
     *
     */
    @Test
    public void testCptUn() {
        Utils.simulateAGame(aGame);
        assertEquals(aGame.getBoard().getTotalTilesCount(), 14);
        assertEquals(aGame.getBoard().getTotalTilesCount(), aGame.getBoard().getTilesCountPlayer1()+aGame.getBoard().getTilesCountPlayer2());
        assertEquals(7, aGame.getBoard().getTilesCountPlayer1());
        assertEquals(7, aGame.getBoard().getTilesCountPlayer2());
    }
    
    /**
     *
     */
    @Test
    public void testCptDeux() {
        Utils.simulateAnOddGame(aGame);
        assertEquals(aGame.getBoard().getTotalTilesCount(), 6);
        assertNotEquals(aGame.getBoard().getTilesCountPlayer1(), aGame.getBoard().getTilesCountPlayer2());
    }
}
