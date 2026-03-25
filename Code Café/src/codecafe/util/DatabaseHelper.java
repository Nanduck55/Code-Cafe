package codecafe.util; // Make sure this matches where you put the file!

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    // IMPORTANT: Make sure this points to the exact location of your cafe.db file
    // If cafe.db is in the main project folder, this works perfectly.
    private static final String URL = "jdbc:sqlite:C:/Users/raina/Downloads/CodeCafeAdmin/codecafe.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void saveOrder(int orderId, String items, String type) {
        String sqlOrder = "INSERT INTO orders (order_id, order_type, order_time, status) VALUES (?, ?, ?, ?)";
        String sqlItems = "INSERT INTO order_items (order_id, quantity, item_name) VALUES (?, ?, ?)";

        String currentTime = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

        try (Connection conn = getConnection()) {

            try (PreparedStatement pstmt = conn.prepareStatement(sqlOrder)) {
                pstmt.setInt(1, orderId);
                pstmt.setString(2, type);
                pstmt.setString(3, currentTime);
                pstmt.setString(4, "Active");
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmtItems = conn.prepareStatement(sqlItems)) {
                pstmtItems.setInt(1, orderId);
                pstmtItems.setInt(2, 1);
                pstmtItems.setString(3, items);
                pstmtItems.executeUpdate();
            }

            System.out.println("SUCCESS: Order ID " + orderId + " fully sent to Admin!");
        } catch (SQLException e) {
            System.out.println("ERROR: Database rejected the order!");
            e.printStackTrace();
        }
    }
}

