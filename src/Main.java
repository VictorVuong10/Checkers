import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class Main extends Application {

    private Group game;
    @Override
    public void start(Stage primaryStage) throws Exception {
        final int appWidth = 800;
        final int appHeight = 800;
        Text welcome = new Text(400, 150, "WELCOME\n\n Pick a game");
        welcome.setFill(Color.WHITE);
        Button button = new Button("Checkers");
        game = new Group();
        button.relocate(100,200);
        game.getChildren().add(welcome);
        game.getChildren().add(button);
        button.setOnAction(this::checkers);
        Scene scene = new Scene(game, appWidth, appHeight, Color.BLACK);

        primaryStage.setTitle("Board Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkers(ActionEvent event) {
        game.getChildren().clear();
        game.getChildren().add(new Check());
    }

    public static void main(String[] args) {
        launch(args);

    }

}