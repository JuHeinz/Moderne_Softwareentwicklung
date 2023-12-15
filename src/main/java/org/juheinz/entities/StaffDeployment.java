package org.juheinz.entities;

/**
 * Example for an internal DSL. Assigns a driver and vehicle to a route.
 */
public class StaffDeployment {

    private final int routeID;
    private final int staffID;
    private final int vehicleID;
    private String weatherCondition;
    private String staffStatus;
    private int fuelPercentage;

    public StaffDeployment(int routeID, int staffID, int vehicleID){
        this.routeID = routeID;
        this.staffID = staffID;
        this.vehicleID = vehicleID;
    }

    public StaffDeployment weatherCondition(String weatherCondition){
        this.weatherCondition = weatherCondition;
        return this;
    }
    public StaffDeployment fuelPercentage(int fuelPercentage){
        this.fuelPercentage = fuelPercentage;
        return this;
    }
    public StaffDeployment staffStatus(String staffStatus){
        this.staffStatus = staffStatus;
        return this;
    }


    @Override
    public String toString() {
        return  "Auf Route " + this.routeID +
                " fährt Zusteller mit ID " + this.staffID +
                " im Wagen mit ID " + this.vehicleID + "." +
                " Draußen ist es " + this.weatherCondition + " und der Zusteller fühlt sich " + this.staffStatus + '.' +
                " Der Tank ist zu " + this.fuelPercentage + "% gefüllt.";
    }
}
