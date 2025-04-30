package medicine_state;
import inventory.Inventory;
import model.Medicine;

public class LowStock implements MedicineStockState {
    @Override
    public void checkAndReorder(Medicine medicine, Inventory inventory) {
        int neededQty = inventory.getDesiredStock() - medicine.getQuantity();
        inventory.reorder(medicine, neededQty);
        System.out.println("Low stock detected for " + medicine.getName() +
                ". Reordering " + neededQty + " units.");
    }

    @Override
    public String getStateName() {
        return "LOW";
    }
}