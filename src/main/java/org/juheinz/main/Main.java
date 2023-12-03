package org.juheinz.main;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.appserver.UserRepository;
import org.juheinz.deliveryserver.ParcelManager;
import org.juheinz.deliveryserver.ParcelRepository;
import org.juheinz.deliveryserver.RouteService;
import org.juheinz.navigation.Navigator;
import org.juheinz.scanner.ParcelScanner;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Navigator navigator = Navigator.getInstance();
        RouteService routeService = RouteService.getInstance(navigator);

        UserRepository userRepository = UserRepository.getInstance();
        userRepository.connectToDatabse("0908-07.appusers.de", "user");

        UserNotifier userNotifier = UserNotifier.getInstance(userRepository);

        ParcelRepository parcelRepository = ParcelRepository.getInstance(userNotifier);
        parcelRepository.connectToDatabse("012-34.packages.de", "package");

        ParcelManager parcelManager = ParcelManager.getInstance(parcelRepository, routeService);
        ParcelScanner parcelScanner = ParcelScanner.getInstance(parcelManager);

        // simulate scanner activity
        System.out.println("VAN BEING LOADED");
        int[] parcelCode = new int[]{8, 7, 6, 5, 4, 3, 1};
        for (int code : parcelCode) {
            parcelScanner.scanParcelAsLoaded(code, LocalDateTime.now());
            Thread.sleep(1000);
        }
        System.out.println("VAN IS ROLLING OUT");
        Thread.sleep(2000);
        for (int code : parcelCode) {
            parcelScanner.scanParcelAsDelivered(code, LocalDateTime.now());
            Thread.sleep(1000);
        }

    }
}