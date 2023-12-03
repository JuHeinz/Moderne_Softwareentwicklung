package org.juheinz.appserver;

import org.juheinz.entities.Parcel;
import org.juheinz.entities.Status;

import java.util.ArrayList;
import java.util.List;

public class UserNotifier {

    private UserRepository userRepository;
    private static final UserNotifier userNotifier = new UserNotifier();

    private UserNotifier() {
        System.out.println("UserNotifier Singleton created");
    }

    public static UserNotifier getInstance(UserRepository userRepository) {
        userNotifier.userRepository = userRepository;
        return userNotifier;
    }


    //TODO: check if session for user already exists, if not start it
    public void receiveUpdate(Parcel parcel, Status status, ArrayList<Parcel> packagesOnTour) {

        getUserForParcel(parcel);

        switch (status) {
            case LOADED:
                if (parcel.hasUser()) {
                    UserSession session = new UserSession(parcel.getUser(), parcel);
                    session.updateLoaded();
                }
                break;
            case DELIVERED:
                notifyAllUsersOnTour(packagesOnTour);
                if (parcel.hasUser()) {
                    UserSession session = new UserSession(parcel.getUser(), parcel);
                    session.updateDelivered();
                }
                break;
            case FAILED_DELIVERY:
                if (parcel.hasUser()) {
                    UserSession session = new UserSession(parcel.getUser(), parcel);
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
     * Update the users still waiting for their package
     */
    private void notifyAllUsersOnTour(List<Parcel> loadedParcels) {
        for (Parcel parcel : loadedParcels) {
            if (parcel.hasUser()) {
                UserSession session = new UserSession(parcel.getUser(), parcel);
                session.updateArrivalTime();
                session.updateVanLocation();
            }
        }
    }

}