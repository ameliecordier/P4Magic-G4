package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddLineEffectTest {

	static Game aGame;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
    public void testAddLineEffectNormalGame() {
        
        // On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Effet fix� sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        
        // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new AddLineEffect());
    
        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // R�cup�ration du nombre de pions pr�sents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup jou� sur cette case 
        aGame.playMove(0);
        
        // R�cup�ration du nombre de pions apr�s le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit �tre d'effet add each column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof AddLineEffect);
        assertEquals(nb_tokens_before +11, nb_tokens_after);
    }
    
    @Test
    public void testAddLineEffectWithDisappearEffectNormalGame() {
        // On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Effet fix� sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        
        // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new AddLineEffect());
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new DisappearEffect());
    
        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // R�cup�ration du nombre de pions pr�sents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup jou� sur cette case 
        aGame.playMove(0);
        
        // R�cup�ration du nombre de pions apr�s le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit �tre d'effet add each column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof AddLineEffect);
        assertTrue("Doit �tre d'effet disappear", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof DisappearEffect);
        assertEquals(nb_tokens_before +10, nb_tokens_after);
    }

    /**
     * Test of AddTile method, of class AddEachColumnEffect.
     */
    @Test
    public void TestByColumn() {
        aGame.getBoard().getTileIJ(9, 0).setEffect(new AddLineEffect());
        aGame.playMove(0);
        assertEquals(1, aGame.getBoard().getTileIJ(9, 0).getStatus());
        assertEquals(1, aGame.getBoard().getTileIJ(9, 1).getStatus());
        for (int j=1;j<10;j++){
            assertEquals(1, aGame.getBoard().getTileIJ(9, j).getStatus());
        }
        
    }

}
