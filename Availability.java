package model;

public class Availability {

    // Slot ID
    public int id;

    // Slot date and time
    public String date;
    public String startTime;
    public String endTime;

    // true = available
    // false = booked
    public boolean available;

    public Availability(int id, String date, String startTime, String endTime) {

        this.id = id;

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;

        // New slot is available by default
        this.available = true;
    }
}