package model;


import observer.OrderObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    // attributes
    private final String orderID;
    private final ArrayList<OrderItem> orderItems;
    private final LocalDate orderDate;
    private OrderStatus orderStatus;
    private final List<OrderObserver> observers = new ArrayList<>();

    // constructor
    public Order (String orderID){
        if (orderID == null || orderID.isBlank()){throw new IllegalArgumentException("Order ID cannot be null or blank.");}
        this.orderID = orderID;
        this.orderItems = new ArrayList<>();
        this.orderDate =  LocalDate.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    // getters and setters
    public String getOrderID() {return orderID;}
    public ArrayList<OrderItem> getOrderItems() {return orderItems;}
    public LocalDate getOrderDate() {return orderDate;}
    public OrderStatus getOrderStatus() {return orderStatus;}
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        notifyObservers();}

    //methods for observers
    public void addObserver(OrderObserver observer){observers.add(observer);}
    public void removeObserver(OrderObserver observer){observers.remove(observer);}
    public void notifyObservers(){
        for (OrderObserver observer: observers){
            observer.onOrderStatusUpdated(this);}}

    // methods
    public void addOrderItem(Medicine medicine, int quantity){
        if (medicine != null && quantity >0){
            orderItems.add(new OrderItem(medicine,quantity));
        } else {System.out.println("Medicine cannot be null.");
        }}

    @Override
    public String toString() {return "Order ID: " + orderID + '\'' + "\nOrder items: " + orderItems +
            "\nOrder date: " + orderDate + "\nOrder status: " + orderStatus;}

}
