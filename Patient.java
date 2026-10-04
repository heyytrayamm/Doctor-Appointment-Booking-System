import java.util.ArrayList;

public class Patient extends User {

    private ArrayList<Appointment> appointments;

    public Patient(int userId, String name, String email,
                   String password, String phone) {

        super(userId, name, email, password, phone);
        appointments = new ArrayList<>();
    }

    public void bookAppointment(Doctor doctor, Availability slot) {

        if (!slot.isAvailable()) {
            System.out.println("Slot is already booked.");
            return;
        }

        Appointment appointment =
                new Appointment(
                        appointments.size() + 1,
                        this,
                        doctor,
                        slot
                );

        slot.setAvailable(false);
        appointments.add(appointment);

        doctor.addAppointment(appointment);

        System.out.println("Appointment booked successfully.");
    }

    public void viewAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("\nPatient Appointments:");

        for (Appointment appointment : appointments) {
            appointment.displayDetails();
        }
    }

    public void cancelAppointment(int appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId() == appointmentId) {

                appointment.setStatus("Cancelled");

                appointment.getSlot().setAvailable(true);

                System.out.println("Appointment cancelled.");

                return;
            }
        }

        System.out.println("Appointment not found.");
    }
}