package org.juheinz.navigation;

import org.juheinz.utility.Logger;

import java.util.List;

/**
 * Displays route information on navigation system in delivery van.
 */
public class Navigator {

    private static final Navigator NAVIGATOR = new Navigator();
    private final Logger logger;

    private Navigator() {
        this.logger = new Logger("navigator");
    }

    public static Navigator getInstance() {
        return NAVIGATOR;
    }

    public void setRoute(List<double[]> route) {
        logger.log("Stops auf Route: " + route.size(), "zusteller");
    }

    public void setDrivingInstruction(String drivingInstruction){
        logger.log(drivingInstruction, "zusteller");
    }


}
