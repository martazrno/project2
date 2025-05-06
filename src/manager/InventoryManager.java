package manager;
import model.Medicine;
import java.util.List;

public interface InventoryManager {

    void addMedicine(Medicine medicine);
    void removeMedicine (String name);
    void viewInventory();
    List<Medicine> getAllMedicines();
    int getDesiredStock();
}
