package people;
import database.DBconnect;
import model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Doctor extends User {

    public Doctor(String name){super(name);}

    public void createPrescription(String customerName, String medicineName) {
        if (medicineName == null || medicineName.isBlank()) {
            throw new IllegalArgumentException("Medicine name cannot be null or blank.");}
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be null or blank.");}

        String prescriptionID = "RX" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        String getCustomerIdSql = "SELECT customer_id FROM customers WHERE name = ?";
        String getMedicineIdSql = "SELECT medicine_id FROM medicines WHERE name = ?";
        String checkPrescribableSql = "SELECT is_prescription FROM medicines WHERE medicine_id = ?";
        String insertPrescriptionSql = "INSERT INTO prescriptions (prescription_id, customer_id, medicine_id) VALUES (?, ?, ?)";

        try (Connection connection = DBconnect.getConnection()) {
            String customerId;
            String medicineId;
            try (PreparedStatement customerStmt = connection.prepareStatement(getCustomerIdSql)) {
                customerStmt.setString(1, customerName);
                ResultSet rs = customerStmt.executeQuery();
                if (rs.next()) {customerId = rs.getString("customer_id");}
                else {
                    System.out.println("Customer not found.");
                    return;}}
            try (PreparedStatement medicineStmt = connection.prepareStatement(getMedicineIdSql)) {
                medicineStmt.setString(1, medicineName);
                ResultSet rs = medicineStmt.executeQuery();
                if (rs.next()) {medicineId = rs.getString("medicine_id");}
                else {
                    System.out.println("Medicine not found.");
                    return;}}
            try (PreparedStatement checkStmt = connection.prepareStatement(checkPrescribableSql)) {
                checkStmt.setString(1, medicineId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    boolean isPrescribable = rs.getBoolean("is_prescription");
                    if (!isPrescribable) {
                        System.out.println( medicineName + " does not require a prescription.");
                        return;}}
                else {System.out.println("Medicine not found.");
                    return;}}

            try (PreparedStatement insertStmt = connection.prepareStatement(insertPrescriptionSql)) {
                insertStmt.setString(1, prescriptionID);
                insertStmt.setString(2, customerId);
                insertStmt.setString(3, medicineId);
                int rows = insertStmt.executeUpdate();
                if (rows > 0) {System.out.println("Prescription " + prescriptionID + " created for " + customerName);}
                else {System.out.println("Prescription not created.");}}}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    public List<Prescription> getAllPrescriptionsFromDB() {
        List<Prescription> prescriptions = new ArrayList<>();

        String sql = """
        SELECT p.prescription_id, c.name AS customer_name, m.name AS medicine_name
        FROM prescriptions p
        JOIN customers c ON p.customer_id = c.customer_id
        JOIN medicines m ON p.medicine_id = m.medicine_id
    """;

        try (Connection connection = DBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("prescription_id");
                String customer = rs.getString("customer_name");
                String medicine = rs.getString("medicine_name");

                // Assuming Prescription has a constructor like Prescription(String id, String customer, String medicine)
                prescriptions.add(new Prescription(id, customer, medicine));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return prescriptions;
    }

    @Override
    public String toString() {return super.toString();}

}
