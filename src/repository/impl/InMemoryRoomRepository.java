package repository.impl;

import model.Room;
import repository.RoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryRoomRepository implements RoomRepository {
    private final Map<String, Room> storage = new HashMap<>();

    @Override
    public void save(Room room) {
        this.storage.put(room.getRoomNumber(), room);
    }

    @Override
    public Optional<Room> findByRoomNumber(String roomNumber) {
        return Optional.ofNullable(this.storage.get(roomNumber));
    }

    @Override
    public List<Room> findAll() {
        return this.storage.values().stream().toList();
    }
}
