package observer;
import model.Medicine;

public interface MedicineStockObserver { void onStockLevelChanged(Medicine medicine);}
