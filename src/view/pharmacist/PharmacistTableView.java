package view.pharmacist;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PharmacistTableView
{
  @FXML
  private TextField pharmacistSearchBar;

  @FXML
  private Button pharmacistCustomersTabButton;

  @FXML
  private Button pharmacistPrescriptionsTabButton;

  @FXML
  private Button pharmacistInventoryTabButton;

  @FXML
  private TableView pharmacistPrescriptionsTable;

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
}
