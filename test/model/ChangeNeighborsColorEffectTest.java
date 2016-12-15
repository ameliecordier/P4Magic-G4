package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Simon
 */
public class ChangeNeighborsColorEffectTest {
    
    static Game aGame;
    
    public ChangeNeighborsColorEffectTest() {
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
     * Test de ChangeNeighborsColorEffect sur grille vide 
     * Vérification de l'état de la tuile après application de l'effet 
     * Résultats attendus : la case doit être identique avec le pion joué dans la couleur jouée
     */
    @Test
    public void testChangeNeighborsColorEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ChangeNeighborsColorEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Vérification que le nombre de jetons au total est égal à 1  
        assertEquals(1, aGame.getBoard().getTotalTilesCount());
        
        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        

        // Vérifications :
        // - la case est bien de la couleur jouée
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet ChangeNeighborsColorEffect", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof ChangeNeighborsColorEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

    /**
     * Test de ChangeNeighborsColorEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case jouée est située sur la colonne <b>tout à gauche</b> de la grille
     * la case est de la couleur jouée
     * les voisins de la case jouée ont changé de couleur
     * l'effet doit être sur la case
     * et le tour doit être passé
     */
    @Test
    public void testChangeNeighborsColorEffectLeft() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ChangeNeighborsColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération de la couleur de la case voisine avant que le coup soit joué
        int beforeColor = aGame.getBoard().getTileIJ(height-2, 0).getStatus();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Récupération de la couleur de la case voisine après que le coup soit joué
        int afterColor = aGame.getBoard().getTileIJ(height-2, 0).getStatus();
        
        // Vérifications :
        // - la case est bien de la couleur jouée
        // - les cases voisines ont changé de couleur
        // - l'effet est bien appliqué sur la case
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorEffect);
        assertTrue(id_next_player != id_player);
        assertTrue(beforeColor != afterColor);

    }
    
        /**
     * Test de ChangeNeighborsColorEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case jouée est située sur la colonne <b>tout à droite</b> de la grille
     * la case est de la couleur jouée
     * les voisins de la case jouée ont changé de couleur
     * l'effet doit être sur la case
     * et le tour doit être passé
     */
    @Test
    public void testChangeNeighborsColorEffectRight() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        int width = aGame.getBoard().getWidth();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, width-1).setEffect(new ChangeColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération des couleurs des cases voisines avant que le coup soit joué
        int beforeColor1 = aGame.getBoard().getTileIJ(height-2, width-2).getStatus();
        int beforeColor2 = aGame.getBoard().getTileIJ(height-1, width-2).getStatus();
        int beforeColor3 = aGame.getBoard().getTileIJ(height-1, width-1).getStatus();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Récupération des couleurs des cases voisines après que le coup soit joué
        int afterColor1 = aGame.getBoard().getTileIJ(height-2, width-2).getStatus();
        int afterColor2 = aGame.getBoard().getTileIJ(height-1, width-2).getStatus();
        int afterColor3 = aGame.getBoard().getTileIJ(height-1, width-1).getStatus();
        
        // Vérifications :
        // - la case est bien de la couleur du joueur suivant (puisque changecolor s'applique)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorEffect);
        assertTrue(id_next_player != id_player);
        assertTrue(beforeColor1 != afterColor1);
        assertTrue(beforeColor2 != afterColor2);
        assertTrue(beforeColor2 != afterColor2);

    }
    
            /**
     * Test de ChangeNeighborsColorEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case jouée est située sur la ligne <b>tout en bas</b> de la grille
     * la case est de la couleur jouée
     * les voisins de la case jouée ont changé de couleur
     * l'effet doit être sur la case
     * et le tour doit être passé
     */
    @Test
    public void testChangeNeighborsColorEffectDown() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAnOddGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 1, 4).setEffect(new ChangeNeighborsColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);
        
        // Récupération de la couleur de la case voisine avant que le coup soit joué
        int beforeColor = aGame.getBoard().getTileIJ(height-1, 3).getStatus();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Récupération de la couleur de la case voisine après que le coup soit joué
        int afterColor = aGame.getBoard().getTileIJ(height-1, 3).getStatus();
        
        // Vérifications :
        // - la case est bien de la couleur du joueur suivant (puisque changecolor s'applique)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorEffect);
        assertTrue(id_next_player != id_player);
        assertTrue(beforeColor != afterColor);

    }

        /**
     * Test de ChangeNeighborsColorEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case jouée est située sur la ligne <b>tout en haut</b> de la grille
     * la case est de la couleur jouée
     * les voisins de la case jouée ont changé de couleur
     * l'effet doit être sur la case
     * et le tour doit être passé
     */
    @Test
    public void testChangeNeighborsColorEffectUp() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ChangeColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Vérifications :
        // - la case est bien de la couleur du joueur suivant (puisque changecolor s'applique)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorEffect);
        assertTrue(id_next_player != id_player);

    }

         /**
     * Test de ChangeNeighborsColorEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case jouée est située n'importe où sur la grille
     * les voisins de la case jouée ont changé de couleur
     * l'effet doit être sur la case
     * et le tour doit être passé
     */
    @Test
    public void testChangeNeighborsColorEffectMiddle() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ChangeColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Vérifications :
        // - la case est bien de la couleur du joueur suivant (puisque changecolor s'applique)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeColorEffect);
        assertTrue(id_next_player != id_player);

    }   
}