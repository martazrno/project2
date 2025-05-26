package view.doctor;

import goodclient.RemoteDoctorClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Medicine;
import people.Customer;
import viewmodel.AddPrescriptionViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddPrescription
{
  @FXML
  private ComboBox<String> medicineComboBox;

  @FXML
  private ComboBox<String> customerComboBox;

  @FXML
  private Button addPrescriptionOKButton;

  @FXML
  private Button addPrescriptionCancelButton;

  private AddPrescriptionViewModel viewModel;

  public void initialize()
  {
    viewModel = new AddPrescriptionViewModel();

    medicineComboBox.setItems(viewModel.getMedicineList());
    customerComboBox.setItems(viewModel.getCustomerList());

    viewModel.medicineNameProperty().bind(medicineComboBox.valueProperty());
    viewModel.customerNameProperty().bind(customerComboBox.valueProperty());
  }

  @FXML
  private void onOkClicked(ActionEvent event)
  {
    if (viewModel.medicineNameProperty().get() == null || viewModel.customerNameProperty().get() == null)
      return;

    viewModel.sendData();
    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
  }

  @FXML
  private void onCancelClicked(ActionEvent event)
  {
    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
  }
}
