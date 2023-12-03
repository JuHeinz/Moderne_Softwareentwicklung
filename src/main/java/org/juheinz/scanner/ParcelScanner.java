package org.juheinz.scanner;

import org.juheinz.deliveryserver.ParcelManager;
import org.juheinz.entities.Parcel;
import org.juheinz.navigation.GPS;
import org.juheinz.utility.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Software on handheld scanner with delivery person. Updates a parcels status when barcode is scanned.
 */
public class ParcelScanner {
    private ParcelManager parcelManager;

    private final List<Parcel> loadedParcels = new ArrayList<>();

    private static final ParcelScanner PARCEL_SCANNER = new ParcelScanner();

    Logger logger;
    private ParcelScanner() {
        this.logger = new Logger("scanner");
    }

    public static ParcelScanner getInstance(ParcelManager parcelManager) {
        PARCEL_SCANNER.parcelManager = parcelManager;
        return PARCEL_SCANNER;
    }

    public void scanParcelAsLoaded(int scannedCode, LocalDateTime loadedTimeStamp) {
        Parcel parcel = new Parcel(scannedCode);
        parcelManager.setParcelLoaded(parcel, loadedTimeStamp);
        locallySaveLoadedParcel(parcel);
        logger.log("Parcel " + parcel.getId() + " loaded.", "admin");
    }

    public void scanParcelAsDelivered(int scannedCode, LocalDateTime deliveredTimestamp) {
        //get loaded parcel from earlier
        Parcel deliveredParcel = findByCode(loadedParcels, scannedCode);
        if (deliveredParcel != null) {
            boolean validDelivery = DeliveryValidator.matchLocationDestination(deliveredParcel.getDestination(), GPS.getCurrentLocation());
            if (validDelivery) {
                parcelManager.setParcelDelivered(deliveredParcel, deliveredTimestamp);
                logger.log("Zustellung von " + deliveredParcel.getId() + " erfolgreich!", "zusteller");
                loadedParcels.remove(deliveredParcel);
            } else {
                parcelManager.setDeliveryFailure(deliveredParcel);
                logger.log("Fehler bei Zustellung! Sie befinden sich nicht am richtigen Standort!", "zusteller");
            }
        } else {
            logger.log("Für den Code " + scannedCode + " gibt es kein Paket in ihrem Wagen", "zusteller");
        }
    }

    private void locallySaveLoadedParcel(Parcel parcel) {
        loadedParcels.add(parcel);
    }

    private static Parcel findByCode(Collection<Parcel> list, int code) {
        return list.stream().filter(parcel -> (code == parcel.getId())).findFirst().orElse(null);
    }


}
