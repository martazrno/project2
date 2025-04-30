package inventory;
import model.Medicine;

import java.util.ArrayList;

public interface InventoryManager {

    void addMedicine(Medicine medicine);
    void removeMedicine (Medicine medicine);
    void viewInventory();
    void reorder(Medicine medicine, int quantity);
    ArrayList<Medicine> getMedicines();
}
