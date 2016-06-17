package model;

import static model.DisappearEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1511158
 */
public class DeleteColumnEffectTest {
    
    static Game aGame;
    
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
        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
    }
    
    @After
    public void tearDown() {
    }

    /**
        * Test de DeleteColumnEffect sur grille vide 
        * Vérification du nombre de jetons
        * après jeu 
        * Résultat attendu : le nombre doit être égal à 0
    */
    @Test
    public void testColonneVide() {
        // Effet fixé sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());
     
            // Coup joué sur cette case 
            aGame.playMove(0);
     
            // Vérification que le nombre de jetons au total est égal à 0  
            assertEquals(0, aGame.getBoard().getTotalTilesCount());
    }
    /**
         * Test de DeleteColumnEffect sur grille vide 
         * Vérification de l'état de la
         * tuile après application de l'effet 
         * Résultats attendus : la case doit être
         * vide, le tour de jeu doit être passé
    */
    @Test
    public void testDeleteColumnEffectEmptyGame(){
        // Effet fixé sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());
     
            // Récupération de l'ID du joueur courant 
            int id_player = aGame.getCurrentPlayer().getId();
     
            // Coup joué sur la case de l'effet 
            aGame.playMove(0);
     
            // Vérifications :
            // - la case est bien vide après
            // - l'effet est bien appliqué sur la case 
            // - le tour de jeu a bien changé
            assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
            assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DeleteColumnEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
    
    /**
         * Test de DeleteColumnEffect sur grille vide 
         * Vérification du nombre de jetons
         * après jeu 
         * Résultat attendu : le nombre doit être égal à 0
         */
        @Test
        public void testDeleteColumnEffectEmptyGameWithTilesNumber() {
     
            // Effet fixé sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());
     
            // Coup joué sur cette case 
            aGame.playMove(0);
     
            // Vérification que le nombre de jetons au total est égal à 0  
            assertEquals(0, aGame.getBoard().getTotalTilesCount());
     
        }
    
}
