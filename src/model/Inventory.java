package model;
import database.DBconnect;
import interfaces.InventoryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements InventoryManager {

    // constructor
    public Inventory(){}

    //getters
    @Override
    public int getDesiredStock() {return 100;}

    // methods
    public void addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicines (medicine_id, name, quantity, is_prescription) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medicine.getId());
            statement.setString(2, medicine.getName());
            statement.setInt(3, medicine.getQuantity());
            statement.setBoolean(4, medicine.isPrescription());
            int rows = statement.executeUpdate();
            if (rows > 0) {System.out.println("Medicine " + medicine.getName() + " added to database.");}
            else {System.out.println("Medicine not found.");}}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    @Override
    public void removeMedicine(String name) {
        String sql = "DELETE FROM medicines WHERE name = ?";
        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            int rows = stmt.executeUpdate();
            if (rows > 0) {System.out.println("Removed medicine: " + name);}
            else {System.out.println("Medicine not found.");}}

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
                String id = resultSet.getString("medicine_id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                System.out.println("ID: " + id + ", Name: " + name + ", Quantity: " + quantity);}
            if (empty) System.out.println("Inventory is empty.");}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT medicine_id, name, quantity, is_prescription FROM medicines";
        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("medicine_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                boolean isPrescription = rs.getBoolean("is_prescription");

                Medicine medicine = new Medicine(name, isPrescription, quantity);
                medicines.add(medicine);}}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}
        return medicines;}

}