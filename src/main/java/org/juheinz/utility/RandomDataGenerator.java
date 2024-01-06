package org.juheinz.utility;

import java.util.Random;

/**
 * Utility class doe generating test data.
 */
public class RandomDataGenerator {

    public static double[] generateRandomCoordinate(){
        double minLat = -90;
        double maxLat = 90;
        double minLon = -180;
        double maxLon = 180;
        Random rand = new Random();

        // Generate random latitude and longitude within the specified ranges
        double randomLat = minLat + (maxLat - minLat) * rand.nextDouble();
        double randomLon = minLon + (maxLon - minLon) * rand.nextDouble();

        return new double[]{randomLat, randomLon};
    }
}
