import java.util.ArrayList;

public class Doctor extends User {

    private String specialization;

    private ArrayList<Availability> slots;
    private ArrayList<Appointment> appointments;

    public Doctor(int userId, String name, String email,
                  String password, String phone,
                  String specialization) {

        super(userId, name, email, password, phone);

        this.specialization = specialization;

        slots = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    // Add a new availability slot
    public void addSlot(Availability slot) {

        slots.add(slot);

        System.out.println("Slot added successfully.");
    }

    // Find a slot using slot ID
    public Availability findSlot(int slotId) {

        for (Availability slot : slots) {

            if (slot.getSlotId() == slotId) {
                return slot;
            }
        }

        return null;
    }

    // Remove an availability slot
    public void removeSlot(int slotId) {

        Availability slot = findSlot(slotId);

        if (slot != null) {

            slots.remove(slot);

            System.out.println("Slot removed successfully.");

        } else {

            System.out.println("Slot not found.");
        }
    }

    // Display all available slots
    public void showAvailableSlots() {

        boolean found = false;

        System.out.println("\nAvailable Slots:");

        for (Availability slot : slots) {

            if (slot.isAvailable()) {

                slot.displaySlot();

                found = true;
            }
        }

        if (!found) {
            System.out.println("No available slots.");
        }
    }

    // Add a booked appointment
    public void addAppointment(Appointment appointment) {

        appointments.add(appointment);
    }

    // Display doctor's appointments
    public void viewAppointments() {

        System.out.println("\nDoctor Appointments:");

        if (appointments.isEmpty()) {

            System.out.println("No appointments found.");

            return;
        }

        for (Appointment appointment : appointments) {

            appointment.displayDetails();
        }
    }

    // Get doctor specialization
    public String getSpecialization() {

        return specialization;
    }

    // Update specialization
    public void setSpecialization(String specialization) {

        this.specialization = specialization;
    }
}