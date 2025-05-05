package view.pharmacist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.AddMedicineViewModel;

public class AddMedicine
{
  @FXML
  private TextField enterMedicineName;

  @FXML
  private TextField enterMedicineAmount;

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
