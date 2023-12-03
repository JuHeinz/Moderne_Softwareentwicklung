package org.juheinz.userapp;
import org.juheinz.entities.User;
import org.juheinz.utility.Logger;

/**
 * App interface for users waiting for the parcels
 */
public class UserInterface {

    Logger logger;
    User user;
    public UserInterface(User user) {
        this.user = user;
        this.logger = new Logger("ui");
    }

    public void sendNotification(String msg) {
        logger.log("Hi " + user.getId() + "!" + msg, "user");
    }

}
