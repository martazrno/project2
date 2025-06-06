package model;
import interfaces.MedicineStockState;
import interfaces.OrderObserver;
import java.util.List;

public class LowStock implements MedicineStockState {

    private final List<OrderObserver> observers;

    public LowStock(List<OrderObserver> observers) {this.observers = observers;}

    @Override
    public void checkAndReorder(String name, Inventory inventory) {Reorder.checkAndReorder(observers);}

    @Override
    public String getStateName() {
        return "LOW";
    }
}