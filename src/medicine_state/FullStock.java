package medicine_state;
import inventory.Inventory;
import model.Medicine;

public class FullStock implements MedicineStockState {
    @Override
    public void checkAndReorder(Medicine medicine, Inventory inventory) {
        System.out.println(medicine.getName() + " is fully stocked. No reorder needed.");
    }

    @Override
    public String getStateName() {
        return "FULL";
    }
}