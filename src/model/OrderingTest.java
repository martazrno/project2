package model;

import interfaces.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderingTest {
    public static void main(String[] args) {

        Reorder reorderObserver = new Reorder();
        List<OrderObserver> observers = new ArrayList<>();
        observers.add(reorderObserver);

        System.out.println("\n--- Running Reorder Check ---");
        Reorder.checkAndReorder(observers);

        System.out.println("\n--- Pending Orders ---");
        reorderObserver.viewPendingOrders();

        System.out.println("\n--- Confirming Order ---");
        if (!reorderObserver.getTrackedOrders().isEmpty()) {
            String orderIdToConfirm = reorderObserver.getTrackedOrders().get(0).getOrderID();
            reorderObserver.confirmOrder(orderIdToConfirm);
        }

        System.out.println("\n--- Cancelling Order ---");
        if (reorderObserver.getTrackedOrders().size() > 1) {
            String orderIdToCancel = reorderObserver.getTrackedOrders().get(1).getOrderID();
            reorderObserver.cancelOrder(orderIdToCancel);
        }

        System.out.println("\n--- Final Pending Orders ---");
        reorderObserver.viewPendingOrders();
    }
}