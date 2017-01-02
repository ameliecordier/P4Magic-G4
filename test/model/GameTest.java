
package model;

import static model.ColumnDisappearEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Morgane - IUT Lyon 1
 */
public class GameTest {
    static Game aGame;
    
    public GameTest() {
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
     * Test du bon fonctionnement du jeu
     * Résultats attendus après le coup : 
     * - un pion de plus sur le plateau 
     * - le tour de jeu est passé 
     * - l'effet a bien été appliqué
     */
    @Test
    public void testNormalGame() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(1);

        // A CHANGER
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - le tour de jeu a bien changé
        // - il y a bien une colonne de pions en moins sur le plateau
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
     /**
     * Test du bon fonctionnement de la méthode win
     * Résultats attendus après le coup : 
     * - le joueur 1 met une ligne de sa couleur sur le plateau
     * - le joueur 1 gagne, et le jeu est fini
     */
    
    public void testWinLineCombination() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Le  joueur 1 a mis 2 pions sur la 1ere ligne
        aGame.getBoard().getTileIJ(0, 1).setStatus(1);
        aGame.getBoard().getTileIJ(0, 2).setStatus(1);
        aGame.getBoard().getTileIJ(0, 3).setStatus(1);

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(4);

        
        // Vérifications :
        // - le pion joué appartient bien au joueur 1 
        // - le joueur 1 a bien gagné
        // - Le jeu est fini
        assertEquals(1, aGame.getBoard().getTileIJ(0,4).getStatus());
        assertEquals(1, aGame.getWinner());
        assertTrue("Le jeu est fini", aGame.getOver());
    }

    
     /**
     * Test du bon fonctionnement de la méthode win
     * Résultats attendus après le coup : 
     * - le joueur 1 a une colonne de sa couleur sur le plateau
     * - le joueur 1 gagne, et le jeu est fini
     */
    
    public void testWinColumnCombination() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Le  joueur 1 a mis 3 pions sur la 1ere ligne
        aGame.getBoard().getTileIJ(0, 0).setStatus(1);
        aGame.getBoard().getTileIJ(0, 1).setStatus(1);
        aGame.getBoard().getTileIJ(0, 2).setStatus(1);

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(0);

        
        // Vérifications :
        // - le pion joué appartient bien au joueur 1 
        // - le joueur 1 a bien gagné
        // - Le jeu est fini
        assertEquals(1, aGame.getBoard().getTileIJ(0,3).getStatus());
        assertEquals(1, aGame.getWinner());
        assertTrue("Le jeu est fini", aGame.getOver());
    }
    
    
    /**
     * Test du bon fonctionnement de la méthode win
     * Résultats attendus après le coup : 
     * - le joueur 1 a une diagonale de sa couleur sur le plateau
     * - le joueur 1 gagne, et le jeu est fini
     */
    
    public void testWinRightDiagsCombination() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Le  joueur 1 a mis 3 pions en diagonale
        aGame.getBoard().getTileIJ(0, 0).setStatus(1);
        aGame.getBoard().getTileIJ(1, 1).setStatus(1);
        aGame.getBoard().getTileIJ(2, 2).setStatus(1);
        
       // Le joueur 2 a mis des pions dans la colonne 3
       aGame.getBoard().getTileIJ(3, 0).setStatus(2);
       aGame.getBoard().getTileIJ(3, 1).setStatus(2);
       aGame.getBoard().getTileIJ(3, 2).setStatus(2);

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(0);

        
        // Vérifications :
        // - le pion joué appartient bien au joueur 1 
        // - le joueur 1 a bien gagné
        // - Le jeu est fini
        assertEquals(1, aGame.getBoard().getTileIJ(3,3).getStatus());
        assertEquals(1, aGame.getWinner());
        assertTrue("Le jeu est fini", aGame.getOver());
    }
    
    
    
}
