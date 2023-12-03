package org.juheinz.navigation;

/**
 * Displays route information on navigation system in delivery van.
 */
public class Navigator {

    private static final Navigator NAVIGATOR = new Navigator();

    private Navigator() {
    }

    public static Navigator getInstance() {
        return NAVIGATOR;
    }

    public void setRoute(String route) {
        showNotification("(to staff) Route was updated:" + route);
    }

    private void showNotification(String message) {
        System.out.println(">> NAVIGATOR: " + message);
    }


}
