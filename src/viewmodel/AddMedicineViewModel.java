package viewmodel;

import inventory.Inventory;
import inventory.InventoryManager;
import model.Medicine;
import people.Pharmacist;

import java.time.LocalDate;

public class AddMedicineViewModel
{
  private String medicineName;
  private int medicineQuantity;
  private LocalDate expirationDate;
  private String medicineId;
  private boolean isPrescription;

  private Pharmacist pharmacist;

  public void setMedicineName(String medicineName)
  {
    this.medicineName = medicineName;
  }

  public void setExpirationDate(LocalDate expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  public void setMedicineId(String medicineId)
  {
    this.medicineId = medicineId;
  }

  public void setMedicineQuantity(int medicineQuantity)
  {
    this.medicineQuantity = medicineQuantity;
  }

  public void setPrescription(boolean isPrescription)
  {
    this.isPrescription = isPrescription;
  }

  public void sendData()
  {
    InventoryManager inventory = new Inventory();
    pharmacist = new Pharmacist("pharmacist", "2", inventory);

    pharmacist.addToInventory(new Medicine(medicineName, expirationDate, medicineId, isPrescription, medicineQuantity));
  }


}
