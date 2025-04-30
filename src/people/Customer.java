package people;
import model.Prescription;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    //attributes
    private final List<Prescription> prescriptions;

    // constructor
    public Customer (String name,  String id){
        super(name, id);
        this.prescriptions = new ArrayList<>();
    }

    // getters
    public List<Prescription> getPrescriptions() {return prescriptions;}

    // methods
    public void viewMyPrescriptions(){
        if (prescriptions.isEmpty()){
            System.out.println("No prescriptions found.");
            return;}
        System.out.println("Your prescriptions: ");
        for (Prescription prescription: prescriptions){System.out.println(prescription);}}

    @Override
    public String toString() {return "Customer name: " + getName() + "\nID: " + getId();}

}