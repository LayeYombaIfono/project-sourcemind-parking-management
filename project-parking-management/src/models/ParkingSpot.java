package models;

public class ParkingSpot {
    private int id; // Unique identifier
    private String spotNumber; // Seat number (unique)
    private boolean isReserved; // Place status (true = reserved, false = available)
    private Integer currentVehicleId; // ID of the occupied vehicle (null if the space is free)

    public ParkingSpot() {
    }

    // Constructor with all fields
    public ParkingSpot(int id, String spotNumber, boolean isReserved, Integer currentVehicleId) {
        this.id = id;
        this.spotNumber = spotNumber;
        this.isReserved = isReserved;
        this.currentVehicleId = currentVehicleId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Integer getCurrentVehicleId() {
        return currentVehicleId;
    }

    public void setCurrentVehicleId(Integer currentVehicleId) {
        this.currentVehicleId = currentVehicleId;
    }
}
