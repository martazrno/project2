package inventory;
import manager.InventoryManager;
import model.Medicine;
import java.time.LocalDate;

public class InventoryTest {

    public static void main(String[] args) {
        InventoryManager inventory = new Inventory();

        Medicine ibuprofen = new Medicine("Ibuprofen", "75", false, 10);

        //inventory.removeMedicine("Ibuprofen");
        inventory.addMedicine(ibuprofen);

        System.out.println("\n--- Inventory View ---");
        inventory.viewInventory();
        System.out.println("\n--- Reorder Test ---");

        System.out.println("\n--- Inventory After Reorder ---");
        inventory.viewInventory();

    }}
