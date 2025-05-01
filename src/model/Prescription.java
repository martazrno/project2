package model;

import database.DatabaseConnector;
import observer.PrescriptionObserver;
import people.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Prescription {

    // attributes
    private final String id;
    private ArrayList<Medicine> medicines;
    private Customer customer;
    private final List<PrescriptionObserver> observers = new ArrayList<>();

    // constructor
    public Prescription(String id, Customer customer){
        if (id == null || id.isBlank()){throw new IllegalArgumentException("ID cannot be null or blank.");}
        if (customer == null){throw new IllegalArgumentException("people.Customer cannot be null.");}
        this.id= id;
        this.customer= customer;
        this.medicines = new ArrayList<>();
    }

    // getters
    public String getId() {return id;}
    public Customer getCustomer() {return customer;}
    public ArrayList<Medicine> getMedicines() {return medicines;}

    //methods for observers
    public void addObserver(PrescriptionObserver observer){observers.add(observer);}
    public void removeObserver(PrescriptionObserver observer){observers.remove(observer);}
    public void notifyObservers(){
        for (PrescriptionObserver observer: observers){
            observer.onPrescriptionUpdated(this);}}

    // methods
    public void addMedicine(Medicine medicine) {
        if (medicine == null) {
            System.out.println("Medicine cannot be null.");
            return;}
        if (medicines.contains(medicine)) {
            System.out.println("Medicine " + medicine.getId() + " is already in the prescription.");}
        else {
            medicines.add(medicine);
            System.out.println("Medicine " + medicine.getId() + " is now added to a prescription.");
            notifyObservers();
        }}

    public void removeMedicine(Medicine medicine) {
        if (medicine == null) {
            System.out.println("Medicine cannot be null.");
            return;}
        if (medicines.contains(medicine)) {
            medicines.remove(medicine);
            System.out.println("Medicine " + medicine.getId() + " is now removed from prescription.");
            notifyObservers();}
        else {System.out.println("Medicine " + medicine.getId() + " is not in a prescription.");}}

    public void viewPrescriptions() {
        if (medicines.isEmpty()){System.out.println("There are no medicines in the prescription.");}
        else {
            System.out.println("people.Customer: " + customer.getName());
            System.out.println("Prescription ID: " + id);
            for (Medicine medicine: medicines){System.out.println(medicine);}
        }}

    @Override
    public String toString() {
        return "Prescription ID: " + id + "\nMedicines: " + medicines +
            "\npeople.Customer: " + customer.getName();
    }

    public static List<Prescription> loadAllFromDatabase() {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT p.prescription_id, c.customer_id, c.name " +
            "FROM prescriptions p JOIN customers c ON p.customer_id = c.customer_id";

        try (Connection conn = DatabaseConnector.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String prescriptionId = String.valueOf(rs.getInt("prescription_id"));
                String customerId = String.valueOf(rs.getInt("customer_id"));
                String customerName = rs.getString("name");

                Customer customer = new Customer(customerName, customerId);
                Prescription prescription = new Prescription(prescriptionId, customer);
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }
}
