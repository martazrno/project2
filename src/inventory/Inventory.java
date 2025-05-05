package inventory;
import database.DBconnect;
import model.Medicine;
import java.sql.*;

public class Inventory implements InventoryManager {

    // attributes
    private final int THRESHOLD = 20;
    private final int DESIRED_STOCK = 100;

    // constructor
    public Inventory(){}

    //getters
    public int getDesiredStock() {return DESIRED_STOCK;}

    // methods
    public void addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicines (name, expiration_date, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medicine.getName());
            statement.setDate(2, Date.valueOf(medicine.getExpirationDate()));
            statement.setInt(3, medicine.getQuantity());

            int rows = statement.executeUpdate();
            if (rows > 0) {System.out.println("Medicine " + medicine.getName() + " added to database.");}
            else {System.out.println("⚠Medicine not added.");}}

        catch (SQLException e) {System.out.println("Error adding medicine: " + e.getMessage());}}

    @Override
    public void removeMedicine(String name) {
        String sql = "DELETE FROM medicines WHERE name = ?";
        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            int rows = stmt.executeUpdate();
            if (rows > 0) {System.out.println("Removed medicine: " + name);}
            else {System.out.println("⚠️ No medicine found with name: " + name);}}
        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    public void viewInventory() {
        String sql = "SELECT * FROM medicines";
        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);

             ResultSet resultSet = statement.executeQuery()) {
            boolean empty = true;
            System.out.println("Inventory:");

            while (resultSet.next()) {
                empty = false;
                int id = resultSet.getInt("medicine_id");
                String name = resultSet.getString("name");
                Date expiration = resultSet.getDate("expiration_date");
                int quantity = resultSet.getInt("quantity");
                System.out.println("ID: " + id + ", Name: " + name + ", Expiry: " + expiration + ", Quantity: " + quantity);}
            if (empty) System.out.println("Inventory is empty.");}

        catch (SQLException e) {System.out.println("Error retrieving inventory: " + e.getMessage());}}

    public void reorder(String name, int quantity) {
        String checkSql = "SELECT quantity FROM medicines WHERE name = ?";
        String updateSql = "UPDATE medicines SET quantity = ? WHERE name = ?";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkSql);

             PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            checkStmt.setString(1, name);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity");

                if (currentQty < THRESHOLD) {
                    int newQty = currentQty + quantity;
                    updateStmt.setInt(1, newQty);
                    updateStmt.setString(2, name);
                    updateStmt.executeUpdate();

                    System.out.println("Reordered " + quantity + " units of " + name+
                            ". New quantity: " + newQty);}
                else {System.out.println(name+ " is already above threshold.");}}
            else {System.out.println("Medicine " + name+ " not found.");}}
        catch (SQLException e) {System.out.println("Error reordering medicine: " + e.getMessage());}}


}