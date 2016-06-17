package model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1500486
 */
public class DisappearColumnTest {
    static Game jeu;
    
    @Before
    public void setUp() {
        // initialisation d'un jeu
        Game jeu = new Game();
        Board tbl = new Board(10,10);
        jeu.setBoard(tbl);
    }
    
    @Test
    public void testDisappearColumn() {
        Utils.simulateaGame(jeu);
        // on fixe l'effet
        int height = jeu.getBoard().getHeight();
        
        jeu.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumn());


        int joeur = jeu.getCurrentPlayer().getId();

        int nb_pions_début = jeu.getBoard().getTotalTilesCount();

        jeu.playMove(1);


        int nb_pions_fin = jeu.getBoard().getTotalTilesCount();

        // Les verifications:
        assertEquals(nb_pions_début + 1, nb_pions_fin);
        assertTrue("Est-il d'effet disappear column ?",jeu.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumn);
        assertTrue(jeu.getCurrentPlayer().getId() != joeur);
    }
        
     @Test
         public void secondTest() {

        int height = jeu.getBoard().getHeight();
        jeu.getBoard().getTileIJ(- 1, 0).setEffect(new DisappearColumn());

        jeu.playMove(0);
        
        assertEquals(0, jeu.getBoard().getTotalTilesCount());

    }
    
}

