package com.bus.model;

import java.sql.Date;

public class Attendance {
    private int attendanceId;
    private int userId;
    private String userName;   // joined, for display
    private int busId;
    private String busNumber;  // joined, for display
    private Date attendanceDate;
    private String status;     // PRESENT / ABSENT

    public Attendance() {}

    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public Date getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(Date attendanceDate) { this.attendanceDate = attendanceDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}