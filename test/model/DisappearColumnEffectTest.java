/** 
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import static model.ChangeColorEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1517700
 */
public class DisappearColumnEffectTest {
    
    static Game aGame;
    
    public DisappearColumnEffectTest() {
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

    /**
     * Test of playEffect method, of class DisappearColumnEffect.
     */
    @Test
    public void testChangeColorEffectNormalGame() {

        
    }

    /**
     * Test 
     */
    @Test
    public void testChangeColorEffectEmptyGame() {

        

    }
    
    /**
     * Test 
     */
    @Test
    public void testChangeColorEffectTwoTokensEmptyGame() {

        

    }

    /**
     * Test 
     */
    @Test
    public void testChangeColorEffectEmptyGameWithTilesNumber() {

        

    }

    /**
     * Test 
     */
    @Test
    public void testChangeColorEffectFilledGame() {

        

    }
    
}
