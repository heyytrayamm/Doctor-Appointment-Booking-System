import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();

    static int patientId = 1;
    static int doctorId = 1;
    static int slotId = 1;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== DOCTOR APPOINTMENT BOOKING SYSTEM =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Availability Slot");
            System.out.println("4. View Available Slots");
            System.out.println("5. Book Appointment");
            System.out.println("6. View Patient Appointments");
            System.out.println("7. View Doctor Appointments");
            System.out.println("8. Cancel Appointment");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    addDoctor();
                    break;

                case 3:
                    addSlot();
                    break;

                case 4:
                    viewSlots();
                    break;

                case 5:
                    bookAppointment();
                    break;

                case 6:
                    viewPatientAppointments();
                    break;

                case 7:
                    viewDoctorAppointments();
                    break;

                case 8:
                    cancelAppointment();
                    break;

                case 9:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Add Patient
    static void addPatient() {

        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        Patient p = new Patient(
                patientId++,
                name,
                email,
                password,
                phone
        );

        patients.add(p);

        System.out.println("Patient registered successfully.");
    }

    // Add Doctor
    static void addDoctor() {

        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter specialization: ");
        String specialization = sc.nextLine();

        Doctor d = new Doctor(
                doctorId++,
                name,
                email,
                password,
                phone,
                specialization
        );

        doctors.add(d);

        System.out.println("Doctor added successfully.");
    }

    // Add Availability Slot
    static void addSlot() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("\nDoctors:");

        for (Doctor d : doctors) {
            System.out.println(
                    d.getUserId() + " - " +
                    d.getName() + " - " +
                    d.getSpecialization()
            );
        }

        System.out.print("Enter doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Doctor doctor = findDoctor(id);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Enter date: ");
        String date = sc.nextLine();

        System.out.print("Enter start time: ");
        String startTime = sc.nextLine();

        System.out.print("Enter end time: ");
        String endTime = sc.nextLine();

        Availability slot = new Availability(
                slotId++,
                date,
                startTime,
                endTime
        );

        doctor.addSlot(slot);

        System.out.println("Slot added successfully.");
    }

    // View Slots
    static void viewSlots() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        for (Doctor doctor : doctors) {

            System.out.println(
                    "\nDoctor: " +
                    doctor.getName()
            );

            doctor.showAvailableSlots();
        }
    }

    // Book Appointment
    static void bookAppointment() {

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("\nPatients:");

        for (Patient p : patients) {
            System.out.println(
                    p.getUserId() + " - " +
                    p.getName()
            );
        }

        System.out.print("Enter patient ID: ");
        int patientId = sc.nextInt();

        Patient patient = findPatient(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("\nDoctors:");

        for (Doctor d : doctors) {
            System.out.println(
                    d.getUserId() + " - " +
                    d.getName() + " - " +
                    d.getSpecialization()
            );
        }

        System.out.print("Enter doctor ID: ");
        int doctorId = sc.nextInt();

        Doctor doctor = findDoctor(doctorId);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctor.showAvailableSlots();

        System.out.print("\nEnter slot ID: ");
        int slotId = sc.nextInt();

        Availability slot = doctor.findSlot(slotId);

        if (slot == null) {
            System.out.println("Slot not found.");
            return;
        }

        patient.bookAppointment(doctor, slot);
    }

    // View Patient Appointments
    static void viewPatientAppointments() {

        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();

        Patient patient = findPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patient.viewAppointments();
    }

    // View Doctor Appointments
    static void viewDoctorAppointments() {

        System.out.print("Enter doctor ID: ");
        int id = sc.nextInt();

        Doctor doctor = findDoctor(id);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctor.viewAppointments();
    }

    // Cancel Appointment
    static void cancelAppointment() {

        System.out.print("Enter patient ID: ");
        int id = sc.nextInt();

        Patient patient = findPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patient.viewAppointments();

        System.out.print("Enter appointment ID to cancel: ");
        int appointmentId = sc.nextInt();

        patient.cancelAppointment(appointmentId);
    }

    // Find Patient
    static Patient findPatient(int id) {

        for (Patient p : patients) {

            if (p.getUserId() == id) {
                return p;
            }
        }

        return null;
    }

    // Find Doctor
    static Doctor findDoctor(int id) {

        for (Doctor d : doctors) {

            if (d.getUserId() == id) {
                return d;
            }
        }

        return null;
    }
}