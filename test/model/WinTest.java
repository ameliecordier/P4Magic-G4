package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Valentin Gilles - Caille Loïc
 */
public class WinTest {
    
    static Game aGame;
    
    public WinTest() {
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
     * Test du bon fonctionnement de la méthode win
     * sur partie avec quelques coups joués
     * mais non gagnée
     * Résultats attendus après le coup : 
     * - la méthode win renvoit null
     */
    @Test
    public void testWinNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation  
        Utils.simulateAGame(aGame);

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - personne n'a gagné
        assertEquals(win, null);
    }
    
    /**
     * Test du bon fonctionnement de la méthode win
     * sur partie vide
     * Résultats attendus après le coup : 
     * - la méthode win renvoit null
     */
    @Test
    public void testWinEmptyGame() {

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - personne n'a gagné
        assertEquals(win, null);
    }
    
    /**
     * Test du bon fonctionnement de la méthode win
     * sur partie gagnée par P1 en ligne
     * Résultats attendus après le coup : 
     * - la méthode win renvoit le numéro de P1
     */
    @Test
    public void testWinP1WinsLine() {
        
        // On pré-remplit le plateau avec P1 gagnant en line  
        Utils.simulateAnOddGame(aGame);

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - P1 a gagné
        assertEquals(win.getId(), aGame.getPlayer1().getId());
    }
    
    /**
     * Test du bon fonctionnement de la méthode win
     * sur partie gagnée par P1 en colone
     * Résultats attendus après le coup : 
     * - la méthode win renvoit le numéro de P1
     */
    @Test
    public void testWinP1WinsColumn() {
        
        // On pré-remplit le plateau avec P1 gagnant en colone  
        Utils.simulateGameP1WinsColumn(aGame);

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - P1 a gagné
        assertEquals(win.getId(), aGame.getPlayer1().getId());
    }
    
    /**
     * Test du bon fonctionnement de la méthode win
     * sur partie gagnée par P2 sur une diagonale vers la gauche
     * Résultats attendus après le coup : 
     * - la méthode win renvoit le numéro de P2
     */
    @Test
    public void testWinP2WinsLeftDiag() {
        
        // On pré-remplit le plateau avec P2 gagnant sur une diagonale vers la gauche  
        Utils.simulateGameP2WinsLeftDiag(aGame);

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - P2 a gagné
        assertEquals(win.getId(), aGame.getPlayer2().getId());
    }
    
    /**
     * Test du bon fonctionnement de la méthode win
     * sur partie gagnée par P1 sur une diagonale vers la droite
     * Résultats attendus après le coup : 
     * - la méthode win renvoit le numéro de P1
     */
    @Test
    public void testWinP1WinsRightDiag() {
        
        // On pré-remplit le plateau avec P1 gagnant sur une diagonale vers la droite  
        Utils.simulateGameP1WinsRightDiag(aGame);

        //win stocke le numéro du joueur gagnant ou bien nul s'il n'y en a pas
        Player win = aGame.Win();
        
        // Vérification :
        // - P1 a gagné
        assertEquals(win.getId(), aGame.getPlayer1().getId());
    }
}
