package org.juheinz.deliveryserver;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.entities.Package;
import org.juheinz.entities.Status;

import java.util.ArrayList;


/**
 * mock database and db management
 */
public class PackageRepository {

    private UserNotifier userNotifier;

    private static final PackageRepository pr = new PackageRepository();

    private final ArrayList<Package> packagesSetAsLoadedInDatabase = new ArrayList<>();

    private PackageRepository() {
        System.out.println("PackageRepository Singleton created");
    }

    public static PackageRepository getInstance(UserNotifier userNotifier) {
        pr.userNotifier = userNotifier;
        return pr;
    }

    public int getDestination(Package p) {
        //SQL Goes here
        return 1;
    }

    /**
     * after a package has been updated, this lets the application server know.
     * the application server then checks if any of the packages are linked to app users
     */

    //TODO: implement listender pattern
    public void updatePackageStatus(Package p, Status status) {
        switch (status) {
            case LOADED:
                packagesSetAsLoadedInDatabase.add(p);
                log("Package " + p.getCode() + " set as loaded in database");
                break;
            case DELIVERED:
                packagesSetAsLoadedInDatabase.remove(p);
                log("Package " + p.getCode() + " set as delivered in database");
                break;
            case FAILED_DELIVERY:
                log("Package " + p.getCode() + " has failed to deliver");
                break;
        }
        userNotifier.receiveUpdate(p, status, packagesSetAsLoadedInDatabase);
    }


    private void log(String message) {
        System.out.println(">> PACKAGE REPOSITORY: " + message);
    }
}
