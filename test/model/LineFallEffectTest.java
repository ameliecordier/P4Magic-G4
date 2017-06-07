/** 
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import static model.DisappearEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ChangeColorEffect
 * @author Amélie Cordier - IUT Lyon 1 - 2016
 */
public class LineFallEffectTest {
    
    static Game aGame;
    
    public LineFallEffectTest() {
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
    public void testLineFallEffectTestNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new LineFallEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions avant le coup 
        int countPrec = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case contenant l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien un pion de plus sur le plateau
        // - il y a le bon statut sur la case supérieure de chaque colonne
        
        
        int i, j;
        int casesSup = 0;
        for (j = 0 ; j < aGame.getBoard().getWidth(); j++){

        //ce test permet de ne pas tester une colonne pleine.
        if (aGame.getBoard().getTileIJ(0, j).getStatus() == -1) {
            casesSup++;

            for (i = 0; i < aGame.getBoard().getHeight(); ++i) {

                if (aGame.getBoard().getTileIJ(i, j).getStatus() != -1) {
                    break;
                }
            }
                assertEquals(id_player, aGame.getBoard().getTileIJ(i, j).getStatus());
                }
            }
        
            
        assertTrue("Doit être d'effet LineFall", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof LineFallEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);    
        assertEquals(aGame.getBoard().getTotalTilesCount(), casesSup + countPrec);
        }
}

    
  

