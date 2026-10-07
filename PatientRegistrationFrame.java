package gui;

import javax.swing.*;
import java.awt.*;

import model.Patient;

public class PatientRegistrationFrame extends JFrame {

        JTextField nameField;
        JTextField emailField;
        JPasswordField passwordField;
        JTextField phoneField;

        public PatientRegistrationFrame() {

                setTitle("Patient Registration");

                setSize(500, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                // Main panel
                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(6, 2, 10, 10));

                // TITLE

                JLabel title = new JLabel("PATIENT REGISTRATION", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JLabel nameLabel = new JLabel("Name:");

                JLabel emailLabel = new JLabel("Email:");

                JLabel passwordLabel = new JLabel("Password:");

                JLabel phoneLabel = new JLabel("Phone:");

                // INPUT FIELDS

                nameField = new JTextField();

                emailField = new JTextField();

                passwordField = new JPasswordField();

                phoneField = new JTextField();

                // BUTTONS

                JButton registerButton = new JButton("Register");

                JButton backButton = new JButton("Back");

                // ADD COMPONENTS

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

                panel.add(registerButton);
                panel.add(backButton);

                add(panel);

                // REGISTER BUTTON

                registerButton.addActionListener(e -> {

                        registerPatient();

                });

                // BACK BUTTON

                backButton.addActionListener(e -> {

                        new PatientFrame();

                        dispose();

                });

                setVisible(true);
        }

        
        // REGISTER PATIENT

        public void registerPatient() {

                // Get values
                String name = nameField.getText().trim();

                String email = emailField.getText().trim();

                String password = new String(passwordField.getPassword());

                String phone = phoneField.getText().trim();

                // CHECK EMPTY FIELDS

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {

                        JOptionPane.showMessageDialog(this, "Please fill all fields.", "Registration Error", JOptionPane.ERROR_MESSAGE);

                        return;
                }

                // CHECK DUPLICATE EMAIL

                for (Patient patient : Patient.patients) {

                        if (patient.email.equalsIgnoreCase(email)) {

                                JOptionPane.showMessageDialog(this, "Email is already registered.", "Registration Error", JOptionPane.ERROR_MESSAGE);

                                return;
                        }
                }

                // CREATE PATIENT

                int patientId = Patient.nextPatientId;

                Patient patient = new Patient(patientId, name, email, password, phone);

                // ADD PATIENT

                Patient.addPatient(patient);

                // Increase ID for next patient
                Patient.nextPatientId++;

                // SUCCESS MESSAGE

                JOptionPane.showMessageDialog(this, "Registration Successful!\n\n" + "Patient ID: " + patientId + "\n" + "Name: " + name + "\n\n" + "Please remember your Patient ID " + "and password.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

                // CLEAR FIELDS

                nameField.setText("");

                emailField.setText("");

                passwordField.setText("");

                phoneField.setText("");
        }
}
