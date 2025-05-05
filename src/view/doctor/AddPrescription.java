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
import model.Prescription;
import people.Customer;

import java.util.ArrayList;
import java.util.List;

public class AddPrescription
{
  @FXML
  private TextField presciptionIdTextField;

  @FXML
  private ListView<Medicine> medicineListView;

  @FXML
  private ComboBox<Customer> customerComboBox;

  @FXML
  private Button addPrescriptionOKButton;

  @FXML
  private Button addPrescriptionCancelButton;

  private Stage dialogStage;
  private Prescription result = null;

  public void initialize()
  {
    medicineListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }

  public void setMedicineOptions(List<Medicine> meds)
  {
    medicineListView.setItems(FXCollections.observableArrayList(meds));
  }

  public void setCustomerOptions(List<Customer> customers)
  {
    customerComboBox.setItems(FXCollections.observableArrayList(customers));
  }

  public Prescription getResult()
  {
    return result;
  }

  @FXML
  private void onOkClicked(ActionEvent event)
  {
    try
    {
      int id = Integer.parseInt(presciptionIdTextField.getText().trim());
      List<Medicine> selectedMeds = new ArrayList<>(
          medicineListView.getSelectionModel().getSelectedItems());
      Customer selectedCustomer = customerComboBox.getValue();

      if (selectedMeds.isEmpty() || selectedCustomer == null)
      {
        return;
      }

      result = new Prescription(id, selectedMeds, selectedCustomer);
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
