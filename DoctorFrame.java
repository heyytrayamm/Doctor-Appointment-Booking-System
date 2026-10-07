package gui;

import javax.swing.*;
import java.awt.*;

public class DoctorFrame extends JFrame {

        public DoctorFrame() {

                setTitle("Doctor");

                setSize(400, 300);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4, 1, 10, 10));

                JLabel title = new JLabel("DOCTOR", SwingConstants.CENTER);

                JButton register = new JButton("Doctor Registration");

                JButton login = new JButton("Doctor Login");

                JButton back = new JButton("Back");

                panel.add(title);
                panel.add(register);
                panel.add(login);
                panel.add(back);

                add(panel);

                register.addActionListener(e -> {

                        new DoctorRegistrationFrame();

                        dispose();

                });

                login.addActionListener(e -> {

                        new DoctorLoginFrame();

                        dispose();

                });

                back.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                setVisible(true);
        }
}