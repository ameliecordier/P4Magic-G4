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
     * @param usableEffects array containing the effects players want to use
     * @return an effect
     */
    public static Effect createEffect(Effect[] usableEffects) {

        int min = 0;
        int max = (usableEffects.length) - 1;
        Random rand = new Random();
        //Tire un nombre aléatoire entre min et max compris
        int random = rand.nextInt(max - min + 1) + min;
        
        return usableEffects[random];

    }
    
}
