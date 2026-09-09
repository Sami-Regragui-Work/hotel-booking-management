import repository.impl.InMemoryUserRepository;
import service.AuthService;
import util.InputUtils;

public class Main {
    public static void main(String[] args) {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        AuthService authService = new AuthService(userRepository);

        boolean keepGoing = true;
        while (keepGoing) {
            if (authService.isLoggedIn()) break;
            else Main.showGuestMenu(authService);
        }
    }

    private static void showGuestMenu(AuthService authService) {
        System.out.println("=".repeat(19));
        System.out.println(center("HOTEL BOOKING", 19));
        System.out.println("=".repeat(19));
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Choice: ");

        int choice = InputUtils.readInt();


        try {
            switch (choice) {
                case 1:
                    Main.showRegisterForum(authService);
                    break;
                case 2:
                    Main.showLoginForum(authService);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.\n");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showRegisterForum(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(19));
        System.out.println(center("Register", 19));
        System.out.println("=".repeat(19));
        System.out.print("Full Name: ");
        String name = InputUtils.readString();
        System.out.print("Email: ");
        String email = InputUtils.readString();
        System.out.print("Phone: ");
        String phone = InputUtils.readString();
        System.out.print("Password: ");
        String password = InputUtils.readString();

        authService.register(name, email, phone, password);
        System.out.println("Registration successful! Please login\n");
    }

    private static void showLoginForum(AuthService authService) throws IllegalArgumentException {
        System.out.println("=".repeat(19));
        System.out.println(center("Login", 19));
        System.out.println("=".repeat(19));
        System.out.print("Email: ");
        String email = InputUtils.readString();
        System.out.print("Password: ");
        String password = InputUtils.readString();

        authService.login(email, password);
        System.out.println("Welcome back, " + authService.getCurrentUser().getFullName() + "\n");
    }

    private static String center(String text, int width) {
        if (text == null || text.length() >= width) {
            return text;
        }

        int padding = (width - text.length()) / 2;

        return " ".repeat(padding) + text + " ".repeat(padding);
    }

}