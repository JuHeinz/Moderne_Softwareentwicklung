package org.juheinz.deliveryserver;

import org.juheinz.navigation.GPS;
import org.juheinz.navigation.NavigationProvider;
import org.juheinz.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides optimized route.
 */
public class RouteService {

    private static final RouteService ROUTE_SERVICE = new RouteService();

    private static final List<double[]> destinations = new ArrayList<>();
    private Navigator navigator;

    private RouteService() {
    }

    public static RouteService getInstance(Navigator navigator) {
        ROUTE_SERVICE.navigator = navigator;
        return ROUTE_SERVICE;
    }

    public void addToDestinations(double[] destination) {
        destinations.add(destination);
        recalculateRoute();
    }

    public void removeFromDestinations(double[] coordinateToRemove) {
        destinations.removeIf(coordinate -> areCoordinatesEqual(coordinate, coordinateToRemove));
        recalculateRoute();
    }

    private static boolean areCoordinatesEqual(double[] coord1, double[] coord2) {
        return coord1[0] == coord2[0] && coord1[1] == coord2[1];
    }

    public void recalculateRoute() {
        navigator.setRoute(destinations);

        String drivingInstruction = NavigationProvider.getRoute(destinations.get(0), GPS.getCurrentLocation());
        navigator.setDrivingInstruction(drivingInstruction);
    }






}
