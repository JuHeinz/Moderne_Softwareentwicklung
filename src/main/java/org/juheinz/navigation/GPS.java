package org.juheinz.navigation;

import org.juheinz.utility.RandomDataGenerator;

/**
 * Provides current location of delivery van.
 */
public class GPS {

    public static double[] getCurrentLocation() {
        return RandomDataGenerator.generateRandomCoordinate();
    }

}
