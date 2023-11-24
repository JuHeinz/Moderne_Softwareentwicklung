package org.juheinz.scanner;

public class DeliveryValidator {

    public static boolean matchLocationDestination(int currentLocation, int packageDestination) {
        //fancy algorithm goes here
        //simulate 20% failure rate
        return currentLocation <= 8;
    }


}
