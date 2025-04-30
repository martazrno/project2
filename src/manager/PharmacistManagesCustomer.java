package manager;
import people.Customer;

public interface PharmacistManagesCustomer {

    void addCustomer (Customer customer);
    void removeCustomer (Customer customer);
    void viewCustomers();
}
