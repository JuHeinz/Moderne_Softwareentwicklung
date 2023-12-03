package org.juheinz.deliveryserver;

import org.juheinz.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides optimized route.
 */
public class RouteService {

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
        recalculateRoute();
    }

    public void removeFromDestinations(int destination) {
        destinations.remove(Integer.valueOf(destination));
        recalculateRoute();
    }

    public void recalculateRoute() {
        //TODO: implement routing algorithm
        navigator.setRoute(destinations);
    }


}
