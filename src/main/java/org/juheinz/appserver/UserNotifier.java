package org.juheinz.appserver;

import org.juheinz.entities.Package;
import org.juheinz.entities.Status;

import java.util.ArrayList;
import java.util.List;

public class UserNotifier {

    private UserRepository userRepository;
    private static final UserNotifier un = new UserNotifier();

    private UserNotifier() {
        System.out.println("UserNotifier Singleton created");
    }

    public static UserNotifier getInstance(UserRepository userRepository) {
        un.userRepository = userRepository;
        return un;
    }


    //TODO: check if session for user already exists, if not start it
    public void receiveUpdate(Package p, Status status, ArrayList<Package> packagesOnTour) {

        getUserForPackage(p);

        switch (status) {
            case LOADED:
                if (p.hasAppUser()) {
                    UserSession session = new UserSession(p.getAppUser(), p);
                    session.updateLoaded();
                }
                break;
            case DELIVERED:
                notifyAllUsersOnTour(packagesOnTour);
                if (p.hasAppUser()) {
                    UserSession session = new UserSession(p.getAppUser(), p);
                    session.updateDelivered();
                }
                break;
            case FAILED_DELIVERY:
                if (p.hasAppUser()) {
                    UserSession session = new UserSession(p.getAppUser(), p);
                    session.updateFailure();
                }
        }
    }

    private void getUserForPackage(Package p) {
        if (userRepository.getUserForPackage(p) != null) {
            p.setAppUser(userRepository.getUserForPackage(p));
            p.setHasAppUser(true);
        } else {
            p.setHasAppUser(false);
        }

    }

    /**
     * Update the users still waiting for their package
     */
    private void notifyAllUsersOnTour(List<Package> loadedPackages) {
        for (Package p : loadedPackages) {
            if (p.hasAppUser()) {
                UserSession session = new UserSession(p.getAppUser(), p);
                session.updateArrivalTime();
                session.updateVanLocation();
            }
        }
    }

}