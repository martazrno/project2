package controller;
import model.*;
import people.Customer;

import java.time.LocalDate;

public class PharmacyTest {
    public static void main(String[] args) {

        System.out.println("=== Testing Pharmacy ===");

        // Setup
        Pharmacy pharmacy = new Pharmacy(new inventory.Inventory());
        Medicine paracetamol = new Medicine("Paracetamol", LocalDate.of(2026, 5, 15), "M001", false, 10);
        pharmacy.getInventory().addMedicine(paracetamol);

        // Order test
        Order order = new Order("ORD001");
        order.addOrderItem(paracetamol, 5);
        order.addObserver(pharmacy); // Pharmacy is the OrderObserver
        pharmacy.addOrder(order);
        order.setOrderStatus(OrderStatus.CONFIRMED);

        // Prescription test
        Customer customer = new people.Customer("Alice", "C001");
        Prescription prescription = new Prescription("PRES001", customer);
        prescription.addObserver(pharmacy); // Pharmacy is the PrescriptionObserver
        pharmacy.addPrescription(prescription);
        prescription.addMedicine(paracetamol);
        prescription.removeMedicine(paracetamol);
    }
}