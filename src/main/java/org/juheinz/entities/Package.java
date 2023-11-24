package org.juheinz.entities;

import java.time.LocalDateTime;


public class Package {
    private final int code;
    private LocalDateTime loadedTime;
    private LocalDateTime deliveredTime;
    private boolean isLoaded;
    private boolean isDelivered;
    private boolean hasAppUser;
    private AppUser appUser;

    private int destination;

    public Package(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
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

    public boolean hasAppUser() {
        return hasAppUser;
    }

    public void setHasAppUser(boolean hasAppUser) {
        this.hasAppUser = hasAppUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Package{" + "code=" + code + ", loadedTime=" + loadedTime + ", deliveredTime=" + deliveredTime + ", isLoaded=" + isLoaded + ", isDelivered=" + isDelivered + ", hasAppUser=" + hasAppUser + ", appUser=" + appUser + ", destination='" + destination + '\'' + '}';
    }


}
