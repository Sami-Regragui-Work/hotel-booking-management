package model;

import java.math.BigDecimal;

public class Room {
    private String roomNumber;
    private RoomType type;
    private int capacity;
    private BigDecimal pricePerNight;
    private RoomStatus status;

    public Room(String roomNumber, RoomType type, int capacity, BigDecimal pricePerNight) {
        this.setRoomNumber(roomNumber);
        this.setType(type);
        this.setCapacity(capacity);
        this.setPricePerNight(pricePerNight);
        this.status = RoomStatus.AVAILABLE;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new IllegalArgumentException("Empty room number");
        this.roomNumber = roomNumber.trim();
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        if (type == null) {
            throw new IllegalArgumentException("Empty room type");
        }
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Negative capacity");
        }
        this.capacity = capacity;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        if (pricePerNight == null || pricePerNight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Negative price");
        }
        this.pricePerNight = pricePerNight;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Empty status");
        }
        this.status = status;
    }
}
