package medicine_state;
import inventory.Inventory;
import model.Medicine;

public class MediumStock implements MedicineStockState {

    @Override
    public void checkAndReorder(Medicine medicine, Inventory inventory) {
        System.out.println(medicine.getName() + " is not fully stocked. However, no reorder needed.");
    }

    @Override
    public String getStateName() {
        return "MEDIUM";
    }
}
