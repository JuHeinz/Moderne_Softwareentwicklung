package org.juheinz.deliveryserver;

import org.juheinz.navigation.GPS;
import org.juheinz.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the deliveryPersons current location and provides optimized route after each successful delivery
 */
public class RouteService {
    private int calculatedRoute;

    private static final RouteService rs = new RouteService();

    private static final List<Integer> destinations = new ArrayList<>();
    private Navigator navigator;

    private RouteService() {
        System.out.println("RouteService Singleton created");
    }

    public static RouteService getInstance(Navigator n) {
        rs.navigator = n;
        return rs;
    }

    public void addToDestinations(int destination) {
        destinations.add(destination);
    }

    public void recalculateRoute() {
        int currentLocation = GPS.getCurrentLocation();
        // Imagine fancy algorithm
        this.calculatedRoute = currentLocation + 1;
    }

    public void sendRouteToNavi() {
        navigator.setRoute("Please go here: " + calculatedRoute);
    }


}
