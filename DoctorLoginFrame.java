package gui;

import javax.swing.*;
import java.awt.*;

import model.Doctor;

public class DoctorLoginFrame extends JFrame {

        JTextField idField;
        JPasswordField passwordField;

        public DoctorLoginFrame() {

                setTitle("Doctor Login");

                setSize(450, 300);

                setDefaultCloseOperation(
                                JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4, 2, 10, 10));

                JLabel title = new JLabel("DOCTOR LOGIN", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JLabel idLabel = new JLabel("Doctor ID:");

                JLabel passwordLabel = new JLabel("Password:");

                idField = new JTextField();

                passwordField = new JPasswordField();

                JButton loginButton = new JButton("Login");

                JButton backButton = new JButton("Back");

                panel.add(new JLabel(""));
                panel.add(title);

                panel.add(idLabel);
                panel.add(idField);

                panel.add(passwordLabel);
                panel.add(passwordField);

                panel.add(loginButton);
                panel.add(backButton);

                add(panel);

                loginButton.addActionListener(e -> {

                        loginDoctor();

                });

                backButton.addActionListener(e -> {

                        new DoctorFrame();

                        dispose();

                });

                setVisible(true);
        }

        public void loginDoctor() {

                String idText = idField.getText();

                String password = new String(passwordField.getPassword());

                if (idText.isEmpty() || password.isEmpty()) {

                        JOptionPane.showMessageDialog(this, "Please enter Doctor ID and Password.");

                        return;
                }

                int id;

                try {

                        id = Integer.parseInt(idText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(this, "Doctor ID must be a number.");

                        return;
                }

                Doctor doctor = Doctor.login(id, password);

                if (doctor != null) {

                        JOptionPane.showMessageDialog(this, "Login Successful!\n\n" + "Welcome " + doctor.name);

                        new DoctorDashboard(doctor);

                        dispose();

                } else {

                        JOptionPane.showMessageDialog(this, "Invalid Doctor ID or Password.");
                }
        }
}