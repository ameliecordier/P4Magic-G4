package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests de l'effet ColumnDisappearEffect
 * Principe de l'effet : lorsqu'un pion est joué
 * sur la case portant l'effet ColumnDisappearEffect,
 * toutes les cases de la colonnes deviennent vides.
 * Conséquences : la colonne où a été joué le pion est vide, le pion n'est pas affiché
 * le tour de jeu change
 * 
 * @author p1502007
 */
public class ColumnDisappearEffectTest {
    
    static Game aGame;
    
    public ColumnDisappearEffectTest() {
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
     * Test du bon fonctionnement du jeu en dehors de l'effet.
     * .
     */
    @Test
    public void testColumnDisappearEffectNormalGame(){
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ColumnDisappearEffect());

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
        assertTrue("Doit être d'effet columnDisappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ColumnDisappearEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    @Test
    public void testColumnDisappearEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 2, 0).setEffect(new ColumnDisappearEffect());
        
        //Coup joué pour commencer
        aGame.playMove(0);
        
        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - la colonne est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertTrue("Doit être d'effet columnDisappear", aGame.getBoard().getTileIJ(height - 2, 0).getEffect() instanceof ColumnDisappearEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }   
}
