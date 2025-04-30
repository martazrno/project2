package controller;
import inventory.InventoryManager;
import manager.OrderManager;
import manager.PrescriptionManager;
import medicine_state.MedicineStockState;
import model.Medicine;
import observer.MedicineStockObserver;
import model.Order;
import model.Prescription;
import observer.OrderObserver;
import observer.PrescriptionObserver;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy implements OrderManager, PrescriptionManager, MedicineStockObserver, PrescriptionObserver, OrderObserver {

    // attributes
    private final InventoryManager inventory;
    private final ArrayList<Order> orders;
    private final List<Prescription> prescriptions;

    // constructor
    public Pharmacy (InventoryManager inventory){
        if (inventory == null){throw new IllegalArgumentException("inventory.Inventory cannot be null.");}
        this.inventory = inventory;
        this.orders = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    //getters
    public InventoryManager getInventory() {return inventory;}
    public ArrayList<Order> getOrders() {return orders;}
    public List<Prescription> getPrescriptions() {return prescriptions;}

    // methods for orders
    public void addOrder(Order order) {
        if (order == null){System.out.println("Order cannot be null.");
            return;}
        if (orders.contains(order)){System.out.println("Order " + order.getOrderID()+ " already in the system.");
            return; }
        orders.add(order);
        System.out.println("Order " + order.getOrderID() + " is added to the system.");}

    public void viewOrders(){
        if (orders.isEmpty()){System.out.println("There are no orders in the system.");
            return;}
        for (Order order: orders){System.out.println(order);}}

    public void cancelOrder(Order order){
        if (order == null){System.out.println("Order cannot be null.");
            return;}
        if (!orders.contains(order)){System.out.println("Order " + order.getOrderID() + " is not registered.");
            return;}
        orders.remove(order);
        System.out.println("Order " + order.getOrderID() + " is removed from the system.");}

    // methods for prescriptions
    public void addPrescription(Prescription prescription) {
        if (prescription == null){System.out.println("Prescription cannot be null.");
            return;}
        if (prescriptions.contains(prescription)){System.out.println("Prescription " + prescription.getId() + " is already in the system.");
            return; }
        prescriptions.add(prescription);
        System.out.println("Prescription " + prescription.getId() + " is added to the system.");}

    public void viewPrescriptions() {
        if (prescriptions.isEmpty()){System.out.println("There are no prescriptions in the system.");
            return;}
        for (Prescription prescription: prescriptions){System.out.println(prescription);}}

    public void cancelPrescription(Prescription prescription){
        if (prescription == null){System.out.println("Prescription cannot be null.");
            return;}
        if (!prescriptions.contains(prescription)){System.out.println("Prescription " + prescription.getId()+ " is not registered.");
            return;}
        prescriptions.remove(prescription);
        System.out.println("Prescription " + prescription.getId() + " is removed from the system.");}

    // stock observer
    @Override
    public void onStockLevelChanged(Medicine medicine) {
        System.out.println("Stock level changed for " + medicine.getName() +
                ". New stock state: "+ medicine.getStockStateName());}

    // prescription observer
    public void onPrescriptionUpdated(Prescription prescription){
        System.out.println("Prescription " + prescription.getId() + " was updated.");}

    // order observer
    public void onOrderStatusUpdated(Order order){
        System.out.println("Order " + order.getOrderID() + " status was changed to " + order.getOrderStatus());}
}