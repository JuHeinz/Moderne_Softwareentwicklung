package org.juheinz.scanner;

/**
 * Validates given location against desired parcel destination
 */
public class DeliveryValidator {

    /**
     * Currently just compares two randomly generated numbers
     *
     * @param currentLocation   of van
     * @param parcelDestination recipient location
     * @return boolean if destinations match
     */
    public static boolean matchLocationDestination(int currentLocation, int parcelDestination) {
        return currentLocation >= parcelDestination;
    }


}
