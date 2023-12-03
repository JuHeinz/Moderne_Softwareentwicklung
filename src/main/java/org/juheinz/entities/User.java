package org.juheinz.entities;

/**
 * A user of the parcel tracking app. Note that not all parcel recipients are app users.
 */
public class User {
    private final int id;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}

