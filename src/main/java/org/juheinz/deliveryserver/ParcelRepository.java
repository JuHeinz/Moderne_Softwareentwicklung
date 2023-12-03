package org.juheinz.deliveryserver;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;
import org.juheinz.utility.AbstractRepository;

import java.util.ArrayList;


public class ParcelRepository extends AbstractRepository<Parcel> {

    private UserNotifier userNotifier;

    private static final ParcelRepository PARCEL_REPOSITORY = new ParcelRepository();

    private final ArrayList<Parcel> parcelsSetAsLoadedInDatabase = new ArrayList<>();

    private ParcelRepository() {
        System.out.println("ParcelRepository Singleton created");
    }

    public static ParcelRepository getInstance(UserNotifier userNotifier) {
        PARCEL_REPOSITORY.userNotifier = userNotifier;
        return PARCEL_REPOSITORY;
    }


    public int getDestination(Parcel p) {
        //SQL Goes here
        return 1;
    }

    /**
     * after a package has been updated, this lets the application server know.
     * the application server then checks if any of the packages are linked to app users
     */

    //TODO: implement listender pattern
    public void updateParcelStatus(Parcel parcel, Status status) {
        switch (status) {
            case LOADED:
                parcelsSetAsLoadedInDatabase.add(parcel);
                log("Parcel " + parcel.getId() + " set as loaded in database");
                break;
            case DELIVERED:
                parcelsSetAsLoadedInDatabase.remove(parcel);
                log("Parcel " + parcel.getId() + " set as delivered in database");
                break;
            case FAILED_DELIVERY:
                log("Parcel " + parcel.getId() + " has failed to deliver");
                break;
        }
        userNotifier.receiveUpdate(parcel, status, parcelsSetAsLoadedInDatabase);
    }


    private void log(String message) {
        System.out.println(">> PACKAGE REPOSITORY: " + message);
    }
}
