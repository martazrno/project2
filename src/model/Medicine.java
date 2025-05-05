package model;
import inventory.Inventory;
import medicine_state.*;
import java.time.LocalDate;

public class Medicine {

    // attributes
    private final String name;
    private final LocalDate expirationDate;
    private final int id;
    private final boolean isPrescription;
    private int quantity;
    private MedicineStockState stockState;

    // constructor
    public Medicine(String name, LocalDate expirationDate, int id, boolean isPrescription,  int quantity){
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date cannot be in the past.");}
        if (name == null || name.isBlank()){throw new IllegalArgumentException("Medicine name cannot be null.");}
        //if (id == null || id.isBlank()){throw new IllegalArgumentException("Medicine id cannot be null.");}
        if (quantity < 0){throw new IllegalArgumentException("Medicine quantity cannot be negative.");}
        this.expirationDate=expirationDate;
        this.name=name;
        this.id = id;
        this.isPrescription= isPrescription;
        this.quantity = quantity;
        updateState();
    }

    // booleans
    public boolean isExpired() {return expirationDate.isBefore(LocalDate.now());}
    public boolean isPrescription() {return isPrescription;}

    // getters and setters
    public String getName() {return name;}
    public LocalDate getExpirationDate() {return expirationDate;}
    public int getId() {return id;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantity = quantity;
        updateState();}
    private void updateState() {
        if (quantity < 20) stockState = new LowStock();
        else stockState = new FullStock();}
    public String getStockStateName() {return stockState.getStateName();}

    //methods
    public void checkAndReorder(String name, Inventory inventory) {
        stockState.checkAndReorder(name, inventory);}

    @Override
    public String toString() {return "Medicine name: " + name + "\nID: "+ id +
            "\nExpiration date: " + expirationDate + "\nExpired: " +
            isExpired() + "\nPrescription: " + isPrescription  + "\nQuantity: " + quantity  ;}
}
