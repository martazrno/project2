package people;

import inventory.Inventory;
import inventory.InventoryManager;
import people.Customer;

public class PeopleTest {
    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist("Ella Pharmacist", "PH123", new Inventory() );

        // Add a customer
        Customer c1 = new Customer("Samuel Green", "C043");
        pharmacist.addCustomer(c1);

        // Try adding the same customer again (should warn)
        pharmacist.addCustomer(c1);

        // View customers
        System.out.println("\n--- Customers ---");
        pharmacist.viewCustomers();



        // Final check
        System.out.println("\n--- Final Customer List ---");
        pharmacist.viewCustomers();
    }
}