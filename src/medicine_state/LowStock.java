package medicine_state;
import inventory.Inventory;
import database.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LowStock implements MedicineStockState {

    @Override
    public void checkAndReorder(String name, Inventory inventory) {
        String sql = "SELECT quantity FROM medicines WHERE name = ?";

        try (Connection conn = DBconnect.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int currentQty = resultSet.getInt("quantity");
                int neededQty = inventory.getDesiredStock() - currentQty;

                if (neededQty > 0) {
                    inventory.reorder(name, neededQty);
                    System.out.println("Low stock detected for " + name + ". Reordering " + neededQty + " units.");}
                else {System.out.println("Stock is actually okay for " + name + ".");}}
            else {System.out.println("⚠️ Medicine " + name + " not found.");}}
        catch (SQLException e) {System.out.println("Error checking quantity: " + e.getMessage());}}

    @Override
    public String getStateName() {
        return "LOW";
    }
}