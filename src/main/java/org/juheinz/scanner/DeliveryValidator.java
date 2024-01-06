package org.juheinz.scanner;

import org.juheinz.navigation.NavigationProvider;

/**
 * Validates given location against desired parcel destination
 */
public class DeliveryValidator {


    /**
     * Compares the correntLocation of the van with the location of the parcels destination and returns true if they are within a range of each other.
     * Calls microservice
     *
     * @param currentLocation   of van
     * @param parcelDestination recipient location
     * @param maximumRange  below this range, the delivery is considered validated
     * @return boolean if destinations are within range of each other
     */
    public static boolean matchLocationDestination(double[] currentLocation, double[] parcelDestination, int maximumRange) {
        double distance = getDistance(currentLocation, parcelDestination);
        return distance < maximumRange;
    }

    public static double getDistance(double[] currentLocation, double[] parcelDestination){
        return NavigationProvider.getDistance(currentLocation,parcelDestination);
    }


}
