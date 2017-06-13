
package model;

//Fait disparaître la colonne dans laquelle le pion est joué

public class ColonneEffect extends Effect {

    @Override
    public void playEffect(int line, int column, Game game) {
        for(int i=0;i<game.getBoard().getHeight();i++){
            game.getBoard().getTileIJ(i, column).setStatus(-1);
        }
    }
    
}
