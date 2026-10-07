package gui;

import javax.swing.*;
import java.awt.*;

import model.Administrator;

public class AdminLoginFrame extends JFrame {

        JTextField idField;
        JPasswordField passwordField;

        public AdminLoginFrame() {

                setTitle("Administrator Login");

                setSize(450, 300);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setLocationRelativeTo(null);

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4, 2, 10, 10));

                JLabel title = new JLabel("ADMINISTRATOR LOGIN", SwingConstants.CENTER);

                title.setFont(new Font("Arial", Font.BOLD, 20));

                JLabel idLabel = new JLabel("Admin ID:");

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

                // Login
                loginButton.addActionListener(e -> {

                        loginAdmin();

                });

                // Back
                backButton.addActionListener(e -> {

                        new AdminFrame();

                        dispose();

                });

                setVisible(true);
        }

        // Administrator login
        public void loginAdmin() {

                String idText = idField.getText();

                String password = new String(passwordField.getPassword());

                if (idText.isEmpty() || password.isEmpty()) {

                        JOptionPane.showMessageDialog(this, "Please enter Admin ID and Password.");

                        return;
                }

                int id;

                try {

                        id = Integer.parseInt(idText);

                } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(this, "Admin ID must be a number.");

                        return;
                }

                // Check administrator login
                boolean login = Administrator.login(id, password);

                if (login) {

                        JOptionPane.showMessageDialog(this, "Login Successful!\n\n" + "Welcome Administrator");

                        new AdminDashboard();

                        dispose();

                } else {

                        JOptionPane.showMessageDialog(this, "Invalid Admin ID or Password.");
                }
        }
}