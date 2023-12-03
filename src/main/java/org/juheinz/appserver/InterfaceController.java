package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.User;
import org.juheinz.userapp.UserInterface;

import java.time.LocalDateTime;

/**
 * Controller for app's user interface.
 */
public class InterfaceController {

    private final User user;
    private final Parcel parcel;
    private final UserInterface userInterface;

  /** @param user the user for whom an interface is created
    * @param parcel the parcel of the user
   * */
    public InterfaceController(User user, Parcel parcel) {
        this.user = user;
        this.parcel = parcel;
        userInterface = new UserInterface(user);
    }

    public void updateLoaded() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + parcel.getLoadedTime() + " eingeladen.");
    }

    public void updateFailure() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " konnte nicht zugestellt werden.");

    }

    public void updateDelivered() {
        userInterface.sendNotification(" Ihr Paket mit Nr. " + parcel.getId() + " wurde um " + parcel.getDeliveredTime() + " zugestellt.");
    }

    public void updateArrivalTime() {
        //TODO: implement arrival time algorithm
        LocalDateTime arrivalTime = LocalDateTime.now();
        userInterface.sendNotification(" Änderung in Ankunftszeit für Ihr Paket mit Nr." + parcel.getId() + " : " + arrivalTime);
    }

    public void updateVanLocation() {
        //TODO: implement van location getter
        String vanLocation = "000000";
        userInterface.sendNotification(" Das Fahrzeug mit ihrem Paket hat sich bewegt: " + vanLocation);
    }

}
