package org.juheinz.userapp;

import org.juheinz.entities.Parcel;

public class UserInterface {

    public UserInterface(Parcel parcel) {
    }

    public void sendNotification(String message) {
        System.out.println("|||||||| UserInterface:" + message);
    }

}
