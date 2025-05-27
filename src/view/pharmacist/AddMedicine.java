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

    viewModel.medicineNameProperty().bind(enterMedicineName.textProperty());
    viewModel.medicineQuantityProperty().bindBidirectional(enterMedicineAmount.textProperty());
    viewModel.isPrescriptionProperty().bind(isPrescriptionCheckBox.selectedProperty());
  }

  @FXML
  public void onOkClicked(ActionEvent event)
  {
    try
    {
      viewModel.sendData();
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.close();
    } catch (NumberFormatException e)
    {
      e.printStackTrace();
    }
  }

  @FXML
  public void onCancelClicked(ActionEvent event)
  {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }
}
