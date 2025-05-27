package view.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Prescription;
import viewmodel.CustomerViewModel;
import viewmodel.DoctorViewModel;

public class CustomerTableView
{
  @FXML
  private TableView<Prescription> customerPrescriptionsTable;

  @FXML
  private TableColumn<Prescription, String> idColumn;

  @FXML
  private TableColumn<Prescription, String> customerColumn;

  @FXML
  private TableColumn<Prescription, String> medicineColumn;

  private CustomerViewModel viewModel;

  public void initialize()
  {
    viewModel = new CustomerViewModel();

    customerPrescriptionsTable.setItems(viewModel.getPrescriptions());

    idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
    customerColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
    medicineColumn.setCellValueFactory(cellData -> cellData.getValue().getMedicineName());

    viewModel.loadPrescriptions();
    loadTestData();
  }

  private void loadTestData()
  {
    ObservableList<Prescription> testData = FXCollections.observableArrayList(
        new Prescription("1", "Alice Johnson", "Ibuprofen"),
        new Prescription("2", "Bob Smith", "Paracetamol"),
        new Prescription("3", "Charlie Lee", "Amoxicillin")
    );

    customerPrescriptionsTable.setItems(testData);
  }
}
