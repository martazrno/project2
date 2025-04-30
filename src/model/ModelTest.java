package model;
import medicine_state.MedicineType;
import people.Customer;
import java.time.LocalDate;

public class ModelTest {
    public static void main(String[] args) {

        System.out.println("=== Testing Medicine and MedicineFactory ===");
        Medicine med1 = MedicineFactory.createMedicine(MedicineType.NORMAL, "Ibuprofen", LocalDate.of(2026, 5, 15), "M001", 100);
        System.out.println(med1);

        System.out.println("\n=== Testing Order and OrderItem ===");
        Order order = new Order("ORD001");
        order.addOrderItem(med1, 10);
        System.out.println(order);

        System.out.println("\n=== Testing OrderStatus Change ===");
        order.setOrderStatus(OrderStatus.CONFIRMED);
        System.out.println(order);
        order.setOrderStatus(OrderStatus.COMPLETED);
        System.out.println(order);

        System.out.println("\n=== Testing Prescription ===");
        Customer customer = new Customer("Alice", "C001");
        Prescription prescription = new Prescription("PRES001", customer);
        prescription.addMedicine(med1);
        prescription.viewPrescriptions();
        prescription.removeMedicine(med1);
    }
}