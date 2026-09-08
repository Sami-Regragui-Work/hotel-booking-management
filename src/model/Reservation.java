package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private String reservationCode;

    private UUID userId;
    private String roomNumber;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private int numberOfGuests;
    private long numberOfNights;

    private BigDecimal totalPrice;

    private ReservationStatus status;

    private LocalDateTime createdAt;

    public Reservation(String reservationCode, UUID userId, String roomNumber, LocalDate checkIn, LocalDate checkOut, int numberOfGuests, long numberOfNights, BigDecimal totalPrice, ReservationStatus status) {
        this.generateId();
        this.setReservationCode(reservationCode);
        this.setUserId(userId);
        this.setRoomNumber(roomNumber);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setNumberOfGuests(numberOfGuests);
        this.setNumberOfNights(numberOfNights);
        this.setTotalPrice(totalPrice);
        this.setStatus(status);
        this.generateCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public void generateId() {
        this.id = UUID.randomUUID();
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        if (reservationCode == null || reservationCode.trim().isEmpty())
            throw new IllegalArgumentException("Empty reservation code");
        this.reservationCode = reservationCode.trim();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("No user was specified");
        }
        this.userId = userId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException("No room number was specified");
        this.roomNumber = roomNumber.trim();
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        if (checkIn == null) {
            throw new IllegalArgumentException("Empty check-in date");
        }
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        if (checkOut == null) {
            throw new IllegalArgumentException("Empty checkout date");
        }
        this.checkOut = checkOut;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests <= 0) {
            throw new IllegalArgumentException("Negative number of guests");
        }
        this.numberOfGuests = numberOfGuests;
    }

    public long getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(long numberOfNights) {
        if (numberOfNights <= 0) {
            throw new IllegalArgumentException("Negative number of nights");
        }
        this.numberOfNights = numberOfNights;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Negative total price");
        }
        this.totalPrice = totalPrice;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("No status was specified");
        }
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void generateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
