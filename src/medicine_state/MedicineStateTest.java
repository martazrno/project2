package medicine_state;
import inventory.Inventory;
import model.Medicine;
import model.MedicineFactory;

import java.time.LocalDate;

public class MedicineStateTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Medicine med = MedicineFactory.createMedicine(
                MedicineType.NORMAL, "Aspirin", LocalDate.of(2026, 1, 1), "999", 5);

        inventory.addMedicine(med);
        new LowStock().checkAndReorder("Aspirin", inventory);

        System.out.println("State name: " + new LowStock().getStateName());
        System.out.println("State name: " + new FullStock().getStateName());
    }
}