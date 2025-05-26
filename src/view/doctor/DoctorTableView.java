package view.doctor;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Prescription;
import viewmodel.DoctorViewModel;

import java.io.IOException;

public class DoctorTableView
{
  @FXML
  private TableView<Prescription> adminPrescriptionsTable;

  @FXML
  private TableColumn<Prescription, String> idColumn;

  @FXML
  private TableColumn<Prescription, String> customerColumn;

  @FXML
  private TableColumn<Prescription, String> medicineColumn;

  @FXML
  private Button doctorAddPrescriptionButton;

  private DoctorViewModel viewModel;

  public void initialize()
  {
    viewModel = new DoctorViewModel();

    adminPrescriptionsTable.setItems(viewModel.getPrescriptions());

    idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
    customerColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
    medicineColumn.setCellValueFactory(cellData -> cellData.getValue().getMedicineName());

    viewModel.loadPrescriptions();
    loadTestData();
  }

  private void loadTestData() {
    ObservableList<Prescription> testData = FXCollections.observableArrayList(
        new Prescription("1", "Alice Johnson", "Ibuprofen"),
        new Prescription("2", "Bob Smith", "Paracetamol"),
        new Prescription("3", "Charlie Lee", "Amoxicillin")
    );

    adminPrescriptionsTable.setItems(testData);
  }

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


      popupStage.setScene(new Scene(root));
      popupStage.showAndWait();


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
