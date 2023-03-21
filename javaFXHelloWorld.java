import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class javaFXHelloWorld extends Application{
  public static void main(String[] args){
    Application.launch(JavaFXHelloWorld.class, args);
  }

  @Override
  public void start(Stage stage) {
    HBox hbox = new HBox();
    Scene scene = new Scene(hbox);
    hbox.getChildren().add(new Label("Hello, World"));

    stage.setScene(scene);
    stage.setTitle("Hello");
    stage.show();
  }
}
