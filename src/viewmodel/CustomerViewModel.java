package viewmodel;

import goodclient.RemoteCustomerClient;
import goodclient.RemoteDoctorClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prescription;

import java.util.List;

public class CustomerViewModel
{
  private final RemoteCustomerClient remoteCustomerClient;
  private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
  private final BooleanProperty loading = new SimpleBooleanProperty(false);

  public CustomerViewModel()
  {
    this.remoteCustomerClient = new RemoteCustomerClient();
  }

  public ObservableList<Prescription> getPrescriptions()
  {
    return prescriptions;
  }

  public ReadOnlyBooleanProperty loadingProperty()
  {
    return loading;
  }

  public void loadPrescriptions()
  {
    loading.set(true);
    new Thread(() -> {
      List<Prescription> data = remoteCustomerClient.getAllPrescriptions();
      javafx.application.Platform.runLater(() -> {
        prescriptions.setAll(data);
        loading.set(false);
      });
    }).start();
  }
}
