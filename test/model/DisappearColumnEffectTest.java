package model;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Alice
 */
public class DisappearColumnEffectTest {
    static Game aGame;
    
    public DisappearColumnEffectTest() {
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
    public void testColumnDisappearEffectNormalGame() {
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à  la première case vide dans la colonne O, vu 
        //que l'on a déjà  joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(1);

        // A CHANGER
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien une colonne de pions en moins sur le plateau
        assertTrue("Doit être d'effet DisappearColumn", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    
    
    /**
     * Test de ColumnDisappearEffect sur grille vide 
     * Vérification de l'état de la colonne après application de l'effet 
     * Résultats attendus : la colonne doit être vide, aucun pion ne doit être rajouté
     */
    @Test
    public void DisappearColumnEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
      
        // Vérifications :
        // - la case est bien vide
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet DisappearColumn", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    /**
     * Test de ColumnDisappearEffect sur grille vide 
     * Vérification de l'état de la tuile après application de l'effet et deux coups joués
     * Résultat attendu : il ne doit plus y avoir de pions dans la colonne
     */
    @Test
    public void testDisappearColumnEffecTwoTokensEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());
        aGame.getBoard().getTileIJ(height - 2, 0).setEffect(new DisappearColumnEffect());

        
        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - les cases n'appartiennt plus à  aucun joueur, elles sont vides
        // - l'effet est bien appliqué sur les cases
        // - le tour de jeu est bien revenu au joueur initial
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertTrue("Doit être d'effet Column Disappear", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue("Doit être d'effet Column Disappear", aGame.getBoard().getTileIJ(height - 2, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(aGame.getCurrentPlayer().getId() == id_player);

    }
    
    
    /**
     * Test de ColumnDisappearEffect sur grille vide 
     * Vérification du nombre de jetons
     * après jeu 
     * Résultat attendu : le nombre doit être égal à 0
     */
    @Test
    public void testDisappearColumnEffectEmptyGameWithTilesNumber() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearColumnEffect());

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérification que le nombre de jetons au total est égal à  1  
        assertEquals(0, aGame.getBoard().getTotalTilesCount());

    }

    /**
     * Test de ColumnDisappearEffect sur grille pré-remplie 
     * Vérification de l'état de la colonne après application de l'effet 
     * Résultat attendu : la colonne doit être vide et le tour doit être passé
     */
    @Test
    public void testDisappearColumnEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à  la première case vide dans la colonne O, vu que l'on a déjà  joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearColumnEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_player);

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        System.out.println(id_next_player);
        
        // Vérifications :
        // - la colonne est bien vide
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet Column Disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearColumnEffect);
        assertTrue(id_next_player != id_player);

    }
    
}