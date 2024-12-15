package services.interfaces;

import models.Vehicle;

import java.util.List;

public interface VehicleService {
    boolean registerVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    boolean updateVehicle(Vehicle vehicle);
    boolean deleteVehicle(int id);
}
