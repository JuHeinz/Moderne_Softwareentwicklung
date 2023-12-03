package org.juheinz.deliveryserver;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;
import org.juheinz.utility.AbstractRepository;
import org.juheinz.utility.Logger;

import java.util.ArrayList;


/**
 * Provides read access to parcel database and sends updates to user notifier.
 */
public class ParcelRepository extends AbstractRepository<Parcel> {

    private static final ParcelRepository PARCEL_REPOSITORY = new ParcelRepository();
    private final ArrayList<Parcel> parcelsSetAsLoadedInDatabase = new ArrayList<>();
    private final Logger logger;
    private UserNotifier userNotifier;

    private ParcelRepository() {
        this.logger = new Logger("parcelrepositry");
    }

    public static ParcelRepository getInstance(UserNotifier userNotifier) {
        PARCEL_REPOSITORY.userNotifier = userNotifier;
        return PARCEL_REPOSITORY;
    }


    public int getDestination(Parcel parcel) {
        //TODO: connect to actual database
        int destination = (int) ((Math.random() * (10 - 1)) + 1);
        parcel.setDestination(destination);
        return destination;
    }

    /**
     * notifies the userNotifier on package status
     */
    public void updateParcelStatus(Parcel parcel, Status status) {
        switch (status) {
            case LOADED:
                parcelsSetAsLoadedInDatabase.add(parcel);
                logger.log("Parcel " + parcel.getId() + " set as loaded in database", "admin");
                break;
            case DELIVERED:
                parcelsSetAsLoadedInDatabase.remove(parcel);
                logger.log("Parcel " + parcel.getId() + " set as delivered in database", "admin");
                break;
            case FAILED_DELIVERY:
                logger.log("Parcel " + parcel.getId() + " has failed to deliver", "admin");
                break;
        }
        userNotifier.receiveUpdate(parcel, status, parcelsSetAsLoadedInDatabase);
    }


}
