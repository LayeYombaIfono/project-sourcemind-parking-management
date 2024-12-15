package models;

import java.time.LocalDateTime;


public class ActivityLog {
    private int id; // Unique identifier
    private int vehicleId; // ID of the vehicle concerned
    private User user; // ID of the user who performed the action
    private String action; // Action type (entry, exit, reservation, release)
    private LocalDateTime timestamp; // Date and time of the action

    // empty constructor
    public ActivityLog() {
    }

    //Constructor with all fields
    public ActivityLog(int id, int vehicleId, User user, String action, LocalDateTime timestamp) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.user = user;
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



    @Override
    public String toString() {
        return "ActivityLog{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", userId=" + user +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
