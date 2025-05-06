package manager;
import people.Customer;

public interface PharmacistManagesCustomer {

    void addCustomer (Customer customer);
    void removeCustomer (String name);
    void viewCustomers();
}
