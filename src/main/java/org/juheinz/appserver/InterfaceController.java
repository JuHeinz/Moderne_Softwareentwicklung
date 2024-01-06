package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.User;
import org.juheinz.navigation.GPS;
import org.juheinz.userapp.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Controller for app's user interface.
 */
public class InterfaceController {

    private final Parcel parcel;
    private final UserInterface userInterface;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * @param user   the user for whom an interface is created
     * @param parcel the parcel of the user
     */
    public InterfaceController(User user, Parcel parcel) {
        this.parcel = parcel;
        userInterface = new UserInterface(user);
    }

    public void updateLoaded() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + formatter.format(parcel.getLoadedTime()) + " eingeladen.");
    }

    public void updateFailure() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " konnte nicht zugestellt werden.");

    }

    public void updateDelivered() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + formatter.format(parcel.getDeliveredTime()) + " zugestellt.");
    }

    public void updateTrackingInformation() {
        //TODO: implement arrival time algorithm & get current van location
        LocalDateTime arrivalTime = LocalDateTime.now();
        double[] currentLocation = GPS.getCurrentLocation();
        userInterface.sendNotification(" Ihr Paket kommt näher! Ankunft vrsl. " + formatter.format(arrivalTime) + ". Aktueller Standort:" + Arrays.toString(currentLocation));
    }


}
