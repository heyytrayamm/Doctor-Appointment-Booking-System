package model;

import java.util.ArrayList;

public class Doctor extends User {

    public static ArrayList<Doctor> doctors = new ArrayList<>();

    public static int nextDoctorId = 201;

    public String specialization;

    // Doctor consultation fee
    public double consultationFee;

    public ArrayList<Availability> slots = new ArrayList<>();

    public ArrayList<Appointment> appointments = new ArrayList<>();

    // Constructor
    public Doctor(int id,
            String name,
            String email,
            String password,
            String phone,
            String specialization) {

        super(
                id,
                name,
                email,
                password,
                phone);

        this.specialization = specialization;

        // Default fee
        this.consultationFee = 0;
    }

    // Add doctor
    public static void addDoctor(Doctor doctor) {

        doctors.add(doctor);
    }

    // Doctor login
    public static Doctor login(int id,
            String password) {

        for (Doctor doctor : doctors) {

            if (doctor.id == id &&
                    doctor.password.equals(password)) {

                return doctor;
            }
        }

        return null;
    }

    // Set consultation fee
    public void setConsultationFee(
            double consultationFee) {

        this.consultationFee = consultationFee;
    }

    // Add availability
    public void addSlot(Availability slot) {

        slots.add(slot);
    }

    // Show available slots
    public void showSlots() {

        if (slots.size() == 0) {

            System.out.println(
                    "No slots available.");

            return;
        }

        System.out.println(
                "\n========== AVAILABLE SLOTS ==========");

        for (Availability slot : slots) {

            if (slot.available) {

                System.out.println(
                        "Slot ID: "
                                + slot.id
                                + " | Date: "
                                + slot.date
                                + " | Time: "
                                + slot.startTime
                                + " - "
                                + slot.endTime);
            }
        }
    }

    // Find slot
    public Availability findSlot(int slotId) {

        for (Availability slot : slots) {

            if (slot.id == slotId) {

                return slot;
            }
        }

        return null;
    }

    // View appointments
    public void viewAppointments() {

        if (appointments.size() == 0) {

            System.out.println(
                    "\nNo appointments found.");

            return;
        }

        for (Appointment appointment : appointments) {

            appointment.showAppointment();
        }
    }
}
