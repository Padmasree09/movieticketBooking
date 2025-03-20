package com.theatre;

public class Movie {
    private int id;
    private String name;
    private String genre;
    private int duration;

    public Movie(int id, String name, String genre, int duration) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
}