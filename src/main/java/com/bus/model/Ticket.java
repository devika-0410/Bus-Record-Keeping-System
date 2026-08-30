package com.bus.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Ticket {
    private int ticketId;
    private int passengerId;
    private String passengerName;    // joined
    private int busId;
    private String busNumber;        // joined
    private int timetableId;
    private int seatNumber;
    private Date travelDate;
    private double amount;
    private String status;           // BOOKED / CANCELLED
    private Timestamp bookedAt;

    public Ticket() {}

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }
    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getTimetableId() { return timetableId; }
    public void setTimetableId(int timetableId) { this.timetableId = timetableId; }
    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public Date getTravelDate() { return travelDate; }
    public void setTravelDate(Date travelDate) { this.travelDate = travelDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getBookedAt() { return bookedAt; }
    public void setBookedAt(Timestamp bookedAt) { this.bookedAt = bookedAt; }
}