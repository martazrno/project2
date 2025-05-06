package view.pharmacist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
  private VBox pharmacistCustomersTable;

  @FXML
  private VBox pharmacistInventoryTable;

  @FXML
  public void initialize() {
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
