package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ChangeColorEffect
 * 
 * @author Gilles Valentin - Caille 
 */
public class ComptageTest {
    
    public ComptageTest() {
    }
    
    static Game aGame;
    
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
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie avec 10 coups joués (il n'y a pas d'effets sur le jeu)
     * Résultats attendus après le coup : 
     * - la méthode de comptage renvoi 15
     */
    @Test
    public void testComptAll() {

        // On pré-remplit le plateau pour les besoins de la simulation
        // Contient 10 pions
        Utils.simulateGameP2WinsLeftDiag(aGame);
        
        // Vérification :
        // - Il y a 10 pions en total
        assertEquals(aGame.getBoard().getTotalTilesCount(), 10);
    }
    
    /**
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie avec 10 coups joués (il n'y a pas d'effets sur le jeu)
     * Résultats attendus après le coup : 
     * - la méthode de comptage de P1 renvoi 6
     */
    @Test
    public void testComptP1() {

        // On pré-remplit le plateau pour les besoins de la simulation
        // Contient 15 pions dont 6 de P1
        Utils.simulateGameP2WinsLeftDiag(aGame);
        
        // Vérification :
        // - Il y a 6 pions de P1
        assertEquals(aGame.getBoard().getTilesCountPlayer1(), 6);
    }
    
    /**
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie avec 10 coups joués (il n'y a pas d'effets sur le jeu)
     * Résultats attendus après le coup : 
     * - la méthode de comptage renvoi 4
     */
    @Test
    public void testComptP2() {

        // On pré-remplit le plateau pour les besoins de la simulation
        // Contient 10 pions dont 4 de P2
        Utils.simulateGameP2WinsLeftDiag(aGame);
        
        // Vérification :
        // - Il y a 4 pions de P2
        assertEquals(aGame.getBoard().getTilesCountPlayer2(), 4);
    }
    
    /**
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie vide
     * Résultats attendus après le coup : 
     * - la méthode de comptage renvoi 0
     */
    @Test
    public void testComptAllEmptyGame() {
        // Vérification :
        // - Il n'y a aucun pion sur le jeu
        assertEquals(aGame.getBoard().getTotalTilesCount(), 0);
    }
    
    /**
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie vide
     * Résultats attendus après le coup : 
     * - la méthode de comptage renvoi 0
     */
    @Test
    public void testComptP1EmptyGame() {
        // Vérification :
        // - Il n'y a aucun pion de P1
        assertEquals(aGame.getBoard().getTilesCountPlayer1(), 0);
    }
    
    /**
     * Test du bon fonctionnement de la méthode de comptage
     * sur partie vide
     * Résultats attendus après le coup : 
     * - la méthode de comptage renvoi 0
     */
    @Test
    public void testComptP2EmptyGame() {
        // Vérification :
        // - Il n'y a aucun pion de P2
        assertEquals(aGame.getBoard().getTilesCountPlayer2(), 0);
    }
    
}
