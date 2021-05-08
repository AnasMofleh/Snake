package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Model {
    private Rectangle head, food;
    private Rectangle[] snake;
    private Scene scene;
    private Pane layout;
    private int height = 600;
    private int width = 800;
    private int dim = 20;
    private int pixel;


    public Model() {
        layout = new BorderPane();
        createSnake();
        createFood();
        scene = new Scene(layout, width, height);
        scene.setFill(Color.DARKSEAGREEN);
    }


    private void createSnake() {
        pixel = (height * width) / dim;
        head = new Rectangle(dim, dim, dim, dim);
        head.setFill(Color.RED);
        layout.getChildren().add(head);
    }

    private void createFood() {
        food = new Rectangle(dim, dim, dim / 1.5, dim / 1.5);
        food.setFill(Color.YELLOW);
        food.setX(new Random().nextInt(height));
        food.setY(new Random().nextInt(height));
        layout.getChildren().add(food);
    }

    public Scene getScene() {
        return scene;
    }

    public Rectangle getHead() {
        return head;
    }


    public int getDim() {
        return dim;
    }

    public double getWidth() {
        return (getScene().getWidth() / width) * dim;
    }

    public double getHeight() {
        return (getScene().getHeight() / height) * dim;
    }
}
