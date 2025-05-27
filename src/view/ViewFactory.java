package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

  private Stage stage;

  public enum ViewType {
    LOGIN,
    DOCTOR,
    PHARMACIST,
    PATIENT
  }

  public ViewFactory(Stage stage) {
    this.stage = stage;
  }

  public void showView(ViewType viewType)
  {
    String fxmlPath = switch (viewType) {
      case LOGIN -> "MainView.fxml";
      case DOCTOR -> "doctor/DoctorTableView.fxml";
      case PHARMACIST -> "pharmacist/PharmacistTableView.fxml";
      case PATIENT -> "customer/CustomerTableView.fxml";
    };

    try {
      FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
      Parent root = loader.load();

      Object controller = loader.getController();
      if (controller instanceof MainView mainView) {
        mainView.init(this, stage);
      }

      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}