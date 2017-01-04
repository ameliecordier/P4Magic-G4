package model;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Classe de tests de l'effet Random Tile
 * Principe de l'effet : un pion joué sur une case portant l'effet Random Tile
 * ajoute un pion au hasard dans une des colonnes.
 * Conséquences : Un autre effet peut être joué si le pion arrive dessus
 * et le tour de jeu change
 * @author p1517348
 */
public class RandomTileEffectTest {
    static Game aGame;

    public RandomTileEffectTest() {
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
    public void testRandomTileEffectNormalGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RandomTileEffect());

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
        assertTrue("Doit être d'effet Random Tile", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RandomTileEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }

    /**
     * Test de RandomTileEffect sur grille vide 
     * Vérification de l'état de la
     * tuile après application de l'effet 
     * Résultats attendus : la case doit être
     * remplie, une autre case est jouée, le tour de jeu doit être passé
     */
    @Test
    public void testRandomTileEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RandomTileEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();
        
        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - 2 jetons ont été jouée sur la grille
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(2, nb_tokens_after);
        assertTrue("Doit être d'effet Random Tile", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof RandomTileEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }

    /**
     * Test de RandomTileEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : la case doit
     * être remplie, l'effet doit être sur la case, un 2e jeton doit être joué 
     * et le tour doit être passé
     */
    @Test
    public void testRandomTileEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RandomTileEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérifications :
        // - la case est bien remplie après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(1, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet Random Tile", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RandomTileEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    public void testRandomTileEffectOtherEffect () {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RandomTileEffect());
        
        //Autres effets fixés sur des cases non remplies
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new RandomTileEffect());
        aGame.getBoard().getTileIJ(height - 3, 2).setEffect(new ChangeColorEffect());
        aGame.getBoard().getTileIJ(height - 2, 3).setEffect(new DisappearEffect());
        aGame.getBoard().getTileIJ(height - 3, 4).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette case 
        aGame.playMove(0);
        
        // Vérifications
        // - la case jouée est bien remplie après
        // - le 2e jeton joue l'effet de sa case s'il existe
        // - le tour de jeu a bien changé
        
        assertEquals(1, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        if (aGame.getBoard().getTileIJ(height - 2, 1).getStatus()==1)
        {
            assertTrue("Doit être d'effet Random Tile", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof RandomTileEffect);
        }
        else if (aGame.getBoard().getTileIJ(height - 3, 2).getStatus()==1)
        {
            assertTrue("Doit être d'effet Change Color", aGame.getBoard().getTileIJ(height - 3, 2).getEffect() instanceof ChangeColorEffect);
        }
        else if (aGame.getBoard().getTileIJ(height - 2, 3).getStatus()==1)
        {
            assertTrue("Doit être d'effet Disappear", aGame.getBoard().getTileIJ(height - 2, 3).getEffect() instanceof DisappearEffect);
        }
        else if (aGame.getBoard().getTileIJ(height - 3, 4).getStatus()==1)
        {
            assertTrue("Doit être d'effet Disappear Column", aGame.getBoard().getTileIJ(height - 3, 4).getEffect() instanceof DisappearColumnEffect);
        }
        
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
}
