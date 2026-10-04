public class Availability {

    private int slotId;
    private String date;
    private String startTime;
    private String endTime;
    private boolean available;

    public Availability(int slotId, String date,
                        String startTime, String endTime) {

        this.slotId = slotId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

        // Initially the slot is available
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getSlotId() {
        return slotId;
    }

    public void displaySlot() {

        System.out.println(
                "Slot ID: " + slotId +
                " | Date: " + date +
                " | Time: " + startTime +
                " - " + endTime
        );
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }
}