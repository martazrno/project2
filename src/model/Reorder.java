package model;
import database.DBconnect;
import observer.OrderObserver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reorder implements OrderObserver{

    //attributes
    private static final int THRESHOLD = 20;
    private static final int DESIRED_STOCK = 100;
    private final List<Order> trackedOrders;

    //constructor
    public Reorder(){this.trackedOrders= new ArrayList<>();}

    //getters
    public List<Order> getTrackedOrders() {return trackedOrders;}

    //methods
    public void trackOrder(Order order) {trackedOrders.add(order);}

    public static void checkAndReorder(List<OrderObserver> observers) {
        String selectSql = "SELECT name, quantity FROM medicines";
        try (Connection connection = DBconnect.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                if (quantity < THRESHOLD) {
                    int reorderAmount = DESIRED_STOCK;
                    String orderId = "ORDER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                    Order order = new Order(orderId, name, reorderAmount);
                    for (OrderObserver observer : observers) {
                        order.addObserver(observer);
                        if (observer instanceof Reorder reorderObserver) {
                            reorderObserver.trackOrder(order);}}

                    order.setOrderStatus(OrderStatus.PENDING);
                    System.out.println("New order created: " + name + " "+(reorderAmount-quantity)+ "x");}}}

        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}

    //observer methods for orders
    @Override
    public void onOrderStatusUpdated(Order order) {
        if (!trackedOrders.contains(order)) {
            trackedOrders.add(order);}
        System.out.println("🔔 Pharmacist notified: Order " + order.getOrderID() + " is now " +
                order.getOrderStatus());}

    public void viewPendingOrders() {
        System.out.println("Pending Orders:");
        for (Order order : trackedOrders) {
            if (order.getOrderStatus() == OrderStatus.PENDING) {
                System.out.println("- " + order.getOrderID() + ": " + order.getMedicineName() +
                        " x" + order.getMedicineQuantity());}}}

    public void confirmOrder(String orderId) {
        for (Order order : trackedOrders) {
            if (order.getOrderID().equals(orderId) && order.getOrderStatus() == OrderStatus.PENDING) {
                order.setOrderStatus(OrderStatus.CONFIRMED);
                order.setOrderStatus(OrderStatus.COMPLETED);
                setStockTo(order.getMedicineName(), 100);
                return;}}
        System.out.println("Order not found.");}

    public void cancelOrder(String orderId) {
        for (Order order : trackedOrders) {
            if (order.getOrderID().equals(orderId) && order.getOrderStatus() == OrderStatus.PENDING) {
                order.setOrderStatus(OrderStatus.CANCELLED);
                return;}}
        System.out.println("Order not found.");}

    private void setStockTo(String name, int newQuantity) {
        String sql = "UPDATE medicines SET quantity = ? WHERE name = ?";
        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setString(2, name);
            stmt.executeUpdate();}
        catch (SQLException e) {System.out.println("Error: " + e.getMessage());}}
}
