package services.interfaces;

import models.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {
    boolean registerParkingSpot(ParkingSpot parkingSpot);
    ParkingSpot getParkingSpotById(int id);
    List<ParkingSpot> getAllParkingSpots();
    boolean updateParkingSpot(ParkingSpot parkingSpot);
    boolean deleteParkingSpot(int id);
}
