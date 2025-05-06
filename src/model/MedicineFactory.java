package model;

public class MedicineFactory {

    private MedicineFactory(){}

    public static Medicine createMedicine (MedicineType type, String name, int quantity){

        boolean isPrescription = (type == MedicineType.PRESCRIPTION);
        return new Medicine(name, isPrescription, quantity);
    }

}
