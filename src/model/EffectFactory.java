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
    
    static int effect[] = {1,1,1,1};

    /**
     * Returns a random effect chosen amongst the available effects listed
     *
     * @return an effect
     */
    public static Effect createEffect() {
        int random;
        int min = 0;
        int max = 3;
        Random rand = new Random();
        //Tire un nombre aléatoire entre min et max compris
        do {
            random = rand.nextInt(max - min + 1) + min;
        } while (testIn(random));

        switch (random) {

            case 0:
                return new ChangeColorEffect();
            case 1:
                return new DisappearEffect();
            case 2:
                return new RangeChangeColorEffect();
            case 3:
                return new RandomAppearEffect();
        }

        return null;

    }
    
    /**
     * Makes the effect with the number e playable
     *
     * @param e Effect number
     */
    public static void addEffect(int e){
        effect[e]=1;
    }
    
    /**
     * Makes the effect with the number e unplayable
     *
     * @param e Effect number
     */
    public static void remEffect(int e){
        effect[e]=0;
    }
    
    /**
     * Makes the effect with the number e playable
     *
     * @param r Effect number
     * @return boolean which states if the randomized number point on a valid effect or not
     */
    public static boolean testIn(int r){
        return (effect[r]!=1);
    }
    
}
