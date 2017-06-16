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
     * Classe de tests de l'effet Ajout Random 
     * Principe de l'effet : un pion joué sur
     * une case portant l'effet Ajout Random fait apparaître un piont sur le jeu.
     *
     * @author Caille Loïc - Valentin Gilles
     */
    public class RandomAppearEffectTest {
     
        static Game aGame;
     
        public RandomAppearEffectTest() {
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
        public void testRandomAppearEffectNormalGame() {
     
            // On pré-remplit le plateau pour les besoins de la simulation 
            Utils.simulateAGame(aGame);
     
            // Effet fixé sur une case (qui n'est pas encore remplie)
            int height = aGame.getBoard().getHeight();
            // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
            aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RandomAppearEffect());
     
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
            // - il y a bien 1 pion de plus sur le plateau
            assertTrue("Doit être d'effet Ajout Random", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RandomAppearEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
            assertEquals(nb_tokens_before + 1, nb_tokens_after);
        }
     
        /**
         * Test de DisappearEffect sur grille vide 
         * Vérification de l'état de la
         * tuile après application de l'effet 
         * Résultats attendus : la case doit être
         * de la couleur du joueur, 
         * le tour de jeu doit être passé
         */
        @Test
        public void testRandomAppearEffectEmptyGame() {
     
            // Effet fixé sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RandomAppearEffect());
     
            // Récupération de l'ID du joueur courant 
            int id_player = aGame.getCurrentPlayer().getId();
            
            // Coup joué sur la case de l'effet 
            aGame.playMove(0);
     
            // Vérifications :
            // - la case est bien vide après
            // - l'effet est bien appliqué sur la case 
            // - le tour de jeu a bien changé
            assertEquals(id_player, aGame.getBoard().getTileIJ(height - 1, 0).getStatus());
            assertTrue("Doit être d'effet Ajout Random", aGame.getBoard().getTileIJ(height - 1, 0).getEffect() instanceof RandomAppearEffect);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
     
        }
     
        /**
         * Test de RandomAppear sur grille vide 
         * Vérification du nombre de jetons
         * après jeu 
         * Résultat attendu : le nombre doit être égal à 2
         */
        @Test
        public void testRandomAppearEffectEmptyGameWithTilesNumber() {
     
            // Effet fixé sur une case 
            int height = aGame.getBoard().getHeight();
            aGame.getBoard().getTileIJ(height - 1, 0).setEffect(new RandomAppearEffect());
     
            // Coup joué sur cette case 
            aGame.playMove(0);
     
            // Vérification que le nombre de jetons au total est égal à 2  
            assertEquals(2, aGame.getBoard().getTotalTilesCount());
     
        }
     
        /**
         * Test de RandomAppearEffect sur grille pré-remplie 
         * Vérification de l'état de
         * la tuile après application de l'effet 
         * Résultat attendu : la case doit
         * être de la couleur du joueur, l'effet doit être sur la case,
         * un nouveau pion de la couleur du joueur apparaît
         * et le tour doit être passé
         */
        @Test
        public void testRandomAppearEffectFilledGame() {
     
            // On pré-remplit le plateau pour les besoins de la simulation 
            Utils.simulateAGame(aGame);
     
            // Effet fixé sur une case (qui n'est pas encore remplie)
            int height = aGame.getBoard().getHeight();
            // height-3 correspond à la première case vide dans la colonne O, vu que l'on a déjà joué deux coups dans cette colonne
            aGame.getBoard().getTileIJ(height - 3, 0).setEffect(new RandomAppearEffect());
     
            // Récupération de l'ID du joueur avant que le coup soit joué 
            int id_player = aGame.getCurrentPlayer().getId();
            
            //Indication sur les valeurs de la grille
            int[][] statusBe = new int[height][aGame.getBoard().getWidth()];
            for (int i=height-1;i>=0;i--){
                for (int j = aGame.getBoard().getWidth()-1;j>=0;j--){
                    if (aGame.getBoard().getTileIJ(i, j).getStatus()!=-1){
                        statusBe[i][j]=0;
                    } else {
                        statusBe[i][j]=1;
                    }
                        
                }
            }
            
            // Récupération du nombre de pions présents 
            int nb_tokens_before = aGame.getBoard().getTotalTilesCount();
     
            // Coup joué sur cette case 
            aGame.playMove(0);
            
            // Récupération du nombre de pions après le coup 
            int nb_tokens_after = aGame.getBoard().getTotalTilesCount();
            
            // Vérifications :
            // - la case est bien de la couleur du joueur
            // - l'effet est bien appliqué sur la case 
            // - un nouveau pion de la couleur du joueur est bien apparut
            // - le tour de jeu a bien changé
            assertEquals(id_player, aGame.getBoard().getTileIJ(height - 3, 0).getStatus());
            assertTrue("Doit être d'effet disappear", aGame.getBoard().getTileIJ(height - 3, 0).getEffect() instanceof RandomAppearEffect);
            for (int i=height-1;i>=0;i--){
                for (int j = aGame.getBoard().getWidth()-1;j>=0;j--){
                    if ((aGame.getBoard().getTileIJ(i, j).getStatus()!=-1)&&(statusBe[i][j]==1)){
                        assertEquals(id_player, aGame.getBoard().getTileIJ(i, j).getStatus());
                    }
                }
            }
            assertEquals(nb_tokens_before + 2, nb_tokens_after);
            assertTrue(aGame.getCurrentPlayer().getId() != id_player);
     
        }
     
    }

