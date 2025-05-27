package viewmodel;

import goodclient.RemotePharmacistClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Medicine;
import model.Prescription;

import java.util.List;

public class PharmacistViewModel
{
  private final RemotePharmacistClient remotePharmacistClient;
  private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
  private final ObservableList<Medicine> medicine = FXCollections.observableArrayList();

  private final BooleanProperty loadingPrescriptions = new SimpleBooleanProperty(false);
  private final BooleanProperty loadingMedicine = new SimpleBooleanProperty(false);

  public PharmacistViewModel()
  {
    this.remotePharmacistClient = new RemotePharmacistClient();
  }

  public ObservableList<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  public ObservableList<Medicine> getMedicine()
  {
    return medicine;
  }

  public ReadOnlyBooleanProperty loadingPrescriptionsProperty()
  {
    return loadingPrescriptions;
  }

  public ReadOnlyBooleanProperty loadingMedicineProperty()
  {
    return loadingMedicine;
  }

  public void loadPrescriptions()
  {
    loadingPrescriptions.set(true);
    new Thread(() -> {
      List<Prescription> data = remotePharmacistClient.getAllPrescriptions();
      javafx.application.Platform.runLater(() -> {
        prescriptions.setAll(data);
        loadingPrescriptions.set(false);
      });
    }).start();
  }

  public void loadMedicine()
  {
    loadingMedicine.set(true);
    new Thread(() -> {
      List<Medicine> data = remotePharmacistClient.getAllMedicine();
      javafx.application.Platform.runLater(() -> {
        medicine.setAll(data);
        loadingMedicine.set(false);
      });
    }).start();
  }
}
