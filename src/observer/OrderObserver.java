package observer;
import model.Order;

public interface OrderObserver { void onOrderStatusUpdated(Order order);}
