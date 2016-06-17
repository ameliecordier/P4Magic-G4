package model;

/**
 * MagicP4 IUT Lyon 1 - 2016
 */
import static model.DisappearEffectTest.aGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests de l'effet Disappear Principe de l'effet : un pion joué sur
 * une case portant l'effet Disappear disparaît immédiatement. Conséquences :
 * l'état du jeu n'est pas modifié, le pion joué n'apparaît pas sur la grille,
 * et le tour de jeu change
 *
 * @author acordier
 */
public class ChangeNeighborhoodColorEffectTest {

    static Game aGame;

    public ChangeNeighborhoodColorEffectTest() {
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
    public void testChangeNeighborhoodColorEffect() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearEffect());

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
        // - les couleurs du voisinage ont bien changé
        assertTrue("Doit être d'effet changeNeighborhoodColor", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ChangeNeighborhoodColorEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
}
