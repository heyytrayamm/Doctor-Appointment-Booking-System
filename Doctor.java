package model;

import java.util.ArrayList;

public class Doctor extends User {

    // Store all registered doctors
    public static ArrayList<Doctor> doctors = new ArrayList<>();

    public static int nextDoctorId = 201;

    public String specialization;

    // Doctor's available slots
    public ArrayList<Availability> slots = new ArrayList<>();

    // Doctor's appointments
    public ArrayList<Appointment> appointments = new ArrayList<>();

    public Doctor(int id, String name, String email, String password, String phone, String specialization) {

        super(id, name, email, password, phone);

        this.specialization = specialization;
    }

    // Add doctor to the doctor list
    public static void addDoctor(Doctor doctor) {

        doctors.add(doctor);
    }

    // Doctor login
    public static Doctor login(int id, String password) {

        for (Doctor doctor : doctors) {

            if (doctor.id == id && doctor.password.equals(password)) {

                return doctor;
            }
        }

        return null;
    }

    // Add availability slot
    public void addSlot(Availability slot) {

        slots.add(slot);

        System.out.println("Slot added successfully!");
    }

    // Show available slots
    public void showSlots() {

        if (slots.size() == 0) {

            System.out.println(
                    "No slots available.");

            return;
        }

        System.out.println("\n========== AVAILABLE SLOTS ==========");

        boolean found = false;

        for (Availability slot : slots) {

            if (slot.available) {

                System.out.println("Slot ID: " + slot.id + " | Date: " + slot.date + " | Time: " + slot.startTime + " - " + slot.endTime);

                found = true;
            }
        }

        if (found == false) {

            System.out.println("No available slots.");
        }
    }

    // Find slot using slot ID
    public Availability findSlot(int slotId) {

        for (Availability slot : slots) {

            if (slot.id == slotId) {

                return slot;
            }
        }

        return null;
    }

    // View doctor's appointments
    public void viewAppointments() {

        if (appointments.size() == 0) {

            System.out.println("\nNo appointments found.");

            return;
        }

        System.out.println("\n========== DOCTOR APPOINTMENTS ==========");

        for (Appointment appointment : appointments) {

            appointment.showAppointment();
        }
    }
}
