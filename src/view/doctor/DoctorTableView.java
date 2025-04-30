package view.doctor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
  public void initialize()
  {
    showTable(1);
  }

  @FXML
  private void showCustomers() {
    showTable(1);
  }

  @FXML
  private void showPrescriptions() {
    showTable(2);
  }

  private void showTable(int index) {
    adminCustomersTable.setVisible(index == 1);
    adminPrescriptionsTable.setVisible(index == 2);
  }
}
