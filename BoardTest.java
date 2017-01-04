package model;

import static model.AddEachColumnEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1523149
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
    }
    
    @After
    public void tearDown() {
    }
//
//    /**
//     * Test of resetBoard method, of class Board.
//     */
//    @Test
//    public void testResetBoard() {
//        System.out.println("resetBoard");
//        Board instance = null;
//        instance.resetBoard();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEffectChances method, of class Board.
//     */
//    @Test
//    public void testSetEffectChances() {
//        System.out.println("setEffectChances");
//        int tileEffectChance = 0;
//        Board instance = null;
//        instance.setEffectChances(tileEffectChance);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setWidth method, of class Board.
//     */
//    @Test
//    public void testSetWidth() {
//        System.out.println("setWidth");
//        int width = 0;
//        Board instance = null;
//        instance.setWidth(width);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setHeight method, of class Board.
//     */
//    @Test
//    public void testSetHeight() {
//        System.out.println("setHeight");
//        int height = 0;
//        Board instance = null;
//        instance.setHeight(height);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTileIJ method, of class Board.
//     */
//    @Test
//    public void testGetTileIJ() {
//        System.out.println("getTileIJ");
//        int i = 0;
//        int j = 0;
//        Board instance = null;
//        Tile expResult = null;
//        Tile result = instance.getTileIJ(i, j);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTileEffectChance method, of class Board.
//     */
//    @Test
//    public void testGetTileEffectChance() {
//        System.out.println("getTileEffectChance");
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.getTileEffectChance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getWidth method, of class Board.
//     */
//    @Test
//    public void testGetWidth() {
//        System.out.println("getWidth");
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.getWidth();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHeight method, of class Board.
//     */
//    @Test
//    public void testGetHeight() {
//        System.out.println("getHeight");
//        Board instance = null;
//        int expResult = 0;
//        int result = instance.getHeight();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Board.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Board instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toStringSymbols method, of class Board.
//     */
//    @Test
//    public void testToStringSymbols() {
//        System.out.println("toStringSymbols");
//        Board instance = null;
//        String expResult = "";
//        String result = instance.toStringSymbols();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    

    /**
     * Test of getTilesCountPlayer1 method, of class Board.
     */
    @Test
    public void testGetTilesCountPlayer1() {
        System.out.println("getTilesCountPlayer1");
        aGame._currentPlayer.setId(1);
        aGame.playMove(1);
        aGame._currentPlayer.setId(2);
        aGame.playMove(2);
        
        assertEquals(aGame._board.getTilesCountPlayer1(), 1);
    }

    /**
     * Test of getTilesCountPlayer2 method, of class Board.
     */
    @Test
    public void testGetTilesCountPlayer2() {
        System.out.println("getTilesCountPlayer1");
        aGame._currentPlayer.setId(1);
        aGame.playMove(1);
        aGame._currentPlayer.setId(2);
        aGame.playMove(2);
        assertEquals(aGame._board.getTilesCountPlayer2(), 1);
    }
    

    /**
     * Test of getTotalTilesCount method, of class Board.
     */
    @Test
    public void testGetTotalTilesCount() {
        System.out.println("getTilesCountPlayer1");
        aGame._currentPlayer.setId(1);
        aGame.playMove(1);
        aGame._currentPlayer.setId(2);
        aGame.playMove(2);
        assertEquals(aGame._board.getTotalTilesCount(), 2);
    }
    
}
