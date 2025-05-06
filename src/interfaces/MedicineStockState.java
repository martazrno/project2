package interfaces;

import model.Inventory;

public interface MedicineStockState {

    void checkAndReorder(String name, Inventory inventory);
    String getStateName();
}
