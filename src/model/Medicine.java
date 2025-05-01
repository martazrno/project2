package model;

import database.DatabaseConnector;
import inventory.Inventory;
import medicine_state.EmptyStock;
import medicine_state.FullStock;
import medicine_state.LowStock;
import medicine_state.MediumStock;
import medicine_state.MedicineStockState;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Medicine {
    private final String name;
    private final LocalDate expirationDate;
    private final String id;
    private final boolean isPrescription;
    private int quantity;
    private MedicineStockState stockState;

    public Medicine(String name, LocalDate expirationDate, String id, boolean isPrescription, int quantity) {
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past.");
        } else if (name != null && !name.isBlank()) {
            if (id != null && !id.isBlank()) {
                if (quantity < 0) {
                    throw new IllegalArgumentException("Medicine quantity cannot be negative.");
                } else {
                    this.expirationDate = expirationDate;
                    this.name = name;
                    this.id = id;
                    this.isPrescription = isPrescription;
                    this.quantity = quantity;
                    this.updateState();
                }
            } else {
                throw new IllegalArgumentException("Medicine id cannot be null.");
            }
        } else {
            throw new IllegalArgumentException("Medicine name cannot be null.");
        }
    }

    public boolean isExpired() {
        return this.expirationDate.isBefore(LocalDate.now());
    }

    public boolean isPrescription() {
        return this.isPrescription;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public String getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        } else {
            this.quantity = quantity;
            this.updateState();
        }
    }

    private void updateState() {
        if (this.quantity == 0) {
            this.stockState = new EmptyStock();
        } else if (this.quantity < 50) {
            this.stockState = new LowStock();
        } else if (this.quantity < 100) {
            this.stockState = new MediumStock();
        } else {
            this.stockState = new FullStock();
        }
    }

    public String getStockStateName() {
        return this.stockState.getStateName();
    }

    public void checkAndReorder(Inventory inventory) {
        this.stockState.checkAndReorder(this, inventory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;
        return this.id.equals(medicine.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "Medicine name: " + this.name +
            "\nID: " + this.id +
            "\nExpiration date: " + this.expirationDate +
            "\nExpired: " + this.isExpired() +
            "\nPrescription: " + this.isPrescription +
            "\nQuantity: " + this.quantity;
    }

    public static List<Medicine> loadAllFromDatabase() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines";

        try (Connection conn = DatabaseConnector.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = String.valueOf(rs.getInt("medicine_id"));
                String name = rs.getString("name");
                LocalDate expiration = rs.getDate("expiration_date").toLocalDate();
                int quantity = rs.getInt("quantity");

                Medicine m = new Medicine(name, expiration, id, false, quantity);
                medicines.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines;
    }
}
