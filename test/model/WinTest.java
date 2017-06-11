package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for ChangeColorEffect
 * @author Amélie Cordier - IUT Lyon 1 - 2016
 */
public class WinTest {
    
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
     * Test si la détection de victoire en colonne fonctionne.
     */
    @Test
     public void testWinCol() {
        Utils.simulateEzWinCol(aGame);
        assertEquals(aGame.Win().getId(), aGame.getPlayer1().getId());
    }
     
    /**
     *Test si la détection de victoire en ligne fonctionne.
     */
    @Test
     public void testWinLig() {
        Utils.simulateEzWinLig(aGame);
        assertEquals(aGame.Win().getId(), aGame.getPlayer1().getId());
    }
     
    /**
     *Test si la détection de victoire en diagonale fonctionne.
     */
    @Test
     public void testWinDiag() {
        Utils.simulateEzWinDiag(aGame);
        assertEquals(aGame.Win().getId(), aGame.getPlayer1().getId());
    }
     
    /**
     *
     */
    @Test
     public void testLoose() {
        Utils.simulateAGame(aGame);
        assertEquals(aGame.Win(), null);
    }
}
