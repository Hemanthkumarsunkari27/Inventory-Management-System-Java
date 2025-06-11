import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root"; // change to your DB user
    private static final String PASSWORD = "your_password"; // change to your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addItem(String name, int quantity, double price) throws SQLException {
        String sql = "INSERT INTO items (name, quantity, price) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
        }
    }

    public static void viewItems() throws SQLException {
        String sql = "SELECT * FROM items";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Qty: %d | Price: %.2f%n",
                    rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"));
            }
        }
    }

    public static void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void updateItem(int id, int quantity, double price) throws SQLException {
        String sql = "UPDATE items SET quantity = ?, price = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setDouble(2, price);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }
}