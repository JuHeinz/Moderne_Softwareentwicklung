package org.juheinz.deliveryserver;


import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;

import java.time.LocalDateTime;

/**
 * Receives updates on the parcel status and updates the parcel repository as well as the route service.
 */
public class ParcelManager {

    private ParcelRepository parcelRepository;
    private RouteService routeService;


    private static final ParcelManager parcelManager = new ParcelManager();

    private ParcelManager() {
    }

    public static ParcelManager getInstance(ParcelRepository parcelRepository, RouteService routeService) {
        parcelManager.parcelRepository = parcelRepository;
        parcelManager.routeService = routeService;

        return parcelManager;
    }

    public void setParcelLoaded(Parcel parcel, LocalDateTime timestamp) {
        parcel.setLoaded(true);
        parcel.setLoadedTime(timestamp);
        int destination = parcelRepository.getDestination(parcel);
        parcel.setDestination(destination);
        routeService.addToDestinations(destination);
        parcelRepository.updateParcelStatus(parcel, Status.LOADED);
    }

    public void setParcelDelivered(Parcel parcel, LocalDateTime timestamp) {
        parcel.setDelivered(true);
        parcel.setDeliveredTime(timestamp);
        parcelRepository.updateParcelStatus(parcel, Status.DELIVERED);
        routeService.recalculateRoute();
        routeService.sendRouteToNavigator();
    }

    public void setDeliveryFailure(Parcel parcel) {
        parcelRepository.updateParcelStatus(parcel, Status.FAILED_DELIVERY);
    }


}
