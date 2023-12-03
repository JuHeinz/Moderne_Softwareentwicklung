package org.juheinz.userapp;
import org.juheinz.entities.User;

/**
 * App interface for users waiting for the parcels
 */
public class UserInterface {

    User user;
    public UserInterface(User user) {
        this.user = user;
    }

    public void sendNotification(String message) {
        System.out.println("|||||||| Hi " + user.getId() + "!" + message);
    }

}
