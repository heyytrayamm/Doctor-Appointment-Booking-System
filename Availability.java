package model;

public class Availability {

    public int id;

    public String date;
    public String startTime;
    public String endTime;

    public boolean available;

    // 4-digit Slot ID starts from 1001
    public static int nextSlotId = 1001;


    // Constructor
    public Availability(int id,
                        String date,
                        String startTime,
                        String endTime) {

        this.id = id;

        this.date = date;

        this.startTime = startTime;

        this.endTime = endTime;

        this.available = true;
    }
}
