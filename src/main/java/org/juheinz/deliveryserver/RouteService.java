package org.juheinz.deliveryserver;

import org.juheinz.navigation.GPS;
import org.juheinz.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides optimized route.
 */
public class RouteService {
    private int calculatedRoute;

    private static final RouteService ROUTE_SERVICE = new RouteService();

    private static final List<Integer> destinations = new ArrayList<>();
    private Navigator navigator;

    private RouteService() {
    }

    public static RouteService getInstance(Navigator navigator) {
        ROUTE_SERVICE.navigator = navigator;
        return ROUTE_SERVICE;
    }

    public void addToDestinations(int destination) {
        destinations.add(destination);
    }

    public void recalculateRoute() {
        int currentLocation = GPS.getCurrentLocation();
        //TODO: implement route calculation
        this.calculatedRoute = currentLocation + 1;
    }

    public void sendRouteToNavigator() {
        navigator.setRoute("Please go here: " + calculatedRoute);
    }


}
