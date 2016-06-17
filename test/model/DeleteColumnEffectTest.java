package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1500925
 */
public class DeleteColumnEffectTest {
    
    static Game aGame;
    
    public DeleteColumnEffectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
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
     * Test de l'effet pour ue partie normal
     */
    @Test
    public void testPlayEffect() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        aGame.getBoard().getTileIJ(aGame.getBoard().getHeight() - 3, 0).setEffect(new DeleteColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        

        // Coup joué sur une case contenant l'effet 
        aGame.playMove(0);

        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien moins de pion sur le plateau
        assertTrue("Doit être d'effet delete column", aGame.getBoard().getTileIJ(aGame.getBoard().getHeight() - 3, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        System.out.println(nb_tokens_before);
        System.out.println(nb_tokens_after);
        assertTrue(nb_tokens_after < nb_tokens_before);
    }

    /**
     * Test de DisappearEffect sur grille vide 
     * Vérification de l'état de la
     * tuile après application de l'effet 
     * Résultats attendus : la case doit être
     * vide, le tour de jeu doit être passé
     */
    @Test
    public void testDeleteColumnEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - la case est bien vide après
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
    
}
