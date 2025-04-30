package medicine_state;
import inventory.Inventory;
import model.Medicine;

public interface MedicineStockState {
    void checkAndReorder(Medicine medicine, Inventory inventory);
    String getStateName();
}
