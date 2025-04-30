
package inventory;

import model.Medicine;
import java.time.LocalDate;

public class InventoryTest {

    public static void main(String[] args) {
        InventoryManager inventory = new Inventory();

        // Test 1: Add new medicine
        Medicine ibuprofen = new Medicine("Ibuprofen", LocalDate.of(2026, 5, 15),
                "M001", false, 10);
        inventory.addMedicine(ibuprofen);

        // Test 2: Try adding the same medicine again (should be blocked)
        inventory.addMedicine(ibuprofen);

        // Test 3: View inventory
        System.out.println("\n--- Inventory View ---");
        inventory.viewInventory();

        // Test 4: Reorder if quantity is below threshold
        System.out.println("\n--- Reorder Test ---");
        inventory.reorder(ibuprofen, 10);

        // Test 5: View after reorder
        System.out.println("\n--- Inventory After Reorder ---");
        inventory.viewInventory();

        // Test 6: Remove medicine
        inventory.removeMedicine(ibuprofen);

        // Test 7: Try removing again (should not exist)
        inventory.removeMedicine(ibuprofen);
    }
}
