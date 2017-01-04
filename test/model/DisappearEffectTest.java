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
     * Classe de tests de l'effet Disappear 
     * Principe de l'effet : un pion jou� sur
     * une case portant l'effet Disappear dispara�t imm�diatement. Cons�quences :
     * l'�tat du jeu n'est pas modifi�, le pion jou� n'appara�t pas sur la grille,
     * et le tour de jeu change
     *
     * @author acordier
     */
    public class DisappearEffectTest {
     
        static Game aGame;
     
        public DisappearEffectTest() {
        }
     
        @BeforeClass
        public static void setUpClass() {
     
        }
     
        @AfterClass
        public static void tearDownClass() {
        }
     
        @Before
        public void setUp() {
     
            // Cr�ation d'un jeu vide
            aGame = new Game();
            Board b = new Board(10, 10);
            aGame.setBoard(b);
     
        }
     
        @After
        public void tearDown() {
        }
     
        /**
         * Test du bon fonctionnement du jeu, en dehors de l'effet R�sultats
         * attendus apr�s le coup : - un pion de plus sur le plateau - le tour de
         * jeu est pass� - l'effet a bien �t� appliqu�
         */
        @Test
        public void testDisappearEffectNormalGame() {
     
            // On pr�-remplit le plateau pour les besoins de la simulation 
            Utils.simulateAGame(aGame);
     
            // Effet fix� sur une case (qui n'est pas encore remplie)
            int height = aGame.getBoard().getHeight();
            // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
            aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearEffect());
     
            // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
            int id_player = aGame.getCurrentPlayer().getId();
     
            // R�cup�ration du nombre de pions pr�sents 
            int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
     
            // Coup jou� sur une case ne contenant pas l'effet 
            aGame.playMove(1);
     
            // R�cup�ration du nombre de pions apr�s le coup 
            int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
     
            // V�rifications :
            // - l'effet est bien appliqu� sur la case 
            // - le tour de jeu a bien chang�
            // - il y a bien un pion de plus sur le plateau
            assertTrue("Doit �tre d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
            assertEquals(nb_tokens_before + 1, nb_tokens_after);
        }
     
        /**
         * Test de DisappearEffect sur grille vide 
         * V�rification de l'�tat de la
         * tuile apr�s application de l'effet 
         * R�sultats attendus : la case doit �tre
         * vide, le tour de jeu doit �tre pass�
         */
        @Test
        public void testDisappearEffectEmptyGame() {
     
            // Effet fix� sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearEffect());
     
            // R�cup�ration de l'ID du joueur courant 
            int id_player = aGame.getCurrentPlayer().getId();
     
            // Coup jou� sur la case de l'effet 
            aGame.playMove(0);
     
            // V�rifications :
            // - la case est bien vide apr�s
            // - l'effet est bien appliqu� sur la case 
            // - le tour de jeu a bien chang�
            assertEquals(-1, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
            assertTrue("Doit �tre d'effet disappear", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof DisappearEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
     
        }
     
        /**
         * Test de DisappearEffect sur grille vide 
         * V�rification du nombre de jetons
         * apr�s jeu 
         * R�sultat attendu : le nombre doit �tre �gal � 0
         */
        @Test
        public void testDisappearEffectEmptyGameWithTilesNumber() {
     
            // Effet fix� sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new DisappearEffect());
     
            // Coup jou� sur cette case 
            aGame.playMove(0);
     
            // V�rification que le nombre de jetons au total est �gal � 0  
            assertEquals(0, aGame.getBoard().getTotalTilesCount());
     
        }
     
        /**
         * Test de DisappearEffect sur grille pr�-remplie 
         * V�rification de l'�tat de
         * la tuile apr�s application de l'effet 
         * R�sultat attendu : la case doit
         * �tre vide, l'effet doit �tre sur la case 
         * et le tour doit �tre pass�
         */
        @Test
        public void testDisappearEffectFilledGame() {
     
            // On pr�-remplit le plateau pour les besoins de la simulation 
            Utils.simulateAGame(aGame);
     
            // Effet fix� sur une case (qui n'est pas encore remplie)
            int height = aGame.getBoard().getHeight();
            // height-3 correspond � la premi�re case vide dans la colonne O, vu que l'on a d�j� jou� deux coups dans cette colonne
            aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new DisappearEffect());
     
            // R�cup�ration de l'ID du joueur avant que le coup soit jou� 
            int id_player = aGame.getCurrentPlayer().getId();
     
            // Coup jou� sur cette case 
            aGame.playMove(0);
     
            // V�rifications :
            // - la case est bien vide apr�s
            // - l'effet est bien appliqu� sur la case 
            // - le tour de jeu a bien chang�
            assertEquals(-1, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
            assertTrue("Doit �tre d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof DisappearEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
     
        }
     
    }

