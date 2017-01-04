package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardCountTest {

	static Game aGame;

    @Before
    public void setUp() {

        // Cr�ation d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }
    
    /**
     * Test du bon fonctionnement du comptage par joueur
     * resultats attendus : -A vide le compte est de 0 pour chaque joueur
     * -apr�s un coup jou� par un joueur le compte de tuile
     * de ce joueur a �t� incr�mente�
     * -apr�s un coup jou� par l'autre joueur le compte de tuile
     * du premier joueur n'a pas �t� incr�ment�
     */
    @Test
    public void CountPlayerTest(){
    	// R�cup�ration du nombre de pions des joueurs 1 et 2 � vide
        int nb_tokens_1_empty = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_empty = aGame.getBoard().getTilesCountPlayer2();
    	
    	// On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
        int id_player = aGame.getCurrentPlayer().getId();

        // R�cup�ration du nombre de pions des joueurs 1 et 2
        int nb_tokens_1_before = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_before = aGame.getBoard().getTilesCountPlayer2();
        
        // Coup jou� par le premier joueur
        aGame.playMove(0);
        
        // R�cup�ration du nombre de pions apr�s le coup 
        int nb_tokens_1_after = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_after = aGame.getBoard().getTilesCountPlayer2();
        
        // Coup jou� par le second joueur
        aGame.playMove(0);
        
        // R�cup�ration du nombre de pions apr�s le coup 
        int nb_tokens_1_after2 = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_after2 = aGame.getBoard().getTilesCountPlayer2();
        
        // V�rifications :
        // - Le compte � vide est nul pour les deux joueurs
        // - Les deux joueurs ont leurs compteurs incr�ment�s une seule fois
        assertEquals(nb_tokens_1_empty,0);
        assertEquals(nb_tokens_2_empty,0);
        if(id_player == 1){
        	assertEquals(nb_tokens_1_before,nb_tokens_1_after-1);
        	assertEquals(nb_tokens_1_after,nb_tokens_1_after2);
        }
        else{
        	assertEquals(nb_tokens_2_before,nb_tokens_2_after-1);
        	assertEquals(nb_tokens_2_after,nb_tokens_2_after2);
        }
    }
    
    /**
     * Test du bon fonctionnement du comptage total
     * resultats attendus : -A vide le compte est de 0
     * -apr�s un coup jou� par un joueur le compte de tuile
     * a �t� incr�ment�
     */
    @Test
    public void CountTotalTest(){
    	// R�cup�ration du nombre de pions � vide
        int nb_tokens_empty = aGame.getBoard().getTotalTilesCount();
    	
    	// On pr�-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // R�cup�ration du nombre de pions avant le coup
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup jou� par le premier joueur
        aGame.playMove(0);
        
        // R�cup�ration du nombre de pions apr�s le coup
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        // V�rifications :
        // - Le compte � vide est nul
        // - Apr�s un coup jou� le compte est incr�m�nt�
        assertEquals(nb_tokens_empty,0);
        assertEquals(nb_tokens_before,nb_tokens_after-1);
    }
}
