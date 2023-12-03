package org.juheinz.navigation;

import org.juheinz.utility.Logger;

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

    public void setRoute(String route) {
        logger.log("Neues Ziel:" +  route, "zusteller");
    }



}
