package model;

import java.util.ArrayList;

public class Patient extends User {

        // Store all registered patients
        public static ArrayList<Patient> patients = new ArrayList<>();

        public static int nextPatientId = 101;

        // Store patient's appointments
        public ArrayList<Appointment> appointments = new ArrayList<>();

        public Patient(int id, String name, String email, String password, String phone) {

                super(id, name, email, password, phone);
        }

        public static void addPatient(Patient patient) {

                patients.add(patient);
        }

        public static Patient login(int id, String password) {

                for (Patient patient : patients) {

                        if (patient.id == id && patient.password.equals(password)) {

                                return patient;
                        }
                }

                return null;
        }

        public void bookAppointment(
                        Doctor doctor,
                        Availability slot) {

                // Check whether slot is available
                if (slot.available == false) {

                        System.out.println("Slot is already booked.");

                        return;
                }

                // Create appointment
                Appointment appointment = new Appointment(appointments.size() + 1, this, doctor, slot);

                // Add appointment to patient
                appointments.add(appointment);

                // Add appointment to doctor
                doctor.appointments.add(appointment);

                // Make slot unavailable
                slot.available = false;

                System.out.println("Appointment booked successfully!");
        }

        // VIEW APPOINTMENTS

        public void viewAppointments() {

                if (appointments.size() == 0) {

                        System.out.println("No appointments found.");

                        return;
                }

                System.out.println("\n========== MY APPOINTMENTS ==========");

                for (Appointment appointment : appointments) {

                        appointment.showAppointment();
                }
        }

        // CANCEL APPOINTMENT

        public void cancelAppointment(int appointmentId) {

                for (Appointment appointment : appointments) {

                        if (appointment.id == appointmentId && appointment.status.equals("Booked")) {

                                // Change status
                                appointment.status = "Cancelled";

                                // Make slot available again
                                appointment.slot.available = true;

                                System.out.println("Appointment cancelled successfully.");

                                return;
                        }
                }

                System.out.println("Appointment not found.");
        }
}