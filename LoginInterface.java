import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("ALL")
 public class LoginInterface extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
     public LoginInterface() {
                //JFrame
                setTitle("Banking App Login");
                setSize(400, 200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                // Use GridBagLayout
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Username Label
                gbc.gridx = 0; // column 0
                gbc.gridy = 1; // row 0
                panel.add(new JLabel("Username:"), gbc);

                // Username Field
                gbc.gridx = 1; // column 1
                gbc.gridy = 1;
                usernameField = new JTextField(20);
                panel.add(usernameField, gbc);

                // Password Label
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(new JLabel("Password:"), gbc);

                // Password Field
                gbc.gridx = 1;
                gbc.gridy = 2;
                passwordField = new JPasswordField(20);
                panel.add(passwordField, gbc);

                // Login Button
                gbc.gridx = 1;
                gbc.gridy = 3;
                JButton loginButton = new JButton("Login");
                panel.add(loginButton, gbc);

                //Register Button
                gbc.gridx = 0;
                gbc.gridy = 0;
                JButton Register = new JButton("Signup ");
                panel.add(Register, gbc);

                // Add panel to frame
                add(panel);

                // Button action
                loginButton.addActionListener(e -> verifyLogin());
                Register.addActionListener(e -> {
                    dispose();
                    new Registration().setVisible(true);
                });
            }
            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new LoginInterface().setVisible(true));
            }

            private void verifyLogin() {
                String inputUsername = usernameField.getText();
                String inputPass = new String(passwordField.getPassword());
                try (Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bankdb", "root", "")) {
                    String sql = "SELECT * FROM users WHERE username=? AND password=?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, inputUsername);
                    stmt.setString(2, inputPass);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid credentials.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
                }
            }
}