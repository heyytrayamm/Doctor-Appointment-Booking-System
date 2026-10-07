package gui;

import javax.swing.*;
import java.awt.*;

public class PatientFrame extends JFrame {

        JButton registrationButton;
        JButton loginButton;
        JButton backButton;

        public PatientFrame() {

                setTitle("Patient");

                setSize(500, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                // MAIN PANEL

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4, 1, 10, 10));

                // TITLE

                JLabel title = new JLabel(
                                "PATIENT",
                                SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 24));

                // BUTTONS

                registrationButton = new JButton(
                                "Patient Registration");

                loginButton = new JButton(
                                "Patient Login");

                backButton = new JButton(
                                "Back");

                // ADD COMPONENTS

                panel.add(title);

                panel.add(registrationButton);

                panel.add(loginButton);

                panel.add(backButton);

                add(panel);

                // PATIENT REGISTRATION

                registrationButton.addActionListener(e -> {

                        new PatientRegistrationFrame();

                        dispose();

                });

                // PATIENT LOGIN

                loginButton.addActionListener(e -> {

                        new PatientLoginFrame();

                        dispose();

                });

                // BACK

                backButton.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                // Show window
                setVisible(true);
        }
}