package model;

import interfaces.MedicineStockState;

public class FullStock implements MedicineStockState {
    @Override
    public void checkAndReorder(String name, Inventory inventory) {
        System.out.println(name+ " is fully stocked. No reorder needed.");}

    @Override
    public String getStateName() {
        return "FULL";
    }
}