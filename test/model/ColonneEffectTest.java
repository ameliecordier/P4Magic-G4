
package model;

import java.awt.Color;
import static model.ChangeColorEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColonneEffectTest {
    static Game aGame;
    public Boolean[] effect={true,true,true};
            
    public ColonneEffectTest() {
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
        aGame = new Game(Color.RED,Color.YELLOW,effect);
        Board b = new Board(10, 10);
        aGame.setBoard(b);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testColonneEffectPartieVide() {
        
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ColonneEffect());
        
        aGame.playMove(0);
        
        assertEquals(aGame.getBoard().getTileIJ(height - 1,0).getStatus(), -1);
        
    }
    
    @Test
    public void testColonneEffectPartieRemplie() {
        Utils.simulateAGame(aGame);
        
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ColonneEffect());
        
        aGame.playMove(0);
        
        assertEquals(aGame.getBoard().getTileIJ(height - 3,0).getStatus(),aGame.getBoard().getTileIJ(height - 2,0).getStatus(), -1);
        
    }
}
