package gui;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

        public AdminFrame() {

                setTitle("Administrator");

                setSize(400, 300);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(3, 1, 10, 10));

                JLabel title = new JLabel("ADMINISTRATOR", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JButton login = new JButton("Administrator Login");

                JButton back = new JButton("Back");

                panel.add(title);
                panel.add(login);
                panel.add(back);

                add(panel);

                // Administrator Login
                login.addActionListener(e -> {

                        new AdminLoginFrame();

                        dispose();

                });

                // Back
                back.addActionListener(e -> {

                        new LoginFrame();

                        dispose();

                });

                setVisible(true);
        }
}