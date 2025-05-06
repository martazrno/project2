package model;
import medicine_state.MedicineType;

public class MedicineFactory {

    private MedicineFactory(){}
    public static Medicine createMedicine (MedicineType type, String name, String id,int quantity){

        boolean isPrescription = (type == MedicineType.PRESCRIPTION);
        return new Medicine(name,  id, isPrescription, quantity);
    }
}
