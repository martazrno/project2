package model;

public class OrderItem {

    private final Medicine medicine;
    private final int quantity;

    public OrderItem(Medicine medicine, int quantity){
        if (medicine == null) {throw new IllegalArgumentException("Medicine cannot be null.");}
        if (quantity <= 0){throw new IllegalArgumentException("Quantity must be more than 0.");}
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public Medicine getMedicine() {return medicine;}
    public int getQuantity() {return quantity;}

    @Override
    public String toString() {
        return "Order medicine: " + medicine + "\nquantity: " + quantity;
    }
}
