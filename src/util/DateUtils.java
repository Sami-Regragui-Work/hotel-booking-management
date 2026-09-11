package util;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    private DateUtils() {}

    public static boolean periodsOverlap(LocalDate reservedCheckIn, LocalDate reservedCheckOut, LocalDate newCheckIn, LocalDate newCheckOut) {
        return newCheckIn.isBefore(reservedCheckOut) && newCheckOut.isAfter(reservedCheckIn);
    }

    public static long nightsBetween(LocalDate checkIn, LocalDate checkOut) {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
