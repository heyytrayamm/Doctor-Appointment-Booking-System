package gui;

import javax.swing.*;
import java.awt.*;

import model.Patient;
import model.Doctor;
import model.Appointment;

public class AdminDashboard extends JFrame {

        JButton manageUsersButton;
        JButton manageDoctorsButton;
        JButton manageAppointmentsButton;
        JButton logoutButton;

        public AdminDashboard() {

                setTitle("Administrator Dashboard");

                setSize(600, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(5, 1, 10, 10));

                JLabel title = new JLabel("ADMINISTRATOR DASHBOARD", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 22));

                manageUsersButton = new JButton("Manage Users");

                manageDoctorsButton = new JButton("Manage Doctors");

                manageAppointmentsButton = new JButton("Manage Appointments");

                logoutButton = new JButton("Logout");

                panel.add(title);
                panel.add(manageUsersButton);
                panel.add(manageDoctorsButton);
                panel.add(manageAppointmentsButton);
                panel.add(logoutButton);

                add(panel);

                // Manage Users
                manageUsersButton.addActionListener(e -> {

                        manageUsers();

                });

                // Manage Doctors
                manageDoctorsButton.addActionListener(e -> {

                        manageDoctors();

                });

                // Manage Appointments
                manageAppointmentsButton.addActionListener(e -> {

                        manageAppointments();

                });

                // Logout
                logoutButton.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                setVisible(true);
        }

        // MANAGE USERS - JTable

        public void manageUsers() {

                if (Patient.patients.size() == 0) {

                        JOptionPane.showMessageDialog(this, "No patients registered.");

                        return;
                }

                String[] columns = {
                                "Patient ID",
                                "Name",
                                "Email",
                                "Phone"
                };

                Object[][] data = new Object[Patient.patients.size()][4];

                int row = 0;

                for (Patient patient : Patient.patients) {

                        data[row][0] = patient.id;

                        data[row][1] = patient.name;

                        data[row][2] = patient.email;

                        data[row][3] = patient.phone;

                        row++;
                }

                JTable table = new JTable(data, columns);

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(600, 300));

                JOptionPane.showMessageDialog(this, scrollPane,"Manage Users", JOptionPane.INFORMATION_MESSAGE);
        }

        // MANAGE DOCTORS - JTable

        public void manageDoctors() {

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

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(700, 300));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Manage Doctors",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // MANAGE APPOINTMENTS - JTable

        public void manageAppointments() {

                int appointmentCount = 0;

                // Count appointments
                for (Doctor doctor : Doctor.doctors) {

                        appointmentCount += doctor.appointments.size();
                }

                if (appointmentCount == 0) {

                        JOptionPane.showMessageDialog(this, "No appointments found.");

                        return;
                }

                String[] columns = {
                                "Appointment ID",
                                "Patient",
                                "Doctor",
                                "Specialization",
                                "Date",
                                "Time",
                                "Status"
                };

                Object[][] data = new Object[appointmentCount][7];

                int row = 0;

                for (Doctor doctor : Doctor.doctors) {

                        for (Appointment appointment : doctor.appointments) {

                                data[row][0] = appointment.id;

                                data[row][1] = appointment.patient.name;

                                data[row][2] = appointment.doctor.name;

                                data[row][3] = appointment.doctor.specialization;

                                data[row][4] = appointment.slot.date;

                                data[row][5] = appointment.slot.startTime
                                                + " - "
                                                + appointment.slot.endTime;

                                data[row][6] = appointment.status;

                                row++;
                        }
                }

                JTable table = new JTable(data, columns);

                table.setRowHeight(25);

                table.getTableHeader().setReorderingAllowed(false);

                JScrollPane scrollPane = new JScrollPane(table);

                scrollPane.setPreferredSize(new Dimension(850, 350));

                JOptionPane.showMessageDialog(
                                this,
                                scrollPane,
                                "Manage Appointments",
                                JOptionPane.INFORMATION_MESSAGE);
        }
}
