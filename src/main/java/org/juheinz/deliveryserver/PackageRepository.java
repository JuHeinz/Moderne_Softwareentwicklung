package org.juheinz.deliveryserver;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.entities.Package;
import org.juheinz.entities.Status;
import org.juheinz.utility.AbstractRepository;

import java.util.ArrayList;


public class PackageRepository extends AbstractRepository<Package> {

    private UserNotifier userNotifier;

    private static final PackageRepository packageRepository = new PackageRepository();

    private final ArrayList<Package> packagesSetAsLoadedInDatabase = new ArrayList<>();

    private PackageRepository() {
        System.out.println("PackageRepository Singleton created");
    }

    public static PackageRepository getInstance(UserNotifier userNotifier) {
        packageRepository.userNotifier = userNotifier;
        return packageRepository;
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
