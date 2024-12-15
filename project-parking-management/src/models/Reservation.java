package models;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation(int id, User user, Vehicle vehicle, ParkingSpot spot, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.spot = spot;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", spot=" + spot +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
