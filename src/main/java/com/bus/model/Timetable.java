package com.bus.model;

import java.sql.Date;
import java.sql.Time;

public class Timetable {
    private int timetableId;
    private int busId;
    private String busNumber;       // joined, for display
    private int routeId;
    private String sourcePlace;     // joined, for display
    private String destinationPlace;// joined, for display
    private Date travelDate;
    private Time scheduledArrival;
    private Time scheduledDeparture;
    private int createdBy;
    private int totalSeats;
    private int availableSeats;

    public Timetable() {}

    public int getTimetableId() { return timetableId; }
    public void setTimetableId(int timetableId) { this.timetableId = timetableId; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getRouteId() { return routeId; }
    public void setRouteId(int routeId) { this.routeId = routeId; }
    public String getSourcePlace() { return sourcePlace; }
    public void setSourcePlace(String sourcePlace) { this.sourcePlace = sourcePlace; }
    public String getDestinationPlace() { return destinationPlace; }
    public void setDestinationPlace(String destinationPlace) { this.destinationPlace = destinationPlace; }
    public Date getTravelDate() { return travelDate; }
    public void setTravelDate(Date travelDate) { this.travelDate = travelDate; }
    public Time getScheduledArrival() { return scheduledArrival; }
    public void setScheduledArrival(Time scheduledArrival) { this.scheduledArrival = scheduledArrival; }
    public Time getScheduledDeparture() { return scheduledDeparture; }
    public void setScheduledDeparture(Time scheduledDeparture) { this.scheduledDeparture = scheduledDeparture; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}