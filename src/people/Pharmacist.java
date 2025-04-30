package people;

import inventory.InventoryManager;
import model.Medicine;
import manager.PharmacistManagesCustomer;
import manager.PharmacistManagesInventory;

import java.util.List;

public abstract class Pharmacist extends User implements PharmacistManagesInventory, PharmacistManagesCustomer {

    // attributes
    protected List<Customer> customers;
    protected InventoryManager inventory;

    // constructor
    public Pharmacist(String name, String id, List<Customer> customers, InventoryManager inventory){
        super(name, id);
        if (customers == null || inventory == null) {
            throw new IllegalArgumentException("Customers and inventory cannot be null.");}
        this.inventory = inventory;
        this.customers = customers;
    }

    // customer management
    public void addCustomer(Customer customer) {
        if (customer == null) {throw new IllegalArgumentException("Customer cannot be null.");}
        if (customers.contains(customer)) {System.out.println("Customer " + customer.getName() + " already exists.");}
        else {
            customers.add(customer);
            System.out.println("Customer " + customer.getName() + " added.");}}

    public void removeCustomer(Customer customer){
        if (customer == null){throw new IllegalArgumentException("Customer cannot be null.");}
        if (customers.contains(customer)){
                customers.remove(customer);
                System.out.println("Customer " + customer.getName()+ " removed.");}
        else {
        System.out.println("Customer " + customer.getName()+ " does not exist.");}}

    public void viewCustomers(){
        if (customers.isEmpty()){
            System.out.println("No customers in the system.");
            return;}
        for (Customer customer:customers){System.out.println(customer);}}

    // inventory management
    public void addToInventory(Medicine medicine){
        if (medicine== null){throw new IllegalArgumentException("Medicine cannot be null.");}
        inventory.addMedicine(medicine);
        System.out.println("Medicine " + medicine.getName()+ " added to inventory.");}

    public void removeFromInventory(Medicine medicine){
        if (medicine== null){throw new IllegalArgumentException("Medicine cannot be null.");}
        inventory.removeMedicine(medicine);
        System.out.println("Medicine " + medicine.getName() + " removed from inventory.");}

    public void viewInventory() {
        if (inventory.getMedicines().isEmpty()){
            System.out.println("No medicines in the system.");
            return;}
        for (Medicine medicine : inventory.getMedicines()) {System.out.println(medicine);}}

    @Override
    public String toString() {return "Pharmacist name: " + getName()+ "\nID: " + getId();}
}
