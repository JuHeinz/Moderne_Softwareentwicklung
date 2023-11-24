package org.juheinz.appserver;

import org.juheinz.entities.AppUser;
import org.juheinz.entities.Package;
import org.juheinz.userapp.UI;

import java.time.LocalDateTime;

public class UserSession {

    private final AppUser user;
    private final Package p;
    private final UI ui;

    public UserSession(AppUser user, Package p) {
        this.user = user;
        this.p = p;
        log("New session for user: " + user.getUserID());
        ui = new UI(p);
    }

    public void updateLoaded() {
        ui.sendNotification(user.getUserID() + " | Ihr Paket mit Nr. " + p.getCode() + " wurde um " + p.getLoadedTime() + " eingeladen.");
    }

    public void updateFailure() {
        ui.sendNotification(user.getUserID() + " | Ihr Paket mit Nr. " + p.getCode() + " konnte nicht zugestellt werden.");

    }

    public void updateDelivered() {
        ui.sendNotification(user.getUserID() + " | Ihr Paket mit Nr. " + p.getCode() + " wurde um " + p.getDeliveredTime() + " zugestellt.");
    }

    public void updateArrivalTime() {
        //fancy algorithm
        LocalDateTime arrivalTime = LocalDateTime.now();
        ui.sendNotification(user.getUserID() + "| Änderung in Ankunftszeit für Ihr Paket mit Nr." + p.getCode() + " : " + arrivalTime);
    }

    public void updateVanLocation() {
        //fancy algorithm
        String vanLocation = "000000";
        ui.sendNotification(user.getUserID() + "| Das Fahrzeug mit ihrem Paket hat sich bewegt: " + vanLocation);
    }


    private void log(String message) {
        System.out.println(">> USER SESSION: " + message);
    }
}
