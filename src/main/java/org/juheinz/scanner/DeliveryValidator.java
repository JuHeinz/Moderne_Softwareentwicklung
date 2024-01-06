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

        Double distance = NavigationProvider.getDistance(currentLocation,parcelDestination);
        System.out.println("Current Location is + " + currentLocation[0] + "/" +currentLocation[1]);
        System.out.println("Destination is + " + parcelDestination[0] + "/" +parcelDestination[1]);
        System.out.println("Distance is " + distance);
        return distance < maximumRange;

    }


}
