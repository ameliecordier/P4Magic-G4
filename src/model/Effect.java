/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

/**
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 */
public abstract class Effect {
    
    //Par défaut les effets on une durée de vie ilimitée
    int lifeTime=-1;

    //line and column are in the coordinates of the token that has just been played
    /**
     * Méthode jouant l'effet
     * 
     * @param line
     * @param column
     * @param game 
     */
    public abstract void playEffect(int line, int column, Game game);
    
    /**
     * Méthode permettant de décrémenter la life time d'un effet si elle n'est pas infinie ou nulle
     * De plus elle revoit vrai ou faux si l'effet doit être joué ou non
     * 
     * @return booléen vrai si l'effet doit être joué, faux sinon
     */
    public boolean play(){
        if (lifeTime==-1){
            return true;
        } else if (lifeTime > 0){
            lifeTime--;
            return true;
        }else{
            return false;
        }
    }

}
