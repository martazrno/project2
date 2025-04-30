package people;

import inventory.Inventory;
import model.Medicine;
import model.Prescription;

import java.time.LocalDate;
import java.util.ArrayList;

public class PeopleTest {
    public static void main(String[] args) {

        System.out.println("=== Testing Customer ===");
        Customer customer = new Customer("Marta Zrno", "C001");
        System.out.println(customer);

        System.out.println("\n=== Testing Doctor ===");
        Doctor doctor = new Doctor("Dr. Strange", "D001");
        Prescription prescription = doctor.createPrescription("P001", customer);
        doctor.viewPrescriptions(customer);

        System.out.println("\n=== Testing Pharmacist ===");
        Inventory inventory = new Inventory();
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer);

        Pharmacist pharmacist = new Pharmacist("John Smith", "P001", customers, inventory) {}; // anonymous subclass
        pharmacist.viewCustomers();

        Medicine med = new Medicine(
                "Ibuprofen", LocalDate.of(2026, 5, 15),
                "M001", false, 100
        );
        pharmacist.addToInventory(med);
        pharmacist.viewInventory();
        pharmacist.removeFromInventory(med);
        pharmacist.viewInventory();
    }
}