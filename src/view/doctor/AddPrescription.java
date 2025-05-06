package view.doctor;

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

  private Stage dialogStage;

  public void initialize()
  {
    viewModel = new AddPrescriptionViewModel();
  }

  @FXML
  private void onOkClicked(ActionEvent event)
  {
    try
    {
      String selectedMed = medicineComboBox.getValue();
      String selectedCustomer = customerComboBox.getValue();

      if (selectedMed == null || selectedCustomer == null)
      {
        return;
      }

      viewModel.setMedicineName(selectedMed);
      viewModel.setCustomerName(selectedCustomer);
      viewModel.sendData();


      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.close();
    }
    catch (NumberFormatException e)
    {
    }
  }

  @FXML
  private void onCancelClicked(ActionEvent event)
  {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }
}
