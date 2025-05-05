package viewmodel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prescription;

import java.util.List;

public class DoctorViewModel
{
  private final ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();

  public ObservableList<Prescription> getPrescriptionList() {
    return prescriptionList;
  }

  public void sendPrescription(Prescription prescription) {
    new Thread(() -> {
      List<Prescription> updated = PrescriptionService.sendToServer(prescription); //Need to figure out how the socket will look like
      Platform.runLater(() -> {
        prescriptionList.setAll(updated); // update observable list
      });
    }).start();
  }
}
