package gui;

import javax.swing.*;
import java.awt.*;

import model.Doctor;
import model.Availability;
import model.Appointment;

public class DoctorDashboard extends JFrame {

        Doctor doctor;

        JButton manageProfileButton;
        JButton setFeeButton;
        JButton addAvailabilityButton;
        JButton viewSlotsButton;
        JButton viewAppointmentsButton;
        JButton logoutButton;

        // ==========================================
        // CONSTRUCTOR
        // ==========================================

        public DoctorDashboard(Doctor doctor) {

                this.doctor = doctor;

                setTitle("DocNest | Doctor Dashboard");

                setSize(650, 500);

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
                                "DOCTOR DASHBOARD",
                                SwingConstants.CENTER);

                title.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                24));

                // ==========================================
                // WELCOME MESSAGE
                // ==========================================

                JLabel welcome = new JLabel(
                                "Welcome, Dr. " + doctor.name,
                                SwingConstants.CENTER);

                welcome.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                18));

                // ==========================================
                // BUTTONS
                // ==========================================

                manageProfileButton = new JButton(
                                "Manage Profile");

                setFeeButton = new JButton(
                                "Set Consultation Fee");

                addAvailabilityButton = new JButton(
                                "Add Availability");

                viewSlotsButton = new JButton(
                                "View Available Slots");

                viewAppointmentsButton = new JButton(
                                "View Appointments");

                logoutButton = new JButton(
                                "Logout");

                // ==========================================
                // ADD COMPONENTS
                // ==========================================

                panel.add(title);

                panel.add(welcome);

                panel.add(manageProfileButton);

                panel.add(setFeeButton);

                panel.add(addAvailabilityButton);

                panel.add(viewSlotsButton);

                panel.add(viewAppointmentsButton);

                panel.add(logoutButton);

                add(panel);

                // ==========================================
                // MANAGE PROFILE
                // ==========================================

                manageProfileButton.addActionListener(e -> {

                        manageProfile();

                });

                // ==========================================
                // SET CONSULTATION FEE
                // ==========================================

                setFeeButton.addActionListener(e -> {

                        setFee();

                });

                // ==========================================
                // ADD AVAILABILITY
                // ==========================================

                addAvailabilityButton.addActionListener(e -> {

                        addAvailability();

                });

                // ==========================================
                // VIEW AVAILABLE SLOTS
                // ==========================================

                viewSlotsButton.addActionListener(e -> {

                        viewSlots();

                });

                // ==========================================
                // VIEW APPOINTMENTS
                // ==========================================

                viewAppointmentsButton.addActionListener(e -> {

                        viewAppointments();

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
        // MANAGE PROFILE
        // ==========================================

        public void manageProfile() {

                JOptionPane.showMessageDialog(
                                this,

                                "Doctor ID: "
                                                + doctor.id

                                                + "\nName: "
                                                + doctor.name

                                                + "\nEmail: "
                                                + doctor.email

                                                + "\nPhone: "
                                                + doctor.phone

                                                + "\nSpecialization: "
                                                + doctor.specialization

                                                + "\nConsultation Fee: ₹"
                                                + String.format(
                                                                "%.2f",
                                                                doctor.consultationFee),

                                "Doctor Profile",

                                JOptionPane.INFORMATION_MESSAGE);
        }

        // ==========================================
        // SET CONSULTATION FEE
        // ==========================================

        public void setFee() {

                String feeText = JOptionPane.showInputDialog(
                                this,
                                "Enter consultation fee (₹):");

                if (feeText == null) {

                        return;
                }

                if (feeText.trim().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please enter the consultation fee.");

                        return;
                }

                double fee;

                try {

                        fee = Double.parseDouble(
                                        feeText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Fee must be a valid number.");

                        return;
                }

                if (fee < 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Fee cannot be negative.");

                        return;
                }

                doctor.setConsultationFee(
                                fee);

                JOptionPane.showMessageDialog(
                                this,

                                "Consultation fee updated successfully!\n\n"
                                                + "Fee: ₹"
                                                + String.format(
                                                                "%.2f",
                                                                fee));
        }

        // ==========================================
        // ADD AVAILABILITY
        // ==========================================

        public void addAvailability() {

                JTextField dateField = new JTextField();

                JTextField startTimeField = new JTextField();

                JTextField endTimeField = new JTextField();

                JPanel panel = new JPanel(
                                new GridLayout(
                                                3,
                                                2,
                                                10,
                                                10));

                panel.add(
                                new JLabel("Date:"));

                panel.add(
                                dateField);

                panel.add(
                                new JLabel("Start Time:"));

                panel.add(
                                startTimeField);

                panel.add(
                                new JLabel("End Time:"));

                panel.add(
                                endTimeField);

                int result = JOptionPane.showConfirmDialog(
                                this,
                                panel,
                                "Add Availability",
                                JOptionPane.OK_CANCEL_OPTION);

                if (result != JOptionPane.OK_OPTION) {

                        return;
                }

                String date = dateField
                                .getText()
                                .trim();

                String startTime = startTimeField
                                .getText()
                                .trim();

                String endTime = endTimeField
                                .getText()
                                .trim();

                if (date.isEmpty() ||
                                startTime.isEmpty() ||
                                endTime.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please fill all fields.");

                        return;
                }

                // Generate 4-digit slot ID
                int slotId = Availability.nextSlotId;

                Availability slot = new Availability(
                                slotId,
                                date,
                                startTime,
                                endTime);

                doctor.addSlot(
                                slot);

                Availability.nextSlotId++;

                JOptionPane.showMessageDialog(
                                this,

                                "Slot added successfully!\n\n"
                                                + "Slot ID: "
                                                + slotId

                                                + "\nDate: "
                                                + date

                                                + "\nTime: "
                                                + startTime
                                                + " - "
                                                + endTime);
        }

        // ==========================================
        // VIEW AVAILABLE SLOTS
        // ==========================================

        public void viewSlots() {

                if (doctor.slots.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No slots available.");

                        return;
                }

                String[] columns = {

                                "Slot ID",
                                "Date",
                                "Start Time",
                                "End Time",
                                "Status"
                };

                Object[][] data = new Object[doctor.slots.size()][5];

                int row = 0;

                for (Availability slot : doctor.slots) {

                        data[row][0] = slot.id;

                        data[row][1] = slot.date;

                        data[row][2] = slot.startTime;

                        data[row][3] = slot.endTime;

                        if (slot.available) {

                                data[row][4] = "Available";

                        } else {

                                data[row][4] = "Booked";
                        }

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
                                                600,
                                                300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "My Availability",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // ==========================================
        // VIEW APPOINTMENTS
        // ==========================================

        public void viewAppointments() {

                if (doctor.appointments.size() == 0) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "No appointments found.");

                        return;
                }

                String[] columns = {

                                "Appointment ID",
                                "Patient",
                                "Date",
                                "Start Time",
                                "End Time",
                                "Status"
                };

                Object[][] data = new Object[doctor.appointments.size()][6];

                int row = 0;

                for (Appointment appointment : doctor.appointments) {

                        data[row][0] = appointment.id;

                        data[row][1] = appointment.patient.name;

                        data[row][2] = appointment.slot.date;

                        data[row][3] = appointment.slot.startTime;

                        data[row][4] = appointment.slot.endTime;

                        data[row][5] = appointment.status;

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
                                                650,
                                                300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "My Appointments",
                                JOptionPane.INFORMATION_MESSAGE);
        }
}
