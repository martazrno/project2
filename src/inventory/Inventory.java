package inventory;
import model.Medicine;
import java.util.ArrayList;

public class Inventory implements InventoryManager {

    // attributes
    private final ArrayList<Medicine> medicines;
    private final int THRESHOLD = 20;
    private final int DESIRED_STOCK = 100;

    // constructor
    public Inventory(){this.medicines= new ArrayList<>();}

    //getters
    public ArrayList<Medicine> getMedicines() {return medicines;}
    public int getDesiredStock() {return DESIRED_STOCK;}

    // methods
    public void addMedicine(Medicine medicine){
        if (medicine == null) throw new IllegalArgumentException("Medicine cannot be null.");
        if (medicines.contains(medicine)) {
            System.out.println("Medicine " + medicine.getId()+ " already exists.");
            return;}
        if (medicine.getQuantity()<=0)throw new IllegalArgumentException("Medicine quantity cannot be negative.");
        else {
            medicines.add(medicine);
            System.out.println("Medicine " + medicine.getName()+ " added to inventory.");
        }}

    public void removeMedicine(Medicine medicine){
        if (medicine == null){throw new IllegalArgumentException("Medicine cannot be null.");}
        if (medicines.contains(medicine)){
            medicines.remove(medicine);
            System.out.println("Medicine " + medicine.getName()+ " removed from inventory.");
        } else {System.out.println("Medicine " + medicine.getName()+ " not found.");}}

    public void viewInventory() {
        if (medicines.isEmpty()) {System.out.println("Inventory is empty.");}
        else {
            System.out.println("Inventory: ");
            for (Medicine medicine : medicines) {System.out.println(medicine);}}}

    public void reorder(Medicine medicine, int quantity) {
        for (Medicine existing : medicines) {
            if (existing.getQuantity() < THRESHOLD) {
                int needed = DESIRED_STOCK - existing.getQuantity();
                existing.setQuantity(DESIRED_STOCK);
                System.out.println("Reordered " + needed + " units of " + medicine.getName() + ".");}}}

    @Override
    public String toString() {return "Inventory: " + medicines;}

}