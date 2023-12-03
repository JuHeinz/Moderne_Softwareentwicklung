package org.juheinz.navigation;

/**
 * Provides current location of delivery van.
 */
public class GPS {

    public static int getCurrentLocation() {
        return (int) ((Math.random() * (10 - 1)) + 1);

    }

}
