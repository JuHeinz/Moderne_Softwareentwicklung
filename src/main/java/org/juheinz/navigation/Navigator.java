package org.juheinz.navigation;

public class Navigator {

    private static final Navigator NAVIGATOR = new Navigator();

    private Navigator() {
        System.out.println("Navigator Singleton created");
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
