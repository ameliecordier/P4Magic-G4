
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p1607831
 */
public class CompteTuilesTest {
    
    static Game aGame;
    
    public CompteTuilesTest() {
        
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
     * Test des méthodes getTilesCountPlayer1, getTilesCountPlayer2, getTotalTilesCount de la classe Board sur grille vide 
     * Vérification du nombres de tuiles après que les joueurs aient joué
     */
    @Test
    public void CompteTuilesTestEmptyGame() {

        // Coup joué 3 fois, 2 fois pour le joueur 1 et une seule fois pour le joueur 2
        aGame.playMove(0);  // joueur1
        aGame.playMove(0);
        aGame.playMove(0);  // joueur1

        assertEquals(2, aGame.getBoard().getTilesCountPlayer1());   // le joueur 1 a joué 2 fois
        assertEquals(1, aGame.getBoard().getTilesCountPlayer2());   // le joueur 2 a joué 1 fois
        assertEquals(3, aGame.getBoard().getTotalTilesCount());     // le nombre de tuiles sur la grille : 2+1

    }

    /**
     * Test des méthodes getTilesCountPlayer1, getTilesCountPlayer2, getTotalTilesCount de la classe Board sur grille pré-remplie 
     * Vérification du nombres de tuiles après que les joueurs aient joué
     */
    @Test
    public void CompteTuilesTestFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Coup joué 3 fois, 2 fois pour le joueur 1 et une seule fois pour le joueur 2
        aGame.playMove(0);  // joueur1
        aGame.playMove(0);
        aGame.playMove(0);  // joueur1
        
        System.out.println(aGame.getBoard().toStringSymbols()); // Affiche la grille avec des symboles

        assertEquals(9, aGame.getBoard().getTilesCountPlayer1());   // le joueur 1 a joué 9 fois en comptant les jetons présents grâce à la simulation
        assertEquals(8, aGame.getBoard().getTilesCountPlayer2());   // le joueur 2 a joué 8 fois
        assertEquals(17, aGame.getBoard().getTotalTilesCount());     // le nombre de tuiles sur la grille : 8+9
    }
}
