package gui;

import javax.swing.*;
import java.awt.*;

import model.Doctor;

public class DoctorRegistrationFrame extends JFrame {

        JTextField nameField;
        JTextField emailField;
        JPasswordField passwordField;
        JTextField phoneField;
        JTextField specializationField;

        public DoctorRegistrationFrame() {

                setTitle("Doctor Registration");

                setSize(500, 450);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(7, 2, 10, 10));

                JLabel title = new JLabel("DOCTOR REGISTRATION", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JLabel nameLabel = new JLabel("Name:");

                JLabel emailLabel = new JLabel("Email:");

                JLabel passwordLabel = new JLabel("Password:");

                JLabel phoneLabel = new JLabel("Phone:");

                JLabel specializationLabel = new JLabel("Specialization:");

                nameField = new JTextField();

                emailField = new JTextField();

                passwordField = new JPasswordField();

                phoneField = new JTextField();

                specializationField = new JTextField();

                JButton registerButton = new JButton("Register");

                JButton backButton = new JButton("Back");

                panel.add(new JLabel(""));
                panel.add(title);

                panel.add(nameLabel);
                panel.add(nameField);

                panel.add(emailLabel);
                panel.add(emailField);

                panel.add(passwordLabel);
                panel.add(passwordField);

                panel.add(phoneLabel);
                panel.add(phoneField);

                panel.add(specializationLabel);
                panel.add(specializationField);

                panel.add(registerButton);
                panel.add(backButton);

                add(panel);

                registerButton.addActionListener(e -> {

                        registerDoctor();

                });

                backButton.addActionListener(e -> {

                        new DoctorFrame();

                        dispose();

                });

                setVisible(true);
        }

        public void registerDoctor() {

                String name = nameField.getText();

                String email = emailField.getText();

                String password = new String(passwordField.getPassword());

                String phone = phoneField.getText();

                String specialization = specializationField.getText();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || specialization.isEmpty()) {

                        JOptionPane.showMessageDialog(this, "Please fill all fields.");

                        return;
                }

                Doctor doctor = new Doctor(
                                Doctor.nextDoctorId,
                                name,
                                email,
                                password,
                                phone,
                                specialization);

                Doctor.addDoctor(doctor);

                int registeredId = Doctor.nextDoctorId;

                Doctor.nextDoctorId++;

                JOptionPane.showMessageDialog(this, "Registration Successful!\n\n" + "Your Doctor ID is: "
                                + registeredId + "\n\n" + "Please remember your ID and password.");

                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                phoneField.setText("");
                specializationField.setText("");
        }
}
