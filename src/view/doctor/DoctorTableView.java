/*package view.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Prescription;

import java.io.IOException;

public class DoctorTableView
{
  @FXML
  private TextField adminSearchBar;

  @FXML
  private Button adminCustomersTabButton;

  @FXML
  private Button adminPrescriptionsTabButton;

  @FXML
  private TableView adminCustomersTable;

  @FXML
  private TableView adminPrescriptionsTable;

  @FXML
  private Button doctorAddPrescriptionButton;

  @FXML
  private void handleOpenForm(ActionEvent event)
  {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPrescription.fxml"));
      Parent root = loader.load();

      AddPrescription controller = loader.getController();
      Stage popupStage = new Stage();
      popupStage.setTitle("Create Prescription");
      popupStage.initModality(Modality.APPLICATION_MODAL);

      controller.setCustomerOptions(customerList); // refer to viewmodel to query data
      controller.setMedicineOptions(medicineList);

      popupStage.setScene(new Scene(root));
      popupStage.showAndWait(); // Wait until the popup is closed

      Prescription newPrescription = controller.getResult();
      if (newPrescription != null) {
        viewModel.sendPrescription(newPrescription);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}*/
