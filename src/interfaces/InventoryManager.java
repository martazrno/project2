package interfaces;
import model.Medicine;
import java.util.List;

public interface InventoryManager {

    void addMedicine(Medicine medicine);
    void removeMedicine (String name);
    List<Medicine> getAllMedicines();
    int getDesiredStock();
    void viewInventory();
}
