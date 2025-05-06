package medicine_state;
import inventory.Inventory;
import model.Medicine;

public interface MedicineStockState {

    void checkAndReorder(String name, Inventory inventory);
    String getStateName();
}
