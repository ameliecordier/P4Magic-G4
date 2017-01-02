package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ColorColumnEffect
 * @author Alice
 */
public class ColorColumnEffectTest {
    
    static Game aGame;
    
    public ColorColumnEffectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        // CrÃ©ation d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test du bon fonctionnement du jeu, en dehors de l'effet 
     * RÃ©sultats attendus aprÃ¨s le coup : 
     * - un pion de plus sur le plateau 
     * - le tour de jeu est passÃ© 
     * - l'effet a bien Ã©tÃ© appliquÃ©
     */
    @Test
    public void testColorColumnEffectNormalGame() {

        // On prÃ©-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixÃ© sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        
        // height-3 correspond Ã  la premiÃ¨re case vide dans la colonne O, vu que l'on a dÃ©jÃ  jouÃ© deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ColorColumnEffect());

        // RÃ©cupÃ©ration de l'ID du joueur avant que le coup soit jouÃ© 
        int id_player = aGame.getCurrentPlayer().getId();

        // RÃ©cupÃ©ration du nombre de pions prÃ©sents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup jouÃ© sur une case ne contenant pas l'effet 
        aGame.playMove(1);

        // RÃ©cupÃ©ration du nombre de pions aprÃ¨s le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        

        // VÃ©rifications :
        // - l'effet est bien appliquÃ© sur la case 
        // - le tour de jeu a bien changÃ©
        // - il y a bien 1 pion de plus sur le plateau
        assertTrue("Doit Ãªtre d'effet color column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ColorColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    /**
     * Test de ColorColumnEffect sur grille vide 
     * VÃ©rification de l'Ã©tat de la tuile aprÃ¨s application de l'effet 
     * RÃ©sultats attendus : la case doit Ãªtre de la couleur jouÃ©e, le tour de
     * jeu doit Ãªtre passÃ©
     */
    @Test
    public void testColorColumnEffectEmptyGame() {

        // Effet fixÃ© sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ColorColumnEffect());

        // RÃ©cupÃ©ration de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup jouÃ© sur la case de l'effet 
        aGame.playMove(0);
                

        // VÃ©rifications :
        // - la case est bien de la couleur de celle jouÃ©e
        // - l'effet est bien appliquÃ© sur la case 
        // - le tour de jeu a bien changÃ©
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit Ãªtre d'effet color column", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof ColorColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    /**
     * Test de ColorColumnEffect sur grille vide 
     * VÃ©rification du nombre de jetons aprÃ¨s jeu 
     * RÃ©sultat attendu : le nombre doit Ãªtre Ã©gal Ã  10
     */
    @Test
    public void testColorColumnEffectEmptyGameWithTilesNumber() {

        // Effet fixÃ© sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ColorColumnEffect());

        // Coup jouÃ© sur cette case 
        aGame.playMove(0);

        // VÃ©rification que le nombre de jetons au total est Ã©gal Ã  0  
        assertEquals(10, aGame.getBoard().getTotalTilesCount());

    }
    
    /**
     * Test de ColorColumnEffect sur grille prÃ©-remplie 
     * VÃ©rification de l'Ã©tat de la tuile aprÃ¨s application de l'effet 
     * RÃ©sultat attendu : la case doit Ãªtre remplie, l'effet doit Ãªtre sur la 
     * case et le tour doit Ãªtre passÃ©
     */
    @Test
    public void testColorColumnEffectFilledGame() {

        // On prÃ©-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixÃ© sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond Ã  la premiÃ¨re case vide dans la colonne O, vu que l'on a dÃ©jÃ  jouÃ© deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new ColorColumnEffect());

        // RÃ©cupÃ©ration de l'ID du joueur avant que le coup soit jouÃ© 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup jouÃ© sur cette case 
        aGame.playMove(0);

        // VÃ©rifications :
        // - la case est bien de la couleur du joueur courant
        // - l'effet est bien appliquÃ© sur la case 
        // - le tour de jeu a bien changÃ©
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit Ãªtre d'effet color column", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof ColorColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    
}

