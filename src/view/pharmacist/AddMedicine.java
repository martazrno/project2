package view.pharmacist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.AddMedicineViewModel;

import java.time.LocalDate;

public class AddMedicine
{
  @FXML
  private TextField enterMedicineName;

  @FXML
  private TextField enterMedicineAmount;

  @FXML
  private TextField medicineIdTextField;

  @FXML
  private CheckBox isPrescriptionCheckBox;

  @FXML
  private Button addMedicineOkButton;

  @FXML
  private Button addMedicineCancelButton;

  private AddMedicineViewModel viewModel;

  public void initialize()
  {
    viewModel = new AddMedicineViewModel();
  }

  @FXML
  public void onOkClicked(ActionEvent event)
  {
    try
    {
     String medicineName = enterMedicineName.getText();
     int medicineQuantity = Integer.parseInt(enterMedicineAmount.getText().trim());
     String medicineId = medicineIdTextField.getText();
     boolean isPrescription = isPrescriptionCheckBox.isSelected();

     viewModel.setMedicineName(medicineName);
     viewModel.setMedicineId(medicineId);
     viewModel.setPrescription(isPrescription);
     viewModel.setMedicineQuantity(medicineQuantity);

     viewModel.sendData();
    }
    catch (Exception e)
    {
    }

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }

  @FXML
  public void onCancelClicked(ActionEvent event)
  {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }


}
