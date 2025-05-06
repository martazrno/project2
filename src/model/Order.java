package model;
import observer.OrderObserver;
import java.util.ArrayList;
import java.util.List;

public class Order {

    // attributes
    private final String orderID;
    private OrderStatus orderStatus;
    private final List<OrderObserver> observers = new ArrayList<>();
    private final String medicineName;
    private final int medicineQuantity;

    // constructor
    public Order(String orderID, String medicineName, int medicineQuantity) {
        this.medicineName = medicineName;
        this.medicineQuantity = medicineQuantity;
        if (orderID == null || orderID.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be null or blank.");}
        this.orderID = orderID;
        this.orderStatus = OrderStatus.PENDING;}

    // getters and setters
    public String getOrderID() {return orderID;}
    public OrderStatus getOrderStatus() {return orderStatus;}
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        notifyObservers();}
    public int getMedicineQuantity() {return medicineQuantity;}
    public String getMedicineName() {return medicineName;}

    // methods for observers
    public void addObserver(OrderObserver observer) {observers.add(observer);}
    public void removeObserver(OrderObserver observer) {observers.remove(observer);}
    public void notifyObservers() {
        for (OrderObserver observer : observers) {observer.onOrderStatusUpdated(this);}}

}
