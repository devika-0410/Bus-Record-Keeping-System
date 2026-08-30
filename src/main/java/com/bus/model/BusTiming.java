package com.bus.model;

import java.sql.Time;

public class BusTiming {
    private int timingId;
    private int timetableId;
    private String busNumber;         // joined
    private String sourcePlace;       // joined
    private String destinationPlace;  // joined
    private Time scheduledArrival;    // joined
    private Time scheduledDeparture;  // joined
    private Time actualArrival;
    private Time actualDeparture;
    private int delayMinutes;
    private int recordedBy;

    public BusTiming() {}

    public int getTimingId() { return timingId; }
    public void setTimingId(int timingId) { this.timingId = timingId; }
    public int getTimetableId() { return timetableId; }
    public void setTimetableId(int timetableId) { this.timetableId = timetableId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public String getSourcePlace() { return sourcePlace; }
    public void setSourcePlace(String sourcePlace) { this.sourcePlace = sourcePlace; }
    public String getDestinationPlace() { return destinationPlace; }
    public void setDestinationPlace(String destinationPlace) { this.destinationPlace = destinationPlace; }
    public Time getScheduledArrival() { return scheduledArrival; }
    public void setScheduledArrival(Time scheduledArrival) { this.scheduledArrival = scheduledArrival; }
    public Time getScheduledDeparture() { return scheduledDeparture; }
    public void setScheduledDeparture(Time scheduledDeparture) { this.scheduledDeparture = scheduledDeparture; }
    public Time getActualArrival() { return actualArrival; }
    public void setActualArrival(Time actualArrival) { this.actualArrival = actualArrival; }
    public Time getActualDeparture() { return actualDeparture; }
    public void setActualDeparture(Time actualDeparture) { this.actualDeparture = actualDeparture; }
    public int getDelayMinutes() { return delayMinutes; }
    public void setDelayMinutes(int delayMinutes) { this.delayMinutes = delayMinutes; }
    public int getRecordedBy() { return recordedBy; }
    public void setRecordedBy(int recordedBy) { this.recordedBy = recordedBy; }
}