package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.User;
import org.juheinz.userapp.UserInterface;

import java.time.LocalDateTime;

public class UserSession {

    private final User user;
    private final Parcel parcel;
    private final UserInterface userInterface;

    public UserSession(User user, Parcel parcel) {
        this.user = user;
        this.parcel = parcel;
        log("New session for user: " + user.getId());
        userInterface = new UserInterface(parcel);
    }

    public void updateLoaded() {
        userInterface.sendNotification(user.getId() + " | Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + parcel.getLoadedTime() + " eingeladen.");
    }

    public void updateFailure() {
        userInterface.sendNotification(user.getId() + " | Ihr Paket mit Nr. " + parcel.getId() + " konnte nicht zugestellt werden.");

    }

    public void updateDelivered() {
        userInterface.sendNotification(user.getId() + " | Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + parcel.getDeliveredTime() + " zugestellt.");
    }

    public void updateArrivalTime() {
        //fancy algorithm
        LocalDateTime arrivalTime = LocalDateTime.now();
        userInterface.sendNotification(user.getId() + "| Änderung in Ankunftszeit für Ihr Paket mit Nr." + parcel.getId() + " : " + arrivalTime);
    }

    public void updateVanLocation() {
        //fancy algorithm
        String vanLocation = "000000";
        userInterface.sendNotification(user.getId() + "| Das Fahrzeug mit ihrem Paket hat sich bewegt: " + vanLocation);
    }


    private void log(String message) {
        System.out.println(">> USER SESSION: " + message);
    }
}
