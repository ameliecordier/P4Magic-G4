package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alejandro
 */
public class AddEachColumnEffectTest {
    
    static Game aGame;
    
    public AddEachColumnEffectTest() {
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

    /**
     * Test of playEffect method, of class AddEachColumnEffect.
     */
    @Test
    public void testAddEachColumnEffectNormalGame() {
        System.out.println("testAddEachColumnEffectNormalGame");
        
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new AddEachColumnEffect());
    
        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup joué sur cette case 
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet add each column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof AddEachColumnEffect);
        assertEquals(nb_tokens_before +11, nb_tokens_after);
    }
    
    @Test
    public void testAddEachColumnEffectWithDisappearEffectNormalGame() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new AddEachColumnEffect());
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new DisappearEffect());
    
        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup joué sur cette case 
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet add each column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof AddEachColumnEffect);
        assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof DisappearEffect);
        assertEquals(nb_tokens_before +10, nb_tokens_after);
    }

    /**
     * Test of AddTile method, of class AddEachColumnEffect.
     */
    @Test
    public void TestByColumn() {
        aGame.getBoard().getTileIJ(9, 0).setEffect(new AddEachColumnEffect());
        aGame.playMove(0);
        assertEquals(1, aGame.getBoard().getTileIJ(9, 0).getStatus());
        assertEquals(1, aGame.getBoard().getTileIJ(9, 1).getStatus());
        for (int j=1;j<10;j++){
            assertEquals(1, aGame.getBoard().getTileIJ(9, j).getStatus());
        }
        
    }
}