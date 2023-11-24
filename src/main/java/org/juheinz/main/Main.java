package org.juheinz.main;


import org.juheinz.appserver.UserNotifier;
import org.juheinz.appserver.UserRepository;
import org.juheinz.deliveryserver.PackageManager;
import org.juheinz.deliveryserver.PackageRepository;
import org.juheinz.deliveryserver.RouteService;
import org.juheinz.navigation.Navigator;
import org.juheinz.scanner.PackageScanner;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Navigator navigator = Navigator.getInstance();
        RouteService routeService = RouteService.getInstance(navigator);

        UserRepository userRepository = UserRepository.getInstance();
        UserNotifier userNotifier = UserNotifier.getInstance(userRepository);
        PackageRepository packageRepository = PackageRepository.getInstance(userNotifier);
        PackageManager packageManager = PackageManager.getInstance(packageRepository, routeService);
        PackageScanner packageScanner = PackageScanner.getInstance(packageManager);

        //simulate device activity
        int[] packageCodes = new int[]{8, 7, 6, 5, 4, 3, 1};
        for (int i : packageCodes) {
            packageScanner.scanPackageAsLoaded(i, LocalDateTime.now());
            Thread.sleep(1000);
        }
        System.out.println("Ich fahr los!");
        Thread.sleep(2000);
        for (int i : packageCodes) {
            packageScanner.scanPackageAsDelivered(i, LocalDateTime.now());
            Thread.sleep(1000);
        }

    }
}