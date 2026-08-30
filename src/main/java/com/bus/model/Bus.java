package com.bus.model;

public class Bus {
    private int busId;
    private String busNumber;
    private int totalSeats;
    private int routeId;
    private String sourcePlace;      // joined from routes, for display
    private String destinationPlace; // joined from routes, for display

    public Bus() {}

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    public String getSourcePlace() { return sourcePlace; }
    public void setSourcePlace(String sourcePlace) { this.sourcePlace = sourcePlace; }
    public String getDestinationPlace() { return destinationPlace; }
    public void setDestinationPlace(String destinationPlace) { this.destinationPlace = destinationPlace; }
}