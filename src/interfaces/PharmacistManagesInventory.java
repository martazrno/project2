package interfaces;
import model.Medicine;

public interface PharmacistManagesInventory {

    void addToInventory (Medicine medicine);
    void removeFromInventory (String name);
}
