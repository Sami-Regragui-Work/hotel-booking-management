package repository.impl;

import model.Reservation;
import repository.ReservationRepository;

import java.util.*;

public class InMemoryReservationRepository implements ReservationRepository {
    private final Map<UUID, Reservation> storage = new HashMap<>();

    @Override
    public void save(Reservation reservation) {
        this.storage.put(reservation.getId(), reservation);
    }

    @Override
    public Optional<Reservation> findById(UUID id) {
        return Optional.ofNullable(this.storage.get(id));
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        return this.storage.values().stream().filter(reservation -> reservation.getReservationCode().equals(code)).findFirst();
    }

    @Override
    public List<Reservation> findByUserId(UUID userId) {
        return this.storage.values().stream().filter(reservation -> reservation.getUserId().equals(userId)).toList();
    }

    @Override
    public List<Reservation> findByRoomNumber(String roomNumber) {
        return this.storage.values().stream().filter(reservation -> reservation.getRoomNumber().equals(roomNumber)).toList();
    }

    @Override
    public List<Reservation> findAll() {
        return this.storage.values().stream().toList();
    }
}
