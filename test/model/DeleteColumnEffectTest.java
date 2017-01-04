package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1522867
 */
public class DeleteColumnEffectTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of playEffect method, of class DeleteColumnEffect.
     */
    @Test
    public void testPlayEffect() {
        System.out.println("playEffect");
        int line = 0;
        int column = 0;
        Game game = null;
        DeleteColumnEffect instance = new DeleteColumnEffect();
        instance.playEffect(line, column, game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
