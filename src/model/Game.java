/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Random;
import static model.EffectFactory.createEffect;

/**
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 *
 */
public final class Game extends Observable {

    private int _posPreview;
    private Board _board;
    private final Player _player1;
    private final Player _player2;
    private Player _currentPlayer;
    private int _winner;
    private boolean _over;
    private boolean _debugMode; //true si le jeu est en mode Debug, false sinon
    
    private int _choiceWidth; //largeur de la grille pouvant être modifée par le joueur;
    private int _choiceHeight; //hauteur de la grille pouvant être modifée par le joueur;

    /**
     * Game constructor A game has two players... for now.
     */
    public Game() {

        this._winner = -1;
        this._over = false;
        
        this._player1 = new HumanPlayer(1, Color.RED);
        this._player2 = new HumanPlayer(2, Color.YELLOW);
        
        this._currentPlayer = this._player1;
        //On imagine que la case de séléction du mode débug est décochée par défaut et que la taille présélectionnée est 10*10 tiles.
        this._debugMode=false;
        this._choiceWidth=10;
        this._choiceHeight=10;
        
        
        init();

    }

    
    
    /**
     * Game initialiser
     */
    public void init() {

        resetPosPreview();

    }
    
    /**
     * Méthode permettant de changer la valeur du boolean debugMode
     * Cette méthode doit être appelée lorsque le joueur coche ou décoche la case de séléction du mode débug.
     */
    public void changeDebugMode() {
        if (_debugMode){
            _debugMode = false;
        } else {
            _debugMode = true;
        }
    }
    
    /**
     * Cette méthode doit être appelée lorsque le joueur modifie la largeur de grille
     * @param w largeur de la grille choisie par le joueur
     */
    public void setChoiceWidth(int w){
        _choiceWidth = w;
    }
    
    /**
     * Cette méthode doit être appelée lorsque le joueur modifie la hauteur de grille
     * @param h hauteur de la grille choisie par le joueur
     */
    public void setChoiceHeight(int h){
        _choiceHeight = h;
    }
    
    /**
     * Renvoie la hauteur de la grille choisie par le joueur
     * @return _choiceHeight
     */
    public int getChoiceHeight(){
        return(_choiceHeight);
    }
    
    /**
     * Renvoie la largeur de la grille choisie par le joueur
     * @return _choiceWidth
     */
    public int getChoiceWidth(){
        return(_choiceWidth);
    }
    
    
    /**
     * Renvoi true si le jeu est en mode debug, false sinon
     * 
     * @return 
     */
    public boolean getDebugMode(){
        return _debugMode;
    }
    
    
    /**
     * Changes the color of P1 to the new color stated
     * (If P1 and P2 arent already of that color)
     * 
     * @param c new color for P1
     */
    public void changeColorP1(Color c){
        if (c != _player1.getColor() && c != _player2.getColor()){
            _player1.setColor(c);
        }
    }
    
    /**
     * Changes the color of P2 to the new color stated
     * (If P1 and P2 arent already of that color)
     * 
     * @param c new color for P2
     */
    public void changeColorP2(Color c){
        if (c != _player1.getColor() && c != _player2.getColor()){
            _player2.setColor(c);
        }
    }

    /**
     * Play move: given the id of a column, the token of the current player is
     * played in this column. The token falls in the column until it cannot fall
     * anymore
     *
     * @param column
     */
    public void playMove(int column) {

        int i;

        if (this._board.getTileIJ(0, column).getStatus() == -1) {

            for (i = 0; i < this._board.getHeight(); ++i) {

                if (this._board.getTileIJ(i, column).getStatus() != -1) {
                    break;
                }

            }

            if (i > 0) {

                i--;
                this._board.getTileIJ(i, column).setStatus(this._currentPlayer.getId());

            }

            if (this._board.getTileIJ(i, column).getEffect() != null) {
                this._board.getTileIJ(i, column).getEffect().playEffect(i, column, this);
            }

            Player tmp = Win();
            if (tmp != null) {
                this._winner = tmp.getId();
            }

            isOver();

            if (this._currentPlayer.getId() == this._player1.getId()) {
                _currentPlayer = _player2;
            } else {
                _currentPlayer = _player1;
            }

            setChanged();
            notifyObservers();
        }
        
    }
    
    /**
     * Méthode jouant un coup sans changer de joueur
     * ni jouer d'effet s'il y en a
     * (utile pour les effets qui rajoutent des pions suplémentaires)
     * 
     * @param column 
     */
    public void playMoveNoChange(int column) {
        
        int i;

        if (this._board.getTileIJ(0, column).getStatus() == -1) {
            
            for (i = 0; i < this._board.getHeight(); ++i) {

                if (this._board.getTileIJ(i, column).getStatus() != -1) {
                    break;
                }

            }

            if (i > 0) {

                i--;
                this._board.getTileIJ(i, column).setStatus(this._currentPlayer.getId());

            }

            Player tmp = Win();
            if (tmp != null) {
                this._winner = tmp.getId();
            }

            isOver();

            setChanged();
            notifyObservers();
        }
    }
    

    /**
     * Make sure the player can play in the column
     *
     * @param column
     * @return a boolean indicating if the player can play or not
     */
    public boolean strokeIsValid(int column) {
        return this._board.getTileIJ(0, column).getStatus() == -1;
    }

    /**
     * Check if there is a winner or not
     *
     * @return id of the winner, or null if there is no winner
     */
    public Player Win() {

        //Look for win combinations on lines
        for (int i = 0; i < this._board.getHeight(); ++i) {

            for (int j = 0; j <= (this._board.getWidth() - 4); ++j) {

                if (this._player1.getId() == this._board.getTileIJ(i, j).getStatus() && this._player1.getId() == this._board.getTileIJ(i, j + 1).getStatus() && this._player1.getId() == this._board.getTileIJ(i, j + 2).getStatus() && this._player1.getId() == this._board.getTileIJ(i, j + 3).getStatus()) {
                    return _player1;
                }

                if (this._player2.getId() == this._board.getTileIJ(i, j).getStatus() && this._player2.getId() == this._board.getTileIJ(i, j + 1).getStatus() && this._player2.getId() == this._board.getTileIJ(i, j + 2).getStatus() && this._player2.getId() == this._board.getTileIJ(i, j + 3).getStatus()) {
                    return _player2;
                }

            }

        }

        //Look for win combinations on columns
        for (int i = 0; i < this._board.getWidth(); ++i) {

            for (int j = 0; j <= (this._board.getHeight() - 4); ++j) {

                if (this._player1.getId() == this._board.getTileIJ(j, i).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 1, i).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 2, i).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 3, i).getStatus()) {
                    return _player1;
                }

                if (this._player2.getId() == this._board.getTileIJ(j, i).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 1, i).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 2, i).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 3, i).getStatus()) {
                    return _player2;
                }

            }

        }

        //Look for win combinations on right diags
        for (int i = 0; i <= (this._board.getWidth() - 4); ++i) {

            for (int j = 0; j <= (this._board.getHeight() - 4); ++j) {

                if (this._player1.getId() == this._board.getTileIJ(j, i).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 1, i + 1).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 2, i + 2).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 3, i + 3).getStatus()) {
                    return _player1;
                }

                if (this._player2.getId() == this._board.getTileIJ(j, i).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 1, i + 1).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 2, i + 2).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 3, i + 3).getStatus()) {
                    return _player2;
                }

            }

        }

        //Vérification des diagonales gauches
        for (int i = 3; i < this._board.getWidth(); ++i) {

            for (int j = 0; j <= (this._board.getHeight() - 4); ++j) {

                if (this._player1.getId() == this._board.getTileIJ(j, i).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 1, i - 1).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 2, i - 2).getStatus() && this._player1.getId() == this._board.getTileIJ(j + 3, i - 3).getStatus()) {
                    return _player1;
                }

                if (this._player2.getId() == this._board.getTileIJ(j, i).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 1, i - 1).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 2, i - 2).getStatus() && this._player2.getId() == this._board.getTileIJ(j + 3, i - 3).getStatus()) {
                    return _player2;
                }

            }

        }

        return null;

    }

    /**
     * Check if the game is over
     */
    public void isOver() {

        for (int i = 0; i < this._board.getHeight(); ++i) {

            for (int j = 0; j < this._board.getWidth(); j++) {

                if (this._board.getTileIJ(i, j).getStatus() == -1) {
                    return;
                }

            }

        }

        this._over = true;

    }

    /**
     * Reset the game
     */
    public void resetGame() {

        this._board.resetBoard();
        this._winner = -1;
        this._currentPlayer = this._player1;

        setChanged();
        notifyObservers();

    }

    /**
     * Reset the previw
     */
    public void resetPosPreview() {
        setPosPreview(-1);
    }

    /**
     * Set effects on the tiles (randomly)
     */
    public void setTilesEffect() {

        for (int i = 0; i < this._board.getHeight(); ++i) {

            for (int j = 0; j < this._board.getWidth(); ++j) {

                Random rand = new Random();
                //Tire un nombre aléatoire entre min et max compris
                int random = rand.nextInt(100 - 1 + 1) + 1;

                if (random <= this._board.getTileEffectChance()) {
                    this._board.getTileIJ(i, j).setEffect(createEffect());
                }

            }

        }

    }

    /**
     * Set the preview
     *
     * @param i
     */
    public void setPosPreview(int i) {

        this._posPreview = i;
        setChanged();
        notifyObservers();

    }

    /**
     * Set the board for the game
     *
     * @param board
     */
    public void setBoard(Board board) {
        this._board = board;
    }

    /**
     * Get the preview
     *
     * @return the pos preview
     */
    public int getPosPreview() {
        return _posPreview;
    }

    /**
     * Get the board of the game
     *
     * @return board
     */
    public Board getBoard() {
        return this._board;
    }

    /**
     * Get the current player id
     *
     * @return id of the player
     */
    public Player getCurrentPlayer() {
        return this._currentPlayer;
    }

    /**
     * Get a player from its id
     *
     * @param id
     * @return a player
     */
    public Player getPlayerById(int id) {

        if (id == this._player1.getId()) {
            return this._player1;
        } else if (id == this._player2.getId()) {
            return this._player2;
        } else {
            return null;
        }

    }

    /**
     * Get the winner of the game
     *
     * @return id of the winner
     */
    public int getWinner() {
        return this._winner;
    }

    /**
     * Is the game over ?
     *
     * @return true if the game is over
     */
    public boolean getOver() {
        return this._over;
    }

    /**
     * Get player 1
     *
     * @return player 1
     */
    public Player getPlayer1() {
        return this._player1;
    }

    /**
     * Get player 2
     *
     * @return player 2
     */
    public Player getPlayer2() {
        return this._player2;
    }

}
