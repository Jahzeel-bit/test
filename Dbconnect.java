import java.sql.*;
public class Dbconnect {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // JDBC URL
            String url = "jdbc:mysql://localhost:3306/bankdb";
            String user = "root"; // default XAMPP user
            String password = ""; // default XAMPP password is empty

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
