package viewmodel;

import goodclient.RemotePharmacistClient;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import goodclient.RemoteDoctorClient;
import model.Prescription;
import java.util.List;

public class DoctorViewModel
{
  private final RemoteDoctorClient remoteDoctorClient;
  private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
  private final BooleanProperty loading = new SimpleBooleanProperty(false);

  public DoctorViewModel()
  {
    this.remoteDoctorClient = new RemoteDoctorClient();
  }

  public ObservableList<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  public RemoteDoctorClient getClient()
  {
    return remoteDoctorClient;
  }

  public ReadOnlyBooleanProperty loadingProperty() {
    return loading;
  }

  public void loadPrescriptions()
  {
    loading.set(true);
    new Thread(() -> {
      List<Prescription> data = remoteDoctorClient.getAllPrescriptions();
      javafx.application.Platform.runLater(() -> {
        prescriptions.setAll(data);
        loading.set(false);
      });
    }).start();
  }
}
