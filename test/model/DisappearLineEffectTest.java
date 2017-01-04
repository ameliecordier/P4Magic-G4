package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * MagicP4 IUT Lyon 1 - 2016
 */
public class DisappearLineEffectTest {

    static Game aGame;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDisappearEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearLineEffect());

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
        assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    @Test
    public void testDisappearLineEffectblock() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new DisappearLineEffect());

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien un pion de plus sur le plateau
        aGame.playMove(1);
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        assertEquals(nb_tokens_before - 4, nb_tokens_after);
    }

    @Test
    public void testDisappearLineEffectEmpiler() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new DisappearLineEffect());

        if (aGame.getCurrentPlayer().getId() == 1) {
            aGame.playMove(8);
        }
        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        int id_play1 = aGame.getCurrentPlayer().getId();
        aGame.playMove(0);
        int id_play2 = aGame.getCurrentPlayer().getId();
        aGame.playMove(2);

        // Vérifications :
        // - que l'empilage s'est bien effectué
        // - il y a bien un pion de plus sur le plateau
        aGame.playMove(1);
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        assertEquals(aGame.getBoard().getTileIJ(height - 3, 0).getStatus(), -1);
        assertEquals(aGame.getBoard().getTileIJ(height - 3, 2).getStatus(), -1);
        assertEquals(aGame.getBoard().getTileIJ(height - 2, 0).getStatus(), id_play1);
        assertEquals(aGame.getBoard().getTileIJ(height - 2, 2).getStatus(), id_play2);
        assertEquals(nb_tokens_before - 3, nb_tokens_after);
    }

}
