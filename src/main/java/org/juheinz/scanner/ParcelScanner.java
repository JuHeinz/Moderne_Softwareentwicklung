package org.juheinz.scanner;

import org.juheinz.deliveryserver.ParcelManager;
import org.juheinz.entities.Parcel;
import org.juheinz.navigation.GPS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParcelScanner {
    private ParcelManager parcelManager;

    private final List<Parcel> loadedParcels = new ArrayList<>();

    private static final ParcelScanner PARCEL_SCANNER = new ParcelScanner();

    private ParcelScanner() {
        System.out.println("ParcelScanner Singleton created");
    }

    public static ParcelScanner getInstance(ParcelManager pm) {
        PARCEL_SCANNER.parcelManager = pm;
        return PARCEL_SCANNER;
    }

    public void scanParcelAsLoaded(int scannedCode, LocalDateTime loadedTimeStamp) {
        Parcel parcel = new Parcel(scannedCode);
        parcelManager.setParcelLoaded(parcel, loadedTimeStamp);
        locallySaveLoadedParcel(parcel);
        showNotification("Parcel " + parcel.getId() + " loaded.");
    }

    public void scanParcelAsDelivered(int scannedCode, LocalDateTime deliveredTimestamp) {
        //get loaded parcel from earlier
        Parcel deliveredParcel = findByCode(loadedParcels, scannedCode);
        if (deliveredParcel != null) {
            boolean validDelivery = DeliveryValidator.matchLocationDestination(deliveredParcel.getDestination(), GPS.getCurrentLocation());
            if (validDelivery) {
                parcelManager.setParcelDelivered(deliveredParcel, deliveredTimestamp);
                showNotification("(to staff) Zustellung von " + deliveredParcel + " erfolgreich!");
                loadedParcels.remove(deliveredParcel);
            } else {
                parcelManager.setDeliveryFailure(deliveredParcel);
                showNotification(" (to staff) Fehler bei Zustellung! Sie befinden sich nicht am richtigen Standort!");
            }
        } else {
            showNotification("(to staff) Für den Code " + scannedCode + " gibt es kein Paket in ihrem Wagen");
        }
    }

    private void locallySaveLoadedParcel(Parcel p) {
        loadedParcels.add(p);
    }

    private static Parcel findByCode(Collection<Parcel> list, int code) {
        return list.stream().filter(p -> (code == p.getId())).findFirst().orElse(null);
    }

    private void showNotification(String message) {
        System.out.println(">> PACKAGE SCANNER: " + message);
    }
}
