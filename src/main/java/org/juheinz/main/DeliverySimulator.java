package org.juheinz.main;

import org.juheinz.appserver.UserNotifier;
import org.juheinz.appserver.UserRepository;
import org.juheinz.deliveryserver.ParcelManager;
import org.juheinz.deliveryserver.ParcelRepository;
import org.juheinz.deliveryserver.RouteService;
import org.juheinz.navigation.Navigator;
import org.juheinz.scanner.ParcelScanner;
import org.juheinz.entities.StaffDeployment;
import org.juheinz.utility.Logger;

import java.time.LocalDateTime;

/**
 * Simulates activities from scanner. Packages get loaded and delivered.
 */
public class DeliverySimulator {
    Logger logger;

    public void startSimulation(){
        Navigator navigator = Navigator.getInstance();
        RouteService routeService = RouteService.getInstance(navigator);

        UserRepository userRepository = UserRepository.getInstance();
        userRepository.connectToDatabse("0908-07.appusers.de", "user");

        UserNotifier userNotifier = UserNotifier.getInstance(userRepository);

        ParcelRepository parcelRepository = ParcelRepository.getInstance(userNotifier);
        parcelRepository.connectToDatabse("012-34.packages.de", "package");

        ParcelManager parcelManager = ParcelManager.getInstance(parcelRepository, routeService);
        ParcelScanner parcelScanner = ParcelScanner.getInstance(parcelManager);
        logger = new Logger("main");

        setStaffDeployment();


        // simulate scanner activity
        logger.log("van is being loaded", "meta");
        int[] parcelCode = new int[]{8, 7, 6, 5, 4, 3, 1};
        for (int code : parcelCode) {
            parcelScanner.scanParcelAsLoaded(code, LocalDateTime.now());
        }
        logger.log("van is rolling out", "meta");

        for (int code : parcelCode) {
            parcelScanner.scanParcelAsDelivered(code, LocalDateTime.now());
        }
    }

    /**
     * Example for an interal Domain Specific Language (DSL).
     */
    private void setStaffDeployment(){
        StaffDeployment staffDeployment = new StaffDeployment(1,2,3);
        staffDeployment.weatherCondition("sonnig").fuelPercentage(98).staffStatus("gesund");
        logger.log(staffDeployment.toString(), "admin");
    }

}
