/**
 * MagicP4
 * IUT Lyon 1 - 2016
 */
package model;

import java.util.Random;

/**
 * Class EffectFactory
 *
 * @author hakkahi - IUT Lyon 1 - 2016
 *
 */
public class EffectFactory {

    /**
     * Returns a random effect chosen amongst the available effects listed
     *
     * @return an effect
     */
    public static Effect createEffect() {
        Random rand = new Random();
        //Tire un nombre aléatoire entre 0 et 5
        int random = Math.abs(rand.nextInt(6));
        
        //Un effet est appliqué en fonction du nombre tiré au hasard
        switch (random) {

            case 0:
                return new AddTileEffect();
            case 1:
                return new DisappearEffect();
            case 2:
                return new AddTileRandomEffect();
            case 3:
                return new DeleteColumnEffect();
            case 4:
                return new DeleteLineEffect();
            case 5:
                return new ChangeColorEffect();
        }

        return null;

    }

}
