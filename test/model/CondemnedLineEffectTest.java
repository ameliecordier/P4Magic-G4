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
public class CondemnedLineEffectTest {

    static Game aGame;

    public CondemnedLineEffectTest() {
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
     * Test du bon fonctionnement du jeu, en dehors de l'effet Résultats
     * attendus après le coup : - un pion de plus sur le plateau - le tour de
     * jeu est passé - l'effet a bien été appliqué
     */
    @Test
    public void testCondemnedLineEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new CondemnedLineEffect());

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
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof CondemnedLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    /**
     * Test du bon fonctionnement de l'effet On place l'effect sur une ligne
     * vide, on joue sur la case contenant l'effet Puis on jouera un second coup
     * afin de vérifier que les pions ne tombent plus sous la ligne en question
     * Résultats attendus après le premier coup :
     * - x pions de plus sur le plateau où x est le nombre de cases de la ligne
     * - le tour de jeu est passé
     * - l'effet a bien été appliqué
     * - la ligne a bien le statut condamné
     * Résultats attendus après le premier coup :
     * - 1 pion de plus sur le plateau
     * - le tour de jeu est passé
     * - le pion joué est bien tombé au dessus de la ligne condamnée
     * - la case sous la ligne condamnée est resté vide
     */
    @Test
    public void testCondemnedLineEffectNormalGameBis() {

        Utils.simulateAGame(aGame);

        int height = aGame.getBoard().getHeight();
        int width = aGame.getBoard().getWidth();
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new CondemnedLineEffect());

        // PREMIER COUP
        // // Récupération avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
 
        aGame.playMove(0);

        // // Récupération après que le coup soit joué 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien x pions de plus sur le plateau où x est le nombre de cases de la ligne
        assertTrue("Doit être d'effet change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof CondemnedLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + width, nb_tokens_after);
        for(int column = 0; column<width ; column++) {
            assertEquals(403, aGame.getBoard().getTileIJ(height-3, column).getStatus());
        }

        // SECOND COUP
        // // Récupération avant que le coup soit joué
        id_player = aGame.getCurrentPlayer().getId();
        nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        int beforeColorUp = aGame.getBoard().getTileIJ(height-4, width-1).getStatus();
        int beforeColorDown = aGame.getBoard().getTileIJ(height-2, width-1).getStatus();
        
        aGame.playMove(width-1);
        
        // // Récupération après que le coup soit joué
        nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        int afterColorUp = aGame.getBoard().getTileIJ(height-4, width-1).getStatus();
        int afterColorDown = aGame.getBoard().getTileIJ(height-2, width-1).getStatus();
        
        // Vérifications :
        // - le tour de jeu a bien changé
        // - il y a bien 1 pion de plus sur le plateau
        // - le pion joué se situe bien au dessus de la ligne condamnée
        // - la case sous la ligne condamnée est restée vide (statut = -1)
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
        assertTrue(beforeColorUp != afterColorUp);
        assertTrue(beforeColorDown == afterColorDown);
        assertTrue(beforeColorDown == -1);
    }

}
