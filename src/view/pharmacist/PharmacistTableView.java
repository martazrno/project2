package view.pharmacist;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Medicine;
import model.Prescription;
import viewmodel.PharmacistViewModel;

import java.io.IOException;

public class PharmacistTableView
{

  @FXML
  private Button pharmacistCustomersTabButton;

  @FXML
  private Button pharmacistInventoryTabButton;

  @FXML
  private Button addCustomerButton;

  @FXML
  private Button addMedicineButton;

  @FXML
  private TableView<Prescription> pharmacistCustomersTable;

  @FXML
  private TableColumn<Prescription, String> idColumn;

  @FXML
  private TableColumn<Prescription, String> customerColumn;

  @FXML
  private TableColumn<Prescription, String> medicineColumn;

  @FXML
  private TableView<Medicine> pharmacistInventoryTable;

  @FXML
  private TableColumn<Medicine, String> medicineNameColumn;

  @FXML
  private TableColumn<Medicine, Number> medicineAmountColumn;

  private PharmacistViewModel viewModel;

  @FXML
  public void initialize()
  {
    viewModel = new PharmacistViewModel();

    pharmacistCustomersTable.setItems(viewModel.getPrescriptions());

    idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
    customerColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
    medicineColumn.setCellValueFactory(cellData -> cellData.getValue().getMedicineName());

    viewModel.loadPrescriptions();

    pharmacistInventoryTable.setItems(viewModel.getMedicine());
    medicineNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
    medicineAmountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));

    viewModel.loadMedicine();

    showTable(1);
  }

  @FXML
  private void showCustomers() {
    showTable(1);
  }

  @FXML
  private void showInventory() {
    showTable(2);
  }

  private void showTable(int index) {
    pharmacistCustomersTable.setVisible(index == 1);
    pharmacistInventoryTable.setVisible(index == 2);
  }

  @FXML
  private void handleAddCustomer(ActionEvent event)
  {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
      Parent root = loader.load();

      Stage popupStage = new Stage();
      popupStage.setTitle("Add Customer");
      popupStage.initModality(Modality.APPLICATION_MODAL);
      popupStage.setScene(new Scene(root));
      popupStage.showAndWait();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void handleAddMedicine(ActionEvent event)
  {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMedicine.fxml"));
      Parent root = loader.load();

      Stage popupStage = new Stage();
      popupStage.setTitle("Add Medicine");
      popupStage.initModality(Modality.APPLICATION_MODAL);
      popupStage.setScene(new Scene(root));
      popupStage.showAndWait();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
