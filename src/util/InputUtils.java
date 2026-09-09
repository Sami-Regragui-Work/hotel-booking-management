package util;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private InputUtils() {}

    public static String readString() {
        while (true) {
            String input = scanner.nextLine();
            if (ValidationUtils.isNotEmpty(input)) {
                return  input.trim();
            }
            System.out.println("This field cannot be empty. Try again");
        }
    }

    public static int readInt() {
        while (true) {
            String  input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static LocalDate readDate() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input.trim(), DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try dd/MM/yyyy");
            }
        }
    }

    public static BigDecimal readBigDecimal() {
        while (true) {
            String input = scanner.nextLine();
            try {
                BigDecimal value = new BigDecimal(input.trim());
                if (value.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Amount cannot be negative");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount");
            }
        }
    }


}
