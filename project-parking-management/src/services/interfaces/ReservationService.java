package services.interfaces;

import models.Reservation;

import java.util.List;

public interface ReservationService {
    boolean addReservation(Reservation reservation);
    Reservation getReservationById(int id);
    List<Reservation> getAllReservations();
    boolean updateReservation(Reservation reservation);
    boolean deleteReservation(int id);
}
