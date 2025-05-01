package people;

import database.DatabaseConnector;
import model.Prescription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    // attributes
    private final List<Prescription> prescriptions;

    // constructor
    public Customer(String name, String id) {
        super(name, id);
        this.prescriptions = new ArrayList<>();
    }

    // getters
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    // methods
    public void viewMyPrescriptions() {
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions found.");
            return;
        }
        System.out.println("Your prescriptions: ");
        for (Prescription prescription : prescriptions) {
            System.out.println(prescription);
        }
    }

    @Override
    public String toString() {
        return "Customer name: " + getName() + "\nID: " + getId();
    }

    public static List<Customer> loadAllFromDatabase() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, name FROM customers";

        try (Connection conn = DatabaseConnector.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String id = String.valueOf(rs.getInt("customer_id"));
                customers.add(new Customer(name, id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }
}
