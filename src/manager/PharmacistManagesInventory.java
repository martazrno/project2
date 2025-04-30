package manager;
import model.Medicine;

public interface PharmacistManagesInventory {

    void addToInventory (Medicine medicine);
    void removeFromInventory (Medicine medicine);
    void viewInventory ();
}
