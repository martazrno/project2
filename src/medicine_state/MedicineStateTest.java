package medicine_state;
import inventory.Inventory;
import model.Medicine;
import model.MedicineFactory;

import java.time.LocalDate;

public class MedicineStateTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Medicine med = MedicineFactory.createMedicine(
                MedicineType.NORMAL, "Aspirin", LocalDate.of(2026, 1, 1), "M999", 5
        );

        // Add to inventory and reorder based on LOW stock
        inventory.addMedicine(med);

        // Simulate observer reacting to low stock
        new LowStock().checkAndReorder(med, inventory);

        // Check state names
        System.out.println("State name (should be LOW): " + new LowStock().getStateName());
        System.out.println("State name (should be FULL): " + new FullStock().getStateName());
    }
}