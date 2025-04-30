package view.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class AddPrescription
{
  @FXML
  private TextField presciptionIdTextField;

  @FXML
  private ListView medicineListView;

  @FXML
  private ComboBox customerComboBox;

  @FXML
  private Button addPrescriptionOKButton;

  @FXML
  private Button addPrescriptionCancelButton;

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
