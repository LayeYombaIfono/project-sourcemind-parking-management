package models;

public class Vehicle {

    private int id; // Unique identifier
    private String licencePlate; // License plate (unique)
    private String model; // Vehicle model
    private String fuelType; // Fuel type (petrol, diesel, electric, hybrid)
    private User ownerId; // Owner ID (foreign key to `users`)

    // Constructor with all fields
    public Vehicle(int id, String licencePlate, String model, String fuelType, User ownerId) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.model = model;
        this.fuelType = fuelType;
        this.ownerId = ownerId;
    }

     // Getters et setters pour chaque champ
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
