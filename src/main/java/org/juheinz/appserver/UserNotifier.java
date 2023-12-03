package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Starts user sessions for a given parcel, its status and its user.
 */
public class UserNotifier {

    private static final UserNotifier USER_NOTIFIER = new UserNotifier();
    private UserRepository userRepository;

    private UserNotifier() {
    }

    public static UserNotifier getInstance(UserRepository userRepository) {
        USER_NOTIFIER.userRepository = userRepository;
        return USER_NOTIFIER;
    }


    /**
     * Creates UI according to packages status.
     *
     * @param parcel         parcel entity that has changed.
     * @param status         status for parcel.
     * @param packagesOnTour all parcels still in the van.
     */
    public void receiveUpdate(Parcel parcel, Status status, ArrayList<Parcel> packagesOnTour) {

        getUserForParcel(parcel);

        switch (status) {
            case LOADED:
                if (parcel.hasUser()) {
                    InterfaceController session = new InterfaceController(parcel.getUser(), parcel);
                    session.updateLoaded();
                }
                break;
            case DELIVERED:
                notifyAllUsersOnTour(packagesOnTour);
                if (parcel.hasUser()) {
                    InterfaceController session = new InterfaceController(parcel.getUser(), parcel);
                    session.updateDelivered();
                }
                break;
            case FAILED_DELIVERY:
                if (parcel.hasUser()) {
                    InterfaceController session = new InterfaceController(parcel.getUser(), parcel);
                    session.updateFailure();
                }
        }
    }

    private void getUserForParcel(Parcel parcel) {
        if (userRepository.getUserForParcel(parcel) != null) {
            parcel.setAppUser(userRepository.getUserForParcel(parcel));
            parcel.setHasUser(true);
        } else {
            parcel.setHasUser(false);
        }

    }

    /**
     * Update the users still waiting for their parcel
     */
    private void notifyAllUsersOnTour(List<Parcel> loadedParcels) {
        for (Parcel parcel : loadedParcels) {
            if (parcel.hasUser()) {
                InterfaceController session = new InterfaceController(parcel.getUser(), parcel);
                session.updateArrivalTime();
                session.updateVanLocation();
            }
        }
    }

}