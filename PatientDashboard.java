package gui;

import javax.swing.*;
import java.awt.*;

import model.Patient;
import model.Doctor;
import model.Availability;
import model.Appointment;

public class PatientDashboard extends JFrame {

        Patient patient;

        JButton searchDoctorButton;
        JButton viewSlotsButton;
        JButton bookAppointmentButton;
        JButton viewAppointmentsButton;
        JButton cancelAppointmentButton;
        JButton logoutButton;

        // ==========================================
        // CONSTRUCTOR
        // ==========================================

        public PatientDashboard(Patient patient) {

                this.patient = patient;

                setTitle("DocNest | Patient Dashboard");

                setSize(700, 500);

                setDefaultCloseOperation(
                                JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                // ==========================================
                // MAIN PANEL
                // ==========================================

                JPanel panel = new JPanel();

                panel.setLayout(
                                new GridLayout(8, 1, 10, 10));

                // ==========================================
                // TITLE
                // ==========================================

                JLabel title = new JLabel(
                                "PATIENT DASHBOARD",
                                SwingConstants.CENTER);

                title.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                24));

                // ==========================================
                // WELCOME
                // ==========================================

                JLabel welcome = new JLabel(
                                "Welcome, " + patient.name,
                                SwingConstants.CENTER);

                welcome.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                18));

                // ==========================================
                // BUTTONS
                // ==========================================

                searchDoctorButton = new JButton(
                                "Search Doctor");

                viewSlotsButton = new JButton(
                                "View Available Slots");

                bookAppointmentButton = new JButton(
                                "Book Appointment");

                viewAppointmentsButton = new JButton(
                                "View Appointments");

                cancelAppointmentButton = new JButton(
                                "Cancel Appointment");

                logoutButton = new JButton(
                                "Logout");

                // ==========================================
                // ADD COMPONENTS
                // ==========================================

                panel.add(title);

                panel.add(welcome);

                panel.add(searchDoctorButton);

                panel.add(viewSlotsButton);

                panel.add(bookAppointmentButton);

                panel.add(viewAppointmentsButton);

                panel.add(cancelAppointmentButton);

                panel.add(logoutButton);

                add(panel);

                // ==========================================
                // SEARCH DOCTOR
                // ==========================================

                searchDoctorButton.addActionListener(e -> {

                        searchDoctor();

                });

                // ==========================================
                // VIEW AVAILABLE SLOTS
                // ==========================================

                viewSlotsButton.addActionListener(e -> {

                        viewAvailableSlots();

                });

                // ==========================================
                // BOOK APPOINTMENT
                // ==========================================

                bookAppointmentButton.addActionListener(e -> {

                        bookAppointment();

                });

                // ==========================================
                // VIEW APPOINTMENTS
                // ==========================================

                viewAppointmentsButton.addActionListener(e -> {

                        viewAppointments();

                });

                // ==========================================
                // CANCEL APPOINTMENT
                // ==========================================

                cancelAppointmentButton.addActionListener(e -> {

                        cancelAppointment();

                });

                // ==========================================
                // LOGOUT
                // ==========================================

                logoutButton.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                setVisible(true);
        }

        // ==========================================
        // SEARCH DOCTOR
        // ==========================================

        public void searchDoctor() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No doctors registered.");

                        return;
                }

                String[] columns = {

                                "Doctor ID",
                                "Name",
                                "Specialization",
                                "Consultation Fee",
                                "Email",
                                "Phone"
                };

                Object[][] data = new Object[Doctor.doctors.size()][6];

                int row = 0;

                for (Doctor doctor : Doctor.doctors) {

                        data[row][0] = doctor.id;

                        data[row][1] = doctor.name;

                        data[row][2] = doctor.specialization;

                        data[row][3] = "₹"
                                        + String.format(
                                                        "%.2f",
                                                        doctor.consultationFee);

                        data[row][4] = doctor.email;

                        data[row][5] = doctor.phone;

                        row++;
                }

                JTable table = new JTable(
                                data,
                                columns);

                table.setRowHeight(25);

                table.getTableHeader()
                                .setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(
                                table);

                scrollPane.setPreferredSize(
                                new Dimension(
                                                850,
                                                300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Search Doctor",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // ==========================================
        // VIEW AVAILABLE SLOTS
        // ==========================================

        public void viewAvailableSlots() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No doctors registered.");

                        return;
                }

                int slotCount = 0;

                for (Doctor doctor : Doctor.doctors) {

                        for (Availability slot : doctor.slots) {

                                if (slot.available) {

                                        slotCount++;
                                }
                        }
                }

                if (slotCount == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No available slots.");

                        return;
                }

                String[] columns = {

                                "Doctor ID",
                                "Doctor",
                                "Specialization",
                                "Consultation Fee",
                                "Slot ID",
                                "Date",
                                "Start Time",
                                "End Time"
                };

                Object[][] data = new Object[slotCount][8];

                int row = 0;

                for (Doctor doctor : Doctor.doctors) {

                        for (Availability slot : doctor.slots) {

                                if (slot.available) {

                                        data[row][0] = doctor.id;

                                        data[row][1] = doctor.name;

                                        data[row][2] = doctor.specialization;

                                        data[row][3] = "₹"
                                                        + String.format(
                                                                        "%.2f",
                                                                        doctor.consultationFee);

                                        data[row][4] = slot.id;

                                        data[row][5] = slot.date;

                                        data[row][6] = slot.startTime;

                                        data[row][7] = slot.endTime;

                                        row++;
                                }
                        }
                }

                JTable table = new JTable(
                                data,
                                columns);

                table.setRowHeight(25);

                table.getTableHeader()
                                .setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(
                                table);

                scrollPane.setPreferredSize(
                                new Dimension(
                                                1000,
                                                350));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Available Slots",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // ==========================================
        // BOOK APPOINTMENT
        // ==========================================

        public void bookAppointment() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No doctors registered.");

                        return;
                }

                // ------------------------------------------
                // ENTER DOCTOR ID
                // ------------------------------------------

                String doctorText = JOptionPane.showInputDialog(
                                this,
                                "Enter Doctor ID:");

                if (doctorText == null) {

                        return;
                }

                if (doctorText.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please enter Doctor ID.");

                        return;
                }

                int doctorId;

                try {

                        doctorId = Integer.parseInt(
                                        doctorText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Doctor ID must be a number.");

                        return;
                }

                // ------------------------------------------
                // FIND DOCTOR
                // ------------------------------------------

                Doctor selectedDoctor = null;

                for (Doctor doctor : Doctor.doctors) {

                        if (doctor.id == doctorId) {

                                selectedDoctor = doctor;

                                break;
                        }
                }

                if (selectedDoctor == null) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Doctor not found.");

                        return;
                }

                // ------------------------------------------
                // COUNT AVAILABLE SLOTS
                // ------------------------------------------

                int slotCount = 0;

                for (Availability slot : selectedDoctor.slots) {

                        if (slot.available) {

                                slotCount++;
                        }
                }

                if (slotCount == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No available slots for this doctor.");

                        return;
                }

                // ------------------------------------------
                // SLOT TABLE
                // ------------------------------------------

                String[] columns = {

                                "Slot ID",
                                "Date",
                                "Start Time",
                                "End Time"
                };

                Object[][] data = new Object[slotCount][4];

                int row = 0;

                for (Availability slot : selectedDoctor.slots) {

                        if (slot.available) {

                                data[row][0] = slot.id;

                                data[row][1] = slot.date;

                                data[row][2] = slot.startTime;

                                data[row][3] = slot.endTime;

                                row++;
                        }
                }

                JTable table = new JTable(
                                data,
                                columns);

                table.setSelectionMode(
                                ListSelectionModel.SINGLE_SELECTION);

                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(
                                table);

                scrollPane.setPreferredSize(
                                new Dimension(
                                                550,
                                                300));

                int result = JOptionPane.showConfirmDialog(
                                this,

                                scrollPane,

                                "Select Available Slot",

                                JOptionPane.OK_CANCEL_OPTION,

                                JOptionPane.PLAIN_MESSAGE);

                if (result != JOptionPane.OK_OPTION) {

                        return;
                }

                // ------------------------------------------
                // GET SELECTED ROW
                // ------------------------------------------

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please select a slot.");

                        return;
                }

                int slotId = (Integer) table.getValueAt(
                                selectedRow,
                                0);

                // ------------------------------------------
                // FIND SELECTED SLOT
                // ------------------------------------------

                Availability selectedSlot = selectedDoctor.findSlot(
                                slotId);

                if (selectedSlot == null) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Slot not found.");

                        return;
                }

                if (selectedSlot.available == false) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Slot is already booked.");

                        return;
                }

                // ------------------------------------------
                // CONFIRM BOOKING
                // ------------------------------------------

                int confirm = JOptionPane.showConfirmDialog(
                                this,

                                "Doctor: "
                                                + selectedDoctor.name

                                                + "\nSpecialization: "
                                                + selectedDoctor.specialization

                                                + "\nConsultation Fee: ₹"
                                                + String.format(
                                                                "%.2f",
                                                                selectedDoctor.consultationFee)

                                                + "\nDate: "
                                                + selectedSlot.date

                                                + "\nTime: "
                                                + selectedSlot.startTime
                                                + " - "
                                                + selectedSlot.endTime

                                                + "\n\nConfirm appointment?",

                                "Confirm Appointment",

                                JOptionPane.YES_NO_OPTION);

                if (confirm != JOptionPane.YES_OPTION) {

                        return;
                }

                // ------------------------------------------
                // BOOK APPOINTMENT
                // ------------------------------------------

                patient.bookAppointment(
                                selectedDoctor,
                                selectedSlot);

                JOptionPane.showMessageDialog(
                                this,

                                "Appointment booked successfully!\n\n"

                                                + "Appointment ID: "
                                                + (patient.appointments
                                                                .get(
                                                                                patient.appointments.size() - 1).id)

                                                + "\nDoctor: "
                                                + selectedDoctor.name

                                                + "\nSpecialization: "
                                                + selectedDoctor.specialization

                                                + "\nConsultation Fee: ₹"
                                                + String.format(
                                                                "%.2f",
                                                                selectedDoctor.consultationFee)

                                                + "\nDate: "
                                                + selectedSlot.date

                                                + "\nTime: "
                                                + selectedSlot.startTime
                                                + " - "
                                                + selectedSlot.endTime);
        }

        // ==========================================
        // VIEW APPOINTMENTS
        // ==========================================

        public void viewAppointments() {

                if (patient.appointments.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No appointments found.");

                        return;
                }

                String[] columns = {

                                "Appointment ID",
                                "Doctor",
                                "Specialization",
                                "Consultation Fee",
                                "Date",
                                "Start Time",
                                "End Time",
                                "Status"
                };

                Object[][] data = new Object[patient.appointments.size()][8];

                int row = 0;

                for (Appointment appointment : patient.appointments) {

                        data[row][0] = appointment.id;

                        data[row][1] = appointment.doctor.name;

                        data[row][2] = appointment.doctor.specialization;

                        data[row][3] = "₹"
                                        + String.format(
                                                        "%.2f",
                                                        appointment.doctor.consultationFee);

                        data[row][4] = appointment.slot.date;

                        data[row][5] = appointment.slot.startTime;

                        data[row][6] = appointment.slot.endTime;

                        data[row][7] = appointment.status;

                        row++;
                }

                JTable table = new JTable(
                                data,
                                columns);

                table.setRowHeight(25);

                table.getTableHeader()
                                .setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(
                                table);

                scrollPane.setPreferredSize(
                                new Dimension(
                                                1000,
                                                350));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "My Appointments",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // ==========================================
        // CANCEL APPOINTMENT
        // ==========================================

        public void cancelAppointment() {

                if (patient.appointments.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No appointments found.");

                        return;
                }

                String[] columns = {

                                "Appointment ID",
                                "Doctor",
                                "Date",
                                "Time",
                                "Status"
                };

                Object[][] data = new Object[patient.appointments.size()][5];

                int row = 0;

                for (Appointment appointment : patient.appointments) {

                        data[row][0] = appointment.id;

                        data[row][1] = appointment.doctor.name;

                        data[row][2] = appointment.slot.date;

                        data[row][3] = appointment.slot.startTime
                                        + " - "
                                        + appointment.slot.endTime;

                        data[row][4] = appointment.status;

                        row++;
                }

                JTable table = new JTable(
                                data,
                                columns);

                table.setSelectionMode(
                                ListSelectionModel.SINGLE_SELECTION);

                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(
                                table);

                scrollPane.setPreferredSize(
                                new Dimension(
                                                650,
                                                300));

                int result = JOptionPane.showConfirmDialog(
                                this,

                                scrollPane,

                                "Select Appointment to Cancel",

                                JOptionPane.OK_CANCEL_OPTION,

                                JOptionPane.PLAIN_MESSAGE);

                if (result != JOptionPane.OK_OPTION) {

                        return;
                }

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please select an appointment.");

                        return;
                }

                int appointmentId = (Integer) table.getValueAt(
                                selectedRow,
                                0);

                // ------------------------------------------
                // CONFIRM CANCELLATION
                // ------------------------------------------

                int confirm = JOptionPane.showConfirmDialog(
                                this,

                                "Are you sure you want to "
                                                + "cancel this appointment?",

                                "Cancel Appointment",

                                JOptionPane.YES_NO_OPTION);

                if (confirm != JOptionPane.YES_OPTION) {

                        return;
                }

                patient.cancelAppointment(
                                appointmentId);

                JOptionPane.showMessageDialog(
                                this,
                                "Appointment cancelled successfully.");
        }
}
