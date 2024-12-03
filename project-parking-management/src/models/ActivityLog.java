package models;

import java.time.LocalDateTime;


public class ActivityLog {
    private int id; // Unique identifier
    private int vehicleId; // ID of the vehicle concerned
    private int userId; // ID of the user who performed the action
    private String action; // Action type (entry, exit, reservation, release)
    private LocalDateTime timestamp; // Date and time of the action

    // empty constructor
    public ActivityLog() {
    }

    //Constructor with all fields
    public ActivityLog(int id, int vehicleId, int userId, String action, LocalDateTime timestamp) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
