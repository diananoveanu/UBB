import controllers.ToyLanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Interpreter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Toy Language Interpreter");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./view/ToyLanguageInterpreter.fxml"));
        Pane pane = (Pane) fxmlLoader.load();
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        //        ToyLanguageController controller = fxmlLoader.getController();
        //        controller.setCtr(getController());
        //        primaryStage.setScene(new Scene(pane));
        //        primaryStage.show();
    }

    //TODO: getController
}
