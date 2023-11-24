package org.juheinz.deliveryserver;


import org.juheinz.entities.Package;
import org.juheinz.entities.Status;

import java.time.LocalDateTime;

public class PackageManager {

    private PackageRepository packageRepository;
    private RouteService routeService;


    private static final PackageManager pm = new PackageManager();

    private PackageManager() {
        System.out.println("PackageManager Singleton created");
    }

    public static PackageManager getInstance(PackageRepository packageRepository, RouteService routeService) {
        pm.packageRepository = packageRepository;
        pm.routeService = routeService;

        return pm;
    }

    public void setPackageLoaded(Package p, LocalDateTime timestamp) {
        //Set package data
        p.setLoaded(true);
        p.setLoadedTime(timestamp);
        int destination = packageRepository.getDestination(p);
        p.setDestination(destination);
        routeService.addToDestinations(destination);
        packageRepository.updatePackageStatus(p, Status.LOADED);
    }

    public void setPackageDelivered(Package p, LocalDateTime timestamp) {
        p.setDelivered(true);
        p.setDeliveredTime(timestamp);
        packageRepository.updatePackageStatus(p, Status.DELIVERED);
        routeService.recalculateRoute();
        routeService.sendRouteToNavi();
    }

    public void setDeliveryFailure(Package p) {
        packageRepository.updatePackageStatus(p, Status.FAILED_DELIVERY);
    }


}
