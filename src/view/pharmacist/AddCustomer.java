package view.pharmacist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import people.Customer;
import viewmodel.AddCustomerViewModel;
import viewmodel.AddPrescriptionViewModel;
import viewmodel.PharmacistViewModel;

public class AddCustomer
{
  @FXML
  private TextField enterCustomerID;

  @FXML
  private TextField enterCustomerName;

  @FXML
  private Button addCustomerOkButton;

  @FXML
  private Button addCustomerCancelButton;

  private AddCustomerViewModel viewModel;

  public void initialize()
  {
    viewModel = new AddCustomerViewModel();

    enterCustomerName.textProperty().bindBidirectional(
        viewModel.customerNameProperty());
  }

  @FXML
  public void onOkClicked(ActionEvent event)
  {
    viewModel.sendData();

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
