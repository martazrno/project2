package people;
import database.DBconnect;
import manager.InventoryManager;
import model.Medicine;
import manager.PharmacistManagesCustomer;
import manager.PharmacistManagesInventory;
import model.Order;
import observer.OrderObserver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pharmacist extends User implements PharmacistManagesInventory, PharmacistManagesCustomer, OrderObserver {

    // attributes
    protected InventoryManager inventory;

    // constructor
    public Pharmacist(String name, InventoryManager inventory){
        super(name);
        if (inventory == null) {throw new IllegalArgumentException("Inventory cannot be null.");}
        this.inventory = inventory;}

    // customer management
    public void addCustomer(Customer customer) {
        if (customer == null) {throw new IllegalArgumentException("Customer cannot be null.");}

        String checkSql = "SELECT 1 FROM customers WHERE name = ?";
        String insertSql = "INSERT INTO customers (customer_id, name) VALUES (?, ?)";

        try (Connection connection = DBconnect.getConnection()) {
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setString(1, customer.getName());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Customer "+ customer.getName()+ " already exists.");
                    return;}}

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setString(1, customer.getId());
                insertStmt.setString(2, customer.getName());
                int rows = insertStmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Customer " + customer.getName() + " added.");}}}
        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    public void removeCustomer(String name) {
        if (name == null || name.isBlank()) {throw new IllegalArgumentException("Customer name cannot be null or blank.");}

        String deleteSql = "DELETE FROM customers WHERE name = ?";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteSql)) {

            stmt.setString(1, name);
            int rows = stmt.executeUpdate();
            if (rows > 0) {System.out.println("Customer " + name + " removed.");}
            else {System.out.println("Customer " + name + " not found.");}}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    public void viewCustomers() {
        String sql = "SELECT customer_id, name FROM customers";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("📋 Customers:");
            while (rs.next()) {
                String id = rs.getString("customer_id");
                String name = rs.getString("name");
                System.out.println("- " + name + " (ID: " + id + ")");}}
        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    // inventory management
    public void addToInventory(Medicine medicine){
        if (medicine== null){throw new IllegalArgumentException("Medicine cannot be null.");}
        inventory.addMedicine(medicine);}

    public void removeFromInventory(String name){
        if (name== null){throw new IllegalArgumentException("Medicine cannot be null.");}
        inventory.removeMedicine(name);}

    /*public void viewInventory() {inventory.viewInventory();}*/

    //observer stuff
    @Override
    public void onOrderStatusUpdated(Order order) {System.out.println("Order " + order.getOrderID() +
            " is now " + order.getOrderStatus());}
}
