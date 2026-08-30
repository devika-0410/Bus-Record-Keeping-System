package com.bus.model;

public class Route {
    private int routeId;
    private String sourcePlace;
    private String destinationPlace;
    private double distanceKm;

    public Route() {}

    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    public String getSourcePlace() { return sourcePlace; }
    public void setSourcePlace(String sourcePlace) { this.sourcePlace = sourcePlace; }
    public String getDestinationPlace() { return destinationPlace; }
    public void setDestinationPlace(String destinationPlace) { this.destinationPlace = destinationPlace; }
    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
}