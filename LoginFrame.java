package gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

        public LoginFrame() {

                setTitle("DocNest - Connect with the Right Doctor");

                setSize(500, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(5, 1, 10, 10));

                JLabel title = new JLabel("DocNest ", SwingConstants.CENTER);

                JLabel select = new JLabel("SELECT USER TYPE", SwingConstants.CENTER);

                JButton patientButton = new JButton("Patient");

                JButton doctorButton = new JButton("Doctor");

                JButton adminButton = new JButton("Administrator");

                panel.add(title);
                panel.add(select);
                panel.add(patientButton);
                panel.add(doctorButton);
                panel.add(adminButton);

                add(panel);

                title.setFont(new Font("Arial", Font.BOLD, 30));

                patientButton.addActionListener(e -> {

                        new PatientFrame();

                        dispose();
                });

                doctorButton.addActionListener(e -> {

                        new DoctorFrame();

                        dispose();
                });

                adminButton.addActionListener(e -> {

                        new AdminFrame();

                        dispose();
                });

                setVisible(true);
        }
}
