package com.bus.model;

import java.sql.Timestamp;

public class MaintenanceReport {
    private int reportId;
    private int busId;
    private String busNumber;       // joined
    private int reportedBy;
    private String reportedByName;  // joined
    private String issueDescription;
    private String status;          // PENDING / RESOLVED
    private Timestamp reportedAt;
    private Timestamp resolvedAt;
    private Integer updatedBy;
    private String updatedByName;   // joined

    public MaintenanceReport() {}

    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }
    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getReportedBy() { return reportedBy; }
    public void setReportedBy(int reportedBy) { this.reportedBy = reportedBy; }
    public String getReportedByName() { return reportedByName; }
    public void setReportedByName(String reportedByName) { this.reportedByName = reportedByName; }
    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getReportedAt() { return reportedAt; }
    public void setReportedAt(Timestamp reportedAt) { this.reportedAt = reportedAt; }
    public Timestamp getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(Timestamp resolvedAt) { this.resolvedAt = resolvedAt; }
    public Integer getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(Integer updatedBy) { this.updatedBy = updatedBy; }
    public String getUpdatedByName() { return updatedByName; }
    public void setUpdatedByName(String updatedByName) { this.updatedByName = updatedByName; }
}