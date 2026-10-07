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

        public PatientDashboard(Patient patient) {

                this.patient = patient;

                setTitle("Patient Dashboard");

                setSize(600, 500);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(8, 1, 10, 10));

                JLabel title = new JLabel("PATIENT DASHBOARD", SwingConstants.CENTER);

                title.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                22));

                JLabel welcome = new JLabel("Welcome, " + patient.name, SwingConstants.CENTER);

                welcome.setFont(
                                new Font(
                                                "Arial",
                                                Font.PLAIN,
                                                16));

                searchDoctorButton = new JButton("Search Doctor");

                viewSlotsButton = new JButton("View Available Slots");

                bookAppointmentButton = new JButton("Book Appointment");

                viewAppointmentsButton = new JButton("View Appointments");

                cancelAppointmentButton = new JButton("Cancel Appointment");

                logoutButton = new JButton("Logout");

                panel.add(title);
                panel.add(welcome);
                panel.add(searchDoctorButton);
                panel.add(viewSlotsButton);
                panel.add(bookAppointmentButton);
                panel.add(viewAppointmentsButton);
                panel.add(cancelAppointmentButton);
                panel.add(logoutButton);

                add(panel);

                searchDoctorButton.addActionListener(e -> {

                        searchDoctor();

                });

                viewSlotsButton.addActionListener(e -> {

                        viewAvailableSlots();

                });

                bookAppointmentButton.addActionListener(e -> {

                        bookAppointment();

                });

                viewAppointmentsButton.addActionListener(e -> {

                        viewAppointments();

                });

                cancelAppointmentButton.addActionListener(e -> {

                        cancelAppointment();

                });

                logoutButton.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                setVisible(true);
        }

        // SEARCH DOCTOR - JTable

        public void searchDoctor() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(this, "No doctors registered.");

                        return;
                }

                String[] columns = {
                                "Doctor ID",
                                "Name",
                                "Specialization",
                                "Email",
                                "Phone"
                };

                Object[][] data = new Object[Doctor.doctors.size()][5];

                int row = 0;

                for (Doctor doctor : Doctor.doctors) {

                        data[row][0] = doctor.id;
                        data[row][1] = doctor.name;
                        data[row][2] = doctor.specialization;
                        data[row][3] = doctor.email;
                        data[row][4] = doctor.phone;

                        row++;
                }

                JTable table = new JTable(data, columns);

                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(600, 250));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Search Doctor",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // VIEW AVAILABLE SLOTS - JTable

        public void viewAvailableSlots() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(this, "No doctors registered.");

                        return;
                }

                String[] columns = {
                                "Doctor ID",
                                "Doctor",
                                "Specialization",
                                "Slot ID",
                                "Date",
                                "Start Time",
                                "End Time"
                };

                int slotCount = 0;

                for (Doctor doctor : Doctor.doctors) {

                        for (Availability slot : doctor.slots) {

                                if (slot.available) {

                                        slotCount++;
                                }
                        }
                }

                if (slotCount == 0) {

                        JOptionPane.showMessageDialog(this, "No available slots.");

                        return;
                }

                Object[][] data = new Object[slotCount][7];

                int row = 0;

                for (Doctor doctor : Doctor.doctors) {

                        for (Availability slot : doctor.slots) {

                                if (slot.available) {

                                        data[row][0] = doctor.id;
                                        data[row][1] = doctor.name;
                                        data[row][2] = doctor.specialization;
                                        data[row][3] = slot.id;
                                        data[row][4] = slot.date;
                                        data[row][5] = slot.startTime;
                                        data[row][6] = slot.endTime;

                                        row++;
                                }
                        }
                }

                JTable table = new JTable(data, columns);

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(750, 300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Available Slots",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // BOOK APPOINTMENT

        public void bookAppointment() {

                if (Doctor.doctors.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No doctors registered.");

                        return;
                }

                String doctorText = JOptionPane.showInputDialog(
                                this,
                                "Enter Doctor ID:");

                if (doctorText == null) {
                        return;
                }

                if (doctorText.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please enter Doctor ID.");

                        return;
                }

                int doctorId;

                try {

                        doctorId = Integer.parseInt(doctorText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Doctor ID must be a number.");

                        return;
                }

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

                String[] columns = {
                                "Slot ID",
                                "Date",
                                "Start Time",
                                "End Time"
                };

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

                JTable table = new JTable(data, columns);

                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(500, 250));

                int result = JOptionPane.showConfirmDialog(
                                this,
                                scrollPane,
                                "Select Available Slot",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                if (result != JOptionPane.OK_OPTION) {

                        return;
                }

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please select a slot.");

                        return;
                }

                int slotId = (Integer) table.getValueAt(selectedRow, 0);

                Availability selectedSlot = selectedDoctor.findSlot(slotId);

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

                patient.bookAppointment(
                                selectedDoctor,
                                selectedSlot);

                JOptionPane.showMessageDialog(this,
                                "Appointment booked successfully!\n\n" + "Doctor: " + selectedDoctor.name
                                                + "\nSpecialization: " + selectedDoctor.specialization + "\nDate: "
                                                + selectedSlot.date + "\nTime: " + selectedSlot.startTime + " - "
                                                + selectedSlot.endTime);
        }

        
        // VIEW APPOINTMENTS - JTable
        

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
                                "Date",
                                "Start Time",
                                "End Time",
                                "Status"
                };

                Object[][] data = new Object[patient.appointments.size()][7];

                int row = 0;

                for (Appointment appointment : patient.appointments) {

                        data[row][0] = appointment.id;

                        data[row][1] = appointment.doctor.name;

                        data[row][2] = appointment.doctor.specialization;

                        data[row][3] = appointment.slot.date;

                        data[row][4] = appointment.slot.startTime;

                        data[row][5] = appointment.slot.endTime;

                        data[row][6] = appointment.status;

                        row++;
                }

                JTable table = new JTable(data, columns);

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(750, 300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "My Appointments",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        
        // CANCEL APPOINTMENT

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

                JTable table = new JTable(data, columns);

                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(600, 300));

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

                patient.cancelAppointment(
                                appointmentId);

                JOptionPane.showMessageDialog(
                                this,
                                "Appointment cancellation request processed.");
        }
}