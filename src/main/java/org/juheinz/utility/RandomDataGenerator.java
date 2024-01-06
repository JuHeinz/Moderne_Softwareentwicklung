package org.juheinz.utility;

import java.util.Random;

/**
 * Utility class for generating test data.
 */
public class RandomDataGenerator {

    public static double[] generateRandomCoordinate(){
        //Max GPS values LAT: -90 to 90, LONG: -180 to 180
        //Roughly Berlin Area: LAT: 52.8 to 52.2 LONG: 12.9 to 14
        double minLat = 12.9;
        double maxLat = 14;
        double minLon = 52.2;
        double maxLon = 52.8;
        Random rand = new Random();

        // Generate random latitude and longitude within the specified ranges
        double randomLat = minLat + (maxLat - minLat) * rand.nextDouble();
        double randomLon = minLon + (maxLon - minLon) * rand.nextDouble();

        return new double[]{randomLat, randomLon};
    }
}
