package com.theatre;

public class Seat {
    private int id;
    private int theaterId;
    private String rowName;
    private int seatNumber;
    private boolean isAvailable;

    public Seat(int id, int theaterId, String rowName, int seatNumber, boolean isAvailable) {
        this.id = id;
        this.theaterId = theaterId;
        this.rowName = rowName;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getLabel() {
        return rowName + seatNumber;
    }
}