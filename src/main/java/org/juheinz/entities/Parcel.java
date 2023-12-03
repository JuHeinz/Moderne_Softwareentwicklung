package org.juheinz.entities;

import java.time.LocalDateTime;


/**
 * A parcel entity.
 */
public class Parcel {
    private final int id;
    private LocalDateTime loadedTime;
    private LocalDateTime deliveredTime;

    public boolean isLoaded() {
        return isLoaded;
    }



    private boolean isLoaded;
    private boolean isDelivered;
    private boolean hasAppUser;
    private User user;

    private int destination;

    public Parcel(int code) {
        this.id = code;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getLoadedTime() {
        return loadedTime;
    }

    public void setLoadedTime(LocalDateTime loadedTime) {
        this.loadedTime = loadedTime;
    }

    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public void setLoaded(boolean loaded) {
        this.isLoaded = loaded;
    }

    public void setDelivered(boolean delivered) {
        this.isDelivered = delivered;
    }

    public boolean hasUser() {
        return hasAppUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasAppUser = hasUser;
    }

    public User getUser() {
        return user;
    }

    public void setAppUser(User user) {
        this.user = user;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

}
