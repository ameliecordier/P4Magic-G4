package model;
/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de tests de l'effet Delete 
 * Principe de l'effet : un pion joué sur
 * une case portant l'effet Delete ne disparaît pas. Conséquences :
 * l'état du jeu n'est pas modifié, le pion joué apparaît  sur la grille, et la colonne est injouable
 * et le tour de jeu change
 *
 * @author acordier
 */
public class DeleteColumnEffectTest {

    static Game aGame;

    public DeleteColumnEffectTest() {
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
     * - Le pion est ajouté
     * - La colonne est injouable
     * - Passage au joueur suivant
     */

    @Test
    public void testDeleteColumnEffectNormalGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteColumnEffect());


        int id_player = aGame.getCurrentPlayer().getId();


        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();


        aGame.playMove(1);


        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();


        assertTrue("Doit être d'effet deleteColumn", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    /**
     * Test de DeleteColumnEffect sur la grille vide
     * Vérification de l'état de la tuile après application de l'effet 
     * Résultats attendus : la case doit être remplie et la colonne injouable
     */
    @Test
    public void testDeleteColumnEffectEmptyGame() {

        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);


        assertEquals(0, aGame.getBoard().getTileIJ(height - 1, 0).getStatus()-1);
        assertTrue("Doit être d'effet deleteColumn", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

     /**
     * Test de DeleteColumnEffect sur grille vide 
     * Vérification du nombre de jetons
     * après jeu 
     * Résultat attendu : le nombre doit être égal à 0
     */
    @Test
    public void testDeleteColumnEffectEmptyGameWithTilesNumber() {


        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteColumnEffect());


        aGame.playMove(0);


        assertEquals(0, aGame.getBoard().getTotalTilesCount() -1);

    }

    /**
     * Test de DeleteColumnEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case est vide, la colonne injouable
     * l'effet doit être sur la case 
     * et le tour doit être passé
     */
    @Test
    public void testDeleteColumnEffectFilledGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteColumnEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);

        assertEquals(0, aGame.getBoard().getTileIJ(height - 3, 0).getStatus() -1);
        assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

}