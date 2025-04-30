package view;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainView
{
  @FXML
  private TextField usernameTextField;

  @FXML
  private TextField passwordTextField;

  @FXML
  private Button logInButton;

  private ViewFactory viewFactory;
  private Stage stage;

  public void init(ViewFactory viewFactory, Stage stage)
  {
    this.viewFactory = viewFactory;
    this.stage = stage;
  }

    @FXML
    private void handleLogin() {
      String role = usernameTextField.getText().trim().toLowerCase();

      switch (role) {
        case "doctor" -> viewFactory.showView(ViewFactory.ViewType.DOCTOR); // or DOCTOR_DASHBOARD
        case "pharmacist" -> viewFactory.showView(ViewFactory.ViewType.PHARMACIST); // or PHARMACIST_DASHBOARD
        case "customer" -> viewFactory.showView(ViewFactory.ViewType.PATIENT); // or CUSTOMER_VIEW
        default -> System.out.println("Invalid role entered");
      }
    }
}
