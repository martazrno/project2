package manager;
import model.Order;

public interface OrderManager {
    void addOrder(Order order);
    void cancelOrder(Order order);
    void viewOrders();

}
