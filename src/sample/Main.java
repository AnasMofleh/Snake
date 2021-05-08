package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Snake game");
        Model m = new Model();
        View v = new View();
        new Controller(m,v);
        primaryStage.setScene(m.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
