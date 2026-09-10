package service;

import model.Room;
import repository.RoomRepository;

import java.util.List;
import java.util.Optional;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> listAllRooms() {
        return this.roomRepository.findAll();
    }

    public Optional<Room> findByRoomNumber(String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }
}
