package view;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartGUI extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Danish Medicine Cabinet");

    ViewFactory viewFactory = new ViewFactory(primaryStage);
    viewFactory.showView(ViewFactory.ViewType.LOGIN);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
