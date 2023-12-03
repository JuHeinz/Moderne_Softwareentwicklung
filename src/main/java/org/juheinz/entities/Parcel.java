package org.juheinz.entities;

import java.time.LocalDateTime;


public class Parcel {
    private final int id;
    private LocalDateTime loadedTime;
    private LocalDateTime deliveredTime;
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

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
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

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Parcel{" + "id=" + id + ", loadedTime=" + loadedTime + ", deliveredTime=" + deliveredTime + ", isLoaded=" + isLoaded + ", isDelivered=" + isDelivered + ", hasAppUser=" + hasAppUser + ", user=" + user + ", destination='" + destination + '\'' + '}';
    }


}
