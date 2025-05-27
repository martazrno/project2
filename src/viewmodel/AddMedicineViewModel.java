package viewmodel;

import goodclient.RemotePharmacistClient;

import javafx.beans.property.*;

public class AddMedicineViewModel
{
  private final StringProperty medicineName = new SimpleStringProperty();
  private final StringProperty medicineQuantity = new SimpleStringProperty();
  private final BooleanProperty isPrescription = new SimpleBooleanProperty();

  private final RemotePharmacistClient remoteClient;

  public AddMedicineViewModel() {
    this.remoteClient = new RemotePharmacistClient();
  }

  public StringProperty medicineNameProperty() {
    return medicineName;
  }

  public StringProperty medicineQuantityProperty() {
    return medicineQuantity;
  }

  public BooleanProperty isPrescriptionProperty() {
    return isPrescription;
  }

  public void sendData() {
    String name = medicineName.get();
    boolean prescription = isPrescription.get();
    int parsedQuantity = Integer.parseInt(medicineQuantity.get());

    if (name != null && !name.isBlank() && parsedQuantity >= 0) {
      remoteClient.addMedicine(name, prescription, parsedQuantity);
    }
  }

}
