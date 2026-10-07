package gui;

import javax.swing.*;
import java.awt.*;

import model.Patient;

public class PatientLoginFrame extends JFrame {

        JTextField idField;
        JPasswordField passwordField;

        public PatientLoginFrame() {

                setTitle("Patient Login");

                setSize(450, 300);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                // Main panel
                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4, 2, 10, 10));

                // Title
                JLabel title = new JLabel(
                                "PATIENT LOGIN",
                                SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JLabel idLabel = new JLabel("Patient ID:");

                JLabel passwordLabel = new JLabel("Password:");

                // Input fields
                idField = new JTextField();

                passwordField = new JPasswordField();

                // Buttons
                JButton loginButton = new JButton("Login");

                JButton backButton = new JButton("Back");

                // Add components
                panel.add(new JLabel(""));
                panel.add(title);

                panel.add(idLabel);
                panel.add(idField);

                panel.add(passwordLabel);
                panel.add(passwordField);

                panel.add(loginButton);
                panel.add(backButton);

                add(panel);

                // Login button
                loginButton.addActionListener(e -> {

                        loginPatient();

                });

                // Back button
                backButton.addActionListener(e -> {

                        new PatientFrame();

                        dispose();

                });

                setVisible(true);
        }

        // Patient login method
        public void loginPatient() {

                String idText = idField.getText();

                String password = new String(passwordField.getPassword());

                // Check empty fields
                if (idText.isEmpty() || password.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Please enter Patient ID and Password.");

                        return;
                }

                // Convert ID from String to int
                int id;

                try {

                        id = Integer.parseInt(idText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Patient ID must be a number.");

                        return;
                }

                // Check login
                Patient patient = Patient.login(id, password);

                if (patient != null) {

                        JOptionPane.showMessageDialog(this, "Login Successful!\n\n" + "Welcome, " + patient.name);

                        new PatientDashboard(patient);

                        dispose();

                } else {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Invalid Patient ID or Password.");
                }
        }
}