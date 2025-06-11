import java.sql.SQLException;
import java.util.Scanner;

public class InventoryManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            try {
                switch (choice) {
                    case 1 -> addItem();
                    case 2 -> DatabaseHandler.viewItems();
                    case 3 -> updateItem();
                    case 4 -> deleteItem();
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void addItem() throws SQLException {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        DatabaseHandler.addItem(name, qty, price);
        System.out.println("Item added successfully.");
    }

    private static void updateItem() throws SQLException {
        System.out.print("Enter item ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int qty = scanner.nextInt();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        DatabaseHandler.updateItem(id, qty, price);
        System.out.println("Item updated.");
    }

    private static void deleteItem() throws SQLException {
        System.out.print("Enter item ID to delete: ");
        int id = scanner.nextInt();
        DatabaseHandler.deleteItem(id);
        System.out.println("Item deleted.");
    }
}