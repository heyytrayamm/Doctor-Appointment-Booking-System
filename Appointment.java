public class Appointment {

    private int appointmentId;

    private Patient patient;
    private Doctor doctor;
    private Availability slot;

    private String status;

    public Appointment(int appointmentId,
                       Patient patient,
                       Doctor doctor,
                       Availability slot) {

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;

        status = "Booked";
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Availability getSlot() {
        return slot;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayDetails() {

        System.out.println(
                "Appointment ID: " + appointmentId +
                " | Patient: " + patient.getName() +
                " | Doctor: " + doctor.getName() +
                " | Date: " + slot.getDate() +
                " | Time: " + slot.getStartTime() +
                " | Status: " + status
        );
    }
}