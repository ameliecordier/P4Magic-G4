package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExplodeSurroundingsEffectTest {
	static Game aGame;

    @Before
    public void setUp() {

        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }
    
    
    /**
     * Test du bon fonctionnement du jeu, en dehors de l'effet Résultats
     * attendus après le coup : - un pion de plus sur le plateau - le tour de
     * jeu est passé - l'effet a bien été appliqué
     */
    @Test
    public void testExplodeSurroundingsTokensNormalGame(){
    	// On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à  la première case vide dans la colonne O, vu que l'on a déjà  joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions présents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup joué sur une case ne contenant pas l'effet 
        aGame.playMove(0);

        // Récupération du nombre de pions après le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // Vérifications :
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        // - il y a bien un pion de plus sur le plateau
        assertTrue("Doit àªtre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille vide 
     * Vérification de l'état de la
     * tuile après application de l'effet 
     * Résultats attendus : la case doit àªtre
     * vide, le tour de jeu doit àªtre passé
     */
    @Test
    public void testExplodeSurroundingsTokensEmptyGame(){
    	// Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ExplodeSurroundingsEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);

        // Vérifications :
        // - la case est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit àªtre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille vide 
     * Vérification du nombre de jetons
     * après jeu 
     * Résultat attendu : le nombre doit àªtre égal à  0
     */
    @Test
    public void testExplodeSurroundingsEffectEmptyGameWithTilesNumber() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ExplodeSurroundingsEffect());

        // Coup joué sur cette case 
        aGame.playMove(0);

        // Vérification que le nombre de jetons au total est égal à  0  
        assertEquals(0, aGame.getBoard().getTotalTilesCount());

    }
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille pré-remplie 
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * Résultat attendu : les case de gauche et de droite doivent
     * àªtre vide, l'effet doit àªtre sur la case 
     * et le tour doit àªtre passé
     */
    @Test
    public void testExplodeSurroundingsEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à  la première case vide dans la colonne O, vu que l'on a déjà  joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette case 
        aGame.playMove(1);

        // Vérifications :
        // - les cases adjacentes de la ligne sont bien vides après
        // - la case en dessous est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 1).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 2).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 1).getStatus());
        assertTrue("Doit àªtre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    /**
     * Test de la gravité générée par 
     * ExplodeSurroundingsEffect sur grille pré-remplie 
     * Vérification de l'état de la
     * tuiles en haut à gauche après application de l'effet 
     * Résultat attendu : la case de gauche doit
     * àªtre vide, la case de gauche doit
     * avoir la couleur de la case du dessus précédente l'effet doit àªtre sur la case 
     * et le tour doit àªtre passé
     */
    @Test
    public void testExplodeSurroundingsEffectGravity() {
    	// On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à  la première case vide dans la colonne O, vu que l'on a déjà  joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur cette la case en haut à gauche de la case ciblée 
        aGame.playMove(0);
        
     // Coup joué sur cette cette case
        aGame.playMove(1);

        // Vérifications :
        // - les cases adjacentes de la ligne sont bien vides après
        // - la case en dessous est bien vide après
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertTrue("Doit être d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() == id_player);
    }
}
