package com.theatre;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Booking {
    private int id;
    private Timestamp bookingDate;
    private double totalAmount;
    private String status;
    private String customerName;
    private String customerEmail;
    private String movieName;
    private String theaterName;
    private Date showDate;
    private Time showTime;
    private List<String> seatLabels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }
}