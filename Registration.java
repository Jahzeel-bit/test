import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration extends JFrame {
    public Registration() {
        // JFrame
        setTitle("Registration Form");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userNameLabel = new JLabel("USERNAME: ");
        panel.add(userNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        //Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("EMAIL: ");
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("PASSWORD: ");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        //Pin
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel pinLabel = new JLabel("PIN: ");
        panel.add(pinLabel, gbc);

        gbc.gridx = 1;
        JPasswordField pinField = new JPasswordField();
        panel.add(pinField, gbc);

        //Mobile Number
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel numberLabel = new JLabel("MOBILE NUMBER: ");
        panel.add(numberLabel, gbc);

        gbc.gridx = 1;
        JTextField numberField = new JTextField();
        panel.add(numberField, gbc);

        //Register button
        gbc.gridx = 1;
        gbc.gridy = 6;
        JButton signupButton = new JButton("Register");
        panel.add(signupButton, gbc);

        // Add panel to frame
        add(panel);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username =usernameField.getText();
                String email =emailField.getText();
                String pin = new String(pinField.getPassword());
                String pass = new String(passwordField.getPassword());
                String number = numberField.getText();
                int pinInt = Integer.parseInt(pin);

                if(username.isEmpty()||email.isEmpty() || pin.isEmpty() ||pass.isEmpty()||number.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all field!");
                }else {
                    try {
                        verifyRegistration(username,email,pinInt,pass,number);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registration().setVisible(true));
    }
    public void verifyRegistration(String username, String email, int pin, String pass,String number) throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb", "root", "")) {

            String sql = "INSERT INTO users (username, email, pin, password, number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setInt(3, pin);          // pin stored as INT
            stmt.setString(4, pass);
            stmt.setString(5, number);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                dispose();
                JOptionPane.showMessageDialog(this, "Please Login your new account!");
                new LoginInterface().setVisible(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}

