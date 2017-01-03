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

        // Cr�ation d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }
    
    
    /**
     * Test du bon fonctionnement du jeu, en dehors de l'effet R�sultats
     * attendus apr�s le coup : - un pion de plus sur le plateau - le tour de
     * jeu est pass� - l'effet a bien �t� appliqu�
     */
    @Test
    public void testExplodeSurroundingsTokensNormalGame(){
    	// On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fix� sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();

        // R�cup�ration du nombre de pions pr�sents 
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();

        // Coup jou� sur une case ne contenant pas l'effet 
        aGame.playMove(0);

        // R�cup�ration du nombre de pions apr�s le coup 
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();

        // V�rifications :
        // - l'effet est bien appliqu� sur la case 
        // - le tour de jeu a bien chang�
        // - il y a bien un pion de plus sur le plateau
        assertTrue("Doit �tre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille vide 
     * V�rification de l'�tat de la
     * tuile apr�s application de l'effet 
     * R�sultats attendus : la case doit �tre
     * vide, le tour de jeu doit �tre pass�
     */
    @Test
    public void testExplodeSurroundingsTokensEmptyGame(){
    	// Effet fix� sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ExplodeSurroundingsEffect());

        // R�cup�ration de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup jou� sur la case de l'effet 
        aGame.playMove(0);

        // V�rifications :
        // - la case est bien vide apr�s
        // - l'effet est bien appliqu� sur la case 
        // - le tour de jeu a bien chang�
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit �tre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
    }
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille vide 
     * V�rification du nombre de jetons
     * apr�s jeu 
     * R�sultat attendu : le nombre doit �tre �gal � 0
     */
    @Test
    public void testExplodeSurroundingsEffectEmptyGameWithTilesNumber() {

        // Effet fix� sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new ExplodeSurroundingsEffect());

        // Coup jou� sur cette case 
        aGame.playMove(0);

        // V�rification que le nombre de jetons au total est �gal � 0  
        assertEquals(0, aGame.getBoard().getTotalTilesCount());

    }
    
    /**
     * Test de ExplodeSurroundingsEffect sur grille pr�-remplie 
     * V�rification de l'�tat de
     * la tuile apr�s application de l'effet 
     * R�sultat attendu : les case de gauche et de droite doivent
     * �tre vide, l'effet doit �tre sur la case 
     * et le tour doit �tre pass�
     */
    @Test
    public void testExplodeSurroundingsEffectFilledGame() {

        // On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fix� sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup jou� sur cette case 
        aGame.playMove(1);

        // V�rifications :
        // - les cases adjacentes de la ligne sont bien vides apr�s
        // - la case en dessous est bien vide apr�s
        // - l'effet est bien appliqu� sur la case 
        // - le tour de jeu a bien chang�
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 1).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 2, 2).getStatus());
        assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 1).getStatus());
        assertTrue("Doit �tre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    /**
     * Test de la gravit� g�n�r�e par 
     * ExplodeSurroundingsEffect sur grille pr�-remplie 
     * V�rification de l'�tat de la
     * tuiles en haut � gauche apr�s application de l'effet 
     * R�sultat attendu : la case de gauche doit
     * �tre vide, la case de gauche doit
     * avoir la couleur de la case du dessus pr�c�dente l'effet doit �tre sur la case 
     * et le tour doit �tre pass�
     */
    @Test
    public void testExplodeSurroundingsEffectGravity() {
    	// On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fix� sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new ExplodeSurroundingsEffect());

        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup jou� sur cette la case en haut � gauche de la case cibl�e 
        aGame.playMove(0);
        
     // Coup jou� sur cette cette case
        aGame.playMove(1);

        // V�rifications :
        // - les cases adjacentes de la ligne sont bien vides apr�s
        // - la case en dessous est bien vide apr�s
        // - l'effet est bien appliqu� sur la case 
        // - le tour de jeu a bien chang�
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertTrue("Doit �tre d'effet explodeSurroundings", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof ExplodeSurroundingsEffect);
        assertTrue(aGame.getCurrentPlayer().getId() == id_player);
    }
}
