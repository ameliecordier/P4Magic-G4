/** 
 * MagicP4
 * IUT Lyon 1 - 2017
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for RangeChangeColorEffect
 * @author Caille Loïc - IUT Lyon 1 - 2017
 */
public class RangeChangeColorEffectTest {
    
    static Game aGame;
    
    public RangeChangeColorEffectTest() {
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
     */
    @Test
    public void testRangeChangeColorEffectNormalGame() {
        
        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RangeChangeColorEffect());

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
        assertTrue("Doit être d'effet range change color ", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RangeChangeColorEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);
        assertEquals(nb_tokens_before + 1, nb_tokens_after);
    }
    
    /**
     * Test de RangeChangeColorEffect sur grille vide 
     * Vérification de l'état de la tuile après application de l'effet 
     * Résultats attendus : la case doit être de la couleur opposée à celle 
     * jouée (on ne vérifie pas les cases car dans ce test les autres cases
     * sont vides)
     */
    @Test
    public void testRangeChangeColorEffectEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RangeChangeColorEffect());

        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        

        // Vérifications :
        // - la case est bien de la couleur opposée à celle jouée (donc de la couleur du joueur suivant)
        // - l'effet est bien appliqué sur la case 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertTrue("Doit être d'effet range change color", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof RangeChangeColorEffect);
        assertTrue(aGame.getCurrentPlayer().getId() != id_player);

    }
    
    /**
     * Test de RangeChangeColorEffect sur grille vide 
     * Vérification de l'état des deux tuiles après application de l'effet et deux coups joués
     * Résultats attendus : la case doit être de la couleur opposée à celle 
     * jouée au premier tour puis au deuxième tour la case doit être de la couleur opposée
     * à celle jouée et la case précédement jouée doit changer de couleur.
     * Donc le résultat attendu est que les deux cases doivent être de la couleur du premier
     * joueur
     */
    @Test
    public void testRangeChangeColorEffectTwoTokensEmptyGame() {

        // Effet fixé sur une case 
        int height = aGame.getBoard().getHeight();
        aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RangeChangeColorEffect());
        aGame.getBoard().getTileIJ(height - 2, 0).setEffect(new RangeChangeColorEffect());

        
        // Récupération de l'ID du joueur courant 
        int id_player = aGame.getCurrentPlayer().getId();

        // Coup joué sur la case de l'effet 
        aGame.playMove(0);
        
        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        
        aGame.playMove(0);

        // Vérifications :
        // - les deux cases sont de la couleur du premier joueur
        // - l'effet est bien appliqué sur les cases
        // - le tour de jeu est bien revenu au joueur initial
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
        assertEquals(id_player, aGame.getBoard().getTileIJ(height - 2, 0).getStatus());
        assertTrue("Doit être d'effet range change color", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof RangeChangeColorEffect);
        assertTrue("Doit être d'effet range change color", aGame.getBoard().getTileIJ(height - 2, 0).getEffect() instanceof RangeChangeColorEffect);
        assertTrue(aGame.getCurrentPlayer().getId() == id_player);

    }
    
    /**
     * Test de RangeChangeColorEffect sur grille pré-remplie (avec effet dans les limites de la grille)
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * et des tuiles l'entourant 
     * Résultat attendu : la case est de la couleur opposée à celle jouée
     * l'effet doit être sur la case,
     * les tuiles autour doivent soit avoir changé de couleur soit être vide
     * et le tour doit être passé
     */
    @Test
    public void testRangeChangeColorEffectFilledGame() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 2, 1).setEffect(new RangeChangeColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        
        //Récupération des couleurs autour de la case d'effet avant de jouer le coup
        int[] statusBe = new int[9];
        int kBe=0;
        for (int i=height - 3;i<height;i++){
            for (int j=0;j<3;j++){
                statusBe[kBe]=aGame.getBoard().getTileIJ(i, j).getStatus();
                kBe+=1;
            }
        }
        
        // Coup joué sur cette case 
        aGame.playMove(1);
        
        //Récupération des couleurs autour de la case d'effet après avoir jouer le coup
        int[] statusAf = new int[9];
        int kAf=0;
        for (int i=height - 3;i<height;i++){
            for (int j=0;j<3;j++){
                statusAf[kAf]=aGame.getBoard().getTileIJ(i, j).getStatus();
                kAf+=1;
            }
        }

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        
        
        // Vérifications :
        // - les cases déjà initialisées ont bien changer de couleur
        // - l'effet est bien appliqué sur la case (elle est de la couleur opposée à celle du joueur en cours)
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 2, 1).getStatus());
        assertTrue("Doit être d'effet range change color", aGame.getBoard().getTileIJ(height - 2, 1).getEffect() instanceof RangeChangeColorEffect);
        for (int k=0;k<9;k++){
            assertTrue("Doit être de couleur différente ou sans couleur", ((statusBe[k]!= statusAf[k])||(statusBe[k]==-1)));
        }
        assertTrue(id_next_player != id_player);
    }
    
    /**
     * Test de RangeChangeColorEffect sur grille pré-remplie (avec effet hors des limites de la grille)
     * Vérification de l'état de
     * la tuile après application de l'effet 
     * et des tuiles l'entourant 
     * Résultat attendu : la case est de la couleur opposée à celle jouée
     * l'effet doit être sur la case,
     * les tuiles autour doivent soit avoir changé de couleur soit être vide
     * et le tour doit être passé
     */
    @Test
    public void testRangeChangeColorEffectFilledGameOutOfBound() {

        // On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Effet fixé sur une case (qui n'est pas encore remplie)
        int height = aGame.getBoard().getHeight();
        // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
        aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RangeChangeColorEffect());

        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();
        
        
        //Récupération des couleurs autour de la case d'effet avant de jouer le coup
        int[] statusBe = new int[6];
        int kBe=0;
        for (int i=height - 4;i<height-1;i++){
            for (int j=0;j<2;j++){
                statusBe[kBe]=aGame.getBoard().getTileIJ(i, j).getStatus();
                kBe+=1;
                
            }
        }
        
        // Coup joué sur cette case 
        aGame.playMove(0);
        
        //Récupération des couleurs autour de la case d'effet après avoir joué le coup
        int[] statusAf = new int[6];
        int kAf=0;
        for (int i=height - 4;i<height-1;i++){
            for (int j=0;j<2;j++){
                statusAf[kAf]=aGame.getBoard().getTileIJ(i, j).getStatus();
                kAf+=1;
            }
        }

        // Récupération de l'ID du joueur suivant 
        int id_next_player = aGame.getCurrentPlayer().getId();
        
        
        // Vérifications :
        // - les cases déjà initialisées ont bien changer de couleur
        // - l'effet est bien appliqué sur la case (elle est de la couleur opposée à celle du joueur en cours) 
        // - le tour de jeu a bien changé
        assertEquals(id_next_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
        assertTrue("Doit être d'effet range change color", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RangeChangeColorEffect);
        for (int k=0;k<6;k++){
            assertTrue("Doit être de couleur différente ou sans couleur", (statusBe[k]!=statusAf[k]||statusBe[k]==-1));
        }
        assertTrue(id_next_player != id_player);
    }
}
