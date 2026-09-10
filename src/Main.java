import model.Room;
import repository.impl.InMemoryRoomRepository;
import repository.impl.InMemoryUserRepository;
import service.AuthService;
import service.RoomService;
import util.DataSeeder;
import util.InputUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryRoomRepository roomRepository = new InMemoryRoomRepository();
        DataSeeder.seed(roomRepository, userRepository);

        AuthService authService = new AuthService(userRepository);
        RoomService roomService = new RoomService(roomRepository);

        while (true) {
            if (authService.isLoggedIn()) Main.showLoggedInMenu(authService, roomService);
            else Main.showGuestMenu(authService);
        }
    }

    private static void showGuestMenu(AuthService authService) {
        System.out.println("=".repeat(31));
        System.out.println(center("HOTEL BOOKING", 31));
        System.out.println("=".repeat(31));
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Choice: ");

        int choice = InputUtils.readInt();
        System.out.println();


        try {
            switch (choice) {
                case 1 -> Main.showRegisterForum(authService);
                case 2 -> Main.showLoginForum(authService);
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, Try again\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showLoggedInMenu(AuthService authService, RoomService roomService) {
        System.out.println("=".repeat(31));
        System.out.println(center("Logged in as: " + authService.getCurrentUser().getFullName(), 31));
        System.out.println("=".repeat(31));
        System.out.println("1. Search available rooms");
        System.out.println("2. View all rooms");
        System.out.println("3. Create reservation");
        System.out.println("4. My reservations");
        System.out.println("5. Update reservation");
        System.out.println("6. Cancel reservation");
        System.out.println("7. Update profile");
        System.out.println("8. Change password");
        System.out.println("9. Logout");
        System.out.println("0. Exit");
        System.out.print("Choice: ");

        int choice = InputUtils.readInt();
        System.out.println();

        try {
            switch (choice) {
                case 1 -> System.out.println("Not implemented yet\n");
                case 2 -> Main.showAllRooms(roomService);
                case 3 -> System.out.println("Not implemented yet\n");
                case 4 -> System.out.println("Not implemented yet\n");
                case 5 -> System.out.println("Not implemented yet\n");
                case 6 -> System.out.println("Not implemented yet\n");
                case 7 -> Main.showUpdateProfile(authService);
                case 8 -> Main.showChangePassword(authService);
                case 9 -> {
                    authService.logout();
                    System.out.println("Logged out\n");
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, Try again\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            InputUtils.waitForEnter();
        }
    }

    private static void showRegisterForum(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(31));
        System.out.println(center("REGISTER", 31));
        System.out.println("=".repeat(31));
        System.out.print("Full Name: ");
        String name = InputUtils.readString();
        System.out.print("Email: ");
        String email = InputUtils.readString();
        System.out.print("Phone: ");
        String phone = InputUtils.readString();
        System.out.print("Password: ");
        String password = InputUtils.readString();
        System.out.println();

        authService.register(name, email, phone, password);
        System.out.println("Registration successful! Please login\n");
        InputUtils.waitForEnter();
    }

    private static void showLoginForum(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(31));
        System.out.println(center("LOGIN", 31));
        System.out.println("=".repeat(31));
        System.out.print("Email: ");
        String email = InputUtils.readString();
        System.out.print("Password: ");
        String password = InputUtils.readString();
        System.out.println();

        authService.login(email, password);
        System.out.println("Welcome back, " + authService.getCurrentUser().getFullName() + "\n");
        InputUtils.waitForEnter();
    }

    private static void showAllRooms(RoomService roomService) {
        List<Room> rooms = roomService.listAllRooms();

        System.out.println("=".repeat(31));
        System.out.println(center("ALL ROOMS", 31));
        System.out.println("=".repeat(31));

        for (Room room : rooms) {
            System.out.println("Room " + room.getRoomNumber());
            System.out.println("Type: " + room.getType());
            System.out.println("Capacity: " + room.getCapacity());
            System.out.println("Price/night: " + room.getPricePerNight() + " MAD");
            System.out.println("Status: " + room.getStatus());
            System.out.println("-".repeat(31));
        }
        System.out.println();
        InputUtils.waitForEnter();
    }

    private static void showUpdateProfile(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(31));
        System.out.println(center("UPDATE PROFILE", 31));
        System.out.println("=".repeat(31));
        System.out.println("Leave a field blank to keep it unchanged");
        System.out.print("Full Name: ");
        String name = InputUtils.readOptionalString();
        System.out.print("Email: ");
        String email = InputUtils.readOptionalString();
        System.out.print("Phone: ");
        String phone = InputUtils.readOptionalString();
        System.out.println();

        authService.updateProfile(
                name.isBlank() ? null : name,
                email.isBlank() ? null : email,
                phone.isBlank() ? null : phone
        );
        System.out.println("Profile updated\n");
        InputUtils.waitForEnter();
    }

    private static void showChangePassword(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(31));
        System.out.println(center("CHANGE PASSWORD", 31));
        System.out.println("=".repeat(31));
        System.out.print("Old Password: ");
        String oldPassword = InputUtils.readString();
        System.out.print("New Password: ");
        String newPassword = InputUtils.readString();

        authService.changePassword(oldPassword, newPassword);
        System.out.println("Password changed\n");
        InputUtils.waitForEnter();
    }

    private static String center(String text, int width) {
        if (text == null || text.length() >= width) {
            return text;
        }

        int padding = (width - text.length()) / 2;

        return " ".repeat(padding) + text + " ".repeat(padding);
    }

}