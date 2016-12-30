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
 * Classe de tests de l'effet DeleteLine 
 * Principe de l'effet : un pion joué sur
 * une case portant l'effet DeleteLine est ajouté. Conséquences :
 * l'état du jeu n'est pas modifié, le pion joué apparaît sur la grille, la ligne est injouable
 * et le tour de jeu change
 *
 * @author acordier
 */
public class DeleteLineEffectTest {

    static Game aGame;

    public DeleteLineEffectTest() {
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
     * - La ligne est injouable
     * - Passage au joueur suivant
     */
    @Test
    public void testDeleteLineEffectNormalGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteLineEffect());


        int id_player = aGame.getCurrentPlayer().getId();


        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();


        aGame.playMove(1);


        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();


        assertTrue("Doit être d'effet deleteLine", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    /**
     * Test de DeleteLineEffect sur la grille vide
     * Vérification de l'état de la tuile après application de l'effet 
     * Résultats attendus : la case doit être remplie et la ligne injouable
     */
    @Test
    public void testDeleteLineEffectEmptyGame() {

        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteLineEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);


        assertEquals(0, aGame.getBoard().getTileIJ(height - 1, 0).getStatus()-1);
        assertTrue("Doit être d'effet deleteLine", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DeleteLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

     /**
     * Test de DeleteLineEffect sur grille vide 
     * Vérification du nombre de jetons
     * après jeu 
     * Résultat attendu : le nombre doit être égal à 0
     */
    @Test
    public void testDeleteLineEffectEmptyGameWithTilesNumber() {


        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DeleteLineEffect());


        aGame.playMove(0);


        assertEquals(0, aGame.getBoard().getTotalTilesCount() -1);

    }

    /**
     * Test de DeleteLineEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case est remplie, la ligne injouable
     * l'effet doit être sur la case 
     * et le tour doit être passé
     */
    @Test
    public void testDeleteLineEffectFilledGame() {


        Utils.simulateAGame(aGame);


        int height = aGame.getBoard().getHeight();

        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DeleteLineEffect());

 
        int id_player = aGame.getCurrentPlayer().getId();


        aGame.playMove(0);

        assertEquals(0, aGame.getBoard().getTileIJ(height - 3, 0).getStatus() -1);
        assertTrue("Doit être d'effet deleteLine", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DeleteLineEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

}