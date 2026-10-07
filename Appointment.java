package model;

public class Appointment {

        public int id;

        public Patient patient;
        public Doctor doctor;
        public Availability slot;

        public String status;

        public Appointment(int id, Patient patient, Doctor doctor, Availability slot) {

                this.id = id;
                this.patient = patient;
                this.doctor = doctor;
                this.slot = slot;

                status = "Booked";
        }

        // Display appointment
        public void showAppointment() {

                System.out.println("----------------------------------------------");

                System.out.println("Appointment ID : " + id);

                System.out.println("Patient        : " + patient.name);

                System.out.println("Doctor         : " + doctor.name);

                System.out.println("Specialization : " + doctor.specialization);

                System.out.println("Date           : " + slot.date);

                System.out.println("Time           : " + slot.startTime + " - " + slot.endTime);

                System.out.println("Status         : " + status);
        }
}