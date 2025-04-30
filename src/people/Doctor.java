package people;

import model.Prescription;

public class Doctor extends User {

    public Doctor(String name, String id){super(name, id);}

    public Prescription createPrescription (String prescriptionID, Customer customer){
        if (prescriptionID == null || prescriptionID.isBlank()){
            throw new IllegalArgumentException("Prescription cannot be null or blank.");}
        if (customer == null){throw new IllegalArgumentException("Customer cannot be null.");}
        Prescription prescription= new Prescription(prescriptionID, customer);
        customer.getPrescriptions().add(prescription);
        System.out.println("Prescription " + prescriptionID+ " added to customer " + customer.getName()+ "." );
        return prescription;
    }

    public void viewPrescriptions(Customer customer) {
        if (customer == null) {throw new IllegalArgumentException("Customer cannot be null.");}
        customer.viewMyPrescriptions();}

    @Override
    public String toString() {return super.toString();}
}
