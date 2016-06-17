package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1500486
 */
public class GameTest {
    
    public GameTest() {
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
     * Test of init method, of class Game.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        Game instance = new Game();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playMove method, of class Game.
     */
    @Test
    public void testPlayMove() {
        System.out.println("playMove");
        int column = 0;
        Game instance = new Game();
        instance.playMove(column);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of strokeIsValid method, of class Game.
     */
    @Test
    public void testStrokeIsValid() {
        System.out.println("strokeIsValid");
        int column = 0;
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.strokeIsValid(column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Win method, of class Game.
     */
    @Test
    public void testWin() {
        System.out.println("Win");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.Win();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOver() {
        System.out.println("isOver");
        Game instance = new Game();
        instance.isOver();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetGame method, of class Game.
     */
    @Test
    public void testResetGame() {
        System.out.println("resetGame");
        Game instance = new Game();
        instance.resetGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPosPreview method, of class Game.
     */
    @Test
    public void testResetPosPreview() {
        System.out.println("resetPosPreview");
        Game instance = new Game();
        instance.resetPosPreview();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTilesEffect method, of class Game.
     */
    @Test
    public void testSetTilesEffect() {
        System.out.println("setTilesEffect");
        Game instance = new Game();
        instance.setTilesEffect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosPreview method, of class Game.
     */
    @Test
    public void testSetPosPreview() {
        System.out.println("setPosPreview");
        int i = 0;
        Game instance = new Game();
        instance.setPosPreview(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoard method, of class Game.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        Board board = null;
        Game instance = new Game();
        instance.setBoard(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosPreview method, of class Game.
     */
    @Test
    public void testGetPosPreview() {
        System.out.println("getPosPreview");
        Game instance = new Game();
        int expResult = 0;
        int result = instance.getPosPreview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class Game.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        Game instance = new Game();
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerById method, of class Game.
     */
    @Test
    public void testGetPlayerById() {
        System.out.println("getPlayerById");
        int id = 0;
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getPlayerById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class Game.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Game instance = new Game();
        int expResult = 0;
        int result = instance.getWinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOver method, of class Game.
     */
    @Test
    public void testGetOver() {
        System.out.println("getOver");
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.getOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer1 method, of class Game.
     */
    @Test
    public void testGetPlayer1() {
        System.out.println("getPlayer1");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getPlayer1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer2 method, of class Game.
     */
    @Test
    public void testGetPlayer2() {
        System.out.println("getPlayer2");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getPlayer2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
