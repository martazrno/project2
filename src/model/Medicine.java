package model;
import interfaces.MedicineStockState;
import interfaces.OrderObserver;
import people.Pharmacist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Medicine implements Serializable {
    // Serializable fields
    private final String name;
    private final boolean isPrescription;
    private int quantity;
    private final String id;

    // Transient fields (not serializable or reconstructed on deserialization)
    private transient MedicineStockState stockState;
    private transient List<OrderObserver> observers;  // For state updates

    // Constructors
    public Medicine(String name, boolean isPrescription, int quantity) {
        validateInputs(name, quantity);
        this.name = name;
        this.isPrescription = isPrescription;
        this.quantity = quantity;
        this.id = generateId();
        initializeState();
    }

    public Medicine(String id, String name, boolean isPrescription, int quantity) {
        validateInputs(name, quantity);
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Medicine ID cannot be null.");
        this.id = id;
        this.name = name;
        this.isPrescription = isPrescription;
        this.quantity = quantity;
        initializeState();
    }

    // Input validation helper
    private void validateInputs(String name, int quantity) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Medicine name cannot be null.");
        if (quantity < 0) throw new IllegalArgumentException("Medicine quantity cannot be negative.");
    }

    // State management
    private void initializeState() {
        this.observers = new ArrayList<>();
        this.observers.add(new Pharmacist("System Pharmacist", new Inventory()));  // Default observer
        updateState();
    }

    private void updateState() {
        this.stockState = (quantity < 20) ? new LowStock(observers) : new FullStock();
    }

    // Serialization support
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();  // Serialize non-transient fields
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();    // Deserialize non-transient fields
        initializeState();         // Rebuild transient fields
    }

    // Getters and setters
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public String getId() { return id; }
    public boolean isPrescription() { return isPrescription; }
    public String getStockStateName() { return stockState.getStateName(); }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        this.quantity = quantity;
        updateState();  // Update state on quantity change
    }

    // Observer management
    public void addObserver(OrderObserver observer) {
        if (observers == null) observers = new ArrayList<>();
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        if (observers != null) observers.remove(observer);
    }

    // ID generation
    private String generateId() {
        return "M" + UUID.randomUUID().toString().substring(0, 3).toUpperCase();
    }
}