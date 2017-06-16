package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author p1607831
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
     * Test de la méthode Win en créant une ligne sur grille vide 
     * Résultats attendus : le joueur courant doit gagner (joueur 1)
     * 
     */
    @Test
    public void WinLigneEmptyGame() {

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId(); // il s'agit du joueur 1

        // Coup joué 8 fois car le tour change et le joueur 2 doit également jouer
        aGame.playMove(0);  //joueur courant, donc joueur 1
        aGame.playMove(9);
        aGame.playMove(1);  //joueur courant
        aGame.playMove(8);
        aGame.playMove(2);  //joueur courant
        aGame.playMove(9);
        aGame.playMove(3);  //joueur courant
        aGame.playMove(8);
        
        // Vérifications :
        // - l'id du joueur 1 correspond bien a l'id de la case du bas des colonnes 0,1,2,3
        // - on vérifie que le joueur 1 a bien gagné

        assertEquals(id_player, aGame.getBoard().getTileIJ(9,0).getStatus());
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,1).getStatus());
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,2).getStatus());
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,3).getStatus());
        assertEquals(aGame.Win(), aGame.getPlayer1());
       
    }
    
    /**
     * Test de la méthode Win en créant une colonne sur grille vide 
     * Résultats attendus : le joueur courant doit gagner (joueur 1)
     * 
     */
    @Test
    public void WinColonneEmptyGame() {

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId(); // il s'agit du joueur 1

        // Coup joué 8 fois car le tour change et le joueur 2 doit également jouer
        aGame.playMove(0);  //joueur courant, donc joueur 1 va toujours jouer sur la colonne 0
        aGame.playMove(9);
        aGame.playMove(0);  //joueur courant
        aGame.playMove(8);
        aGame.playMove(0);  //joueur courant
        aGame.playMove(9);
        aGame.playMove(0);  //joueur courant
        aGame.playMove(8);
        
        // Vérifications :
        // - l'id du joueur 1 correspond bien a l'id des 4 cases du bas de la colonne 0
        // - on vérifie que le joueur 1 a bien gagné
     
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,0).getStatus());   //Vu qu'on joue toujours sur la même colonne, 
        assertEquals(id_player, aGame.getBoard().getTileIJ(8,0).getStatus());  // on change juste le num. de la ligne à chaque assert
        assertEquals(id_player, aGame.getBoard().getTileIJ(7,0).getStatus());  // 9 correspond à la ligne qui se situe tout en bas de la grille
        assertEquals(id_player, aGame.getBoard().getTileIJ(6,0).getStatus());
        assertEquals(aGame.Win(), aGame.getPlayer1());
       
    }
    
    /**
     * Test de la méthode Win en créant une suite gagnante en diagonale droite sur grille vide 
     * Résultats attendus : le joueur courant doit gagner (joueur 1)
     * 
     */
    @Test
    public void WinRightDiagEmptyGame() {

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId(); // il s'agit du joueur 1

        // Coup joué 11 fois car le tour change et le joueur 2 doit également jouer
        aGame.playMove(0);  //joueur courant, donc joueur 1 va former une diagonale droite gagnante
        aGame.playMove(1);
        aGame.playMove(1);  //joueur courant
        aGame.playMove(2);
        aGame.playMove(2);  //joueur courant
        aGame.playMove(0);
        aGame.playMove(2);  //joueur courant
        aGame.playMove(3);
        aGame.playMove(3);  //joueur courant
        aGame.playMove(3);
        aGame.playMove(3);  //joueur courant
        
        // Vérifications :
        // - l'id du joueur 1 correspond bien a l'id des 4 cases de la diagonale droite gagnante
        // - on vérifie que le joueur 1 a bien gagné
     
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,0).getStatus());   
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-1,0+1).getStatus());  
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-2,0+2).getStatus());  // 9 correspond à la ligne qui se situe tout en bas de la grille
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-3,0+3).getStatus());
        assertEquals(aGame.Win(), aGame.getPlayer1());
       
    }
    
    /**
     * Test de la méthode Win en créant une suite gagnante en diagonale gauche sur grille vide 
     * Résultats attendus : le joueur courant doit gagner (joueur 1)
     * 
     */
    @Test
    public void WinLeftDiagEmptyGame() {

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId(); // il s'agit du joueur 1

        // Coup joué 11 fois car le tour change et le joueur 2 doit également jouer
        aGame.playMove(9);  //joueur courant, donc joueur 1 va former une diagonale gauche gagnante
        aGame.playMove(8);
        aGame.playMove(8);  //joueur courant
        aGame.playMove(7);
        aGame.playMove(7);  //joueur courant
        aGame.playMove(0);
        aGame.playMove(7);  //joueur courant
        aGame.playMove(6);
        aGame.playMove(6);  //joueur courant
        aGame.playMove(6);
        aGame.playMove(6);  //joueur courant
        
        // Vérifications :
        // - l'id du joueur 1 correspond bien a l'id des 4 cases de la diagonale gauche gagnante
        // - on vérifie que le joueur 1 a bien gagné
     
        assertEquals(id_player, aGame.getBoard().getTileIJ(9,9).getStatus());   
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-1,9-1).getStatus());  
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-2,9-2).getStatus());  // 9 correspond à la ligne qui se situe tout en bas de la grille
        assertEquals(id_player, aGame.getBoard().getTileIJ(9-3,9-3).getStatus());
        assertEquals(aGame.Win(), aGame.getPlayer1());
       
    }
    
    

}

