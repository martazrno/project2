package model;
import interfaces.MedicineStockState;
import interfaces.OrderObserver;
import people.Pharmacist;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Medicine {

    // attributes
    private final String name;
    private final boolean isPrescription;
    private int quantity;
    private final String id;
    private MedicineStockState stockState;

    // constructors
    public Medicine(String name, boolean isPrescription,  int quantity){
        if (name == null || name.isBlank()){throw new IllegalArgumentException("Medicine name cannot be null.");}
        if (quantity < 0){throw new IllegalArgumentException("Medicine quantity cannot be negative.");}
        this.name=name;
        this.isPrescription= isPrescription;
        this.quantity = quantity;
        updateState();
        this.id = generateId();}

    public Medicine(String id, String name, boolean isPrescription, int quantity) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Medicine name cannot be null.");
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Medicine ID cannot be null.");
        if (quantity < 0) throw new IllegalArgumentException("Medicine quantity cannot be negative.");
        this.id = id;
        this.name = name;
        this.isPrescription = isPrescription;
        this.quantity = quantity;
        updateState();}

    // booleans
    public boolean isPrescription() {return isPrescription;}

    // getters and setters
    public String getName() {return name;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantity = quantity;
        updateState();}
    public String getId() {return id;}

    //methods
    private String generateId() {return "M" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();}

    private void updateState() {
        List<OrderObserver> observers = new ArrayList<>();
        observers.add(new Pharmacist("Test Pharmacist", new Inventory()));
        if (quantity < 20) stockState = new LowStock(observers);
        else stockState = new FullStock();}
    public String getStockStateName() {return stockState.getStateName();}

}
