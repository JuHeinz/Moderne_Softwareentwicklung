package org.juheinz.scanner;

import org.juheinz.deliveryserver.PackageManager;
import org.juheinz.entities.Package;
import org.juheinz.navigation.GPS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PackageScanner {
    private PackageManager packageManager;

    private final List<Package> loadedPackages = new ArrayList<>();

    private static final PackageScanner ps = new PackageScanner();

    private PackageScanner() {
        System.out.println("PackageScanner Singleton created");
    }

    public static PackageScanner getInstance(PackageManager pm) {
        ps.packageManager = pm;
        return ps;
    }

    public void scanPackageAsLoaded(int scannedCode, LocalDateTime loadedTimeStamp) {
        Package p = new Package(scannedCode);
        packageManager.setPackageLoaded(p, loadedTimeStamp);
        locallySaveLoadedPackage(p);
        showNotification("Package " + p.getCode() + " loaded.");

    }

    public void scanPackageAsDelivered(int scannedCode, LocalDateTime deliveredTimestamp) {
        //get loaded package from earlier
        Package deliveredPackage = findByCode(loadedPackages, scannedCode);
        if (deliveredPackage != null) {
            boolean validDelivery = DeliveryValidator.matchLocationDestination(deliveredPackage.getDestination(), GPS.getCurrentLocation());
            if (validDelivery) {
                packageManager.setPackageDelivered(deliveredPackage, deliveredTimestamp);
                showNotification("(to staff) Zustellung von " + deliveredPackage + " erfolgreich!");
                loadedPackages.remove(deliveredPackage);
            } else {
                packageManager.setDeliveryFailure(deliveredPackage);
                showNotification(" (to staff) Fehler bei Zustellung! Sie befinden sich nicht am richtigen Standort!");
            }
        } else {
            showNotification("(to staff) Für den Code " + scannedCode + " gibt es kein Paket in ihrem Wagen");
        }
    }

    private void locallySaveLoadedPackage(Package p) {
        loadedPackages.add(p);
    }

    private static Package findByCode(Collection<Package> list, int code) {
        return list.stream().filter(p -> (code == p.getCode())).findFirst().orElse(null);
    }

    private void showNotification(String message) {
        System.out.println(">> PACKAGE SCANNER: " + message);
    }
}
