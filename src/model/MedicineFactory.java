package model;
import medicine_state.MedicineType;
import java.time.LocalDate;

public class MedicineFactory {

    private MedicineFactory(){}
    public static Medicine createMedicine (MedicineType type, String name, LocalDate expirationDate, int id,int quantity){
        boolean isPrescription = (type == MedicineType.PRESCRIPTION);
        return new Medicine(name,  expirationDate,  id, isPrescription, quantity);
    }
}
