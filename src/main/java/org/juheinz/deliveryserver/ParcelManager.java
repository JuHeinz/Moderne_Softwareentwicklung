package org.juheinz.deliveryserver;


import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;

import java.time.LocalDateTime;

/**
 * Receives updates on the parcel status and updates the parcel repository as well as the route service.
 */
public class ParcelManager {

    private static final ParcelManager parcelManager = new ParcelManager();
    private ParcelRepository parcelRepository;
    private RouteService routeService;

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
        double[] destination = parcelRepository.getDestination(parcel);
        parcel.setDestination(destination);
        routeService.addToDestinations(destination);
        parcelRepository.updateParcelStatus(parcel, Status.LOADED);
    }

    public void setParcelDelivered(Parcel parcel, LocalDateTime timestamp) {
        if(parcel.isLoaded()){
            parcel.setDelivered(true);
            parcel.setDeliveredTime(timestamp);
            parcelRepository.updateParcelStatus(parcel, Status.DELIVERED);
            routeService.removeFromDestinations(parcel.getDestination());
        }
    }

    public void setDeliveryFailure(Parcel parcel) {
        parcelRepository.updateParcelStatus(parcel, Status.FAILED_DELIVERY);
    }


}
