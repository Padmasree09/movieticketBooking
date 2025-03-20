package com.theatre;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Showtime {
    private int id;
    private int movieId;
    private int theaterId;
    private String theaterName;
    private Date showDate;
    private Time startTime;
    private double price;

    public Showtime(int id, int movieId, int theaterId, String theaterName,
            Date showDate, Time startTime, double price) {
        this.id = id;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.showDate = showDate;
        this.startTime = startTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public Date getShowDate() {
        return showDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public double getPrice() {
        return price;
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat.format(showDate);
    }

    public String getFormattedTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(startTime);
    }

    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
}