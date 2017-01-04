package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardCountTest {

	static Game aGame;

    @Before
    public void setUp() {

        // Création d'un jeu vide
        aGame = new Game();
        Board b = new Board(10, 10);
        aGame.setBoard(b);

    }
    
    /**
     * Test du bon fonctionnement du comptage par joueur
     * resultats attendus : -A vide le compte est de 0 pour chaque joueur
     * -après un coup joué par un joueur le compte de tuile
     * de ce joueur a été incrémenteé
     * -après un coup joué par l'autre joueur le compte de tuile
     * du premier joueur n'a pas été incrémenté
     */
    @Test
    public void CountPlayerTest(){
    	// Récupération du nombre de pions des joueurs 1 et 2 à vide
        int nb_tokens_1_empty = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_empty = aGame.getBoard().getTilesCountPlayer2();
    	
    	// On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);
        
        // Récupération de l'ID du joueur avant que le coup soit joué 
        int id_player = aGame.getCurrentPlayer().getId();

        // Récupération du nombre de pions des joueurs 1 et 2
        int nb_tokens_1_before = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_before = aGame.getBoard().getTilesCountPlayer2();
        
        // Coup joué par le premier joueur
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup 
        int nb_tokens_1_after = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_after = aGame.getBoard().getTilesCountPlayer2();
        
        // Coup joué par le second joueur
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup 
        int nb_tokens_1_after2 = aGame.getBoard().getTilesCountPlayer1();
        int nb_tokens_2_after2 = aGame.getBoard().getTilesCountPlayer2();
        
        // Vérifications :
        // - Le compte à vide est nul pour les deux joueurs
        // - Les deux joueurs ont leurs compteurs incrémentés une seule fois
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
     * -après un coup joué par un joueur le compte de tuile
     * a été incrémenté
     */
    @Test
    public void CountTotalTest(){
    	// Récupération du nombre de pions à vide
        int nb_tokens_empty = aGame.getBoard().getTotalTilesCount();
    	
    	// On pré-remplit le plateau pour les besoins de la simulation 
        Utils.simulateAGame(aGame);

        // Récupération du nombre de pions avant le coup
        int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
        
        // Coup joué par le premier joueur
        aGame.playMove(0);
        
        // Récupération du nombre de pions après le coup
        int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
        
        // Vérifications :
        // - Le compte à vide est nul
        // - Après un coup joué le compte est incréménté
        assertEquals(nb_tokens_empty,0);
        assertEquals(nb_tokens_before,nb_tokens_after-1);
    }
}
