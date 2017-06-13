/** 
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ChangeColorNeighborhoodEffect
 * @author Thiago
 */
public class ChangeColorNeighborhoodEffectTest {
    
    static Game aGame;
    
    public ChangeColorNeighborhoodEffectTest() {
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
     * Test du bon fonctionnement du jeu, en dehors de l'effet 
     * Résultats attendus après le coup : 
     * - un pion de plus sur le plateau 
     * - le tour de jeu est passé 
     * - l'effet a bien été appliqué
     */
    @Test
    public void testChangeColorNeighborhoodEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ChangeColorNeighborhoodEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(1);

        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien un pion de plus sur le plateau
        assertTrue("Doit être d'effet change color neighborhood", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorNeighborhoodEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    /**
     * Test de ChangeColorNeighborhoodEffect sur grille vide 
     * Vérification de l'état de la tuile après application de l'effet 
     * Résultats attendus : la case doit être de la couleur opposée à celle 
     * jouée 
     */
    @Test
    public void testChangeColorNeighborhoodEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ChangeColorNeighborhoodEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        

        // Vérifications :
        // - la case est bien de la couleur opposée à celle jouée (donc de la couleur du joueur suivant)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof ChangeColorNeighborhoodEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

    /**
     * Test de ChangeColorNeighborhoodEffect sur grille vide 
     * Vérification du nombre de jetons après jeu 
     * Résultat attendu : le nombre doit être égal à 1
     */
    @Test
    public void testChangeColorNeighborhoodEffectEmptyGameWithTilesNumber() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ChangeColorNeighborhoodEffect());

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérification que le nombre de jetons au total est égal à 1  
        assertEquals(1, aGame.getBoard().getTotalTilesCount());

    }

    /**
     * Test de ChangeColorNeighborhoodEffect sur grille pré-remplie 
     * Vérification de l'état de la tuile et de son voisinage
     * après application de l'effet 
     * Résultat attendu : la case est de la couleur opposée à celle jouée
     * le voisinage change de couleur
     * l'effet doit être sur la case 
     * et le tour doit être passé
     */
    @Test
    public void testChangeColorNeighborhoodEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-2 correspond à la première case vide dans la colonne 1, vu que l'on a déjà joué un coup dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ChangeColorNeighborhoodEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération des couleurs du voisinage
        int tile_left = aGame.getBoard().getTileIJ(0, 1).getStatus();
        int tile_right = aGame.getBoard().getTileIJ(2, 1).getStatus();
        int tile_bottom = aGame.getBoard().getTileIJ(1, 0).getStatus();
        int tile_bottom_left = aGame.getBoard().getTileIJ(0, 0).getStatus();
        int tile_bottom_right = aGame.getBoard().getTileIJ(2, 0).getStatus();

        // Coup joué sur cette case 
        aGame.playMove(1);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Vérifications :
        // - la case est bien de la couleur du joueur suivant
        // - les cases voisines ont changé de couleur
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 2, 1).getStatus());
        
        for (int i : new Integer[]{tile_left, tile_right, tile_bottom, tile_bottom_left, tile_bottom_right}) {
            switch (i) {
                case 1:
                    assertTrue(i == 2);
                    break;
                case 2:
                    assertTrue(i == 1);
                    break;
                case -1:
                    assertTrue(i == -1);
                    break;
            }
        }
        
        assertTrue("Doit être d'effet change color neighborhood", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ChangeColorNeighborhoodEffect);
        assertTrue(id_next_player != id_player);

    }
    
}
