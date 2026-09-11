package service;

import model.ReservationStatus;
import model.Room;
import model.RoomStatus;
import repository.ReservationRepository;
import repository.RoomRepository;
import util.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoomService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Room> listAllRooms() {
        return this.roomRepository.findAll();
    }

    public Optional<Room> findByRoomNumber(String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }

    public List<Room> searchAvailableRooms(LocalDate checkIn, LocalDate checkOut, int numberOfGuests) throws IllegalArgumentException {
        if (checkIn == null || checkOut == null) throw new IllegalArgumentException("Dates are required");
        if (checkIn.isBefore(LocalDate.now())) throw new IllegalArgumentException("Check-in date must be today or later");
        if (!checkIn.isBefore(checkOut)) throw new IllegalArgumentException("Check-out date must be after check-in date"); // didn't use isAfter to include "or equal" case (isBefore == "<" so !isBefore == ">=")
        if (numberOfGuests <= 0) throw new IllegalArgumentException("Number of guests must be positive");

        return this.listAllRooms().stream().filter(room -> room.getStatus().equals(RoomStatus.AVAILABLE)).filter(room -> room.getCapacity() >= numberOfGuests).filter(room -> this.reservationRepository.findByRoomNumber(room.getRoomNumber()).stream().filter(reservation -> reservation.getStatus().equals(ReservationStatus.CONFIRMED)).noneMatch(reservation -> DateUtils.periodsOverlap(reservation.getCheckIn(), reservation.getCheckOut(), checkIn, checkOut))).toList();
    }

    List<String> noms = List.of("Ali", "Omar", "Yasmine", "Anas");
    Map<Integer, List<String>> byLength = noms.stream().collect(Collectors.groupingBy(String::length));

}
