package util;

import model.Room;
import model.RoomStatus;
import model.RoomType;
import model.User;
import repository.RoomRepository;
import repository.UserRepository;

import java.math.BigDecimal;

public class DataSeeder {
    private DataSeeder() {}

    public static void seed(RoomRepository roomRepository, UserRepository userRepository) {
        DataSeeder.seedRooms(roomRepository);
        DataSeeder.seedUsers(userRepository);
    }

    private static void seedRooms(RoomRepository roomRepository) {
        roomRepository.save(new Room("101", RoomType.SINGLE, 1, new BigDecimal("300.00")));
        roomRepository.save(new Room("201", RoomType.DOUBLE, 2, new BigDecimal("500.00")));
        roomRepository.save(new Room("202", RoomType.DOUBLE, 2, new BigDecimal("550.00")));
        roomRepository.save(new Room("301", RoomType.SUITE, 4, new BigDecimal("900.00")));

        Room SickRoom = new Room("503", RoomType.SUITE, 4, BigDecimal.ONE);

        SickRoom.setStatus(RoomStatus.MAINTENANCE);

        roomRepository.save(SickRoom);
    }

    private static void seedUsers(UserRepository userRepository) {
        userRepository.save(new User("Alice Dupont", "alice@example.com", "0600000001", "alice123"));
        userRepository.save(new User("Bob Martin", "bob@example.com", "0600000002", "bob123"));
    }
}
