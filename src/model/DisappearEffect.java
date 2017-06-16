/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi / acordier - IUT Lyon 1 - 2016
 */
public class DisappearEffect extends Effect {

    /**
     * This effect make it so that the tile played disapear
     * 
     * @param line position sur la ligne de la case sur laquelle on ajoute l'effet
     * @param column position sur la colone de la case sur laquelle on ajoute l'effet
     * @param game jeu dans lequel on ajoute l'effet
     */
    @Override
    public void playEffect(int line, int column, Game game) {
        if (play()){
            game.getBoard().getTileIJ(line, column).setStatus(-1);
        }
    }

}
