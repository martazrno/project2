package inventory;
import model.Medicine;

public interface InventoryManager {

    void addMedicine(Medicine medicine);
    void removeMedicine (String name);
    void viewInventory();
    void reorder(String name, int quantity);
}
